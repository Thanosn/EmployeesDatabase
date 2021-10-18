package My_SQL_Functionality_Queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Add_To_Tables {
	
	public static int getLastEmployeeId(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("SELECT LAST_INSERT_ID() AS last_id FROM employee\r\n");
		ResultSet rs = stm.executeQuery();
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

	public static int addToEmployeeTable(Connection con, String fname, String lname, boolean is_married,
			STAFF_CATEGORY staff_cattegory, int base_salary, String department, int hire_year, int hire_month, int hire_day,
			String address, String phone_number, String bank_account, String bank_name) throws SQLException {
		
		PreparedStatement stm = con.prepareStatement("INSERT INTO `employee`(\r\n" + 
				"    `EMPLOYEE_ID`,\r\n" + 
				"    `FNAME`,\r\n" + 
				"    `LNAME`,\r\n" + 
				"    `MARRIED`,\r\n" + 
				"    `STAFF_CATEGORY`,\r\n" + 
				"	 `BASE_SALARY`,\r\n"+
				"    `DEPARTMENT`,\r\n" + 
				"    `HIRE_DATE`,\r\n" + 
				"    `ADDRESS`,\r\n" + 
				"    `PHONE`,\r\n" + 
				"    `BANK_ACCOUNT`,\r\n" + 
				"    `BANK_NAME`,\r\n" + 
				"	 `ACTIVE`\r\n" +
				")\r\n" + 
				"VALUES(\r\n" + 
				"    NULL,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" +
				"    ?,\r\n" +
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"	 1\r\n"+
				");\r\n");
		stm.setString(1, fname);
		stm.setString(2, lname);
		stm.setBoolean(3, is_married);
		stm.setString(4, staff_cattegory.getCategory());
		stm.setInt(5, base_salary);
		stm.setString(6, department);
		stm.setDate(7, Date.valueOf(hire_year+"-"+hire_month+"-"+hire_day));
		stm.setString(8, address);
		stm.setString(9, phone_number);
		stm.setString(10, bank_account);
		stm.setString(11, bank_name);
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Succesfully added Employee "+fname+" "+lname:"There was a problem at adding to Employee Table");
		return !a?getLastEmployeeId(con):-1;
	}

	
	public static void addToContractsTable(Connection con, int employee_id, int start_day, int start_month,
			int start_year, int expire_day, int expire_month, int expire_year, int salary) throws SQLException {
		if(employee_id<1) {
			System.out.println("Employee Id cannot be less than 1");
			return;
		}
		PreparedStatement stm = con.prepareStatement("INSERT INTO `contracts`(\r\n" + 
				"    `CONTRACT_ID`,\r\n" + 
				"    `EMPLOYEE_ID`,\r\n" + 
				"    `START_DATE`,\r\n" + 
				"    `EXPIRE_DATE`,\r\n" + 
				"    `BASE_SALARY`\r\n" + 
				")\r\n" + 
				"VALUES(\r\n" + 
				"    NULL,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?\r\n" + 
				");");
		

		stm.setInt(1, employee_id);
		stm.setDate(2, Date.valueOf(start_year+"-"+start_month+"-"+start_day));
		stm.setDate(3, Date.valueOf(expire_year+"-"+expire_month+"-"+expire_day));
		stm.setInt(4, salary);
		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully added Contract for Employee No: " + employee_id
				: "There was a problem at adding to Contracts Table");
		stm.close();
	}
	
	public static void addToChildrenTable(Connection con, int parent_id, int child_age) throws SQLException {
		if(parent_id<1) {
			System.out.println("Parent Id cannot be less than 1");
			return;
		}
		
		PreparedStatement stm = con.prepareStatement("INSERT INTO `children`(`CHILD_ID`, `EMPLOYEE_ID`, `AGE`)\r\n" + 
				"VALUES(NULL, ?, ?);");
		
		stm.setInt(1, parent_id);
		stm.setInt(2, child_age);
		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully added Child of Employee No: " + parent_id
				: "There was a problem at adding to Children Table");
		stm.close();
	}
	
	public static void addToPaymentTable(Connection con, int employee_id, STAFF_CATEGORY staff_cattegory,
			int number_of_family_members, int years_of_employment, int special_bonus) throws SQLException {
		
		PreparedStatement stm = con.prepareStatement("INSERT INTO `payment`(\r\n"+
				"	 `PAYMENT_ID`,\r\n" + 
				"    `EMPLOYEE_ID`,\r\n" + 
				"    `STAFF_CATEGORY`,\r\n" + 
				"    `NUMBER_OF_FAMILY_MEMBERS`,\r\n" + 
				"    `YEARS_OF_EMPLOYMENT`,\r\n" + 
				"	 `BONUS`\r\n"+
				")\r\n" + 
				"VALUES(NULL, ?, ?, ?, ?, ?);");
		
		stm.setInt(1, employee_id);
		stm.setString(2, staff_cattegory.getCategory());
		stm.setInt(3, number_of_family_members);
		stm.setInt(4, years_of_employment);
		stm.setInt(5, special_bonus);
		

		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully added Payment Information of Employee No: " + employee_id
				: "There was a problem at adding to Children Table");
		stm.close();
	}

	
	public static void addToPayrollPaymentTable(Connection con, int employee_id, String fname, String lname,
			int payment_ammount, int payment_year, int payment_month, int payment_day) throws SQLException {
		PreparedStatement stm = con.prepareStatement("INSERT INTO `payrollpayment`(\r\n" + 
				"    `PAYMENTPAYROLL_ID`,\r\n" + 
				"    `EMPLOYEE_ID`,\r\n" + 
				"    `FNAME`,\r\n" + 
				"    `LNAME`,\r\n" + 
				"    `PAYMENT_AMOUNT`,\r\n" + 
				"    `PAYMENT_DATE`\r\n" + 
				")\r\n" + 
				"VALUES(\r\n" + 
				"    NULL,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?,\r\n" + 
				"    ?\r\n" + 
				");");
		
		stm.setInt(1, employee_id);
		stm.setString(2, fname);
		stm.setString(3, lname);
		stm.setInt(4, payment_ammount);
		stm.setDate(5, Date.valueOf(payment_year+"-"+payment_month+"-"+payment_day));
		
		boolean a = stm.execute();
		
		System.out.println(!a ? "Succesfully added PayrollPayment of Employee No: " + employee_id + " at "+ 31 +"-"+ payment_month +"-"+ payment_year
				: "There was a problem at adding to PayrollPayment Table");
		stm.close();
	}
	
	
	public static void conductAllPaymentpayrolls(Connection con, int current_year, int current_month, int current_day) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT"
			+ " 	`employee`.`EMPLOYEE_ID`,"
			+ " 	`FNAME`,"
			+ " 	`LNAME`,"
			+ " 	`BASE_SALARY`,"
			+ " 	`NUMBER_OF_FAMILY_MEMBERS`,"
			+ " 	`YEARS_OF_EMPLOYMENT`,"
			+ " 	`BONUS`"
			+ " FROM"
			+ " 	`employee`"
			+ " INNER JOIN `payment` ON `employee`.`EMPLOYEE_ID` = `payment`.`EMPLOYEE_ID`"
			+ "WHERE"
			+ "		`ACTIVE` = 1");
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			final int base_salary = rs.getInt("BASE_SALARY");
			final int years_of_employment = rs.getInt("YEARS_OF_EMPLOYMENT");
			int payment_ammount = base_salary;
			for (int i = 1; i < years_of_employment; i++)
				payment_ammount += payment_ammount * 15 / 100;
			
			payment_ammount += (base_salary * 5 / 100 * rs.getInt("NUMBER_OF_FAMILY_MEMBERS"))
					+ (base_salary * rs.getInt("BONUS") / 100);

			addToPayrollPaymentTable(con, rs.getInt(1), rs.getString(2), rs.getString(3), payment_ammount, current_year,
					current_month, current_day);
		}
		stm.close();
	}
}





















