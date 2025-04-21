import javax.swing.*;

public class Lecturdashbord {
    private JPanel panel1;
    private JButton coursesButton;
    private JButton marksButton;
    private JButton studentsButton;
    private JButton noticeButton;
    private JButton logoutButton;
    private JButton coursesButton1;
    private JButton marksButton1;
    private JButton studentsButton1;
    private JButton noticeButton1;
    private JButton button10;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Lecturdashbord");
        frame.setContentPane(new Lecturdashbord().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
