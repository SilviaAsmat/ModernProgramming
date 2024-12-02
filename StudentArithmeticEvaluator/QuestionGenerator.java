 
// A separate method should be used to generate each new question.  This method should be 
// called when the application begins execution and each time the student answers the question 
// correctly. 

// Difficulty level 
// 1. Basic – 2 Operands and 1 Operator (All students should start at this level) 
// 2. Intermediate – 3 Operands and 2 Operators 
// 3. Advanced – 4 Operands and 3 Operators 
 
// Operators selected a random by the program. 
// *   Multiplication Operator 
// % Mod Operator (This is not an arithmetic operator, but must be included in the expression) 
// +  Addition Operator 
// -   Subtraction Operator 
// (Note: No division operator) 
import java.security.SecureRandom;

public class QuestionGenerator {
    private SecureRandom random = new SecureRandom();

    public String generateQuestion(DifficultyLevel level) {
        // Generate a question based on the difficulty level
        // Return the question as a string
        return "Sample question based on difficulty level: " + level;
    }
}