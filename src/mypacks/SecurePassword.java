package mypacks;
public class SecurePassword
{
    // instance variable
    private String password;

    // constructor
    public SecurePassword(String password)
    {
        this.password = password;
    }

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    /* Returns true if password:
       - Is at least 8 characters long
       - Contains at least one uppercase letter
       - Contains at least one lowercase letter
       - Contains at least one numeric digit
       - Contains at least one of these "special symbols":  ! @ # $ % ^ & * ?
       Must satisfy ALL FIVE categories for password to be "secure"
       Return false if any of the above security requirements are not fulfilled.
    */
    public boolean isSecure()
    {
        /* to be implemented */
        // You should first write the six private "helper"
        // methods below and use them as needed in this method’s implementation
        return isLongEnough() && containsDigit() && containsUppercase() && containsLowercase() && containsSpecialSymbol();
    }

    /* Returns a String that contains information about the security status of the
       current password.

       If isSecure() is true, this method returns "Password is secure"
       If isSecure() is false, this methods should return a single String that informs the
       user of which security requirements are not currently being met.

       For example, the password 3WrT6tH does not meet length or special symbol
       requirements, so this method should return the following String (using a line
       break \n):

       "The password must be at least 8 characters
        The password must contain a special symbol: ! @ # $ % ^ & * ?"
    */
    public String status()
    {
        if (isSecure()) return "Password is secure";
        String output = "";
        if(!isLongEnough()) output += "The password must be at least 8 characters.\n";
        if(!containsDigit()) output += "The password must contain at least one digit.\n";
        if(!containsUppercase()) output += "The password must contain at least one uppercase character.\n";
        if(!containsLowercase()) output += "The password must contain at least one lowercase character.\n";
        if(!containsSpecialSymbol()) output += "The password must contain a special symbol: ! @ # $ % ^ & * ?.";
        return output;
    }


    // PRIVATE HELPER METHODS (marked "private" rather than "public")

    /* Returns true if the password’s length meets the minimum requirement of 8 characters
       and false otherwise.
     */
    private boolean isLongEnough()
    {
        return password.length() >= 8;
    }

    /* Returns true if the password has at least one uppercase letter and false otherwise.
     */
    private boolean containsUppercase()
    {
        /* this one is completed for you as a hint for how to do the others! */
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return checkString(upperCaseLetters);
    }

    /* Returns true if the password has at least one lowercase letter and false otherwise.
     */
    private boolean containsLowercase()
    {
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        return checkString(lowerCaseLetters);
    }

    /* Returns true if the password has at least one digit and false otherwise.
     */
    private boolean containsDigit()
    {
        String[] passwordChars = new String[password.length()];
        int[] digits = {0,1,2,3,4,5,6,7,8,9};
        for(int i = 0; i < password.length();i++){
            passwordChars[i] = password.substring(i,i+1);
        }
        int temp;
        for (int x: digits){
            for(String s: passwordChars){
                try{
                    if (x == Integer.parseInt(s)) return true;
                }catch (Exception ignored){}
            }
        }
        return false;
    }

    /* Returns true if the password has at least one of these special symbols:
       ! @ # $ % ^ & * ?    and false otherwise.
     */
    private boolean containsSpecialSymbol()
    {
        String specialCharacters = "!@#$%^&*?";
        return checkString(specialCharacters);
    }

    /* Checks a given character string to see if password contains at least one
       character from that string.

       For example, if characterString is "ABCDEFGH", and password == "jbHFmfA"
       this method will find two occurrences (F and A) and return true, since two
       is at least one
     */
    private boolean checkString(String characterString)
    {
        String[] characterStringChars = new String[characterString.length()];
        for(int i = 0; i < characterString.length();i++){
            characterStringChars[i] = characterString.substring(i,i+1);
        }
        String[] passwordChars = new String[password.length()];
        for(int i = 0; i < password.length();i++){
            passwordChars[i] = password.substring(i,i+1);
        }
        for(String s:characterStringChars){
            for(String str: passwordChars){
                if(s.equals(str)) return true;
            }
        }
        return false;
    }
}
