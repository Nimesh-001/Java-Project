import javax.swing.*;

public class LecStudentAttendance {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton backButton;
    private JButton clearButton;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentAttendance");
        frame.setContentPane(new LecStudentAttendance().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
