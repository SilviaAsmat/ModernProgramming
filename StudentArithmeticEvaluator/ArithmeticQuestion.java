
import java.security.SecureRandom;
import java.util.ArrayList;

public class ArithmeticQuestion implements Question 
{
    private int numberOfOperators;
    private ArrayList<ExpressionToken> tokens = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();


    public ArithmeticQuestion(int numberOfOperators) 
    {
        this.numberOfOperators = numberOfOperators;
        initializeTokens();
    }

    private void initializeTokens()
    {
        char[] operators = {'*', '%', '+', '-'};
        if(numberOfOperators >= 1)
        {
            tokens.add(new Operand(random.nextInt(10)));
            tokens.add(new Operator(operators[random.nextInt(operators.length)]));
            tokens.add(new Operand(random.nextInt(10)));
        }
        for(int i = 1; i < numberOfOperators; i++)
        {
            tokens.add(new Operator(operators[random.nextInt(operators.length)]));
            tokens.add(new Operand(random.nextInt(10)));
        }
    }

    @Override
    public String getDisplayName() 
    {
        
        return tokens.toString();
    }

    @Override
    public Boolean isAnswerCorrect(String answer) 
    {
        return answer.equals("007");
    }
    
}
