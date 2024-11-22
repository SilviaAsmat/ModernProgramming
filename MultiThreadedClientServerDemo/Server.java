//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     Server.java
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
//  Description:   This program is a server that listens for incoming client connections
//                  and processes each client that connects to the server
//
//********************************************************************
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
   protected Socket clientSocket = null;
    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public static void main(String[] args)
	{
		Server obj = new Server();
		int coreCount;
      	coreCount = Runtime.getRuntime().availableProcessors();
      	obj.runDemo(coreCount);
		
	}	
	//***************************************************************
    //
    //  Method:       runDemo (Non Static)
    // 
    //  Description:  The main processor method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void runDemo(int coreCount)
    {
		int port = 4301;
		ServerSocket serverSocket = null;
		
		try {
			// Create TCP Server Socket
	       	serverSocket = new ServerSocket(port);
	       	System.out.println("[TCP Server says] TCP Server created on port " + port);
		}
		catch (IOException e)
		{
			System.out.println("[TCP Server says] Error: TCP Server cannot be created on port " + port);
			System.exit(1);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
		// Start an endless loop
    try {
        while (true)
        {
            clientSocket = null;
            // Start listening to incoming client request (blocking function)
            System.out.println("\n[TCP Server says] Waiting for connection.....");
            try {
                clientSocket = serverSocket.accept();    
                // Create a new thread for each incoming message
                executorService.execute(new ProcessEachClient(clientSocket));
				System.out.println("[TCP Server says] New client connected.");
            } catch (IOException e) {
                System.out.println("[TCP Server says] Error: " + e.getMessage());
            }
        }
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					System.out.println("[TCP Server says] Error closing server socket: " + e.getMessage());
				}
			}
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
}