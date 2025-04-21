import javax.swing.*;

public class Todashboard {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JButton attendenceButton1;
    private JButton medicalButton1;
    private JButton noticeButton1;
    private JButton timetableButton1;
    private JButton button10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Todashboard");
        frame.setContentPane(new Todashboard().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
