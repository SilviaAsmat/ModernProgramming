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
//  Description:   Extension of Worker class to include
//                 commission.
//
//********************************************************************
public class CommissionWorker extends Worker {
    private double grossSales;
    private double commissionRate;
//***************************************************************
//
//  Method:       CommissionWorker
//
//  Description:  Constructor for CommissionWorker
//
//  Parameters:   String, String, String, double, double
//
//  Returns:      N/A
//
//**************************************************************
    CommissionWorker(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }
//***************************************************************
//
//  Method:       earnings
//
//  Description:  Calculate earnings
//
//  Parameters:   N/A
//
//  Returns:      double
//
//**************************************************************
    @Override
    public double earnings(){
        return grossSales * commissionRate;
    }

    @Override
    public String toString(){
        return String.format("Commission Worker: %s%nGross Sales: $%,.2f%nCommission Rate: %.2f%n", super.toString(), grossSales, commissionRate);
    }
}
