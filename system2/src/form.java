import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton LOGINButton;
    private JButton CLEARButton;
    private JTextField TextField;


    public form() {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextField.setText("");
                passwordField1.setText("");


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("form");
        frame.setContentPane(new form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Login form");
        frame.setResizable(false);

    }
}
