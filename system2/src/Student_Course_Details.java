import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Course_Details {
    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    public JTextArea textArea1;
    public JTextArea textArea2;
    private JButton logoutButton;

    public Student_Course_Details() {
        JFrame frame = new JFrame("Student_Attendance");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
    }

    public static void main(String[] args) {
        new Student_Course_Details();
    }
}
