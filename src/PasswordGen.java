public class PasswordGen {
    private static boolean toUppercase, hasSpecials, hasInts;
    public static String generator(int passLen){
        StringBuilder pass = new StringBuilder(passLen);
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] specialChars = {"!","@","#","$","%","&","*","?",":",";","[","]","_","(",")","<",">",":",";"};
        for(int i = (int)(Math.random()*alphabet.length); pass.length() < passLen; i = (int)(Math.random()*alphabet.length)){
            double ran = Math.random();
            if(toUppercase && ran < 0.3){
                pass.append(alphabet[i].toUpperCase());
            }else if (hasSpecials && ran >= 0.3 && ran < 0.5){
                int ranIn = (int)(Math.random()*specialChars.length);
                pass.append(specialChars[ranIn]);
            }else if (hasInts && ran >= 0.5 && ran < 0.7){
                int ranNum = (int)(Math.random()*10);
                pass.append(ranNum);
            }else{
                pass.append(alphabet[i]);
            }
        }
        return pass.toString();
    }

    public static void setToUppercase(boolean toUppercase) {
        PasswordGen.toUppercase = toUppercase;
    }

    public static void setHasSpecials(boolean hasSpecials) {
        PasswordGen.hasSpecials = hasSpecials;
    }

    public static void setHasInts(boolean hasInts) {
        PasswordGen.hasInts = hasInts;
    }
}
