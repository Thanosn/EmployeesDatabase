package My_SQL_Functionality_Queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Update_in_Tables {
	public static void updateEmployeeTable(Connection con, int employee_id, String fname, String lname, boolean is_married, String department,
			String address, String phone_number, String bank_account, String bank_name) throws SQLException {
		PreparedStatement stm = con.prepareStatement("UPDATE `employee`\r\n" + 
				"SET\r\n" + 
				"    `FNAME` = ?,\r\n" + 
				"    `LNAME` = ?,\r\n" + 
				"    `MARRIED` = ?,\r\n" + 
				"    `DEPARTMENT` = ?,\r\n" + 
				"    `ADDRESS` = ?,\r\n" + 
				"    `PHONE` = ?,\r\n" + 
				"    `BANK_ACCOUNT` = ?,\r\n" + 
				"    `BANK_NAME` = ?\r\n" + 
				"WHERE\r\n" + 
				"    `employee`.`EMPLOYEE_ID` = ?;");

				stm.setString( 1,fname);
				stm.setString( 2,lname);
				stm.setBoolean( 3,is_married);
				stm.setString( 4,department);
				stm.setString( 5,address);
				stm.setString( 6,phone_number);
				stm.setString( 7,bank_account);
				stm.setString( 8,bank_name);
				stm.setInt(9, employee_id);
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Succesfully updated Employee "+fname+" "+lname:"There was a problem at updating to Employee Table");
		stm.close();
	}
	
	public static void inactivateEmployee(Connection con, int employee_id) throws SQLException{
		PreparedStatement stm = con.prepareStatement("UPDATE `employee`\r\n" + 
				"SET\r\n" + 
				"    `ACTIVE` = 0\r\n"+ 
				"WHERE\r\n" + 
				"    `employee`.`EMPLOYEE_ID` = ?;");

		stm.setInt(1, employee_id);

		boolean a = stm.execute();
		
		System.out.println(!a?"Succesfully inactivated Employee "+employee_id:"There was a problem at updating to Employee Table");
		stm.close();
	}
	
	public static void promoteEmployeeToPermanent(Connection con, int employee_id, STAFF_CATEGORY current_category) throws SQLException{
		
		final STAFF_CATEGORY newCategory = current_category == STAFF_CATEGORY.CONTRACT_ADMINISTRATIVE
				? STAFF_CATEGORY.STANDING_ADMINISTRATIVE : STAFF_CATEGORY.PERMANENT_TEACHING;
		
		PreparedStatement stm = con.prepareStatement("UPDATE `employee`\r\n" + 
				"SET\r\n" + 
				"    `STAFF_CATEGORY` = ?\r\n"+ 
				"WHERE\r\n" + 
				"    `employee`.`EMPLOYEE_ID` = ?;");

		stm.setString(1, newCategory.getCategory());
		stm.setInt(2, employee_id);

		boolean a = stm.execute();
		
		System.out.println(!a?"Succesfully updated Employee "+employee_id:"There was a problem at updating to Employee Table");
		stm.close();
	}
	
	public static void updateAllChildrenAges(Connection con, int years) throws SQLException {
		if (years < 1)
			return;
		PreparedStatement stm = con.prepareStatement("UPDATE `children` SET `AGE` = `AGE` + ? WHERE 1;");
		stm.setInt(1, years);
		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully updated all Children ages for: "+ years +" years"
				: "There was a problem at updating to Children Table");
		stm.close();
	}
	
	public static void removeEmployeesChildren(Connection con, int employee_id) throws SQLException{
		PreparedStatement stm = con.prepareStatement("DELETE FROM `children` WHERE `children`.`EMPLOYEE_ID` = ? ");
		stm.setInt(1, employee_id);
		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully removed all Children for Employee: "+ employee_id
				: "There was a problem at updating to Children Table");
		stm.close();
	}
	
	public static void fireAllExpiredContracts(Connection con,int currentYear, int currentMonth) throws SQLException{
		PreparedStatement stm = con.prepareStatement(
				"SELECT `contracts`.`EMPLOYEE_ID` FROM `contracts` INNER JOIN `employee` ON `contracts`.`EMPLOYEE_ID` = `employee`.`EMPLOYEE_ID` WHERE DATEDIFF(? ,`EXPIRE_DATE`) > 0 AND `employee`.`ACTIVE` = 1 GROUP BY `contracts`.`EMPLOYEE_ID` ;");
		stm.setDate(1, Date.valueOf(currentYear + "-" + currentMonth + "-" + 31));
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			inactivateEmployee(con, rs.getInt(1));
		}
		stm.close();
	}
	
	public static void refreshPaymentTable(Connection con, int currentYear, int library_bonus, int research_bonus) throws SQLException{
		PreparedStatement stm = con.prepareStatement(
				"SELECT `EMPLOYEE_ID`, `MARRIED`, `STAFF_CATEGORY`, `HIRE_DATE` FROM `employee` WHERE `ACTIVE` = 1 \r\n");
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			int employee_id = rs.getInt("EMPLOYEE_ID");
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(rs.getDate("HIRE_DATE"));
			STAFF_CATEGORY staff_category = STAFF_CATEGORY.valueOf(rs.getString("STAFF_CATEGORY").toUpperCase());
			boolean married = rs.getBoolean("MARRIED");
			int years_of_employment = currentYear - cal.get(Calendar.YEAR);
			int bonus = staff_category == STAFF_CATEGORY.CONTRACT_TEACHING ? library_bonus
					: staff_category == STAFF_CATEGORY.PERMANENT_TEACHING ? research_bonus : 0;
			int children = 0;
			if (married) {
				PreparedStatement stm2 = con.prepareStatement("SELECT `AGE` FROM `children` WHERE `EMPLOYEE_ID` = ?");
				stm2.setInt(1, employee_id);
				ResultSet childrenRs = stm2.executeQuery();
				while (childrenRs.next())
					if (childrenRs.getInt("AGE") <= 18)
						children++;
				stm2.close();
			}
			PreparedStatement stm3 = con.prepareStatement(
					"UPDATE `payment` SET `STAFF_CATEGORY` = ?, `NUMBER_OF_FAMILY_MEMBERS` = ?, `YEARS_OF_EMPLOYMENT` = ?, `BONUS` = ? WHERE `payment`.`EMPLOYEE_ID` = ? ;");
			stm3.setString(1, staff_category.getCategory());
			stm3.setInt(2, married ? children + 1 : 0);
			stm3.setInt(3, years_of_employment);
			stm3.setInt(4, bonus);
			stm3.setInt(5, employee_id);

			boolean a = stm3.execute();

			System.out.println(!a ? "Succesfully updated Payment information for Employee: " + employee_id
					: "There was a problem at updating Payment information");
			stm3.close();
		}
		stm.close();
	}
	
	public static void updateBaseSalaryForAllEmployees(Connection con, int newBaseSalary){
		try {
			PreparedStatement stm = con.prepareStatement("UPDATE `employee` SET `BASE_SALARY` = ? WHERE `STAFF_CATEGORY` = 'permanent_teaching' OR `STAFF_CATEGORY` = 'standing_administrative'");
			stm.setInt(1, newBaseSalary);
			boolean a = stm.execute();
			
			System.out.println(!a?"Changed Base Salaries for permanent employees":"There was at changing Base Salaries");
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

























