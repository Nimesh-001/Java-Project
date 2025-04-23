import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lecturdashbord {
    private JPanel panel1;
    private JButton editProfileButton;
    private JButton logoutButton;
    private JButton coursesButton;
    private JButton marksButton1;
    private JButton studentsButton1;
    private JButton noticeButton1;
    private JTextField textField1;


    public Lecturdashbord() {
        JFrame frame = new JFrame("Lecturdashbord");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecEditProfile();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        coursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecCourses();
            }
        });
        marksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecMarks();
            }
        });
        studentsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });
        noticeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecNotice();
            }
        });
    }

    public static void main(String[] args) {
            new Lecturdashbord();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
