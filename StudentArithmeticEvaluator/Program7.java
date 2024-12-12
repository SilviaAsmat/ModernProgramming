
import java.util.Scanner;

public class Program7 
{
    private final Scanner scanner = new Scanner(System.in);
    private final QuestionGenerator questionGenerator = new QuestionGenerator();
    private final LevelManager levelManager = new LevelManager();
    private final UserFeedbackMessage userFeedbackMessage = new UserFeedbackMessage();
    private final StudentInteractionLogger logger = new StudentInteractionLogger();
    private Question currentQuestion;
    private boolean shouldExit = true;
    public static void main(String[] args) 
    {
        // Main method to start the program
        Program7 program = new Program7();
        program.developerInfo();
        program.startProgram(); 
    }
    public void startProgram() 
    {
        String response;
        int numOfOperators = levelManager.getNumberOfOperatorsForCurrentLevel();
        currentQuestion = questionGenerator.generateQuestion(numOfOperators);
        boolean isFirstTry = true;
        
        System.out.println("Welcome! Let's start learning integer arithmetic expressions.");
        while (shouldExit) 
        {   
            boolean correct = getAnswerFromUser();
            if (correct) 
            {
                response = userFeedbackMessage.getCorrectResponse();
                System.out.println(response);
                logger.log(response);
                levelManager.onGotAnswerCorrect(isFirstTry);
                if(levelManager.isUserEligibleForNextLevel())
                {
                    onMoveToNextLevel();
                }
                else if(levelManager.isFinalLevel())// Advanced level, can no longer level up
                {
                    onFinalLevel();
                }
                numOfOperators = levelManager.getNumberOfOperatorsForCurrentLevel();
                currentQuestion = questionGenerator.generateQuestion(numOfOperators); 
                isFirstTry = true;
            } 
            else 
            {
                onIncorrectAnswer();
                isFirstTry = false;
                // Student tries again, display same question again, until they get it right
            }
        }
    }// end of startProgram
    public boolean getAnswerFromUser()
    {
        System.out.println("Please enter the answer to the following " + levelManager.getLevelDisplayName() + " expression:");
        System.out.println(currentQuestion.getDisplayName());
        logger.log("Question: " + currentQuestion.getDisplayName());
        int input = getUserIntegerInput();
        // try()
        // int input = scanner.nextInt();
        // scanner.nextLine();
        // System.out.println("You entered: " + input);
        logger.log("Input: " + input);
        return currentQuestion.isAnswerCorrect(input);
    }

    public int getUserIntegerInput()
    {
        boolean validInput = false;
        int input = 0;
        while(!validInput)
        {
            if(scanner.hasNextInt())
            {
                input = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            }
            else
            {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return input;
    }
    public void onMoveToNextLevel()
    {
        System.out.println("Would you like to move to next level or exit?(Y/N/Exit)");
        logger.log("Would you like to move to next level or exit?(Y/N/Exit)");
        String input = scanner.nextLine();
        logger.log("Input: " + input);
        switch (input.toUpperCase()) 
        {
            case "Y" -> {
                levelManager.moveToNextLevel();
            }
            case "N" -> {
                // Do nothing, stay on the current level
            }
            case "EXIT" -> {
                // Find way to exit loop
                shouldExit = false;
                levelManager.displayLevelStats();
                logger.log(levelManager.displayLevelStats());
            }
            default -> System.out.println("Invalid Input");
        }
    }
    public void onFinalLevel()
    {
        System.out.println("Would you like to exit?(Y/N)");
        logger.log("Would you like to exit?(Y/N)");
        String input = scanner.nextLine();
        logger.log("Input: " + input);
        switch (input.toUpperCase()) {
            case"Y" -> {
                //find way to exit loop
                shouldExit = false;
                levelManager.displayLevelStats();
                logger.log(levelManager.displayLevelStats());
            }
            case "N" -> {
                // Do nothing, stay on the current level
            }
            default -> {
                System.out.println("Invalid Input");
                onFinalLevel();
            }
        }
    }
    public void onIncorrectAnswer()
    {
        String response = userFeedbackMessage.getIncorrectResponse();
        logger.log(response);
        System.out.println(response);
        levelManager.onGotAnswerWrong();
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
       System.out.println("Name:   Silvia Asmat");
       System.out.println("Course:   COSC 4301 - Modern Programming");
       System.out.println("Program:  Seven");
	   System.out.println("Due Date: 12/11/2024\n");

    } // End of the developerInfo method
}