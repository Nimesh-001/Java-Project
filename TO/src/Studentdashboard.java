import javax.swing.*;

public class Studentdashboard {


    private JPanel panel1;
    private JButton attendenceButton;
    private JButton logoutButton;
    private JButton noticeButton;
    private JButton timetableButton;
    private JButton gradeGPAButton;
    private JButton medicalButton;
    private JButton courseButton;
    private JButton attendenceButton1;
    private JButton medicalButton1;
    private JButton courseButton1;
    private JButton gradeGPAButton1;
    private JButton timetableButton1;
    private JButton button13;
    private JButton button14;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Studentdashboard");
        frame.setContentPane(new Studentdashboard().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }
}
