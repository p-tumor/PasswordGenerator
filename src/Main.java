import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Window window = new Window();
        String pass = PassGen.generatePassword(16);
        System.out.println("final" + pass);
        System.out.println("final Length of pass = "+pass.length());
    }
}