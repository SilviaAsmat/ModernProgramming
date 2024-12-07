import java.security.SecureRandom;

public class UserFeedbackMessage {
    private final SecureRandom random = new SecureRandom();
    
    public String getCorrectResponse()
    { 
        // Return a random correct response message
        String[] responses = {
            "Excellent!",
            "Very good!",
            "Nice work!",
            "Way to go!",
            "Keep up the good work!"
        };
        return responses[random.nextInt(responses.length)];
    }

    public String getIncorrectResponse()
    {
        String[] incorrectResponses = {
            "That is incorrect!", 
            "No. Please try again!", 
            "Wrong, Try once more!", 
            "No. Don’t give up!", 
            "Incorrect. Keep trying!"
        };
        return incorrectResponses[random.nextInt(incorrectResponses.length)];
    }
}
