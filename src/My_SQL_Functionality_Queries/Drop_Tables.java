package My_SQL_Functionality_Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Drop_Tables {
	public static void dropEmployeeTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("drop TABLE `test`.`employee` ");
		boolean a = stm.execute();

		System.out.println(!a ? "Table Employee droped" : "There was an problem at dropping Employee Table");

	}

	public static void dropContractsTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("drop TABLE `test`.`contracts` ");

		boolean a = stm.execute();

		System.out.println(!a ? "Table Contracts droped" : "There was an problem at dropping Contracts Table");
	}

	public static void dropChildrenTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("drop TABLE `test`.`children` ");

		boolean a = stm.execute();

		System.out.println(!a ? "Table Children droped" : "There was an problem at dropping Children Table");

	}

	public static void dropPaymentTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("drop TABLE `test`.`payment` ");

		boolean a = stm.execute();

		System.out.println(!a ? "Table Payment droped" : "There was an problem at dropping Payment Table");

	}

	public static void dropPayrollPaymentTable(Connection con) throws SQLException {
		PreparedStatement stm = con.prepareStatement("drop TABLE `test`.`payrollpayment` ");

		boolean a = stm.execute();

		System.out.println(!a ? "Table PayrollPayment droped" : "There was an problem at dropping PayrollPayment Table");

	}
}
