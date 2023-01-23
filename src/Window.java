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
    private JComboBox lengthOfPass;
    private Integer passLength;
    private JLabel password;
    public Window() {
        passLength = 16;
        JLabel label = new JLabel();
        label.setText("Garvin's Password Generator!");

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
            password.setText(PasswordGen2.genPass(passLength));
            System.out.println(password.getText().length());
        });
        generatebutton.setFocusable(false);
        generatebutton.setText("Generate");
        generatePanel.add(generatebutton);

        copyToClip = new JButton();
        copyToClip.setText("Copy Password");
        copyToClip.setFocusable(false);
        copyToClip.setSize(100,100);
        copyToClip.addActionListener(e -> {
            String myString = password.getText();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });
        generatePanel.add(copyToClip);

        //combobox
        Integer[] ints = {16,17,18,19,20,2000};
        lengthOfPass = new JComboBox(ints);
        lengthOfPass.addActionListener(e -> {
            passLength = (Integer) lengthOfPass.getSelectedItem();
        });
        JPanel comboPanel = new JPanel();
        comboPanel.add(lengthOfPass,BorderLayout.SOUTH);


        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750, 600);



        this.add(generatePanel,BorderLayout.SOUTH);
        this.add(label,BorderLayout.NORTH);
        this.add(passwordPanel,BorderLayout.CENTER);
        this.add(comboPanel,BorderLayout.WEST);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
