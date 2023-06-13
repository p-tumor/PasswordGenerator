import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class PasswordWindow extends JFrame{
    public PasswordWindow() throws FileNotFoundException {
        JPanel passwords = new JPanel();
        passwords.setLayout(new BoxLayout(passwords, BoxLayout.Y_AXIS));
        Scanner reader = new Scanner(new File("Passwords"));
        while(reader.hasNextLine()){
            String line = reader.nextLine();
            JPanel p = new JPanel();
            p.add(new JLabel(line));
            JButton b = new JButton()
            p.add()
            passwords.add(p);
        }
        reader.close();
        JScrollPane scrollPane = new JScrollPane(passwords);


        this.add(scrollPane);
        this.setContentPane(scrollPane);
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
