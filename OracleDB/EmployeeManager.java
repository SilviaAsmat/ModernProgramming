//***************************************************************
//
//  Developer:    Silvia Asmat
//
//  Program #:    8
//
//  File Name:    EmployeeManager.java
//
//  Java Version: 17.0.12
//
//  Course:       COSC 4301 Modern Programming 
//
//  Due Date:     12/11/2024
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  
//
//***************************************************************

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final Scanner scanner = new Scanner(System.in);
    final static String DATE_FORMAT = "dd-MM-yyyy";
    private List<String> employeeTypes = new ArrayList<>();
	List<String> months = new ArrayList<>(Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    EmployeeManager(Connection connection) {
        this.connection = connection;
        employeeTypes = Arrays.asList("Piece_Employees", "Salaried_Employees", "Commission_Employees", "Plus_Commission_Employees", "Hourly_Employees");
    }

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    public void selectMethod(int choice) {
        switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                addPayrollInfo();
                break;
            case 3:
                increaseSalaryBasePlusCommission();
                break;
            case 4:
                addBirthdayBonus();
                break;
            case 5:
                addCommmissionEmployeeBonus();
                break;
            case 6:
                displayEmployeeInfo();
                break;
            case 7:
                displayFromLastName();
                break;
            case 8:
                displayByBirthdayMonth();
                break;
            case 9:
                displayBetweenLastNames();
                break;
        }
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    // String sql = "INSERT INTO SALARIED_EMPLOYEES (SSN, Week_Number, Weekly_Salary, Bonus) VALUES (?, ?, ?, ?)";

    private void addEmployee() {
        try {
            String ssn = getSSNFromUser();
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter birthdate (DD-MMM-YYYY): ");
            String birthdate = scanner.nextLine();

            System.out.print("Enter employee type: ");
            String employeeType = scanner.nextLine();

            System.out.print("Enter department name: ");
            String departmentName = scanner.nextLine();

            String query = "INSERT INTO EMPLOYEES (SSN, First_Name, Last_Name, Birthday, Employee_Type, Department_Name) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, birthdate);
            preparedStatement.setString(5, employeeType);
            preparedStatement.setString(6, departmentName);
            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
public void addPayrollInfo()
  	{
  		try
  		{
			String ssn = getSSNFromUser();
  			System.out.print("Enter week number: ");
  			int weekNum = scanner.nextInt();
  			System.out.print("Enter bonus: ");
  			double bonus = scanner.nextDouble();
  			System.out.print("Enter employee type (Salaried, Commission, PlusCommission, Hourly, Piece): ");
  			String type = scanner.next();
  			switch (type)
  			{
  				case "Salaried" -> 
  				{
  					System.out.print("Enter weekly salary");
  					double salary = scanner.nextDouble();
  					int numberOfRowsAffected = performExecuteUpdate("INSERT INTO Salaried_Employees VALUES ('" + ssn + "', '" + weekNum + "', '" + salary + "', '" + bonus + "')");
  		  			System.out.println("Payroll added." + numberOfRowsAffected + " rows affected.");
  				}
  				case "Commission" ->
  				{
  					System.out.print("Enter gross sales: ");
  					double grossSales = scanner.nextDouble();
  					System.out.print("Enter commission rate: ");
  					double commissionRate = scanner.nextDouble();
  		  			int numberOfRowsAffected = performExecuteUpdate("INSERT INTO Commission_Employees VALUES ('" + ssn + "', '" + weekNum + "', '" + grossSales + "', '" + commissionRate + "', '" + bonus + "')");
					System.out.println("Payroll added." + numberOfRowsAffected + " rows affected.");
  				}
  				case "PlusCommission" ->
  				{
  					System.out.print("Enter gross sales: ");
  					double grossSales = scanner.nextDouble();
  					System.out.print("Enter commission rate: ");
  					double commissionRate = scanner.nextDouble();
  					System.out.print("Enter weekly salary");
  					double salary = scanner.nextDouble();
  		  			int numberOfRowsAffected = performExecuteUpdate("INSERT INTO Plus_Commission_Employees VALUES ('" + ssn + "', '" + weekNum + "', '" + grossSales + "', '" + commissionRate + "', '" + salary + "', '" + bonus + "')");
  		  			System.out.println("Payroll added." + numberOfRowsAffected + " rows affected.");
  				}
  				case "Hourly" ->
  				{
  					System.out.print("Enter hours worked: ");
  					int hoursWorked = scanner.nextInt();
  					System.out.print("Enter pay rate: ");
  					double payRate = scanner.nextDouble();
  		  			int numberOfRowsAffected = performExecuteUpdate("INSERT INTO Hourly_Employees VALUES ('" + ssn + "', '" + weekNum + "', '" + hoursWorked + "', '" + payRate + "', '" + bonus + "')");
					System.out.println("Payroll added." + numberOfRowsAffected + " rows affected.");
  				}
  				case "Piece" ->
  				{
  					System.out.print("Enter number of pieces: ");
  					int pieces = scanner.nextInt();
  					System.out.print("Enter piece rate: ");
  					double pieceRate = scanner.nextDouble();
  		  			int numberOfRowsAffected = performExecuteUpdate("INSERT INTO Piece_Employees VALUES ('" + ssn + "', '" + weekNum + "', '" + pieceRate + "', '" + pieces + "', '" + bonus + "')");
  		  			System.out.println("Payroll added." + numberOfRowsAffected + " rows affected.");
  				}
  				default -> System.out.println("Invalid employee type please try again!");
  					
  			}
  		} catch (Exception e)
  		{
  			System.out.println("Invalid input given for payroll: " + e + " please try again!");
  		}
  	}
	 //***************************************************************
	//
	// Method:      performExecuteUpdate
	//
	// Description: takes a query that uses .executeUpdate and returns the 
	//			  number of rows affected. (throws SQLException which is handled in caller methods)
	//
	// Parameters:  1 String 1 Connection
	//
	// Returns:     int
	//
	//**************************************************************
  public int performExecuteUpdate(String query)
  {
    int rowsAffected = 0;
    try {
		String sql = query;
        PreparedStatement command;
        command = connection.prepareStatement(sql);
		rowsAffected = command.executeUpdate();
        } catch (SQLException e) {
			System.out.println("Error: " + e);
		}
    return rowsAffected;
  }

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    private void increaseSalaryBasePlusCommission() {
        try 
  		{
	  		int numberOfRowsAffected = performExecuteUpdate("UPDATE Plus_Commission_Employees SET Base_Salary = Base_Salary * 1.12");
	  		System.out.println("Base Salary increased for " + numberOfRowsAffected + " employees.");
  		} catch (Exception e)
  		{
  			System.out.println("Error: " + e);
  		}
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    private void addBirthdayBonus() {
        for (String emp : employeeTypes) 
        {
            String query = "UPDATE " + emp + " SET Bonus = Bonus + 200 WHERE SSN IN (SELECT SSN FROM Employees WHERE EXTRACT(MONTH FROM Birthday) = EXTRACT(MONTH FROM SYSDATE))";
            // UPDATE Plus_Commission_Employees SET Bonus = Bonus + 200 WHERE SSN IN (SELECT SSN FROM Employees WHERE EXTRACT(MONTH FROM Birthday) = EXTRACT(MONTH FROM SYSDATE)
            int rowsUpdated = performExecuteUpdate(query);
			System.out.println("Updated " + rowsUpdated + " employees in " + emp + " with birthday bonuses.");
        }
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************

    public void addCommmissionEmployeeBonus() {
        String query = "UPDATE COMMISSION_EMPLOYEES SET Bonus = Bonus + 200 WHERE Gross_Sales > 9000";
		int rowsUpdated = performExecuteUpdate(query);
		System.out.println("Updated " + rowsUpdated + " employees with sales bonuses.");
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************

    public void displayEmployeeInfo() {
        try {
			String ssn = getSSNFromUser();
			System.out.print("Enter columns to display separated by commas (SSN,First_Name,Last_Name,Birthday,Employee_Type,Department_Name): ");
			String columns = scanner.nextLine();
			ResultSet commandResult = getResultSet("SELECT " + columns + " FROM Employees WHERE SSN = '" + ssn + "'", connection);
			while(commandResult.next())
			{
				for (String column : columns.split(","))
				{
					System.out.print(column + ": " + commandResult.getString(column.trim()) + "\t");
				}
				System.out.println();
			}
			
		} catch (Exception e)
		{
			System.out.println("Invalid input given for employee: " + e + " please try again!");
		}
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************

    public void displayFromLastName() {
        try {
			System.out.print("Enter last name: ");
			  String last = scanner.nextLine();
			  ResultSet commandResult = getResultSet("SELECT * FROM Employees WHERE Last_Name = '" + last + "' ORDER BY First_Name", connection);
			  while(commandResult.next())
			  {
				  System.out.println("SSN: " + commandResult.getString("SSN") + "\tFirst Name: " + commandResult.getString("First_Name") + "\tLast Name: " + commandResult.getString("Last_Name") + "\tBirthday: " + commandResult.getString("Birthday") + "\tEmployee Type: " + commandResult.getString("Employee_Type") + "\tDepartment: " + commandResult.getString("Department_Name"));
			  }
		} catch (SQLException e) {
				System.out.println("Invalid input given for employees: " + e + " please try again!");
		}
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************

    public void displayByBirthdayMonth() {
        try {
			System.out.print("Enter month name: ");
			  String month = scanner.nextLine();
			  int monthInt = months.indexOf(month.toUpperCase()) + 1;
			  ResultSet commandResult = getResultSet("SELECT * FROM Employees WHERE EXTRACT(MONTH FROM Birthday) = '" + monthInt + "' ORDER BY Last_Name, First_Name", connection);
			  while(commandResult.next())
			  {
				  System.out.println("SSN: " + commandResult.getString("SSN") + "\tFirst Name: " + commandResult.getString("First_Name") + "\tLast Name: " + commandResult.getString("Last_Name") + "\tBirthday: " + commandResult.getString("Birthday") + "\tEmployee Type: " + commandResult.getString("Employee_Type") + "\tDepartment: " + commandResult.getString("Department_Name"));
			  }
		} catch (SQLException e)
		{
				System.out.println("Invalid input given for month: " + e + " please try again!");
		  }
    }
    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    public void displayBetweenLastNames() {
        System.out.print("Enter the starting last name: ");
        String startLastName = scanner.nextLine();

        System.out.print("Enter the ending last name: ");
        String endLastName = scanner.nextLine();
		try{
        ResultSet commandResult = getResultSet("SELECT * FROM Employees WHERE Last_Name BETWEEN '" + startLastName + "' AND '" + endLastName + "' ORDER BY Last_Name, First_Name", connection);
  		  while(commandResult.next())
  		  {
  			  System.out.println("SSN: " + commandResult.getString("SSN") + "\tFirst Name: " + commandResult.getString("First_Name") + "\tLast Name: " + commandResult.getString("Last_Name") + "\tBirthday: " + commandResult.getString("Birthday") + "\tEmployee Type: " + commandResult.getString("Employee_Type") + "\tDepartment: " + commandResult.getString("Department_Name"));
  		  }
	  } catch (SQLException e)
	  {
  			System.out.println("Invalid input given for names: " + e + " please try again!");
  	  }
    }

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************

    private String getSSNFromUser() {
        String input = "";
        while (input.equals("")) 
        {
            System.out.println("Enter SSN (000-00-0000): ");
            input = scanner.nextLine();
            String[] columns = input.split("-");
            if (columns.length != 3) {
                input = "";
                System.out.println("Invalid input. Too many hyphens");
            } 
            else if (columns[0].length() != 3 || columns[1].length() != 2 || columns[2].length() != 4) 
            {
                input = "";
                System.out.println("Invalid input. Incorrect amount of numbers");
            }
        }
        return input;

    }

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    //***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:   
    //
    //**************************************************************
    private String getDateFromUser() {
        String input = "";
        
        while (!input.equals("")) {
            System.out.println("Enter Date (DD-MMM-YYYY) (01-JAN-2000): ");
            input = scanner.nextLine();
            String[] columns = input.split("-");

            if (columns.length != 3) {
                input = "";
                System.out.println("Invalid input. Too many hyphens");
            } else if (!months.contains(columns[1])) {
                input = "";
                System.out.println("Invalid month. Use 3 letter format.");
            }
        }
        return input;
    }

	public ResultSet getResultSet(String query, Connection connection) throws SQLException
  	{
	  	String sql = query;
	  	PreparedStatement command = connection.prepareStatement(sql);
	  	ResultSet commandResult = command.executeQuery();
	  	return commandResult;
	  
  	}

}
