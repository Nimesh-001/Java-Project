import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton editProfileButton;
    private JButton button10;
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

        coursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecCourses();
            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecEditProfile();
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
}
