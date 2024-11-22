//********************************************************************
//
//  Developer:     Silvia Asmat
//
//  Program #:     One
//
//  File Name:     Program1.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/15/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  Java SE 8
//
//  Description:   Extension of Worker class to override earnings 
//                 based on salary.
//
//********************************************************************
public class SalariedWorker extends Worker{
    private double weeklySalary;
    //***************************************************************
    //
    //  Method:       SalariedWorker
    //
    //  Description:  Constructor for SalariedWorker
    //
    //  Parameters:   String, String, String, double
    //
    //  Returns:      N/A
    //
    //**************************************************************
    SalariedWorker (String firstName, String lastName, String socialSecurityNumber, double weeklySalary){
        super(firstName, lastName, socialSecurityNumber);
        this.weeklySalary = weeklySalary;
    }
    //***************************************************************
    //
    //  Method:       earnings
    //
    //  Description:  Calculates the earnings of the worker
    //
    //  Parameters:   N/A
    //
    //  Returns:      double
    //
    //**************************************************************
    @Override
    public double earnings(){
        return weeklySalary;
    }
    
    @Override
    public String toString(){
        return String.format("Salaried Worker: %s%nWeekly Salary: $%,.2f%n", super.toString(), earnings());
    }
}
