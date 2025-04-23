import javax.swing.*;

public class LecStudentDetails {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton clearButton;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentDetails");
        frame.setContentPane(new LecStudentDetails().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
