//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     Client.java
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
//  Description:   This program is a client that connects to a server
//
//********************************************************************

import java.io.*;
import java.net.*;

public class Client
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
    	Client clientObj = new Client();
        try {
        	InetAddress address = InetAddress.getByName("localhost");
                try (Socket socket = new Socket(address, 4301)) {
                    System.out.println("Client connected to server.");
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // For reading from the user on the console/keyboard
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    String userInput;
                    System.out.print("Enter two integers separated by commas or spaces or \"Quit\" to quit: ");
                    userInput = stdIn.readLine();
                    while ((userInput != null) && !(userInput.contains("Quit")))
                    {
                        clientObj.writeClient(output, userInput);
                        System.out.println("Client receiving from server: " + input.readLine());
                        
                        System.out.print("Enter two integers separated by commas or spaces or \"Quit\" to quit: ");
                        userInput = stdIn.readLine();
                    }
                }
            System.out.print("Closing socket.");
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port 4301 or listening for a connection");
        }
    }
    //***************************************************************
    //
    //  Method:       writeClient
    // 
    //  Description:  Writes to the client
    //
    //  Parameters:   PrintWriter, String
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void writeClient(PrintWriter output, String message)
	{
        System.out.println("Sending: " + message);
        output.println(message);
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
       System.out.println("Name:     Silvia Asmat");
       System.out.println("Course:   COSC 4301 - Modern Programming");
       System.out.println("Program:  Three");
	   System.out.println("Due Date: 10/14/2024\n");
    } // End of the developerInfo method
}