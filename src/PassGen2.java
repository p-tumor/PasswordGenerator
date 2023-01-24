import java.util.ArrayList;
import java.util.Arrays;

public class PassGen2 {
    private static boolean toUppercase, hasSpecials, hasInts;
    public static String generator(int passLen){
        StringBuilder pass = new StringBuilder(passLen);
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        String[] specialChars = {"!","@","#","$","%","&","*","?",":",";","[","]","_","(",")","<",">",":",";"};
        for(int i = (int)(Math.random()*alphabet.size()); pass.length() < passLen; i = (int)(Math.random()*alphabet.size())){
            double ran = Math.random();
            if(toUppercase && ran < 0.3){
                pass.append(alphabet.get(i).toUpperCase());
            }else if (hasSpecials && ran >= 0.3 && ran < 0.5){
                int ranIn = (int)(Math.random()*specialChars.length);
                pass.append(specialChars[ranIn]);
            }else if (hasInts && ran >= 0.5 && ran < 0.7){
                int ranNum = (int)(Math.random()*10);
                pass.append(ranNum);
            }else{
                pass.append(alphabet.get(i));
            }
        }
        return pass.toString();
    }

    public static void setToUppercase(boolean toUppercase) {
        PassGen2.toUppercase = toUppercase;
    }

    public static void setHasSpecials(boolean hasSpecials) {
        PassGen2.hasSpecials = hasSpecials;
    }

    public static void setHasInts(boolean hasInts) {
        PassGen2.hasInts = hasInts;
    }
}
