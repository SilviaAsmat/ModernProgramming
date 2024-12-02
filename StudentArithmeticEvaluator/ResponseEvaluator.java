// The student then inputs the answer.  Next, the program checks the student’s answer.  If it’s 
// correct, display a message from the possible responses to a correct answer below, and ask 
// another question.  If the answer is wrong, display a message from the possible responses to 
// an  incorrect  answer  below,  display  the  same  question  again,  and  let  the  student  try  it 
// repeatedly until the student finally gets it right.  
 
// Possible responses to a correct answer:  (Selected at random)  
//      Excellent! 
//      Very good! 
//      Nice work! 
//      Way to go! 
//      Keep up the good work! 
 
// Possible responses to an incorrect answer:  (Selected at random)  
//      That is incorrect! 
//      No. Please try again! 
//      Wrong, Try once more! 
//      No. Don’t give up! 
//      Incorrect. Keep trying! 

public class ResponseEvaluator {
    public boolean evaluate(String question, int studentAnswer) {
        // Evaluate the student's answer
        // For now, let's assume the answer is always correct
        return true;
    }

    public String getCorrectResponse() {
        // Return a random correct response message
        return "Correct!";
    }

    public String getIncorrectResponse() {
        // Return a random incorrect response message
        return "Incorrect!";
    }
}