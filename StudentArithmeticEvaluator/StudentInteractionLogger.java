
import java.io.FileWriter;
import java.io.IOException;

public class StudentInteractionLogger 
{

    private final FileWriter writer;

    public StudentInteractionLogger(String filename) throws IOException
    {
        FileWriter tempWriter = null;
        try {
            tempWriter = new FileWriter(filename, true);
        } catch (IOException e) {
            System.out.println("Error creating log file: " + e.getMessage());
        }
        writer = tempWriter;
    }

    public void log(String message)
    {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void close() 
    {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing log file: " + e.getMessage());
        }
    }
}
