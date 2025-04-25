import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Course {
    public JPanel panal1;
    private JButton attendenceButton;
    private JButton logoutButton;
    private JButton noticeButton;
    private JButton timetableButton;
    private JButton gradeGPAButton;
    private JButton medicalButton;
    private JButton courseButton;
    private JPanel panel2;
    private JButton DSAButton;
    private JButton EcommerceButton;
    private JButton OOPButton;
    private JButton OOADButton;
    private JButton OOPpracticumButton;

    public Student_Course() {
        JFrame frame = new JFrame("Student_Course");
        frame.setContentPane(this.panal1); // FIXED
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        DSAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Corse Details");
                frame.setContentPane(new Course_Details().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        EcommerceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Corse Details");
                frame.setContentPane(new Course_Details().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        OOPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Corse Details");
                frame.setContentPane(new Course_Details().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        OOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Corse Details");
                frame.setContentPane(new Course_Details().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        OOPpracticumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Corse Details");
                frame.setContentPane(new Course_Details().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Student_Course();
    }
}
