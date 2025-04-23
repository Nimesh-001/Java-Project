import javax.swing.*;

public class LecStudentEligibility {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentEligibility");
        frame.setContentPane(new LecStudentEligibility().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
