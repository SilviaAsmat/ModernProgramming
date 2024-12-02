import java.io.FileWriter;
import java.io.IOException;

public class StudentInteractionLogger {
    private final FileWriter writer;

    public StudentInteractionLogger(String filename) throws IOException {
        writer = new FileWriter(filename, true);
    }

    public void log(String message) throws IOException {
        writer.write(message + "\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}