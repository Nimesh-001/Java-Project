import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Studentdashboard {


    private JPanel panal1;
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
    private JPanel panel2;

    public Studentdashboard() {
        JFrame frame = new JFrame("Studentdashboard");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        attendenceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student_Attendence");
                frame.setContentPane(new Student_Attendance().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        medicalButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student_Medical");
                frame.setContentPane(new Student_Medical().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        timetableButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student_Timetable");
                frame.setContentPane(new Student_Timetable().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        courseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student_Course");
                frame.setContentPane(new Student_Course().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        gradeGPAButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Student_GPA");
                frame.setContentPane(new View_Marks().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        gradeGPAButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new Studentdashboard();
    }

}
