//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Five
//
//  File Name:     User.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      11/04/2024
//
//  Java Version:  17.0.12
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 7
//
//  Description:   This class will prompt the user to enter two fractions.
//                 It will then prompt the user to enter an operation to perform
//                 on the fractions. The result of the operation will be displayed.
//
//********************************************************************
import java.util.Scanner;

public class User 
{
    private Fraction fraction1;
    private Fraction fraction2;
    private Fraction result;
    //***************************************************************
    //
    //  Method:       User
    //
    //  Description:  Constructor for the User class
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public User ()
    {
        result = null;
    }
    //***************************************************************
    //
    //  Method:       performUserOperation
    //
    //  Description:  This method prompts the user to enter two fractions
    //                and an operation to perform on the fractions.
    //
    //  Parameters:   Scanner
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void performUserOperation(Scanner input)
    {
        int operation;
        boolean exit;
        exit = runFractionMethod(5, input);
        while(exit == false)
        {
            result = null;
            displayOperations();  
            operation = getIntFromUser(input);
            if(operation < 0 || operation > 5)
            {
                System.out.println("Invalid operation number.");
            }
            else 
            {
                exit = runFractionMethod(operation, input);
                   
            }    
        }
    }
    //***************************************************************
    //
    //  Method:       displayOperations
    //
    //  Description:  This method displays the operations that the user can perform
    //                on the fractions.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void displayOperations()
    {
        System.out.println("Enter the operation number you would like to perform: ");
        System.out.println("Operations: \n0 - Add\n1 - Subtract\n2 - Multiply\n3 - Divide\n4 - Display Original Fractions\n5 - Enter New Fractions (Enter 0/0 for both to exit)");
    }
    //***************************************************************
    //
    //  Method:       runFractionMethod
    //
    //  Description:  This method runs the fraction method based on the operation
    //                number entered by the user.
    //
    //  Parameters:   int, Scanner
    //
    //  Returns:      boolean
    //
    //**************************************************************
    public boolean runFractionMethod(int operation, Scanner input)
    {
        boolean exit = false;
        if(operation == 0)
        {
            System.out.println("Adding");
            result = fraction1.addFraction(fraction2);
        }
        else if(operation == 1)
        {
            System.out.println("Subtracting");
            result = fraction1.subtractFraction(fraction2);
        }
        else if(operation == 2)
        {
            System.out.println("Multiplying");
            result = fraction1.multiplyFraction(fraction2);
        }
        else if(operation == 3)
        {
            System.out.println("Dividing");
            result = fraction1.divideFraction(fraction2);
        }
        else if(operation == 4)
        {
            System.out.println("\n******************************************************");
            System.out.println("Fraction 1: " + fraction1.toString());
            System.out.println("Fraction 2: " + fraction2.toString());
            System.out.println("******************************************************\n");
        }
        else if(operation == 5)
        {
            System.out.println("Enter Fraction 1: ");
            fraction1 = enterFraction(input);
            System.out.println("Enter Fraction 2: ");
            fraction2 = enterFraction(input);
            if(fraction1.getDenominator() == 0 && fraction1.getNumerator() == 0 && fraction2.getDenominator() == 0 && fraction2.getNumerator() == 0)
            {
                exit = true;
            } 
        }
        if (result != null)
        {
            System.out.println("\n******************************************************");
            result.reduceFraction();
            System.out.println("Result in Fraction Format: " + result.toString());
            result.displayImproper();
            System.out.println("******************************************************\n");
        }
        return exit;
    }
    //***************************************************************
    //
    //  Method:       enterFraction
    //
    //  Description:  This method prompts the user to enter the numerator
    //                and denominator of a fraction.
    //
    //  Parameters:   Scanner
    //
    //  Returns:      Fraction
    //
    //**************************************************************
    public Fraction enterFraction(Scanner input)
    {
        int denominator;
        int numerator;
        boolean zeroDenominator = true;
        Fraction fraction = null;
        
        System.out.println("Enter the numerator of the fraction: ");
        numerator = getIntFromUser(input);
        while(zeroDenominator == true)
        {    
            System.out.println("Enter the denominator of the fraction: ");
            denominator = getIntFromUser(input);
            if(numerator != 0 && denominator == 0)
            {
                System.out.println("Denominator cannot be 0.");
            }
            else
            {
                fraction = new Fraction(numerator, denominator);
                zeroDenominator = false;
            }
        }
        return fraction;
    }// end enterFraction
    //***************************************************************
    //
    //  Method:       getIntFromUser
    //
    //  Description:  This method gets an integer from the user.
    //
    //  Parameters:   Scanner
    //
    //  Returns:      int
    //
    //**************************************************************
    public int getIntFromUser(Scanner input)
    {
        int number = 0;
        boolean flag = true;
        while(flag == true)
        {
            if (!input.hasNextInt())
            {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }
            else
            {
                number = input.nextInt();
                flag = false;
            }
        }
        return number;
    }
}// end User class
