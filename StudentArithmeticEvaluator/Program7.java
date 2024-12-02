
import java.util.Scanner;

// Write  a  program  that  will  help  an  elementary  school  student  learn  to  evaluate  integer 
// arithmetic expressions.  

// Use a SecureRandom object to produce positive one-digit integers 
// for the expression.  

// The program should then prompt the student with a question based on 
// the following: 
 
// After the student gets five questions correctly (the first try) at the current level, give him/her 
// the opportunity to continue at that level, try a more difficult level or exit the program. If the 
// student  chooses  to  continue  at  the  same  level,  after  every  correct  answer,  thereafter,  give 
// him/her  another  opportunity  to  continue  at  that  level,  try  a  more  difficult  level  or  exit  the 
// program. 
 
// If the student decides to try a more difficult level, let him/her answer five questions correctly 
// before allowing him/her to try a more difficult level.   
 
// If the student is at the advanced level, give him/her the opportunity to exit the program, do not 
// give him/her an opportunity to try a less difficult level. 
 
// After the student chooses to exit the program, calculate the percentage of questions that were 
// answered correctly in each level and display it.  If the percentage in the Basic Level is lower 
// than  80%,  display  the  percentage  and  this  message;  "Please  ask  your  teacher  for  extra 
// help". 
 
// Log all the student’s interaction with the program in a file named Program7-
// Output.txt. 
 
//Wednesday, December 11, 2024

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
        // Method to start the program
        System.out.println("Welcome! Let's start learning integer arithmetic expressions.");
        System.out.println("Please enter the answer to the following " + DifficultyLevel.BASIC + " expression:");
        questionGenerator.generateQuestion(DifficultyLevel.BASIC);
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);

        
    }
}