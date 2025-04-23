import javax.swing.*;

public class LecEditProfile {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton backButton;
    private JButton OKButton;
    private JTextField textField2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecEditProfile");
        frame.setContentPane(new LecEditProfile().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
