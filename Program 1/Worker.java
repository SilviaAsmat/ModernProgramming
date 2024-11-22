//********************************************************************
//
//  Developer:     Instructor
//
//  Program #:     One
//
//  File Name:     Worker.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      9/15/2024
//
//  Instructor:    Fred Kumi 
//
//  Description:   Worker abstract class.  Please do NOT modify
//                 this class.  If you do, you will not receive
//                 credit for this program.
//
//********************************************************************
public abstract class Worker extends Object
{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    // Worker constructor one
    public Worker()
    {
	   this.firstName = "";
	   this.lastName = "";
	   this.socialSecurityNumber = "";
    } // end of constructor one
   
    // Worker constructor two
    public Worker(String firstName, String lastName, String socialSecurityNumber)
    {
	   if (!firstName.matches("[a-zA-Z]+"))
    	  throw new IllegalArgumentException(
    			  "First name should only contain letters");
 
	   if (!lastName.matches("[a-zA-Z]+"))
    	  throw new IllegalArgumentException(
    			  "Last name should only contain letters");
      
	   if (!socialSecurityNumber.matches("\\d{3}-\\d{2}-\\d{4}"))
    	  throw new IllegalArgumentException(
    			  "Social Security Number does not match the following format XXX-XX-XXXX");
	  
      this.firstName = firstName;                                    
      this.lastName = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;         
       
    } // end of constructor two

    // return first name
    public String getFirstName()
    {
       return firstName;
    } 

    // return last name
    public String getLastName()
    {
       return lastName;
    } 

    // return social security number
    public String getSocialSecurityNumber()
    {
       return socialSecurityNumber;
    } 

    // return String representation of Worker object
    @Override
    public String toString()
    {
       return String.format("%s %s%nSocial Security Number: %s", 
          firstName, lastName, socialSecurityNumber);
    } 

    // abstract method must be overridden by concrete subclasses
    public abstract double earnings(); // no implementation here
   
} // end abstract class Worker

