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
//  Description:   This program creates a client that connects to a server
//                 at the specified address and port number. The client sends
//                 a message to the server and receives a response.
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
        clientObj.developerInfo();
        clientObj.useSocket();
    }
    //***************************************************************
    //
    //  Method:       useSocket
    //
    //  Description:  This method creates a socket and connects to the server
    //                at the specified address and port number. It then sends
    //                a message to the server and receives a response.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void useSocket() 
    {
        try {
            String address = "127.0.0.1";
            try (Socket socket = new Socket(address, 4301);
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) 
                {
                    String userInput;
                    System.out.print("Enter three positive integers or \"Quit\" to quit: ");
                    userInput = stdIn.readLine();
                    while ((userInput != null) && !(userInput.contains("Quit")))
                    {
                        writeClient(output, userInput);
                        System.out.println("Client receiving from server: " + input.readLine());
                        
                        System.out.print("Enter three positive integers or \"Quit\" to quit: ");
                        userInput = stdIn.readLine();
                    }
                    System.out.print("Closing socket.");
                }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port 4301 or listening for a connection");
        }
    }
    //***************************************************************
    //
    //  Method:       writeClient
    // 
    //  Description:  
    //
    //  Parameters:   
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
       System.out.println("Program:  Two");
	   System.out.println("Due Date: 10/05/2024\n");
    } // End of the developerInfo method
} // End of the class

