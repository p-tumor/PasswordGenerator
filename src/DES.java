import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class DES {
    public static void doBoth(String key, File in, File out, int cipherMode)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException, InvalidKeySpecException {
        FileInputStream fIN = new FileInputStream(in);
        FileOutputStream fOUT = new FileOutputStream(out);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        if(cipherMode == Cipher.ENCRYPT_MODE){
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherInputStream cIN = new CipherInputStream(fIN, cipher);
            write(cIN, fOUT);
        }else if(cipherMode == Cipher.DECRYPT_MODE){
            cipher.init(Cipher.DECRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherOutputStream cOUT = new CipherOutputStream(fOUT, cipher);
            write(fIN, cOUT);
        }
    }
    public static void decrypt(String key)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException, InvalidKeySpecException {
        File out = new File("temp");
        out.createNewFile();
        FileInputStream fIN = new FileInputStream("Passwords");
        FileOutputStream fOUT = new FileOutputStream(out);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        CipherInputStream cIN = new CipherInputStream(fIN, cipher);
        write(cIN, fOUT);
        fOUT.close();
        fIN.close();
        cIN.close();
        copy(out);
        out.delete();
    }

    public static void encrypt(String key)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException, InvalidKeySpecException {
        File out = new File("temp");
        out.createNewFile();
        FileInputStream fIN = new FileInputStream("Passwords");
        FileOutputStream fOUT = new FileOutputStream(out);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
        CipherOutputStream cOUT = new CipherOutputStream(fOUT, cipher);
        write(fIN, cOUT);
        fOUT.close();
        fIN.close();
        cOUT.close();
        copy(out);
        out.delete();
    }
    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[64];
        int numOfBytesRead;
        while ((numOfBytesRead = in.read(buffer)) != -1) {
            System.out.println("done");
            out.write(buffer, 0, numOfBytesRead);
        }
        out.close();
        in.close();
    }


    private static void copy(File old) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(old));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Passwords"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
