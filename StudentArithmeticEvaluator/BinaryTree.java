public class BinaryTree 
{
    Node root = null;

    public int evaluate()
    {
        return postOrderTraversal(root);
    }

    private int postOrderTraversal(Node node)
    {
        if(node != null)
        {
            int leftChildResult = postOrderTraversal(node.leftChild);
            int rightChildResult = postOrderTraversal(node.rightChild);
            if(node.data instanceof Operand)
            {
                return ((Operand) node.data).getNumber();
            }
            else if(node.data instanceof Operator)
            {
               Operator operator = (Operator) node.data;
               switch (operator.getOperator()) {
                   case '*' -> { return leftChildResult * rightChildResult;
                    }
                    case '%' -> { return leftChildResult % rightChildResult;
                    }
                    case '+' -> { return leftChildResult + rightChildResult;
                    }
                    case '-' -> { return leftChildResult - rightChildResult;
                    }
                   default -> throw new AssertionError();
               }

            }
            else
            {
                throw new IllegalAccessError();
            }
            
        }
        else
        {
            return 0;
        }
    }




}
