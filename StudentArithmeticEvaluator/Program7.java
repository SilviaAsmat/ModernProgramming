
import java.util.Scanner;

public class Program7 
{
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) 
    {
        // Main method to start the program
        Program7 program = new Program7();
        program.startProgram(); 
    }

    public void startProgram() 
    {
        QuestionGenerator questionGenerator = new QuestionGenerator();
        LevelManager levelManager = new LevelManager();
        UserFeedbackMessage userFeedbackMessage = new UserFeedbackMessage();
        // Method to start the program
        System.out.println("Welcome! Let's start learning integer arithmetic expressions.");
        Question currentQuestion = questionGenerator.generateQuestion(2);
        boolean isFirstTry = true;
        //Beginning of loop, clarify exit conditions
        while (true) 
        { 
            
            System.out.println("Please enter the answer to the following " + levelManager.getLevelDisplayName() + " expression:");
            System.out.println(currentQuestion.getDisplayName());
            String input = scanner.nextLine();
            System.out.println("You entered: " + input);
            boolean correct = currentQuestion.isAnswerCorrect(input);

            if (correct) 
            {
                System.out.println(userFeedbackMessage.getCorrectResponse());
                levelManager.onGotAnswerCorrect(isFirstTry);
                boolean userEligibleToMoveNextLevel = levelManager.isUserEligibleForNextLevel();
                if(userEligibleToMoveNextLevel)
                {
                    System.out.println("Would you like to move to next level?(Y/N)");
                    input = scanner.nextLine();
                    switch (input) {
                        case "Y" -> {
                            levelManager.moveToNextLevel();
                        }
                        case "N" -> {
                        }
                        default -> System.out.println("Invalid Input");
                    }
                }

                currentQuestion = questionGenerator.generateQuestion(2); // Correct answer, generate a new question
                // call a method to add if student got it right on the first try - do I need a local variable to track that?s
                // call a method to determine if level needs to be changed
                
                isFirstTry = true;
            } 
            else 
            {
                System.out.println(userFeedbackMessage.getIncorrectResponse());
                levelManager.onGotAnswerWrong();
                isFirstTry = false;
                // Student tries again, display same question again, until they get it right
            }

        }
        
        
    }
}