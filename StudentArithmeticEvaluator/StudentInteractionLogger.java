
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentInteractionLogger 
{
    private final FileOutputStream writer;

    public StudentInteractionLogger() 
    {
        FileOutputStream tempWriter = null;
        try {
            tempWriter = new FileOutputStream("Program7-Output.txt", true);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
        writer = tempWriter;
    }
    public void log(String message)
    {
        try {
            writer.write((message + "\n").getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void close() 
    {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}
