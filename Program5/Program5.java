//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Five
//
//  File Name:     Program5.java
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
//  Description:   This class will serve as the main class of the program.
//                 It will call the developerInfo method and the startProgram method.
//
//********************************************************************
import java.util.Scanner;
public class Program5
{
    //***************************************************************
    //
    //  Method:       main
    //
    //  Description:  The main method of the program
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public static void main(String[] args)
    { 
        Program5 program = new Program5();
        program.developerInfo();
        program.startProgram();
    }
    //***************************************************************
    //
    //  Method:       startProgram
    //
    //  Description:  This method calls the getUserInput method from the User class
    //                which will prompt the user to enter two fractions and an operation
    //                to perform on the fractions.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void startProgram()
    {
        User user = new User();
        Scanner input = new Scanner(System.in);
        user.performUserOperation(input);
        
    }
    //***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
	//                This method and comments must be included in all
	//                programming assignments.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:   Silvia Asmat");
       System.out.println("Course:   COSC 4301 - Modern Programming");
       System.out.println("Program:  Five");
	   System.out.println("Due Date: 11/04/2024\n");

    } // End of the developerInfo method
}