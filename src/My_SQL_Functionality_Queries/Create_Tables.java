package My_SQL_Functionality_Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Create_Tables {

	public static void createEmployeeTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE `test`.`employee` "
			+ "( `EMPLOYEE_ID` INT NOT NULL AUTO_INCREMENT , "
			+ "`FNAME` VARCHAR(50) NOT NULL , "
			+ "`LNAME` VARCHAR(50) NOT NULL , "
			+ "`MARRIED` BOOLEAN NOT NULL , "
			+ "`STAFF_CATEGORY` ENUM('standing_administrative','contract_administrative','permanent_teaching','contract_teaching') NOT NULL , "
			+ "`BASE_SALARY` INT NULL , "
			+ "`DEPARTMENT` VARCHAR(50) NOT NULL , "
			+ "`HIRE_DATE` DATE NOT NULL , "
			+ "`ADDRESS` VARCHAR(200) NOT NULL , "
			+ "`PHONE` VARCHAR(50) NOT NULL , "
			+ "`BANK_ACCOUNT` VARCHAR(200) NOT NULL , "
			+ "`BANK_NAME` VARCHAR(200) NOT NULL , "
			+ "`ACTIVE` BOOLEAN NOT NULL , "
			+ "PRIMARY KEY (`EMPLOYEE_ID`)) "
			+ "ENGINE = InnoDB "
			+ "DEFAULT CHARSET=utf8;\r\n");
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Table Employee Created":"There was an problem at creating Employee Table");
		
	}
	
	public static void createContractsTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE `test`.`contracts`(\r\n" + 
				"`CONTRACT_ID` INT NOT NULL AUTO_INCREMENT,\r\n" + 
				"`EMPLOYEE_ID` INT NOT NULL,\r\n" + 
				"`START_DATE` DATE NOT NULL,\r\n" + 
				"`EXPIRE_DATE` DATE NOT NULL,\r\n" + 
				"`BASE_SALARY` INT NOT NULL,\r\n" + 
				"PRIMARY KEY(`CONTRACT_ID`),\r\n" + 
				"FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee`(`EMPLOYEE_ID`))"+
				"ENGINE = INNODB " +
				"DEFAULT CHARSET=utf8;\r\n");
			
			boolean a = stm.execute();
			
			System.out.println(!a?"Table Contracts Created":"There was an problem at creating Contracts Table");
	}
	
	public static void createChildrenTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE `test`.`children` "
				+ "( `CHILD_ID` INT NOT NULL AUTO_INCREMENT , "
				+ "`EMPLOYEE_ID` INT NOT NULL , "
				+ "`AGE` INT NOT NULL , "
				+ "PRIMARY KEY (`CHILD_ID`), "
				+ "FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee`(`EMPLOYEE_ID`))"
				+ "ENGINE = InnoDB "
				+ "DEFAULT CHARSET=utf8;\r\n");
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Table Children Created":"There was an problem at creating Children Table");
		
	}
	
	public static void createPaymentTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE `test`.`payment` "
				+ "( `PAYMENT_ID` INT NOT NULL AUTO_INCREMENT ,"
				+ "`EMPLOYEE_ID` INT NOT NULL , "
				+ "`STAFF_CATEGORY` ENUM('standing_administrative','contract_administrative','permanent_teaching','contract_teaching') NOT NULL , "
				+ "`NUMBER_OF_FAMILY_MEMBERS` INT NOT NULL , "
				+ "`YEARS_OF_EMPLOYMENT` INT NOT NULL , "
				+ "`BONUS` INT NOT NULL , 	"
				+ "PRIMARY KEY (`PAYMENT_ID`),"
				+ "FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `test`.`employee`(`EMPLOYEE_ID`))"
				+ "ENGINE = InnoDB "
				+ "DEFAULT CHARSET=utf8;\r\n");
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Table Payment Created":"There was an problem at creating Payment Table");
		
	}
	
	public static void createPayrollPaymentTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE `test`.`payrollpayment` "
				+ "( `PAYMENTPAYROLL_ID` INT NOT NULL AUTO_INCREMENT , "
				+ "`EMPLOYEE_ID` INT NOT NULL , "
				+ "`FNAME` VARCHAR(50) NOT NULL , "
				+ "`LNAME` VARCHAR(50) NOT NULL , "
				+ "`PAYMENT_AMOUNT` INT NOT NULL , "
				+ "`PAYMENT_DATE` DATE NOT NULL , "
				+ "PRIMARY KEY (`PAYMENTPAYROLL_ID`), "
				+ "FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `test`.`employee`(`EMPLOYEE_ID`))"
				+ "ENGINE = InnoDB "
				+ "DEFAULT CHARSET=utf8;\r\n");
		
		boolean a = stm.execute();
		
		System.out.println(!a?"Table PayrollPayment Created":"There was an problem at creating PayrollPayment Table");
		
	}
	
}
