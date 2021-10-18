package My_SQL_Functionality_Queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Find_In_Tables {

	public static Object[] getAllEmployeesNames(Connection con) {
		ResultSet rs;
		try {
			PreparedStatement stm = con.prepareStatement("SELECT `EMPLOYEE_ID`, `FNAME`, `LNAME` FROM `employee` WHERE `ACTIVE` = 1\r\n");
			rs = stm.executeQuery();
			ArrayList<String> names = new ArrayList<String>();
			while(rs.next()) {
				names.add(rs.getString("EMPLOYEE_ID")+". "+rs.getString("FNAME")+" "+rs.getString("LNAME"));
			}
			stm.close();
			return names.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet findInEmployeeTable(Connection con, int employee_id) throws SQLException {
		PreparedStatement stm = con.prepareStatement("SELECT * FROM `employee` WHERE `EMPLOYEE_ID` = ? AND `ACTIVE` = 1");
		stm.setInt(1, employee_id);

		ResultSet rs = stm.executeQuery();		
		rs.next();
		stm.closeOnCompletion();
		return rs;	
	}

	public static ResultSet findInContractsTable(Connection con, int employee_id) throws SQLException {
		PreparedStatement stm = con.prepareStatement("SELECT * FROM `contracts` WHERE `EMPLOYEE_ID` = ?");
		stm.setInt(1, employee_id);

		ResultSet rs = stm.executeQuery();
		rs.next();
		stm.closeOnCompletion();
		return rs;	
	}

	public static ResultSet findInChildrenTable(Connection con, int employee_id) throws SQLException {
		PreparedStatement stm = con.prepareStatement("SELECT * FROM `children` WHERE `EMPLOYEE_ID` = ?");
		stm.setInt(1, employee_id);

		ResultSet rs = stm.executeQuery();
		//rs.next();
		stm.closeOnCompletion();
		return rs;
	}
	
	public static int findPrevBaseSalary(Connection con){
		try {
			PreparedStatement stm = con.prepareStatement(
					"SELECT MAX(`BASE_SALARY`) FROM `employee` WHERE `STAFF_CATEGORY` = 'permanent_teaching' OR `STAFF_CATEGORY` = 'standing_administrative' ");
			ResultSet rs = stm.executeQuery();
			rs.next();
			stm.closeOnCompletion();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int finePrevLibraryBonus(Connection con) {
		try {
			PreparedStatement stm = con.prepareStatement(
					"SELECT MAX(`BONUS`) FROM `payment` WHERE `STAFF_CATEGORY` = 'contract_teaching'");
			ResultSet rs = stm.executeQuery();
			rs.next();
			stm.closeOnCompletion();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int findPrevResearchBonus(Connection con) {
		try {
			PreparedStatement stm = con.prepareStatement(
					"SELECT MAX(`BONUS`) FROM `payment` WHERE `STAFF_CATEGORY` = 'permanent_teaching'");
			ResultSet rs = stm.executeQuery();
			rs.next();
			stm.closeOnCompletion();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ResultSet findInPaymentTable(Statement stm, int employee_id) throws SQLException {
		String findInPaymentTable = "SELECT * FROM `employee` WHERE `payment` = %d";

		ResultSet rs = stm.executeQuery(String.format(findInPaymentTable, employee_id));
		rs.next();
		stm.closeOnCompletion();
		return rs;
	}

	public static ResultSet findInPayrollPaymentTable(Statement stm, int employee_id) throws SQLException {
		String findInPayrollPaymentTable = "SELECT * FROM `payrollpayment` WHERE `EMPLOYEE_ID` = %d";

		ResultSet rs = stm.executeQuery(String.format(findInPayrollPaymentTable, employee_id));
		rs.next();
		stm.closeOnCompletion();
		return rs;
	}
	
	public static ResultSet findMinMaxAvgPaymentPerCategory(Connection con, STAFF_CATEGORY staff_category) throws SQLException {
		PreparedStatement stm = con.prepareStatement("SELECT MIN(`PAYMENT_AMOUNT`),MAX(`PAYMENT_AMOUNT`),AVG(`PAYMENT_AMOUNT`) FROM `payrollpayment` INNER JOIN `employee` ON `employee`.`EMPLOYEE_ID` = `payrollpayment`.`EMPLOYEE_ID` WHERE `employee`.`STAFF_CATEGORY` = ? ");
		stm.setString(1, staff_category.getCategory());
		
		ResultSet rs = stm.executeQuery();
		rs.next();
		stm.closeOnCompletion();
		return rs;
	}
	
	public static int findSumPaymentPerCategory(Connection con, STAFF_CATEGORY staff_category){
		PreparedStatement stm;
		try {
			stm = con.prepareStatement("SELECT SUM(`PAYMENT_AMOUNT`) FROM `payrollpayment` INNER JOIN `employee` ON `employee`.`EMPLOYEE_ID` = `payrollpayment`.`EMPLOYEE_ID` WHERE `employee`.`STAFF_CATEGORY` = ? ");
			stm.setString(1, staff_category.getCategory());
			
			ResultSet rs = stm.executeQuery();
			rs.next();
			stm.closeOnCompletion();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int findSumPaymentPerCategoryDuringTime(Connection con, STAFF_CATEGORY staff_category, int fromYear, int fromMonth, int untilYear, int untilMonth){
		PreparedStatement stm;
		try {
			stm = con.prepareStatement("SELECT SUM(`PAYMENT_AMOUNT`) FROM `payrollpayment` INNER JOIN `employee` ON `employee`.`EMPLOYEE_ID` = `payrollpayment`.`EMPLOYEE_ID` WHERE `employee`.`STAFF_CATEGORY` = ? AND (`PAYMENT_DATE` BETWEEN ? AND ?) ");
			stm.setString(1, staff_category.getCategory());
			stm.setDate(2, Date.valueOf(fromYear+"-"+fromMonth+"-"+31));
			stm.setDate(3, Date.valueOf(untilYear+"-"+untilMonth+"-"+31));

			
			ResultSet rs = stm.executeQuery();
			rs.next();
			stm.closeOnCompletion();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Object[] getAllEmploysTotalPayments(Connection con){
		ResultSet rs;
		try {
			PreparedStatement stm = con.prepareStatement("SELECT `EMPLOYEE_ID`, `FNAME`, `LNAME`, AVG(`PAYMENT_AMOUNT`) FROM `payrollpayment` WHERE 1 GROUP BY `EMPLOYEE_ID`\r\n");
			rs = stm.executeQuery();
			ArrayList<String> employees = new ArrayList<String>();
			while(rs.next()) {
				employees.add(rs.getString("EMPLOYEE_ID")+". "+rs.getString("FNAME")+" "+rs.getString("LNAME")+" : "+rs.getInt(4));
			}
			stm.close();
			return employees.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
