import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void addText(String s){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Passwords", true))) {
            writer.append(s);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }

