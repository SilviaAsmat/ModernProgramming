//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Two
//
//  File Name:     Program2.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      10/05/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 4
//
//  Description:   Server class that listens on port 4301 and
//                 takes input from the client, validates it, and
//                 sends the sum, mean, and standard deviation back to the client.
//
//********************************************************************
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server
{
    private final ArrayList<Integer> integersBetweenInputsInclusive = new ArrayList<>();
    String[] inputArray = null;
    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the project
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public static void main(String[] args)
	{
		Server serverObj = new Server();
        serverObj.useSocket();
	}
    //********************************************************************
    //
    //  Method:       useSocket
    //
    //  Description:  This method listens on port 4301 and accepts
    //                connections from clients. It reads the input from
    //                the client, validates it, and sends the result back
    //                to the client.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //********************************************************************
    public void useSocket()
    {
        try (ServerSocket serverSocket = new ServerSocket(4301)) {
            //serverSocket.bind(new InetSocketAddress(LOOPBACK_ADDRESS, 4301));
            while (true)
            {
                System.out.println("Listening on port 4301, CRTL-C to quit ");
                Socket socket = serverSocket.accept();
                try {
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Connection accepted.");                
                    String clientInput;
                    clientInput = input.readLine();
                    while ((clientInput != null) && !(clientInput.contains("Bye!")))
                    {   
                        System.out.println("Server Received: " + clientInput);
                        if(validateInput(clientInput, output))
                        {
                            write(output, calcStats());
                        }
                        clientInput = input.readLine();
                    }
                    System.out.print("Closing Client socket.\n\n");
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Exception caught when trying to listen on port 4301");
                }
            }// end while
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4301.");
            System.exit(1);
        }
    }
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
    //***************************************************************
    //
    //  Method:       findNumsBetween
    //
    //  Description:  This method finds all the numbers between the
    //                first two integers, inclusive, that are either
    //                odd or even, depending on the third integer.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void findNumsBetween()
    {
        int firstInt = Integer.parseInt(inputArray[0]);
        int secondInt = Integer.parseInt(inputArray[1]);
        int thirdInt = Integer.parseInt(inputArray[2]);
        for (int i = firstInt; i <= secondInt; i++)
        {
            if (thirdInt == 3 && i % 2 != 0)
            {integersBetweenInputsInclusive.add(i);
            }
            else if (thirdInt == 2 && i % 2 == 0)
            {integersBetweenInputsInclusive.add(i);
            }
        }
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
        findNumsBetween();
        sum = calcSum();
        result = "Sum: " + sum + " Mean: " + calcMean(sum) + " Standard Deviation: " + calcStandardDeviation(sum, mean);
        integersBetweenInputsInclusive.clear();
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
         for (int i : integersBetweenInputsInclusive)
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
        mean = sum / integersBetweenInputsInclusive.size();
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
        for (int i : integersBetweenInputsInclusive)
        {
            sumOfSquares += Math.pow(i - mean, 2);
        }
        standardDeviation = Math.sqrt(sumOfSquares / integersBetweenInputsInclusive.size());
        return standardDeviation;
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
        if (inputArray.length != 3)
        {
            write(output, "Invalid amount of arguments. Please enter THREE positive integers separated by commas or spaces.");
            isValid = false;
        }
        // If isValid is false, no need to check the rest of the conditions
        if (isValid) 
        {
            try {
                for (String value : inputArray) 
                {
                    // Check if the input is a positive integer
                    if ( Double.parseDouble(value) % 1 != 0)
                    {
                        write(output, "Non-Integer. Please enter three positive integers separated by commas or spaces.");
                        isValid = false;
                    }
                }
            } catch (NumberFormatException e) {
                write(output, "Invalid input. Please enter three positive integers separated by commas or spaces.");
                isValid = false;
            }
        } 
        // If isValid is false, no need to check the rest of the conditions
        if (isValid)
        {
                int firstInt = Integer.parseInt(inputArray[0]);
                int secondInt = Integer.parseInt(inputArray[1]);
                int thirdInt = Integer.parseInt(inputArray[2]);
                if (firstInt >= secondInt)
                {
                    write(output, "First integer must be less than the second. Please enter three positive integers separated by commas or spaces.");
                    isValid = false;
                }
                else if (thirdInt != 2 && thirdInt != 3)
                {
                    write(output, "Third integer must be 2 or 3. Please enter three positive integers separated by commas or spaces.");
                    isValid = false;
                }
                else if (firstInt < 1 || secondInt < 1 || thirdInt < 1)
                {
                    write(output, "All the integers must be greater than zero. Please enter three positive integers separated by commas or spaces.");
                    isValid = false;
                }    
        }
        return isValid;
    }// end validateInput
}