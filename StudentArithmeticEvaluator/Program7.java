
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
        StudentInteractionLogger logger = new StudentInteractionLogger();
        // Method to start the program
        System.out.println("Welcome! Let's start learning integer arithmetic expressions.");
        int numOfOperators = levelManager.getNumberOfOperatorsForCurrentLevel();
        Question currentQuestion = questionGenerator.generateQuestion(numOfOperators);
        logger.log("Question: " + currentQuestion.getDisplayName());
        
        boolean isFirstTry = true;
        //Beginning of loop, clarify exit conditions
        boolean shouldExit = true;
        while (shouldExit) 
        { 
            
            System.out.println("Please enter the answer to the following " + levelManager.getLevelDisplayName() + " expression:");
            System.out.println(currentQuestion.getDisplayName());
            
            String input = scanner.nextLine();
            System.out.println("You entered: " + input);
            logger.log("Input: " + input);
            boolean correct = currentQuestion.isAnswerCorrect(input);

            if (correct) 
            {
                System.out.println(userFeedbackMessage.getCorrectResponse());
                levelManager.onGotAnswerCorrect(isFirstTry);
                boolean userEligibleToMoveNextLevel = levelManager.isUserEligibleForNextLevel();
                if(userEligibleToMoveNextLevel)
                {
                    System.out.println("Would you like to move to next level or exit?(Y/N/Exit)");
                    logger.log("Would you like to move to next level or exit?(Y/N/Exit)");
                    input = scanner.nextLine();
                    logger.log("Input: " + input);
                    switch (input) {
                        case "Y" -> {
                            levelManager.moveToNextLevel();
                        }
                        case "N" -> {
                        }
                        case"Exit" ->{
                            //find way to exit loop
                            shouldExit = false;
                        }
                        default -> System.out.println("Invalid Input");
                    }
                }
                numOfOperators = levelManager.getNumberOfOperatorsForCurrentLevel();
                currentQuestion = questionGenerator.generateQuestion(numOfOperators); 
                logger.log("Question: " + currentQuestion.getDisplayName());
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