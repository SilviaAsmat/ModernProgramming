//***************************************************************
//
//  Developer:    Silvia Asmat
//
//  Program #:    8
//
//  File Name:    Program8.java
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
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program8 
{ 
private static final Scanner scanner = new Scanner(System.in);
        private ConnectToOracleDB dbConnection;
    
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
        // Constructor
        public Program8() 
        {
            dbConnection = new ConnectToOracleDB();
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
        public static void main(String[] args) 
        {
            Program8 program = new Program8();
            program.run();

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
        public void run() 
        {
            // Load Oracle Drivers
            dbConnection.loadDrivers();
        
            // Connect to the database
            try(Connection connection = dbConnection.connectDriver()) 
            {
				EmployeeManager employeeManager = new EmployeeManager(connection);
				 System.out.println("Connection established successfully.\n");
		            int choice = -1;
		            while(choice != 10) //TODO: Change for exit conditions
		            {
		            	displayMenu();
		            	choice = getUserMenuChoice();
		            	if(choice != 10)
		            	{
		            		employeeManager.selectMethod(choice);
		            	}
		            	
		            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
        public void displayMenu()
        {
        	System.out.println("================================================================================================");
        	System.out.println("Menu Options");
        	System.out.println("================================================================================================");
        	System.out.println("1. Add an employee to the employee table.");
        	System.out.println("2. Add payroll information for an employee.");
        	System.out.println("3. Increase base salary by 12% for all base-plus-commission employees.");
        	System.out.println("4. If the employee’s birthday is in the current month, add a $200.00 bonus.");
        	System.out.println("5. For all commission employees with gross sales over $9,000.00, add a $200.00 bonus.");
        	System.out.println("6. Display information about an employee.");
        	System.out.println("7. Display the records matching a last name sorted by first name.");
        	System.out.println("8. Display the records of all the employees whose birthdays are in a given month.");
        	System.out.println("9. Display the records of all the employees between two given last names.");
        	System.out.println("10.Exit.");
        	System.out.println("================================================================================================");
        	System.out.println("Enter the option number:");
        	System.out.println("================================================================================================");
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
        public Integer getUserMenuChoice()
        {
        	int choice = -1;
        	while(choice == -1)
        	{
        		try {
                	choice = scanner.nextInt();
    	        	scanner.nextLine(); // clear buffer
    	        	if(choice < 1 || choice > 10)
    	        	{
    	        		choice = -1;
    	        		System.out.println("Invalid Choice. Enter an integer from 1 - 10");
    	        	}
                } catch (InputMismatchException e) {
                    System.out.println("Integers only. Please enter a valid number.");
                    choice = -1;
                    scanner.nextLine(); // consume the invalid input
                }
        	}
        	return choice;
        } 
  
}