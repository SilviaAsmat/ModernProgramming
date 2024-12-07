import java.security.SecureRandom;




public class ResponseEvaluator 
{
    private final SecureRandom random = new SecureRandom();

    public boolean evaluate(String question, String studentAnswer) 
    {
        // Evaluate the student's answer
        Integer correctAnswer = solveQuestion(question);

        return correctAnswer.equals(Integer.valueOf(studentAnswer));
    }

    private Integer solveQuestion(String question) 
    {
        Integer correctAnswer = -1;

        // Solve the question and return the correct answer
        return correctAnswer;
    }
}


