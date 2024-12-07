public class Operator extends ExpressionToken
{
    char operator;

    public Operator(char operator) {
        this.operator = operator;
    }
    

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "" + operator;
    }
    
}
