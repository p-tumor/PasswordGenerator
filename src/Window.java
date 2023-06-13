import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Window extends JFrame implements ActionListener{
    private JButton generatebutton, copyToClip, savePass, passList;
    private JLabel password, sliderVal;
    private JCheckBox hasSpecials, hasInts, hasUpper;
    private JPanel checks;
    private int lengthOfPass;
    private JSlider passLength;
    public Window() {
        String[] dontCopy = {"Generated password will appear here.","Generate a password first","Choose the length of your password!","Invalid"};

        password = new JLabel();
        password.setText("Generated password will appear here.");
        password.setFont(new Font("Plain", Font.PLAIN, 12));
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(true);
        passwordPanel.add(password);
        JPanel generatePanel = new JPanel();
        generatePanel.setSize(100,200);
        generatePanel.setOpaque(true);


        lengthOfPass = 8;
        passLength = new JSlider(JSlider.HORIZONTAL, 8, 60, 8);
        sliderVal = new JLabel(Integer.toString(passLength.getValue()));
        passLength.addChangeListener(e -> {
            sliderVal.setText(Integer.toString(passLength.getValue()));
            lengthOfPass = passLength.getValue();
        });
        passLength.setPreferredSize(new Dimension(400,200));
        passLength.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        passLength.setOpaque(false);

        generatebutton = new JButton();
        generatebutton.setSize(250,100);
        generatebutton.addActionListener(e -> password.setText(PasswordGen.generator(lengthOfPass)));
        generatebutton.setFocusable(false);
        generatebutton.setText("Generate");
        generatebutton.setFocusable(false);
        generatebutton.setOpaque(false);
        generatebutton.setContentAreaFilled(false);
        generatebutton.setBorderPainted(false);
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
                FileHandler.addText(password.getText());
                System.out.println(copyToClip.getFont());
            }
        });
        copyToClip.setFocusable(false);
        copyToClip.setOpaque(false);
        copyToClip.setContentAreaFilled(false);
        copyToClip.setBorderPainted(false);
        generatePanel.add(copyToClip);

        savePass = new JButton("Save Password");
        savePass.addActionListener(e->{
            boolean notVal = false;
            for(String s: dontCopy){
                if(s.equals(password.getText())) notVal = true;
            }
            if(!notVal) {
                String saveName = JOptionPane.showInputDialog(savePass, "Give your password a name to remember!");
                FileHandler.addText(saveName + " : " + password.getText());
            }
        });
        savePass.setFocusable(false);
        savePass.setOpaque(false);
        savePass.setContentAreaFilled(false);
        savePass.setBorderPainted(false);

        generatePanel.add(savePass);

        passList = new JButton("Passwords");
        passList.addActionListener(e ->{
            try {
                new PasswordWindow();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        generatePanel.add(passList);

        checks = new JPanel();
        checks.setLayout(new BoxLayout(checks,BoxLayout.Y_AXIS));

        hasSpecials = new JCheckBox("Special Characters?");
        hasSpecials.setFocusable(true);
        hasSpecials.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasSpecials.addActionListener(e -> PasswordGen.setHasSpecials(hasSpecials.isSelected()));

        hasSpecials.setOpaque(false);

        hasInts = new JCheckBox("Has Integers?");
        hasInts.setFocusable(false);
        hasInts.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasInts.addActionListener(e -> PasswordGen.setHasInts(hasInts.isSelected()));
        hasInts.setOpaque(false);

        hasUpper = new JCheckBox("Has Uppercase letters?");
        hasUpper.setFocusable(false);
        hasUpper.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasUpper.addActionListener(e -> PasswordGen.setToUppercase(hasUpper.isSelected()));
        hasUpper.setOpaque(false);

        checks.add(hasInts);
        checks.add(hasSpecials);
        checks.add(hasUpper);
        checks.add(passLength);
        checks.add(sliderVal);

        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(450, 300);
        this.setLocationRelativeTo(null);
        this.add(checks, BorderLayout.CENTER);
        this.add(generatePanel,BorderLayout.SOUTH);
        this.add(passwordPanel,BorderLayout.NORTH);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
