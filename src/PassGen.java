import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import mypacks.SecurePassword;
public class PassGen extends SecurePassword{
    private static final String[] WORDS = {"unbelievable","howitzers", "bollocks","senior", "metric", "result", "experience", "minimum", "distributed"};

    public PassGen(String password) {
        super(password);
    }

    public static String ranStr(){
        int i = (int)(Math.random()* WORDS.length);
        return WORDS[i];
    }
    private static ArrayList<String> breakWords(String word){
        ArrayList<String> fragments = new ArrayList<>();
        int length = word.length();
        for(int i = 0;i < 5;i++) {
            int start = (int) (Math.random() * length);
            int end = (int) (Math.random() * (length - start)) + start;
            if (end == start) end++;
            fragments.add(word.substring(start,end));
        }
        return fragments;
    }
    private static String toUppercaseRan(String string){
        StringBuilder result = new StringBuilder();
        String[] chars = new String[string.length()];
        for(int i = 0; i < chars.length; i++){
            String current = string.substring(i,i+1);
            chars[i] = current;
        }
        for(int i = 0; i < chars.length; i++){
            double ran = Math.random();
            if (ran > 0.4){
                chars[i] = chars[i].toUpperCase();
            }
        }
        for(String s: chars){
            result.append(s);
        }
        string = result.toString();
        return string;
    }
    private static String addInt(String string){
        StringBuilder stb = new StringBuilder(string);
        for(int i = 0; i < (int)(Math.random()*13)+1;i++) {
            int spot = (int) (Math.random() * stb.length());
            int ranInt = (int) (Math.random() * 29) + 1;
            double ran = Math.random();
            if (ran > 0.7) {
                String ranInteger = Integer.toString(ranInt);
                int length = ranInteger.length();
                stb.replace(spot,spot+length,ranInteger);
            }else stb.insert(spot, ranInt);
        }
        string = stb.toString();
        return string;
    }
    public static String addSpecial(String string){
        StringBuilder stb = new StringBuilder(string);
        String[] specialChars = {"!","@","#","$","%","&","*","?",":",";","[","]","_","(",")"};
        for(int i = 0; i < (int)(Math.random()*13)+1;i++) {
            int spot = (int) (Math.random() * stb.length());
            String ranChoice = specialChars[(int)(Math.random() * specialChars.length)];
            double ran = Math.random();
            if (ran > 0.3) {
                stb.replace(spot,spot+1,ranChoice);
            }else stb.insert(spot, ranChoice);
        }
        string = stb.toString();
        return string;
    }
    private static ArrayList<String> getWordList() throws FileNotFoundException {
        ArrayList<String> words= new ArrayList<>();
        File wordFile = new File("src/words.txt ");
        Scanner reader = new Scanner(wordFile);
        while(reader.hasNextLine()){
            String word = reader.nextLine();
            words.add(word);
        }
        return words;
    }
    public static String generatePassword() throws FileNotFoundException{
        String password = "";
        ArrayList<String> wordList = getWordList();
        String[] words = new String[4];
        for(int count = 0; count < 4; count++){
            String ranWord = wordList.get((int)(Math.random()* wordList.size()));
            words[count] = ranWord;
        }
        ArrayList<String> wordfrags = breakWords(words[0]);
        ArrayList<String> wordfrags2 = breakWords(words[1]);
        ArrayList<String> wordfrags3 = breakWords(words[2]);
        ArrayList<String> wordfrags4 = breakWords(words[3]);
        for(int count = 0; count < 6;count++){
            double randomNum = Math.random();
            int randomIndex = (int)(Math.random()*5);
            if (randomNum >= .75)password += (wordfrags.get(randomIndex));
            else if (randomNum < .75 && randomNum >= .50)password += (wordfrags2.get(randomIndex));
            else if (randomNum < .50 && randomNum >= .25)password += (wordfrags3.get(randomIndex));
            else if(randomNum < .25) password += (wordfrags4.get(randomIndex));
        }
        password = toUppercaseRan(password.toString());
        password = addInt(password.toString());
        password = addSpecial(password.toString());
        return password;
    }
}
