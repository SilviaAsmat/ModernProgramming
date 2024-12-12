//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Seven
//
//  File Name:     Operand.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      12/11/2024
//
//  Java Version:  23.0.1
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 8 & 9
//
//  Description:   This class is responsible for handling the operands
//
//********************************************************************
import java.security.SecureRandom;

public class UserFeedbackMessage 
{
    private final SecureRandom random = new SecureRandom();
    //***************************************************************
    //
    //  Method:       getCorrectResponse
    //
    //  Description:  Returns a random correct response message
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //**************************************************************
    public String getCorrectResponse()
    { 
        // Return a random correct response message
        String[] responses = 
        {
            "Excellent!",
            "Very good!",
            "Nice work!",
            "Way to go!",
            "Keep up the good work!"
        };
        return responses[random.nextInt(responses.length)];
    }
    //***************************************************************
    //
    //  Method:       getIncorrectResponse
    //
    //  Description:  Returns a random incorrect response message
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //**************************************************************
    public String getIncorrectResponse()
    {
        String[] incorrectResponses = 
        {
            "That is incorrect!", 
            "No. Please try again!", 
            "Wrong, Try once more!", 
            "No. Don’t give up!", 
            "Incorrect. Keep trying!"
        };
        return incorrectResponses[random.nextInt(incorrectResponses.length)];
    }
}
