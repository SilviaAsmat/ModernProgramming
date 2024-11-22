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
//  Description:   Worker hierarchy test class. 
//
//********************************************************************

public class HourlyWorker extends Worker {
    private double wage;
    private int hours;
    //***************************************************************
    //
    //  Method:       HourlyWorker
    // 
    //  Description:  The constructor method of the program
    //
    //  Parameters:   String, String, String, double, int
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    HourlyWorker(String firstName, String lastName, String socialSecurityNumber, double wage, int hours) {
        super(firstName, lastName, socialSecurityNumber);
        this.wage = wage;
        this.hours = hours;
    }
    //***************************************************************
    //
    //  Method:       earnings
    // 
    //  Description:  Calculates the earnings of the worker based on hours
    //
    //  Parameters:   N/A
    //
    //  Returns:      double 
    //
    //**************************************************************
    @Override
    public double earnings(){
        double pay = 0;
        if (hours <= 40){
            pay = hours * wage;
        } 
        else{
            pay = 40 * wage + (hours - 40) * wage * 1.5;
        }
        return pay;
    }
    @Override
    public String toString(){
        return String.format("Hourly Worker: %s%nHourly Wage: $%,.2f%nHours Worked: %d%n", super.toString(), wage, hours);
    }
}
