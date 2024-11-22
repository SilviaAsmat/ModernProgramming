//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Five
//
//  File Name:     Fraction.java
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
//  Description:   This class will perform arithmetic operations on fractions.
//                 It will add, subtract, multiply, and divide two fractions.
//                 It will also display the result in fraction format and improper format.
//
//********************************************************************
public class Fraction 
{
    private int numerator;
    private int denominator;
    //***************************************************************
    //
    //  Method:       Fraction
    //
    //  Description:  Constructor for the Fraction class
    //
    //  Parameters:   int, int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public Fraction(int numerator, int denominator) 
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    //********************************************************************
    //
    //  Method:       setNumerator
    //
    //  Description:  This method sets the numerator of the fraction
    //
    //  Parameters:   int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setNumerator(int numerator) 
    {
        this.numerator = numerator;
    }
    //********************************************************************
    //
    //  Method:       setDenominator
    //
    //  Description:  This method sets the denominator of the fraction
    //
    //  Parameters:   int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setDenominator(int denominator) 
    {
        this.denominator = denominator;
    }
    //********************************************************************
    //
    //  Method:       getNumerator
    //
    //  Description:  This method returns the numerator of the fraction
    //
    //  Parameters:   None
    //
    //  Returns:      int
    //
    //**************************************************************
    public int getNumerator() 
    {
        return numerator;
    }
    //****************************************************************
    //
    //  Method:       getDenominator
    //
    //  Description:  This method returns the denominator of the fraction
    //
    //  Parameters:   None
    //
    //  Returns:      int 
    //
    //**************************************************************
    public int getDenominator() 
    {
        return denominator;
    }
    //****************************************************************
    //
    //  Method:      addFraction 
    //
    //  Description: This method adds two fractions
    //
    //  Parameters:  Fraction
    //
    //  Returns:     Fraction
    //
    //**************************************************************
    public Fraction addFraction(Fraction fraction) 
    {
        int newNumerator = (numerator * fraction.denominator) + (fraction.numerator * denominator);
        int newDenominator = denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }
    //****************************************************************
    //
    //  Method:      subtractFraction 
    //
    //  Description: This method subtracts two fractions
    //
    //  Parameters:  Fraction
    //
    //  Returns:     Fraction
    //
    //**************************************************************
    public Fraction subtractFraction(Fraction fraction) 
    {
        int newNumerator = (numerator * fraction.denominator) - (fraction.numerator * denominator);
        int newDenominator = denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }
    //****************************************************************
    //
    //  Method:      multiplyFraction
    //
    //  Description: This method multiplies two fractions
    //
    //  Parameters:  Fraction
    //
    //  Returns:     Fraction
    //
    //**************************************************************
    public Fraction multiplyFraction(Fraction fraction) 
    {
        int newNumerator = numerator * fraction.numerator;
        int newDenominator = denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }
    //****************************************************************
    //
    //  Method:      divideFraction
    //
    //  Description: This method divides two fractions
    //
    //  Parameters:  Fraction
    //
    //  Returns:     Fraction
    //
    //**************************************************************
    public Fraction divideFraction(Fraction fraction) 
    {
        int newNumerator = numerator * fraction.denominator;
        int newDenominator = denominator * fraction.numerator;
        return new Fraction(newNumerator, newDenominator);
    }
    //****************************************************************
    //
    //  Method:      toString
    //
    //  Description: This method returns the fraction in string format
    //
    //  Parameters:  None
    //
    //  Returns:     String
    //
    //**************************************************************
    public String toString() 
    {
        return numerator + " / " + denominator;
    }
    //****************************************************************
    //
    //  Method:      displayImproper
    //
    //  Description: This method displays the fraction in improper format
    //
    //  Parameters:  None
    //
    //  Returns:     N/A
    //
    //**************************************************************
    public void displayImproper()
    {
        int wholeNumber = 0;
        wholeNumber = numerator / denominator;
        int newNumerator = 0;
        newNumerator = numerator % denominator;
        if(wholeNumber == 0 )
        {
            System.out.println("Result in Improper Format: " + newNumerator + " / " + denominator);
        }
        else if(newNumerator == 0)
        {
            System.out.println("Result in Improper Format: " + wholeNumber);
        }
        else
        {
            System.out.println("Result in Improper Format: " + wholeNumber + " " + newNumerator + " / " + denominator);
        }
    }
    //****************************************************************
    //
    //  Method:      calculateGcd
    //
    //  Description: This method calculates the greatest common divisor
    //
    //  Parameters:  int, int
    //
    //  Returns:     int
    //
    //**************************************************************
    private int calculateGcd(int numerator, int denominator)
    {
        if (denominator != 0)
        {
            return calculateGcd(denominator, numerator % denominator);
        }
        else
        {
            return numerator;
        }
    }
    //****************************************************************
    //
    //  Method:      reduceFraction
    //
    //  Description: This method reduces the fraction to its simplest form
    //
    //  Parameters:  None
    //
    //  Returns:     N/A
    //
    //**************************************************************
    public void reduceFraction()
    {
        int gcd = calculateGcd(numerator, denominator);
        numerator = numerator / gcd;
        denominator = denominator / gcd;
    }
} 
