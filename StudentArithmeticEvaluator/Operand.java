public class Operand extends ExpressionToken 
{
    private int number;

    public Operand(int number) 
    {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "" + number;
    }

    

    
}
