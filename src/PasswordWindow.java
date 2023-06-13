import javax.swing.*;

public class PasswordWindow extends JFrame{
    public PasswordWindow(){
        JPanel passwords = new JPanel();
        passwords.setLayout(new BoxLayout(passwords, BoxLayout.Y_AXIS));
        for (int i = 0; i < 40; i++){
            passwords.add(new JLabel("THIS IS A TEST"));
        }
        JScrollPane scrollPane = new JScrollPane(passwords);

        this.add(scrollPane);
        this.setContentPane(scrollPane);
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
