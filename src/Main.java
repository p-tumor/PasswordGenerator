import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
public class Main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException {
        String key = "12345678";
       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                DES.encrypt(key);
            } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IOException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }));
        new Window();
        DES.decrypt(key);
    }
}