//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     ProcessEachClient.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      10/14/2024
//
//  Java Version:  17.0.12
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 5
//
//  Description:   This program processes each client that connects to the server
//                 and calculates the sum, mean, and standard deviation of the prime numbers
//
//********************************************************************

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ProcessEachClient implements Runnable
{
	protected Socket clientSocket = null;
	protected String serverText = null;
    private String[] inputArray;
	private int[] primeNums;
    //***************************************************************
    //
    //  Method:       ProcessEachClient
    //
    //  Description:  Constructor
    //
    //  Parameters:   Socket
    //
    //  Returns:      N/A
    //
    //**************************************************************

	public ProcessEachClient(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}


	//***************************************************************
    //
    //  Method:       run
    // 
    //  Description:  The overriden method of Runnable
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	@Override
	public void run()
	{
		try {
			OutputStream output = clientSocket.getOutputStream();
			InputStream input = clientSocket.getInputStream();

			InputStreamReader inputStream = new InputStreamReader(input);

			BufferedReader bufferedReader = new BufferedReader(inputStream);
			String message = null;
			message = bufferedReader.readLine();
			System.out.println("TCP Server says: Message received from client: " + message);
			Thread.sleep(1000);
			PrintWriter writer = new PrintWriter(output, true);

			while ((message != null) && !(message.contains("Bye!")))
            { 
				if (validateInput(message, writer))
				{
					String stats = calcStats();
					write(writer, stats);
				}
				message = bufferedReader.readLine();
			}
			

		}
		catch (IOException e)
		{
			System.out.println("IOException caught in ProcessEachClient: " + e);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException caught in ProcessEachClient: " + e);
		}

	}

   //***************************************************************
    //
    //  Method:       findPrimeNumsBetween
    //
    //  Description:  This method finds all the numbers between the
    //                first two integers, inclusive, that are prime.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public int[] findPrimeNumsBetween() {
        return IntStream.rangeClosed(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]))
            .filter(i -> isPrime(i))
            .toArray();
    }

    //********************************************************************
    //
    //  Method:       calcStats
    //
    //  Description:  This method calls the findNumsBetween, sum, mean, and standard
    //                deviation methods and returns the result.
    //
    //  Parameters:   N/A
    //
    //  Returns:      String result
    //
    //********************************************************************
    public String calcStats()
    {
        String result;
        double sum;
        double mean = 0;
        primeNums = findPrimeNumsBetween();
        sum = calcSum();
		String primeNumsString = Arrays.toString(primeNums);
        result = "Prime Numbers: " + primeNumsString  + " Sum: " + sum + " Mean: " + calcMean(sum) + " Standard Deviation: " + calcStandardDeviation(sum, mean);
        return result;
    }
    //********************************************************************
    //
    //  Method:       calcSum
    //
    //  Description:  This method calculates the sum of the numbers
    //                from a list of integers.
    //
    //  Parameters:   N/A
    //
    //  Returns:      double sum
    //
    //********************************************************************
    public Double calcSum()
    {
        double sum = 0;
         for (int i : primeNums)
         {
              sum += i;
         }
         return sum; 
    }
    //********************************************************************
    //  
    //  Method:       calcMean
    //
    //  Description:  This method calculates the mean.
    //
    //  Parameters:   double
    //
    //  Returns:      double mean
    //
    //********************************************************************
    public Double calcMean(double sum)
    { 
        double mean;
        mean = sum / primeNums.length;
        return mean;
    }
    //********************************************************************
    //
    //  Method:       calcStandardDeviation
    //
    //  Description:  This method calculates the standard deviation.
    //
    //  Parameters:   double, double 
    //
    //  Returns:      double standardDeviation
    //
    //********************************************************************
    public Double calcStandardDeviation(double sum, double mean)
    {
        double standardDeviation;
        double sumOfSquares = 0;
        for (int i : primeNums)
        {
            sumOfSquares += Math.pow(i - mean, 2);
        }
        standardDeviation = Math.sqrt(sumOfSquares / primeNums.length);
        return standardDeviation;
    }

   //***************************************************************
   //
   //  Method:       isPrime (Non Static)
   // 
   //  Description:  This method determines whether a positive integer is
   //                a prime number.  It returns true if the integer a prime
   //                number, and false if it is not.
   //
   //  Parameters:   A Positive Integer
   //
   //  Returns:      boolean
   //
   //**************************************************************
   public boolean isPrime(int number)
   {  
	  boolean rtnValue = true;
	  
      if (number < 2)            // Integers < 2 cannot be prime
         rtnValue = false;
      else if (number == 2)      // Special case: 2 is the only even prime number
         rtnValue = true;
      else if (number % 2 == 0)  // Other even numbers are not prime
         rtnValue = false;
      else
      {  // Test odd divisors up to the square root of number
         // If any of them divide evenly into it, then number is not prime
         for (int divisor = 3; divisor <= Math.sqrt(number); divisor += 2)
         {
		     if (number % divisor == 0)
                rtnValue = false;
         }
      }
      
      return rtnValue;
   }

    //********************************************************************
    //
    //  Method:       validateInput
    //
    //  Description:  This method validates the input from the client
    //                to ensure that it is in the correct format.
    //
    //  Parameters:   String, PrintWriter
    //
    //  Returns:      Boolean isValid
    //
    //********************************************************************
    public Boolean validateInput(String input, PrintWriter output)
    {   
        boolean isValid = true;

		inputArray = input.replaceAll("^[\\s,]+", "").split("[\\s,]+");

		if (inputArray.length != 2)
		{
			write(output, "Error: Incorrct Amount of Arguments. Please enter two integers separated by commas or spaces.");
			isValid = false;
		}
		else
		{
			double num1 =  Double.parseDouble(inputArray[0]);
			double num2 = Double.parseDouble(inputArray[1]);

			if (num1 % 1 != 0 || num2 % 1 != 0)
			{
				write(output, "Error: Not Integers. Please enter two integers separated by commas or spaces.");
				isValid = false;
			}
			else if (num1 < 0 || num2 < 0)
			{
				write(output, "Error: Negative Numbers. Please enter two positive integers separated by commas or spaces.");
				isValid = false;
			} 
			else if (num1 > num2)
			{
				write(output, "Error: First Number Greater. Please enter two integers where the first number is less than the second number.");
				isValid = false;
			}
		}

        return isValid;
    }// end validateInput

	//***************************************************************
    //
    //  Method:       write
    // 
    //  Description: Writes a message to the client
	//
    //  Parameters:   PrintWriter, String
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public void write(PrintWriter output, String message)
	{
		System.out.println("Server Sending: " + message);
		output.println(message);
	}
}
