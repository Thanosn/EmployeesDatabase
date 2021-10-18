package Gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import My_SQL_Functionality_Queries.Add_To_Tables;
import My_SQL_Functionality_Queries.Create_Tables;
import My_SQL_Functionality_Queries.Drop_Tables;
import My_SQL_Functionality_Queries.STAFF_CATEGORY;
import My_SQL_Functionality_Queries.Update_in_Tables;

public class Tests {
	public static void initialize(Connection con) throws SQLException {
		Random rand = new Random();

		//Uncomment to Drop the tables id already Exist
		
		Drop_Tables.dropContractsTable(con);
		Drop_Tables.dropChildrenTable(con);
		Drop_Tables.dropPaymentTable(con);
		Drop_Tables.dropPayrollPaymentTable(con);
		Drop_Tables.dropEmployeeTable(con);

		Create_Tables.createEmployeeTable(con);
		Create_Tables.createContractsTable(con);
		Create_Tables.createChildrenTable(con);
		Create_Tables.createPaymentTable(con);
		Create_Tables.createPayrollPaymentTable(con);

		for (int i = 0; i < 20; i++) {
			Add_To_Tables.addToEmployeeTable(con, "P", "F", true, STAFF_CATEGORY.PERMANENT_TEACHING, 541, "csd", 2010,
					5, 26, "HIN " + i, "6985471235", "ABC895785547", "Big Bank");
			Add_To_Tables.addToContractsTable(con, i + 1, 21, 5, 2010, 28, 6, 2015, 521);
			Add_To_Tables.addToPaymentTable(con, i + 1, STAFF_CATEGORY.CONTRACT_ADMINISTRATIVE, 5, 3, 5);
		}

		for (int i = 0; i < 20; i++) {
			Add_To_Tables.addToChildrenTable(con, rand.nextInt(20) + 1, rand.nextInt(20));
		}

		for (int i = 0; i < 12; i++) {
			Add_To_Tables.addToPayrollPaymentTable(con, rand.nextInt(20) + 1, "P", "F", 521, 2000, 1, 31);
		}
		Update_in_Tables.updateAllChildrenAges(con, 1);
	}
}
