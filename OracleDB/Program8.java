import java.util.Scanner;

public class Program8 {
    private static ConnectToOracleDB dbConnection;
    private static Scanner scanner;

    public static void main(String[] args) {
        dbConnection = new ConnectToOracleDB();
        scanner = new Scanner(System.in);

        try {
            dbConnection.loadDrivers();
            dbConnection.connectDriver();

            boolean exit = false;
            while (!exit) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        addPayrollInformation();
                        break;
                    case 3:
                        increaseBaseSalary();
                        break;
                    case 4:
                        addBirthdayBonus();
                        break;
                    case 5:
                        addCommissionBonus();
                        break;
                    case 6:
                        displayEmployeeInfo();
                        break;
                    case 7:
                        displayRecordsByLastName();
                        break;
                    case 8:
                        displayRecordsByBirthdayMonth();
                        break;
                    case 9:
                        displayRecordsBetweenLastNames();
                        break;
                    case 10:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            dbConnection.closeDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add an employee to the employee table.");
        System.out.println("2. Add payroll information for an employee.");
        System.out.println("3. Increase base salary by 12% for all base-plus-commission employees.");
        System.out.println("4. If the employee’s birthday is in the current month, add a $200.00 bonus.");
        System.out.println("5. For all commission employees with gross sales over $9,000.00, add a $200.00 bonus.");
        System.out.println("6. Display information about an employee.");
        System.out.println("7. Display the records matching a last name sorted by first name.");
        System.out.println("8. Display the records of all the employees whose birthdays are in a given month.");
        System.out.println("9. Display the records of all the employees between two given last names.");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee() {
        // Implementation for adding an employee
    }

    private static void addPayrollInformation() {
        // Implementation for adding payroll information
    }

    private static void increaseBaseSalary() {
        // Implementation for increasing base salary
    }

    private static void addBirthdayBonus() {
        // Implementation for adding birthday bonus
    }

    private static void addCommissionBonus() {
        // Implementation for adding commission bonus
    }

    private static void displayEmployeeInfo() {
        // Implementation for displaying employee information
    }

    private static void displayRecordsByLastName() {
        // Implementation for displaying records by last name
    }

    private static void displayRecordsByBirthdayMonth() {
        // Implementation for displaying records by birthday month
    }

    private static void displayRecordsBetweenLastNames() {
        // Implementation for displaying records between last names
    }
}