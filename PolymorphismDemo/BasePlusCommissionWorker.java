//********************************************************************
//
//  Developer:     Silvia Asmat
//
//  Program #:     One
//
//  File Name:     BasePlusCommissionWorker.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/15/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  Java SE 8
//
//  Description:   Extension of CommissionWorker class to include
//                 base salary
//
//********************************************************************
public class BasePlusCommissionWorker extends CommissionWorker{
    private double baseSalary;
    //***************************************************************
    //
    //  Method:       BasePlusCommissionWorker
    //
    //  Description:  Constructor
    //
    //  Parameters:   String, String, String, double, double, double
    //
    //  Returns:      N/A
    //
    //**************************************************************
    BasePlusCommissionWorker(String firstName, String lastName, String socialSecurityNumber, double grossSales, 
        double commissionRate, double baseSalary){
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }
    //**************************************************************
    //
    //  Method:       earnings
    //
    //  Description:  Calculate earnings with base salary
    //                and commission
    //
    //  Parameters:   N/A
    //
    //  Returns:      double
    //
    //**************************************************************
    @Override
    public double earnings(){
        return baseSalary + super.earnings();
    }
    //**************************************************************
    //
    //  Method:       getBaseSalary
    //
    //  Description:  Get base salary
    //
    //  Parameters:   N/A
    //
    //  Returns:      double
    //
    //**************************************************************
    public double getBaseSalary(){
        return baseSalary;
    }
    //**************************************************************
    //
    //  Method:       setBaseSalary
    //
    //  Description:  Set base salary
    //
    //  Parameters:   double
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString(){
        return String.format("%s%nBase Salary: $%,.2f%n", super.toString(), getBaseSalary());
    }
}
