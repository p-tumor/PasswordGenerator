import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Window extends JFrame implements ActionListener{
    private JButton generatebutton;
    private JButton copyToClip;
    private JLabel password;
    private JTextField passLength;
    private JCheckBox hasSpecials, hasInts, hasUpper;
    private JPanel checks;
    private int lengthOfPass;
    public Window() {
        String[] dontCopy = {"Generated password will appear here.","Generate a password first","Choose the length of your password!","Invalid"};

        password = new JLabel();
        password.setText("Generated password will appear here.");
        password.setFont(new Font("Comfortaa",Font.PLAIN,25));
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(password);
        JPanel generatePanel = new JPanel();
        generatePanel.setSize(100,200);



        generatebutton = new JButton();
        generatebutton.setSize(250,100);
        generatebutton.addActionListener(e -> {
            if(lengthOfPass == 0)password.setText("Choose the length of your password!");
            else password.setText(PassGen2.generator(lengthOfPass));
        });
        generatebutton.setFocusable(false);
        generatebutton.setText("Generate");
        generatePanel.add(generatebutton);

        copyToClip = new JButton();
        copyToClip.setText("Copy Password");
        copyToClip.setFocusable(false);
        copyToClip.setSize(100,100);
        copyToClip.addActionListener(e -> {
            boolean notVal = false;
            for(String s : dontCopy){
                if(s.equals(password.getText())) notVal = true;
            }
            if (!notVal) {
                String myString = password.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        generatePanel.add(copyToClip);

        passLength = new JTextField("Insert Length of Password");
        passLength.setPreferredSize(new Dimension(150,25));
        passLength.addActionListener(e -> {
            try{
                lengthOfPass = Integer.parseInt(passLength.getText());
            }catch(Exception s){
                password.setText("Invalid");
            }
        });
        generatePanel.add(passLength);

        checks = new JPanel();
        checks.setLayout(new BoxLayout(checks,BoxLayout.Y_AXIS));

        hasSpecials = new JCheckBox("Special Characters?");
        hasSpecials.setFocusable(false);
        hasSpecials.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasSpecials.addActionListener(e -> PassGen2.setHasSpecials(hasSpecials.isSelected()));

        hasInts = new JCheckBox("Has Integers?");
        hasInts.setFocusable(false);
        hasInts.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasInts.addActionListener(e -> PassGen2.setHasInts(hasInts.isSelected()));

        hasUpper = new JCheckBox("Has Uppercase letters?");
        hasUpper.setFocusable(false);
        hasUpper.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasUpper.addActionListener(e -> PassGen2.setToUppercase(hasUpper.isSelected()));

        checks.add(hasInts);
        checks.add(hasSpecials);
        checks.add(hasUpper);


        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750, 600);

        this.add(checks, BorderLayout.CENTER);
        this.add(generatePanel,BorderLayout.SOUTH);
        this.add(passwordPanel,BorderLayout.NORTH);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
