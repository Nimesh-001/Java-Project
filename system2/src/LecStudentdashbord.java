import javax.swing.*;

public class LecStudentdashbord {
    private JPanel panel1;
    private JButton detailsButton;
    private JButton eligibilityButton;
    private JButton marksGradeGPAButton;
    private JButton attendanceButton;
    private JButton medicalButton;

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
