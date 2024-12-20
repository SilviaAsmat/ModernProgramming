import java.security.SecureRandom;
import java.util.ArrayList;

public class ArithmeticQuestion implements Question 
{
    private final int numberOfOperators;
    private final ArrayList<ExpressionToken> tokens = new ArrayList<>();
    private final BinaryTree tree = new BinaryTree();
    private final SecureRandom random = new SecureRandom();

    public ArithmeticQuestion(int numberOfOperators) 
    {
        this.numberOfOperators = numberOfOperators;
        initializeTokens();
        initializeTree();
    }

    private void initializeTree()
    {
        if(numberOfOperators >= 1)
        {
            Node node = new Node(tokens.get(1));
            node.leftChild = new Node(tokens.get(0));
            node.rightChild = new Node(tokens.get(2));
            tree.root = node;
            for(int i = 3; i < tokens.size(); i+=2)
            {
                Operator currentOperator = (Operator) tokens.get(i);
                Operand currentOperand = (Operand)  tokens.get(i+1);

                Operator rootOperator = (Operator) tree.root.data;
                boolean isHigherPrecedence = isHigherPrecedence(currentOperator, rootOperator);

                if(isHigherPrecedence)
                {
                    //replace right child with subtree
                    Node subTree = new Node(currentOperator);
                    subTree.leftChild = tree.root.rightChild;
                    subTree.rightChild = new Node(currentOperand);
                    tree.root.rightChild = subTree;
                }
                else
                {
                    Node subTree = new Node(currentOperator);
                    subTree.rightChild = new Node(currentOperand);
                    subTree.leftChild = tree.root;
                    tree.root = subTree;
                }
            }
        }

    }

    /**
     * Compares two operators to determine if operator1 is higher
     * @param operator1
     * @param operator2
     * @return
     */
    private boolean isHigherPrecedence(Operator operator1, Operator operator2)
    {
        char operatorOne = operator1.getOperator();
        char operatorTwo = operator2.getOperator();
        boolean isHigher;

        if(operatorOne == operatorTwo)
        {
            isHigher = false;
        }
        else if(operatorOne == '*' && operatorTwo == '%')
        {
            isHigher = false;
        }
        else if(operatorTwo == '*' && operatorOne == '%')
        {
            isHigher = false;
        }
        else 
        {
            isHigher = operatorOne == '*' || operatorOne == '%';
        }
        return isHigher;
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
            tokens.add(new Operand(random.nextInt(1,10)));
        }
        // tokens.add(new Operand(6));
        // tokens.add(new Operator('%'));
        // tokens.add(new Operand(5));
        // tokens.add(new Operator('*'));
        // tokens.add(new Operand(7));
        // tokens.add(new Operator('-'));
        // tokens.add(new Operand(4));
    }

    @Override
    public String getDisplayName() 
    {
        StringBuilder displayName = new StringBuilder();
        for(ExpressionToken token : tokens)
        {
            displayName.append(token.toString());
        }
        return displayName.toString();
    }

    @Override
    public Boolean isAnswerCorrect(String answer) 
    {
        int expectedResult = tree.evaluate();
        return Integer.parseInt(answer) == expectedResult;
    }
    
}
