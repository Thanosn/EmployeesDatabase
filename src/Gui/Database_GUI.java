package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import My_SQL_Functionality_Queries.Add_To_Tables;
import My_SQL_Functionality_Queries.Find_In_Tables;
import My_SQL_Functionality_Queries.STAFF_CATEGORY;
import My_SQL_Functionality_Queries.Update_in_Tables;

public class Database_GUI {
	private static Connection con;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Database_GUI window = new Database_GUI();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}

				String url = new String("jdbc:mysql://localhost");
				String databaseName = new String("test");
				int port = 3306;
				String username = new String("root");
				String password = new String("");

				con = null;
				try {
					con = DriverManager.getConnection(
							url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					Tests.initialize(con);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Database_GUI() {
		initializeMainComponents();
		initializeHirePageComponents();
		initializeQuerysPageComponents();
		initializeUpdatePageComponents();
		initializeChangeDatePageComponents();
		initializeChangeSalariesPageComponents();
		hideAllPages();
	}
	
	private Panel panel;
	private JFrame frame;
	
	private JComboBox<Object> chooserEmployeeList;
	private JButton chooseEmployeeFromTheListButton;

	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField departmentTextField;
	private JTextField contractSalaryTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField bankAccountTextField;
	private JTextField bankNameTextField;
	private JTextField[] kidsAgeTextField = new JTextField[10];
	private JLabel[] kidsAgeLabel = new JLabel[10];
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel isMarriedLabel;
	private JLabel staffCategoryLabel;
	private JLabel departmentLabel;
	private JLabel contractSalaryLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel bankAccountLabel;
	private JLabel bankNameLabel;
	private JCheckBox isMarriedCheckBox;
	private JComboBox<Object> staffCategoryChoiceBox;
	private JLabel kidsLabel;
	private JComboBox<Object> kidsChoiceBox;
	private JButton addEmployButton;

	private JLabel startingDateLabel;
	private JComboBox<Object> startDateDay;
	private JComboBox<Object> startDateMonth;
	private JComboBox<Object> startDateYear;
	private JLabel expireingDateLabel;
	private JComboBox<Object> expireDateDay;
	private JComboBox<Object> expireDateMonth;
	private JComboBox<Object> expireDateYear;
	
	///////////////////////
	private JButton fireEmployeeButton;
	private JButton promoteEmployeeButton;
	private JButton updateEmployeeButton;
	
	private JLabel lblChangeDateDay;
	private JLabel lblChangeDateMonth;
	private JLabel lblChangeDateYear;
	private JComboBox<Object> changeDaySelectionList;
	private JComboBox<Object> changeMonthSelectionList;
	private JComboBox<Object> changeYearSelectionList;
	private JButton changeDateConfirmationButton;
	
	private JLabel currentDateLabel;
	private JButton btnResolvePayments;
	private JButton hirePageButton;
	private JButton updatePageButton;
	private JButton changeSalariesPageButton;
	private JButton changeDatePageButton;
	private JButton querysPageButton;
	
	private JLabel lblChangeBaseSalary;
	private JLabel lblChangeLibraryBonus;
	private JLabel lblChangeResearchBonus;
	private JTextField changeBaseSalaryTextField;
	private JTextField changeLibraryBonusTextField;
	private JTextField changeResearchBonusTextField;
	private JLabel lblChangeLibraryBonusPercent;
	private JLabel lblChangeReasearchBonusPercent;
	private JButton btnUpdateSalariesConfiramtion;
	
	private JButton showPaymentButton;
	private JButton showMinMaxAvgButton;
	private JButton showSumOfPaymentsButton;
	private JComboBox<Object> showPerCategoryChoiseList;
	private JLabel showPerCategoryChoiseListLabel;
	private JLabel showFromDateLabel;
	private JLabel showUntilDateLabel;
	private JComboBox<Object> showFromDateMonthChoiseList;
	private JComboBox<Object> showFromDateYearChoiseList;
	private JComboBox<Object> showUntilDateMonthChoiseList;
	private JComboBox<Object> showUntilDateYearChoiseList;
	private JComboBox<Object> showHisPaymentsChoiseList;
	private JButton showHisPaymentsButton;

	
	
	private void changeToHirePage() {
		hideAllPages();
		firstNameTextField.setVisible(true);
		lastNameTextField.setVisible(true);
		departmentTextField.setVisible(true);
		addressTextField.setVisible(true);
		phoneTextField.setVisible(true);
		bankAccountTextField.setVisible(true);
		bankNameTextField.setVisible(true);
		firstNameLabel.setVisible(true);
		lastNameLabel.setVisible(true);
		isMarriedLabel.setVisible(true);
		staffCategoryLabel.setVisible(true);
		departmentLabel.setVisible(true);
		addressLabel.setVisible(true);
		phoneLabel.setVisible(true);
		bankAccountLabel.setVisible(true);
		bankNameLabel.setVisible(true);
		isMarriedCheckBox.setVisible(true);
		staffCategoryChoiceBox.setVisible(true);
		kidsLabel.setVisible(true);
		kidsChoiceBox.setVisible(true);
		addEmployButton.setVisible(true);

	}

	private void changeToUpdateSelectionOnly() {
		hideAllPages();
		chooserEmployeeList.setEnabled(true);
		chooserEmployeeList.setVisible(true);
		chooseEmployeeFromTheListButton.setVisible(true);
	}

	private void changeToUpdatePage() {
		firstNameTextField.setVisible(true);
		lastNameTextField.setVisible(true);
		departmentTextField.setVisible(true);
		addressTextField.setVisible(true);
		phoneTextField.setVisible(true);
		bankAccountTextField.setVisible(true);
		bankNameTextField.setVisible(true);
		firstNameLabel.setVisible(true);
		lastNameLabel.setVisible(true);
		isMarriedLabel.setVisible(true);
		staffCategoryLabel.setVisible(true);
		departmentLabel.setVisible(true);

		addressLabel.setVisible(true);
		phoneLabel.setVisible(true);
		bankAccountLabel.setVisible(true);
		bankNameLabel.setVisible(true);
		isMarriedCheckBox.setVisible(true);
		staffCategoryChoiceBox.setVisible(true);
		kidsLabel.setVisible(true);
		kidsChoiceBox.setVisible(true);
		fireEmployeeButton.setVisible(true);
		promoteEmployeeButton.setVisible(true);
		updateEmployeeButton.setVisible(true);
	}

	private void changeToChangeSalariesPage() {
		hideAllPages();
		lblChangeBaseSalary.setVisible(true);
		lblChangeLibraryBonus.setVisible(true);
		lblChangeResearchBonus.setVisible(true);
		changeBaseSalaryTextField.setVisible(true);
		changeLibraryBonusTextField.setVisible(true);
		changeResearchBonusTextField.setVisible(true);
		lblChangeLibraryBonusPercent.setVisible(true);
		lblChangeReasearchBonusPercent.setVisible(true);
		btnUpdateSalariesConfiramtion.setVisible(true);
	}

	private void changeToChangeDatePage() {
		hideAllPages();
		lblChangeDateDay.setVisible(true);
		lblChangeDateMonth.setVisible(true);
		lblChangeDateYear.setVisible(true);
		changeDaySelectionList.setVisible(true);
		changeMonthSelectionList.setVisible(true);
		changeYearSelectionList.setVisible(true);
		changeDateConfirmationButton.setVisible(true);
	}

	private void changeToQuerysPage() {
		hideAllPages();
		showPaymentButton.setVisible(true);
		showMinMaxAvgButton.setVisible(true);
		showSumOfPaymentsButton.setVisible(true);
		showPerCategoryChoiseList.setVisible(true);
		showPerCategoryChoiseListLabel.setVisible(true);
		showFromDateLabel.setVisible(true);
		showUntilDateLabel.setVisible(true);
		showFromDateMonthChoiseList.setVisible(true);
		showFromDateYearChoiseList.setVisible(true);
		showUntilDateMonthChoiseList.setVisible(true);
		showUntilDateYearChoiseList.setVisible(true);
		showHisPaymentsChoiseList.setEnabled(true);
		showHisPaymentsChoiseList.setVisible(true);
		showHisPaymentsButton.setVisible(true);
	}
	
	private void hideAllPages() {
		chooserEmployeeList.setEnabled(false);
		chooserEmployeeList.setVisible(false);
		chooseEmployeeFromTheListButton.setVisible(false);
		firstNameTextField.setVisible(false);
		lastNameTextField.setVisible(false);
		departmentTextField.setVisible(false);
		contractSalaryTextField.setVisible(false);
		addressTextField.setVisible(false);
		phoneTextField.setVisible(false);
		bankAccountTextField.setVisible(false);
		bankNameTextField.setVisible(false);
		firstNameLabel.setVisible(false);
		lastNameLabel.setVisible(false);
		isMarriedLabel.setVisible(false);
		staffCategoryLabel.setVisible(false);
		departmentLabel.setVisible(false);
		contractSalaryLabel.setVisible(false);
		addressLabel.setVisible(false);
		phoneLabel.setVisible(false);
		bankAccountLabel.setVisible(false);
		bankNameLabel.setVisible(false);

		for (int i = 0; i < 10; i++) {
			kidsAgeLabel[i].setVisible(false);
			kidsAgeTextField[i].setVisible(false);
		}

		isMarriedCheckBox.setVisible(false);
		staffCategoryChoiceBox.setVisible(false);
		staffCategoryChoiceBox.setSelectedIndex(0);
		kidsLabel.setVisible(false);
		kidsChoiceBox.setVisible(false);
		kidsChoiceBox.setSelectedIndex(0);
		addEmployButton.setVisible(false);
		startingDateLabel.setVisible(false);
		startDateDay.setVisible(false);
		startDateMonth.setVisible(false);
		startDateYear.setVisible(false);
		expireingDateLabel.setVisible(false);
		expireDateDay.setVisible(false);
		expireDateMonth.setVisible(false);
		expireDateYear.setVisible(false);
		fireEmployeeButton.setVisible(false);
		promoteEmployeeButton.setVisible(false);
		updateEmployeeButton.setVisible(false);
		lblChangeDateDay.setVisible(false);
		lblChangeDateMonth.setVisible(false);
		lblChangeDateYear.setVisible(false);
		changeDaySelectionList.setVisible(false);
		changeMonthSelectionList.setVisible(false);
		changeYearSelectionList.setVisible(false);
		changeDateConfirmationButton.setVisible(false);

		lblChangeBaseSalary.setVisible(false);
		lblChangeLibraryBonus.setVisible(false);
		lblChangeResearchBonus.setVisible(false);
		changeBaseSalaryTextField.setVisible(false);
		changeLibraryBonusTextField.setVisible(false);
		changeResearchBonusTextField.setVisible(false);
		lblChangeLibraryBonusPercent.setVisible(false);
		lblChangeReasearchBonusPercent.setVisible(false);
		btnUpdateSalariesConfiramtion.setVisible(false);
		showPaymentButton.setVisible(false);
		showMinMaxAvgButton.setVisible(false);
		showSumOfPaymentsButton.setVisible(false);
		showPerCategoryChoiseList.setVisible(false);
		showPerCategoryChoiseListLabel.setVisible(false);
		showFromDateLabel.setVisible(false);
		showUntilDateLabel.setVisible(false);
		showFromDateMonthChoiseList.setVisible(false);
		showFromDateYearChoiseList.setVisible(false);
		showUntilDateMonthChoiseList.setVisible(false);
		showUntilDateYearChoiseList.setVisible(false);
		showHisPaymentsChoiseList.setEnabled(false);
		showHisPaymentsChoiseList.setVisible(false);
		showHisPaymentsButton.setVisible(false);
	}



	// Helping Globals

	private int kidsOwn = 0;
	private boolean isContract = false;

	private int currentEmployeeUpdatingId;

	private int baseSalary = 120;
	private int libraryBonus = 1; // %
	private int researchBonus = 1; // %

	private int currentDay = java.time.LocalDate.now().getDayOfMonth();
	private int currentMonth = java.time.LocalDate.now().getMonthValue();
	private int currentYear = java.time.LocalDate.now().getYear();

	int prevMonth = currentMonth;
	int prevYear = currentYear;

	

	private void initializeMainComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 754, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new Panel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 33, 738, 474);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnResolvePayments = new JButton("Resolve Payments");
		btnResolvePayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(panel,
						"Do you want to resolve Payments and check Contracts ?", "Update", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.NO_OPTION)
					return;
				try {
					while (prevYear != currentYear || prevMonth != currentMonth) {
						prevMonth++;
						if (prevMonth > 12) {
							prevYear++;
							prevMonth = 1;
						}
						Update_in_Tables.refreshPaymentTable(con, prevYear, libraryBonus, researchBonus);
						Add_To_Tables.conductAllPaymentpayrolls(con, prevYear, prevMonth, 31);
						Update_in_Tables.fireAllExpiredContracts(con, prevYear, prevMonth);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnResolvePayments.setBounds(584, 0, 144, 23);
		panel.add(btnResolvePayments);

		hirePageButton = new JButton("Hire");
		hirePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToHirePage();
			}
		});
		hirePageButton.setBounds(25, 0, 99, 34);
		frame.getContentPane().add(hirePageButton);

		updatePageButton = new JButton("Update");
		updatePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToUpdateSelectionOnly();
			}
		});
		updatePageButton.setBounds(135, 0, 99, 34);
		frame.getContentPane().add(updatePageButton);

		changeSalariesPageButton = new JButton("Change Salaries");
		changeSalariesPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToChangeSalariesPage();
			}
		});
		changeSalariesPageButton.setBounds(244, 0, 130, 34);
		frame.getContentPane().add(changeSalariesPageButton);

		changeDatePageButton = new JButton("Change Date");
		changeDatePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeToChangeDatePage();
			}
		});
		changeDatePageButton.setBounds(384, 0, 99, 34);
		frame.getContentPane().add(changeDatePageButton);

		querysPageButton = new JButton("Querys");
		querysPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToQuerysPage();
			}
		});
		querysPageButton.setBounds(493, 0, 99, 34);
		frame.getContentPane().add(querysPageButton);

		currentDateLabel = new JLabel(currentDay + "-" + currentMonth + "-" + currentYear);
		currentDateLabel.setBounds(668, 0, 70, 34);
		frame.getContentPane().add(currentDateLabel);
	}

	private void initializeQuerysPageComponents() {
		showPaymentButton = new JButton("Payments in Time");
		showPaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Sum: " + Find_In_Tables.findSumPaymentPerCategoryDuringTime(con,
								STAFF_CATEGORY
										.valueOf(showPerCategoryChoiseList.getSelectedItem().toString().toUpperCase()),
								Integer.parseInt(showFromDateYearChoiseList.getSelectedItem().toString()),
								Integer.parseInt(showFromDateMonthChoiseList.getSelectedItem().toString()),
								Integer.parseInt(showUntilDateYearChoiseList.getSelectedItem().toString()),
								Integer.parseInt(showUntilDateMonthChoiseList.getSelectedItem().toString())));
			}
		});
		showPaymentButton.setBounds(82, 235, 144, 23);
		panel.add(showPaymentButton);

		showMinMaxAvgButton = new JButton("MIN/MAX/AVG");
		showMinMaxAvgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = Find_In_Tables.findMinMaxAvgPaymentPerCategory(con, STAFF_CATEGORY
							.valueOf(showPerCategoryChoiseList.getSelectedItem().toString().toUpperCase()));
					JOptionPane.showMessageDialog(frame,
							"MIN: " + rs.getInt(1) + " MAX: " + rs.getInt(2) + " AVG: " + rs.getInt(3));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		showMinMaxAvgButton.setBounds(304, 235, 111, 23);
		panel.add(showMinMaxAvgButton);

		showSumOfPaymentsButton = new JButton("Sum of Payments");
		showSumOfPaymentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Sum: " + Find_In_Tables.findSumPaymentPerCategory(con,
						STAFF_CATEGORY.valueOf(showPerCategoryChoiseList.getSelectedItem().toString().toUpperCase())));
			}
		});
		showSumOfPaymentsButton.setBounds(524, 235, 137, 23);
		panel.add(showSumOfPaymentsButton);

		String[] choices = new String[4];
		choices[0] = "STANDING_ADMINISTRATIVE";
		choices[1] = "CONTRACT_ADMINISTRATIVE";
		choices[2] = "PERMANENT_TEACHING";
		choices[3] = "CONTRACT_TEACHING";

		showPerCategoryChoiseList = new JComboBox<Object>(choices);
		showPerCategoryChoiseList.setBounds(82, 97, 162, 20);
		panel.add(showPerCategoryChoiseList);

		showPerCategoryChoiseListLabel = new JLabel("Staff Category");
		showPerCategoryChoiseListLabel.setBounds(131, 72, 72, 14);
		panel.add(showPerCategoryChoiseListLabel);

		showFromDateLabel = new JLabel("From");
		showFromDateLabel.setBounds(402, 72, 46, 14);
		panel.add(showFromDateLabel);

		showUntilDateLabel = new JLabel("Until");
		showUntilDateLabel.setBounds(584, 72, 46, 14);
		panel.add(showUntilDateLabel);

		ArrayList<Integer> monthsList = new ArrayList<Integer>(12);
		ArrayList<Integer> yearsList = new ArrayList<Integer>(71);

		for (int i = 1; i <= 12; i++)
			monthsList.add(i);
		for (int i = 2000; i <= 2070; i++)
			yearsList.add(i);

		showFromDateMonthChoiseList = new JComboBox<Object>(monthsList.toArray());
		showFromDateMonthChoiseList.setBounds(348, 97, 46, 20);
		panel.add(showFromDateMonthChoiseList);

		showFromDateYearChoiseList = new JComboBox<Object>(yearsList.toArray());
		showFromDateYearChoiseList.setBounds(412, 97, 72, 20);
		panel.add(showFromDateYearChoiseList);

		showUntilDateMonthChoiseList = new JComboBox<Object>(monthsList.toArray());
		showUntilDateMonthChoiseList.setBounds(525, 97, 46, 20);
		panel.add(showUntilDateMonthChoiseList);

		showUntilDateYearChoiseList = new JComboBox<Object>(yearsList.toArray());
		showUntilDateYearChoiseList.setBounds(589, 97, 72, 20);
		panel.add(showUntilDateYearChoiseList);

		showHisPaymentsChoiseList = new JComboBox<Object>();
		showHisPaymentsChoiseList.addPropertyChangeListener("enabled", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				showHisPaymentsChoiseList.removeAllItems();
				if (evt.getNewValue() == (Object) true)
					for (Object item : Find_In_Tables.getAllEmploysTotalPayments(con))
						showHisPaymentsChoiseList.addItem(item);
			}
		});
		showHisPaymentsChoiseList.setBounds(144, 338, 304, 20);
		panel.add(showHisPaymentsChoiseList);

		showHisPaymentsButton = new JButton("Show his Payments");
		showHisPaymentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, showHisPaymentsChoiseList.getSelectedItem().toString());
			}
		});
		showHisPaymentsButton.setBounds(482, 337, 137, 23);
		panel.add(showHisPaymentsButton);
	}

	private void initializeUpdatePageComponents() {
		chooserEmployeeList = new JComboBox<Object>();
		chooserEmployeeList.addPropertyChangeListener("enabled", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				chooserEmployeeList.removeAllItems();
				if (evt.getNewValue() == (Object) true)
					for (Object item : Find_In_Tables.getAllEmployeesNames(con))
						chooserEmployeeList.addItem(item);
			}
		});
		chooserEmployeeList.setBounds(10, 11, 323, 20);
		panel.add(chooserEmployeeList);

		chooseEmployeeFromTheListButton = new JButton("Choose");
		chooseEmployeeFromTheListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = Find_In_Tables.findInEmployeeTable(con,
							getIdFromSelection(chooserEmployeeList.getSelectedItem().toString()));
					changeToUpdatePage();

					currentEmployeeUpdatingId = Integer.parseInt(rs.getString("EMPLOYEE_ID"));
					firstNameTextField.setText(rs.getString("FNAME"));
					lastNameTextField.setText(rs.getString("LNAME"));
					departmentTextField.setText(rs.getString("DEPARTMENT"));
					contractSalaryTextField.setText(rs.getString("EMPLOYEE_ID"));
					addressTextField.setText(rs.getString("ADDRESS"));
					phoneTextField.setText(rs.getString("PHONE"));
					bankAccountTextField.setText(rs.getString("BANK_ACCOUNT"));
					bankNameTextField.setText(rs.getString("BANK_NAME"));
					isMarriedCheckBox.setSelected(rs.getBoolean("MARRIED"));
					staffCategoryChoiceBox.setSelectedIndex(0);
					staffCategoryChoiceBox.setSelectedItem(rs.getString("STAFF_CATEGORY").toUpperCase());

					if (STAFF_CATEGORY
							.valueOf(staffCategoryChoiceBox.getSelectedItem()
									.toString()) == STAFF_CATEGORY.CONTRACT_ADMINISTRATIVE
							|| STAFF_CATEGORY.valueOf(staffCategoryChoiceBox.getSelectedItem()
									.toString()) == STAFF_CATEGORY.CONTRACT_TEACHING) {
						ResultSet contractResults = Find_In_Tables.findInContractsTable(con, currentEmployeeUpdatingId);

						contractSalaryTextField.setText(contractResults.getString("BASE_SALARY"));

						Calendar cal = new GregorianCalendar();
						cal.setTime(contractResults.getDate("START_DATE"));
						startDateYear.setSelectedItem(cal.get(Calendar.YEAR));
						// +1 because January is number 0 in GregorianCalendar
						startDateMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
						startDateDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));

						cal.setTime(contractResults.getDate("EXPIRE_DATE"));
						expireDateYear.setSelectedItem(cal.get(Calendar.YEAR));
						expireDateMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
						expireDateDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));

						contractResults.close();
					}

					if (isMarriedCheckBox.isSelected()) {
						ResultSet kidsResults = Find_In_Tables.findInChildrenTable(con, currentEmployeeUpdatingId);
						int kids = 0;
						while (kidsResults.next() && kids < 10) {
							kidsAgeLabel[kids].setVisible(true);
							kidsAgeTextField[kids].setVisible(true);
							kidsAgeTextField[kids++].setText(kidsResults.getString("AGE"));
						}
						kidsChoiceBox.setSelectedIndex(kids);
						kidsResults.close();
					}
					rs.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		});
		chooseEmployeeFromTheListButton.setBounds(343, 10, 89, 23);
		panel.add(chooseEmployeeFromTheListButton);

		fireEmployeeButton = new JButton("FIRE");
		fireEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int answer = JOptionPane.showConfirmDialog(panel, "Do you want to fire "
							+ firstNameTextField.getText() + " " + lastNameTextField.getText() + "?", "FIRE",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.NO_OPTION)
						return;

					Update_in_Tables.inactivateEmployee(con, currentEmployeeUpdatingId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		fireEmployeeButton.setBounds(516, 441, 89, 23);
		panel.add(fireEmployeeButton);

		promoteEmployeeButton = new JButton("PROMOTE");
		promoteEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int answer = JOptionPane.showConfirmDialog(panel, "Do you want to promote "
							+ firstNameTextField.getText() + " " + lastNameTextField.getText() + "?", "PROMOTE",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.NO_OPTION)
						return;

					Update_in_Tables.promoteEmployeeToPermanent(con, currentEmployeeUpdatingId,
							STAFF_CATEGORY.valueOf(staffCategoryChoiceBox.getSelectedItem().toString()));
					changeToUpdateSelectionOnly();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		promoteEmployeeButton.setBounds(639, 441, 89, 23);
		panel.add(promoteEmployeeButton);

		updateEmployeeButton = new JButton("UPDATE");
		updateEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int answer = JOptionPane.showConfirmDialog(panel, "Do you want to update information for "
							+ firstNameTextField.getText() + " " + lastNameTextField.getText() + "?", "Update",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.NO_OPTION)
						return;
					Update_in_Tables.updateEmployeeTable(con, currentEmployeeUpdatingId, firstNameTextField.getText(),
							lastNameTextField.getText(), isMarriedCheckBox.isSelected(), departmentTextField.getText(),
							addressTextField.getText(), phoneTextField.getText(), bankAccountTextField.getText(),
							bankNameTextField.getText());
					if (isMarriedCheckBox.isSelected()) {
						Update_in_Tables.removeEmployeesChildren(con, currentEmployeeUpdatingId);
						for (int i = 0; i < kidsOwn; i++) {
							Add_To_Tables.addToChildrenTable(con, currentEmployeeUpdatingId,
									Integer.parseInt(kidsAgeTextField[i].getText()));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		updateEmployeeButton.setBounds(311, 440, 89, 23);
		panel.add(updateEmployeeButton);
	}

	private int getIdFromSelection(final String string) {
		int i = 0;
		while (i < string.length() && !Character.isDigit(string.charAt(i)))
			i++;
		int j = i;
		while (j < string.length() && Character.isDigit(string.charAt(j)))
			j++;
		return Integer.parseInt(string.substring(i, j)); 
	}

	private void initializeHirePageComponents() {
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(106, 59, 97, 20);
		panel.add(firstNameTextField);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(22, 36, 86, 47);
		panel.add(firstNameLabel);

		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(22, 81, 86, 47);
		panel.add(lastNameLabel);

		isMarriedLabel = new JLabel("Married");
		isMarriedLabel.setBounds(22, 124, 70, 47);
		panel.add(isMarriedLabel);

		staffCategoryLabel = new JLabel("Staff Category");
		staffCategoryLabel.setBounds(479, 36, 86, 20);
		panel.add(staffCategoryLabel);

		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(106, 94, 97, 20);
		panel.add(lastNameTextField);

		departmentLabel = new JLabel("Department");
		departmentLabel.setBounds(22, 397, 86, 67);
		panel.add(departmentLabel);

		departmentTextField = new JTextField();
		departmentTextField.setBounds(106, 420, 97, 20);
		panel.add(departmentTextField);

		contractSalaryTextField = new JTextField();
		contractSalaryTextField.setBounds(595, 82, 86, 20);
		contractSalaryTextField.setVisible(false);
		panel.add(contractSalaryTextField);

		contractSalaryLabel = new JLabel("Base Salary");
		contractSalaryLabel.setBounds(511, 59, 86, 67);
		contractSalaryLabel.setVisible(false);
		panel.add(contractSalaryLabel);

		addressLabel = new JLabel("Address");
		addressLabel.setBounds(521, 195, 86, 67);
		panel.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(605, 218, 86, 20);
		panel.add(addressTextField);

		phoneLabel = new JLabel("Phone");
		phoneLabel.setBounds(521, 238, 86, 67);
		panel.add(phoneLabel);

		phoneTextField = new JTextField();
		phoneTextField.setBounds(605, 261, 86, 20);
		panel.add(phoneTextField);

		bankAccountLabel = new JLabel("Bank Account");
		bankAccountLabel.setBounds(522, 316, 86, 67);
		panel.add(bankAccountLabel);

		bankAccountTextField = new JTextField();
		bankAccountTextField.setBounds(606, 339, 86, 20);
		panel.add(bankAccountTextField);

		bankNameLabel = new JLabel("Bank Name");
		bankNameLabel.setBounds(522, 374, 86, 67);
		panel.add(bankNameLabel);

		bankNameTextField = new JTextField();
		bankNameTextField.setBounds(606, 397, 86, 20);
		panel.add(bankNameTextField);

		isMarriedCheckBox = new JCheckBox("", true);
		isMarriedCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				kidsLabel.setEnabled(isMarriedCheckBox.isSelected());
				kidsLabel.setVisible(isMarriedCheckBox.isSelected());
				kidsChoiceBox.setEnabled(isMarriedCheckBox.isSelected());
				kidsChoiceBox.setVisible(isMarriedCheckBox.isSelected());

				for (int i = 0; i < 10; i++) {
					kidsAgeLabel[i].setEnabled(i < kidsOwn && isMarriedCheckBox.isSelected());
					kidsAgeTextField[i].setEnabled(i < kidsOwn && isMarriedCheckBox.isSelected());
					kidsAgeLabel[i].setVisible(i < kidsOwn && isMarriedCheckBox.isSelected());
					kidsAgeTextField[i].setVisible(i < kidsOwn && isMarriedCheckBox.isSelected());
				}
			}
		});
		isMarriedCheckBox.setBackground(new Color(230, 230, 250));
		isMarriedCheckBox.setBounds(65, 136, 21, 21);
		panel.add(isMarriedCheckBox);

		String[] choices = new String[4];
		choices[0] = "STANDING_ADMINISTRATIVE";
		choices[1] = "CONTRACT_ADMINISTRATIVE";
		choices[2] = "PERMANENT_TEACHING";
		choices[3] = "CONTRACT_TEACHING";

		staffCategoryChoiceBox = new JComboBox<Object>(choices);
		staffCategoryChoiceBox.setBounds(566, 36, 162, 20);
		staffCategoryChoiceBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					isContract = STAFF_CATEGORY
							.valueOf(staffCategoryChoiceBox.getSelectedItem()
									.toString()) == STAFF_CATEGORY.CONTRACT_ADMINISTRATIVE
							|| STAFF_CATEGORY.valueOf(staffCategoryChoiceBox.getSelectedItem()
									.toString()) == STAFF_CATEGORY.CONTRACT_TEACHING;
					startingDateLabel.setVisible(isContract);
					startDateDay.setVisible(isContract);
					startDateMonth.setVisible(isContract);
					startDateYear.setVisible(isContract);
					expireingDateLabel.setVisible(isContract);
					expireDateDay.setVisible(isContract);
					expireDateMonth.setVisible(isContract);
					expireDateYear.setVisible(isContract);
					contractSalaryTextField.setVisible(isContract);
					contractSalaryLabel.setVisible(isContract);
				}
			}
		});
		panel.add(staffCategoryChoiceBox);

		kidsLabel = new JLabel("KIDS");
		kidsLabel.setBounds(106, 139, 46, 14);
		panel.add(kidsLabel);

		String[] kidsChoise = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		kidsChoiceBox = new JComboBox<Object>(kidsChoise);
		kidsChoiceBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					kidsOwn = Integer.parseInt(kidsChoiceBox.getSelectedItem().toString());
					for (int i = 0; i < 10; i++) {
						kidsAgeLabel[i].setVisible(i < kidsOwn);
						kidsAgeTextField[i].setVisible(i < kidsOwn);
						kidsAgeLabel[i].setEnabled(i < kidsOwn);
						kidsAgeTextField[i].setEnabled(i < kidsOwn);
					}
				}
			}
		});
		kidsChoiceBox.setBounds(157, 137, 46, 20);
		panel.add(kidsChoiceBox);

		addEmployButton = new JButton("EXECUTE");
		addEmployButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int answer = JOptionPane.showConfirmDialog(panel, "Do you want to hire "
							+ firstNameTextField.getText() + " " + lastNameTextField.getText() + "?", "Hire",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.NO_OPTION)
						return;

					STAFF_CATEGORY staff_category = STAFF_CATEGORY
							.valueOf(staffCategoryChoiceBox.getSelectedItem().toString());
					int employeeID = Add_To_Tables.addToEmployeeTable(con, firstNameTextField.getText(),
							lastNameTextField.getText(), isMarriedCheckBox.isSelected(), staff_category,
							isContract ? Integer.parseInt(contractSalaryTextField.getText()) : baseSalary,
							departmentTextField.getText(),
							isContract ? Integer.parseInt(startDateYear.getSelectedItem().toString()) : currentYear,
							isContract ? Integer.parseInt(startDateMonth.getSelectedItem().toString()) : currentMonth,
							isContract ? Integer.parseInt(startDateDay.getSelectedItem().toString()) : 1,
							addressTextField.getText(), phoneTextField.getText(), bankAccountTextField.getText(),
							bankNameTextField.getText());
					if (isMarriedCheckBox.isSelected()) {
						for (int i = 0; i < kidsOwn; i++) {
							Add_To_Tables.addToChildrenTable(con, employeeID,
									Integer.parseInt(kidsAgeTextField[i].getText()));
						}
					}
					if (isContract) {
						Add_To_Tables.addToContractsTable(con, employeeID,
								Integer.parseInt(startDateDay.getSelectedItem().toString()),
								Integer.parseInt(startDateMonth.getSelectedItem().toString()),
								Integer.parseInt(startDateYear.getSelectedItem().toString()),
								Integer.parseInt(expireDateDay.getSelectedItem().toString()),
								Integer.parseInt(expireDateMonth.getSelectedItem().toString()),
								Integer.parseInt(expireDateYear.getSelectedItem().toString()),
								Integer.parseInt(contractSalaryTextField.getText()));
					}
					Add_To_Tables.addToPaymentTable(con, employeeID, staff_category,
							isMarriedCheckBox.isSelected() ? kidsOwn + 1 : 0, 0,
							staff_category == STAFF_CATEGORY.CONTRACT_TEACHING ? libraryBonus : researchBonus);
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addEmployButton.setBackground(new Color(240, 248, 255));
		addEmployButton.setBounds(311, 440, 89, 23);
		panel.add(addEmployButton);

		// Kids Age

		kidsAgeLabel[0] = new JLabel("Kid 1 Age:");
		kidsAgeLabel[0].setBounds(22, 167, 70, 14);
		panel.add(kidsAgeLabel[0]);

		kidsAgeTextField[0] = new JTextField();
		kidsAgeTextField[0].setBounds(87, 164, 116, 20);
		panel.add(kidsAgeTextField[0]);

		kidsAgeLabel[1] = new JLabel("Kid 2 Age:");
		kidsAgeLabel[1].setBounds(22, 189, 70, 14);
		panel.add(kidsAgeLabel[1]);

		kidsAgeTextField[1] = new JTextField();
		kidsAgeTextField[1].setBounds(87, 186, 116, 20);
		panel.add(kidsAgeTextField[1]);

		kidsAgeLabel[2] = new JLabel("Kid 3 Age:");
		kidsAgeLabel[2].setBounds(23, 214, 70, 14);
		panel.add(kidsAgeLabel[2]);

		kidsAgeTextField[2] = new JTextField();
		kidsAgeTextField[2].setBounds(88, 211, 116, 20);
		panel.add(kidsAgeTextField[2]);

		kidsAgeLabel[3] = new JLabel("Kid 4 Age:");
		kidsAgeLabel[3].setBounds(22, 245, 70, 14);
		panel.add(kidsAgeLabel[3]);

		kidsAgeTextField[3] = new JTextField();
		kidsAgeTextField[3].setBounds(87, 242, 116, 20);
		panel.add(kidsAgeTextField[3]);

		kidsAgeLabel[4] = new JLabel("Kid 5 Age:");
		kidsAgeLabel[4].setBounds(22, 271, 70, 14);
		panel.add(kidsAgeLabel[4]);

		kidsAgeTextField[4] = new JTextField();
		kidsAgeTextField[4].setBounds(87, 268, 116, 20);
		panel.add(kidsAgeTextField[4]);

		kidsAgeLabel[5] = new JLabel("Kid 6 Age:");
		kidsAgeLabel[5].setBounds(22, 298, 70, 14);
		panel.add(kidsAgeLabel[5]);

		kidsAgeTextField[5] = new JTextField();
		kidsAgeTextField[5].setBounds(87, 295, 116, 20);
		panel.add(kidsAgeTextField[5]);

		kidsAgeLabel[6] = new JLabel("Kid 7 Age:");
		kidsAgeLabel[6].setBounds(22, 326, 70, 14);
		panel.add(kidsAgeLabel[6]);

		kidsAgeTextField[6] = new JTextField();
		kidsAgeTextField[6].setBounds(87, 323, 116, 20);
		panel.add(kidsAgeTextField[6]);

		kidsAgeLabel[7] = new JLabel("Kid 8 Age:");
		kidsAgeLabel[7].setBounds(22, 349, 70, 14);
		panel.add(kidsAgeLabel[7]);

		kidsAgeTextField[7] = new JTextField();
		kidsAgeTextField[7].setBounds(87, 346, 116, 20);
		panel.add(kidsAgeTextField[7]);

		kidsAgeLabel[8] = new JLabel("Kid 9 Age:");
		kidsAgeLabel[8].setBounds(22, 377, 70, 20);
		panel.add(kidsAgeLabel[8]);

		kidsAgeTextField[8] = new JTextField();
		kidsAgeTextField[8].setBounds(87, 374, 116, 20);
		panel.add(kidsAgeTextField[8]);

		kidsAgeLabel[9] = new JLabel("Kid 10 Age:");
		kidsAgeLabel[9].setBounds(22, 400, 70, 14);
		panel.add(kidsAgeLabel[9]);

		kidsAgeTextField[9] = new JTextField();
		kidsAgeTextField[9].setBounds(87, 397, 116, 20);
		panel.add(kidsAgeTextField[9]);

		ArrayList<Integer> daysList = new ArrayList<Integer>(31);
		ArrayList<Integer> monthsList = new ArrayList<Integer>(12);
		ArrayList<Integer> yearsList = new ArrayList<Integer>(71);

		for (int i = 1; i <= 31; i++)
			daysList.add(i);
		for (int i = 1; i <= 12; i++)
			monthsList.add(i);
		for (int i = 2000; i <= 2070; i++)
			yearsList.add(i);

		startingDateLabel = new JLabel("Starting Date:");
		startingDateLabel.setBounds(479, 110, 79, 27);
		startingDateLabel.setVisible(false);
		panel.add(startingDateLabel);

		startDateDay = new JComboBox<Object>(daysList.toArray());
		startDateDay.setBounds(568, 113, 39, 20);
		startDateDay.setVisible(false);
		panel.add(startDateDay);

		startDateMonth = new JComboBox<Object>(monthsList.toArray());
		startDateMonth.setBounds(605, 113, 55, 20);
		startDateMonth.setVisible(false);
		panel.add(startDateMonth);

		startDateYear = new JComboBox<Object>(yearsList.toArray());
		startDateYear.setBounds(658, 113, 70, 20);
		startDateYear.setVisible(false);
		panel.add(startDateYear);

		expireingDateLabel = new JLabel("Expire Date:");
		expireingDateLabel.setBounds(479, 157, 79, 27);
		expireingDateLabel.setVisible(false);
		panel.add(expireingDateLabel);

		expireDateDay = new JComboBox<Object>(daysList.toArray());
		expireDateDay.setBounds(568, 157, 39, 20);
		expireDateDay.setVisible(false);
		panel.add(expireDateDay);

		expireDateMonth = new JComboBox<Object>(monthsList.toArray());
		expireDateMonth.setBounds(605, 157, 55, 20);
		expireDateMonth.setVisible(false);
		panel.add(expireDateMonth);

		expireDateYear = new JComboBox<Object>(yearsList.toArray());
		expireDateYear.setBounds(658, 157, 70, 20);
		expireDateYear.setVisible(false);
		panel.add(expireDateYear);

	}

	private void initializeChangeSalariesPageComponents() {
		baseSalary = Find_In_Tables.findPrevBaseSalary(con);
		libraryBonus = Find_In_Tables.finePrevLibraryBonus(con);
		researchBonus = Find_In_Tables.findPrevResearchBonus(con);

		lblChangeBaseSalary = new JLabel("Base Salary:");
		lblChangeBaseSalary.setBounds(207, 151, 84, 23);
		panel.add(lblChangeBaseSalary);

		lblChangeLibraryBonus = new JLabel("Library Bonus:");
		lblChangeLibraryBonus.setBounds(207, 185, 84, 23);
		panel.add(lblChangeLibraryBonus);

		lblChangeResearchBonus = new JLabel("Research Bonus:");
		lblChangeResearchBonus.setBounds(207, 219, 84, 23);
		panel.add(lblChangeResearchBonus);

		changeBaseSalaryTextField = new JTextField("" + baseSalary);
		changeBaseSalaryTextField.setBounds(301, 152, 86, 20);
		panel.add(changeBaseSalaryTextField);
		changeBaseSalaryTextField.setColumns(10);

		changeLibraryBonusTextField = new JTextField("" + libraryBonus);
		changeLibraryBonusTextField.setBounds(301, 186, 86, 20);
		panel.add(changeLibraryBonusTextField);
		changeLibraryBonusTextField.setColumns(10);

		changeResearchBonusTextField = new JTextField("" + researchBonus);
		changeResearchBonusTextField.setBounds(301, 220, 86, 20);
		panel.add(changeResearchBonusTextField);
		changeResearchBonusTextField.setColumns(10);

		lblChangeLibraryBonusPercent = new JLabel("%");
		lblChangeLibraryBonusPercent.setBounds(397, 189, 46, 14);
		panel.add(lblChangeLibraryBonusPercent);

		lblChangeReasearchBonusPercent = new JLabel("%");
		lblChangeReasearchBonusPercent.setBounds(397, 223, 46, 14);
		panel.add(lblChangeReasearchBonusPercent);

		btnUpdateSalariesConfiramtion = new JButton("Update Salaries");
		btnUpdateSalariesConfiramtion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(changeBaseSalaryTextField.getText()) < baseSalary)
					JOptionPane.showMessageDialog(frame, "You cant lower the Base Salary");
				else {
					baseSalary = Integer.parseInt(changeBaseSalaryTextField.getText());
					Update_in_Tables.updateBaseSalaryForAllEmployees(con, baseSalary);
				}

				if (Integer.parseInt(changeBaseSalaryTextField.getText()) < libraryBonus)
					JOptionPane.showMessageDialog(frame, "You cant lower the Library Bonus");
				else
					libraryBonus = Integer.parseInt(changeLibraryBonusTextField.getText());

				if (Integer.parseInt(changeBaseSalaryTextField.getText()) < researchBonus)
					JOptionPane.showMessageDialog(frame, "You cant lower the Research Bonus");
				else
					researchBonus = Integer.parseInt(changeResearchBonusTextField.getText());

			}
		});
		btnUpdateSalariesConfiramtion.setBounds(253, 263, 134, 36);
		panel.add(btnUpdateSalariesConfiramtion);
	}

	private void initializeChangeDatePageComponents() {
		ArrayList<Integer> daysList = new ArrayList<Integer>(31);
		ArrayList<Integer> monthsList = new ArrayList<Integer>(12);
		ArrayList<Integer> yearsList = new ArrayList<Integer>(71);

		for (int i = 1; i <= 31; i++)
			daysList.add(i);
		for (int i = 1; i <= 12; i++)
			monthsList.add(i);
		for (int i = 2000; i <= 2070; i++)
			yearsList.add(i);

		changeMonthSelectionList = new JComboBox<Object>(monthsList.toArray());
		changeMonthSelectionList.setBounds(238, 210, 70, 27);
		panel.add(changeMonthSelectionList);

		changeYearSelectionList = new JComboBox<Object>(yearsList.toArray());
		changeYearSelectionList.setBounds(318, 210, 144, 27);
		panel.add(changeYearSelectionList);

		changeDaySelectionList = new JComboBox<Object>(daysList.toArray());
		changeDaySelectionList.setBounds(183, 211, 45, 26);
		panel.add(changeDaySelectionList);

		changeDateConfirmationButton = new JButton("GO");
		changeDateConfirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prevMonth = currentMonth;
					prevYear = currentYear;
					currentDay = Integer.parseInt(changeDaySelectionList.getSelectedItem().toString());
					currentMonth = Integer.parseInt(changeMonthSelectionList.getSelectedItem().toString());
					currentYear = Integer.parseInt(changeYearSelectionList.getSelectedItem().toString());
					currentDateLabel.setText(currentDay + "-" + currentMonth + "-" + currentYear);
					Update_in_Tables.updateAllChildrenAges(con, currentYear - prevYear);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		changeDateConfirmationButton.setBounds(472, 209, 79, 28);
		panel.add(changeDateConfirmationButton);

		lblChangeDateMonth = new JLabel("Month");
		lblChangeDateMonth.setBounds(246, 185, 46, 14);
		panel.add(lblChangeDateMonth);

		lblChangeDateYear = new JLabel("Year");
		lblChangeDateYear.setBounds(363, 185, 46, 14);
		panel.add(lblChangeDateYear);

		lblChangeDateDay = new JLabel("Day");
		lblChangeDateDay.setBounds(190, 185, 46, 14);
		panel.add(lblChangeDateDay);

	}
}
