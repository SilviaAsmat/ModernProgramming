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
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.*;

public class EmployeeManager {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final Scanner scanner = new Scanner(System.in);
    final static String DATE_FORMAT = "dd-MM-yyyy";
    private List<String> employeeTypes = new ArrayList<>();

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
            System.out.print("Enter SSN: ");
            String ssn = scanner.nextLine();

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

    private void addPayrollInfo() {

        System.out.print("Enter SSN: ");
        String ssn = scanner.nextLine();
        try {
            switch (getEmployeeType(ssn)) {
                case "salariedEmployee" -> addSalariedPayroll(ssn);
                case "commissionEmployee" -> addCommissionPayroll(ssn);
                case "basePlusCommissionEmployee" -> addPlusCommissionPayroll(ssn);
                case "hourlyEmployee" -> addHourlyPayroll(ssn);
                case "pieceEmployees" -> addPiecePayroll(ssn);
            }
        } catch (SQLException e) {

        }

    }

    private void addPiecePayroll(String ssn) throws SQLException {
        System.out.print("Enter week number (1-52): ");
        int week = scanner.nextInt();
        System.out.print("Enter piece rate: ");
        double pieceRate = scanner.nextDouble();
        System.out.print("Enter number of pieces produced: ");
        int pieces = scanner.nextInt();
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        String query = "INSERT INTO PIECE_EMPLOYEES (SSN, Week_Number, Piece_Rate, Number_Pieces, Bonus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            stmt.setInt(2, week);
            stmt.setDouble(3, pieceRate);
            stmt.setInt(4, pieces);
            stmt.setDouble(5, bonus);
            stmt.executeUpdate();
            System.out.println("Piece payroll added successfully.");
        }
    }

    private void addPlusCommissionPayroll(String ssn) throws SQLException {
        System.out.print("Enter week number (1-52): ");
        int week = scanner.nextInt();
        System.out.print("Enter base salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter gross sales: ");
        double grossSales = scanner.nextDouble();
        System.out.print("Enter commission rate: ");
        double commissionRate = scanner.nextDouble();
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        String query = "INSERT INTO PLUS_COMMISSION_EMPLOYEES (SSN, Week_Number, Base_Salary, Gross_Sales, Commission_Rate, Bonus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            stmt.setInt(2, week);
            stmt.setDouble(3, baseSalary);
            stmt.setDouble(4, grossSales);
            stmt.setDouble(5, commissionRate);
            stmt.setDouble(6, bonus);
            stmt.executeUpdate();
            System.out.println("Base-plus-commission payroll added successfully.");
        }
    }

    private void addCommissionPayroll(String ssn) throws SQLException {
        System.out.print("Enter week number (1-52): ");
        int week = scanner.nextInt();
        System.out.print("Enter gross sales: ");
        double grossSales = scanner.nextDouble();
        System.out.print("Enter commission rate: ");
        double commissionRate = scanner.nextDouble();
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        String query = "INSERT INTO COMMISSION_EMPLOYEES (SSN, Week_Number, Gross_Sales, Commission_Rate, Bonus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            stmt.setInt(2, week);
            stmt.setDouble(3, grossSales);
            stmt.setDouble(4, commissionRate);
            stmt.setDouble(5, bonus);
            stmt.executeUpdate();
            System.out.println("Commission payroll added successfully.");
        }
    }

    private String getEmployeeType(String ssn) {
        String query = "SELECT Employee_Type FROM EMPLOYEES WHERE SSN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Employee_Type");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving employee type: " + e.getMessage());
        }
        return null;
    }

    private void addHourlyPayroll(String ssn) throws SQLException {
        System.out.print("Enter week number (1-52): ");
        int week = scanner.nextInt();
        System.out.print("Enter hours worked: ");
        double hours = scanner.nextDouble();
        System.out.print("Enter pay rate: ");
        double payRate = scanner.nextDouble();
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        String query = "INSERT INTO HOURLY_EMPLOYEES (SSN, Week_Number, Hours_Worked, Pay_Rate, Bonus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            stmt.setInt(2, week);
            stmt.setDouble(3, hours);
            stmt.setDouble(4, payRate);
            stmt.setDouble(5, bonus);
            stmt.executeUpdate();
            System.out.println("Hourly payroll added successfully.");
        }
    }

    private void addSalariedPayroll(String ssn) throws SQLException {
        System.out.print("Enter week number (1-52): ");
        int week = scanner.nextInt();
        System.out.print("Enter weekly salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        String query = "INSERT INTO SALARIED_EMPLOYEES (SSN, Week_Number, Salary, Bonus) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ssn);
            stmt.setInt(2, week);
            stmt.setDouble(3, salary);
            stmt.setDouble(4, bonus);
            stmt.executeUpdate();
            System.out.println("Salaried payroll added successfully.");
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
    private void increaseSalaryBasePlusCommission() {
        try {
            String query = "UPDATE PLUS_COMMISSION_EMPLOYEES SET Base_Salary = Base_Salary * 1.12";
            preparedStatement = connection.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " employees' base salaries.");
        } catch (SQLException e) {
            System.err.println("Error updating salaries: " + e.getMessage());
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

    // String sql = "SELECT " + columns + " FROM Employees WHERE SSN = ?";
    // PreparedStatement pstmt = connection.prepareStatement(sql));
    // pstmt.setString(1, ssn);
    // ResultSet rs = pstmt.executeQuery();
    private void addBirthdayBonus() {
        for (String emp : employeeTypes) {
            String query = "UPDATE " + emp + " SET Bonus = Bonus + 200 WHERE EXTRACT(MONTH FROM Birthday) = EXTRACT(MONTH FROM SYSDATE)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                int rowsUpdated = stmt.executeUpdate();
                System.out.println("Updated " + rowsUpdated + " employees in " + emp + " with birthday bonuses.");
            } catch (SQLException e) {
                System.err.println("Error updating bonuses for " + emp + ": " + e.getMessage());
            }
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
        try {
            String query = """
                    UPDATE COMMISSION_EMPLOYEES
                    SET Bonus = Bonus + 200
                    WHERE Gross_Sales > 9000
                    """;
            preparedStatement = connection.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " employees with sales bonuses.");
        } catch (SQLException e) {
            System.err.println("Error adding sales bonuses: " + e.getMessage());
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

    public void displayEmployeeInfo() {
        try {
            System.out.print("Enter employee SSN: ");
            String ssn = scanner.nextLine();

            String query = "SELECT * FROM EMPLOYEES WHERE SSN = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("SSN: " + resultSet.getString("SSN")
                        + ", Name: " + resultSet.getString("First_Name") + " " + resultSet.getString("Last_Name"));
            }
        } catch (SQLException e) {
            System.err.println("Error displaying employee info: " + e.getMessage());

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
        System.out.print("Enter the last name: ");
        String lastName = scanner.nextLine();

        String query = "SELECT * FROM EMPLOYEES WHERE UPPER(Last_Name) = UPPER(?) ORDER BY First_Name";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);
            resultSet = stmt.executeQuery();

            System.out.println("Employees with last name " + lastName + ":");
            while (resultSet.next()) {
                System.out.println("SSN: " + resultSet.getString("SSN")
                        + ", Name: " + resultSet.getString("First_Name") + " " + resultSet.getString("Last_Name")
                        + ", Birthday: " + resultSet.getString("Birthday")
                        + ", Type: " + resultSet.getString("Employee_Type")
                        + ", Department: " + resultSet.getString("Department_Name"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage());
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
        System.out.print("Enter the birth month (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine();  // Clear buffer

        String query = "SELECT * FROM EMPLOYEES WHERE EXTRACT(MONTH FROM Birthday) = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, month);
            resultSet = stmt.executeQuery();

            System.out.println("Employees born in month " + month + ":");
            while (resultSet.next()) {
                System.out.println("SSN: " + resultSet.getString("SSN")
                        + ", Name: " + resultSet.getString("First_Name") + " " + resultSet.getString("Last_Name")
                        + ", Birthday: " + resultSet.getString("Birthday")
                        + ", Type: " + resultSet.getString("Employee_Type")
                        + ", Department: " + resultSet.getString("Department_Name"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage());
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

        String query = "SELECT * FROM EMPLOYEES WHERE UPPER(Last_Name) BETWEEN UPPER(?) AND UPPER(?) ORDER BY Last_Name, First_Name";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, startLastName);
            stmt.setString(2, endLastName);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("Employees with last names between " + startLastName + " and " + endLastName + ":");
            while (resultSet.next()) {
                System.out.println("SSN: " + resultSet.getString("SSN")
                        + ", Name: " + resultSet.getString("First_Name") + " " + resultSet.getString("Last_Name")
                        + ", Birthday: " + resultSet.getString("Birthday")
                        + ", Type: " + resultSet.getString("Employee_Type")
                        + ", Department: " + resultSet.getString("Department_Name"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage());
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
    private List<String> getTableColumns(String tableName) {
        List<String> columns = new ArrayList<>();
        String query = "SELECT column_name FROM all_tab_columns WHERE table_name = tableName";
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return columns;
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
        while (!input.equals("")) {
            System.out.println("Enter SSN (0000-00-0000): ");
            input = scanner.nextLine();
            String[] columns = input.split("-");
            if (columns.length != 3) {
                input = "";
                System.out.println("Invalid input. Too many hyphens");
            } else if (columns[0].length() != 4 || columns[1].length() != 2 || columns[2].length() != 4) {
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
        List<String> months = new ArrayList<String>(Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));
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
            } else {
                String date = columns[0] + "-" + months.get(months.indexOf(columns[1])) + 1 + "-" + columns[2];
                //TODO: Validate date format/existence
                input = "";
                System.out.println("Invalid input. Incorrect amount of numbers");
            }
        }
        return input;
    }

    public static boolean isDateValid(String date) {
        boolean isValid = false;
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);// dd-mm-yyyy
            df.setLenient(false);
            df.parse(date);
            isValid = true;
        } catch (ParseException e) {
            isValid = false;
        }
        return isValid;
    }

    private void toQuery(String query) {

    }

}
