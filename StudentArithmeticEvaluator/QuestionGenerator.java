
import java.security.SecureRandom;

public class QuestionGenerator 
{
   
    private final SecureRandom random = new SecureRandom();

    QuestionGenerator() 
    {
        // 
    }


    private int generateOperand() 
    {
        // Generate a random operand
        return random.nextInt(10);
    }

    private char generateOperator() 
    {
        // Generate a random operator
        char[] operators = {'*', '%', '+', '-'};
        return operators[random.nextInt(operators.length)];
    }

    private  String generateExpression(int operands, int operators) 
    {
        // Generate an expression based on the number of operands and operators
        StringBuilder expression = new StringBuilder();
        for (int i = 0; i < operands; i++) 
        {
            expression.append(generateOperand());
            if (i < operators) {
                expression.append(generateOperator());
            }
        }
        return expression.toString();
    }

    private  String getQuestion(int numberOfOperators) 
    {
        // return switch (numberOfOperators) 
        // {
        //     case 1 -> generateExpression(4, 3);
        //     case 2 -> generateExpression(2, 1);
        //     case 3 -> generateExpression(3, 2);
        //     default -> throw new AssertionError("Unknown difficulty level: " + level);
        // };
        return "";
    }

    public Question generateQuestion(int numberOfOperators)
    {
        ArithmeticQuestion question = new ArithmeticQuestion();
        return question;
    }
}
