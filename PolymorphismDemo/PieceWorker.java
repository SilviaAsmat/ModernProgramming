//********************************************************************
//
//  Developer:     Silvia Asmat
//
//  Program #:     One
//
//  File Name:     Program1.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/15/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  Java SE 8
//
//  Description:   Extension of Worker class to include
//                 earning based on pieces made.
//
//********************************************************************
public class PieceWorker extends Worker{
    private double wage;
    private int pieces;
//***************************************************************
//
//  Method:       PieceWorker
//
//  Description:  Constructor for PieceWorker
//
//  Parameters:   String, String, String, double, int
//
//  Returns:      N/A
//
//**************************************************************
    public PieceWorker(String firstName, String lastName, String socialSecurityNumber, double wage, int pieces) {
        super(firstName, lastName, socialSecurityNumber);
        this.wage = wage;
        this.pieces = pieces;
    }
//***************************************************************
//
//  Method:       getWage
//
//  Description:  Get wage
//
//  Parameters:   N/A
//
//  Returns:      double
//
//**************************************************************
    public double getWage(){
        return wage;
    }
//***************************************************************
//
//  Method:       setWage
//
//  Description:  Set wage
//
//  Parameters:   double
//
//  Returns:      N/A
//
//**************************************************************
    public void setWage(double wage) {
        this.wage = wage;
    }
//***************************************************************
//
//  Method:       getPieces
//
//  Description:  Get pieces
//
//  Parameters:   N/A
//
//  Returns:      int
//
//**************************************************************
    public int getPieces() {
        return pieces;
    }
//***************************************************************
//
//  Method:       setPieces
//
//  Description:  Set pieces
//
//  Parameters:   int
//
//  Returns:      N/A
//
//**************************************************************
    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
//***************************************************************
//
//  Method:       earnings
//
//  Description:  Calculate earnings based on pieces made
//
//  Parameters:   N/A
//
//  Returns:      double
//
//**************************************************************
    @Override
    public double earnings(){
        return wage * pieces;
    }

    @Override
    public String toString(){
        return String.format("Piece Worker: %s%nWage: $%,.2f%nPieces: %d%n", super.toString(), getWage(), getPieces());
    }
}
