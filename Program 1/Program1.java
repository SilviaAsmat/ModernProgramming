//********************************************************************
//
//  Developer:     Instructor
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

public class Program1 
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
	  // Create an object of the main class and use it to call
	  // the non-static developerInfo method
	  Program1 obj = new Program1();
	  obj.developerInfo();

      // create subclass objects                                          
      SalariedWorker salariedWorker =                                 
         new SalariedWorker("John", "Smith", "111-11-1111", 800.00);
		 
      HourlyWorker hourlyWorker1 =                                     
         new HourlyWorker("Karen", "Price", "222-22-2222", 16.75, 40); 
		 
	   HourlyWorker hourlyWorker2 =
     	  new HourlyWorker("John", "Doe", "666-66-6666", 16.75, 50);

      CommissionWorker commissionWorker =                             
         new CommissionWorker("Sue", "Jones", "333-33-3333", 10000, .06);
		 
      BasePlusCommissionWorker basePlusCommissionWorker =             
         new BasePlusCommissionWorker("Bob", "Lewis", "444-44-4444", 5000, .04, 300);                  

      PieceWorker pieceWorker =
	     new PieceWorker("Rick", "Bridges", "555-55-5555", 2.25, 400);

      // Workers processed individually
      System.out.println("Workers processed individually:");

      System.out.printf("%n%s%n%s: $%,.2f%n%n", 
         salariedWorker, "Earned", salariedWorker.earnings());
      
      System.out.printf("%s%n%s: $%,.2f%n%n",
         hourlyWorker1, "Earned", hourlyWorker1.earnings());
      
      System.out.printf("%s%n%s: $%,.2f%n%n",
    	         hourlyWorker2, "Earned", hourlyWorker2.earnings());
      
      System.out.printf("%s%n%s: $%,.2f%n%n",
         commissionWorker, "Earned", commissionWorker.earnings());
      
      System.out.printf("%s%n%s: $%,.2f%n%n", 
         basePlusCommissionWorker, 
         "earned", basePlusCommissionWorker.earnings());
      
      System.out.printf("%s%n%s: $%,.2f%n%n",
    		  pieceWorker, "Earned", pieceWorker.earnings());


      System.out.println("Workers processed polymorphically:\n");
      
      // Write more code here to this program work as expected.
      Worker[] workers = new Worker[6];
      workers[0] = salariedWorker;
      workers[1] = hourlyWorker1;
      workers[2] = hourlyWorker2;
      workers[3] = commissionWorker;
      workers[4] = basePlusCommissionWorker;
      workers[5] = pieceWorker;
      obj.polymorphicWorkerDemo(workers);	  
   } // End of the main method
   //***************************************************************
   //
   //  Method:       polymorphicWorkerDemo
   //
   //  Description:  Prints the worker information and earnings polymorphically
   //
   //  Parameters:   Worker array
   //
   //  Returns:      N/A
   //
   //***************************************************************
   public void polymorphicWorkerDemo(Worker[] workers){
      for (Worker worker : workers){
         if (worker instanceof BasePlusCommissionWorker){
            BasePlusCommissionWorker basePlusWorker = (BasePlusCommissionWorker) worker;
            basePlusWorker.setBaseSalary(1.15 * basePlusWorker.getBaseSalary());
         }

         String result = worker.toString();
         System.out.println(result);
      }
   }
   //***************************************************************
   //
   //  Method:       developerInfo (Non Static)
   // 
   //  Description:  The developer information method of the program
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public void developerInfo(){
      System.out.println("Name:     Silvia Asmat");
      System.out.println("Course:   COSC 4301 Modern Programming");
      System.out.println("Program:  One");
	  System.out.println("Due Date: 9/15/2024\n");

   } // End of the developerInfo method

} // End of class Program1
