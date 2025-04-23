import javax.swing.*;

public class LecStudentdashbord {
    private JPanel panel1;
    private JButton detailsButton;
    private JButton eligibilityButton;
    private JButton gradeButton;
    private JButton markButton;
    private JButton GPAButton;
    private JButton attendanceButton1;
    private JButton medicalButton1;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentdashbord");
        frame.setContentPane(new LecStudentdashbord().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
