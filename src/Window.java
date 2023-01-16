import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Window extends JFrame implements ActionListener{
    JButton button;
    public Window() throws FileNotFoundException {
        JLabel label = new JLabel();
        label.setText("Garvin's Password Generator!");

        JLabel password = new JLabel();
        password.setText(PassGen.generatePassword());
        password.setFont(new Font("Comfortaa",Font.PLAIN,25));
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(password);


        JPanel generatePanel = new JPanel();
        generatePanel.setBackground(new Color(0,0,0));
        generatePanel.setSize(100,200);



        button = new JButton();
        button.setSize(250,100);
        button.addActionListener(e -> {
            try {
                password.setText(PassGen.generatePassword());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setFocusable(false);
        button.setText("Generate");
        generatePanel.add(button);


        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750, 600);


        this.add(generatePanel,BorderLayout.CENTER);
        this.add(label,BorderLayout.NORTH);
        this.add(passwordPanel,BorderLayout.SOUTH);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
