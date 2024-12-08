
public class BinaryTree 
{
    Node root = null;

    public int evaluate()
    {
        return postOrderTraversal(root);
    }

    private int postOrderTraversal(Node node)
    {
        int result = 0;
        if(node != null)
        {
            int leftChildResult = postOrderTraversal(node.leftChild);
            int rightChildResult = postOrderTraversal(node.rightChild);
            switch (node.data) {
                case Operand operand -> {
                    result = operand.getNumber();
                }
                case Operator operator -> {
                    result = handleOperator(leftChildResult, rightChildResult, operator);
                    }
                default -> {
                    result = 0;
                }
            }
        }
        return result;
    }

    private Integer handleOperator(int leftChildResult, int rightChildResult, Operator operator)
    {
        int result;
        switch (operator.getOperator()) {
            case '*' -> {
                result = leftChildResult * rightChildResult;
                return result;
            }
            case '-' -> {
                result = leftChildResult - rightChildResult;
                return result;
            }
            case '+' -> {
                result = leftChildResult + rightChildResult;
                return result;
            }
            case '%' -> {
                if (rightChildResult == 0)
                {
                // cannot divide by zero
                    result = 0;
                    return result;
                }
                else
                {
                    result = leftChildResult % rightChildResult;
                    return result;
                }
            }
            default -> {
                return 0;
            }
        }
    }
}

                                // System.out.println("leftChild: " + leftChildResult);
                                // System.out.println("Operator:" + operator.getOperator());
                                // System.out.println("rightChild: " + rightChildResult);
                                // System.out.println("result: " + result);
