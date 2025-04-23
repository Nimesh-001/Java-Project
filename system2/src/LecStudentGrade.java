import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecStudentGrade {
    private JPanel panel1;
    private JButton viewResultsButton;
    private JButton backButton;
    private JTable table1;

    public LecStudentGrade() {

        JFrame frame = new JFrame("LecStudentGrade");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });
    }

    public static void main(String[] args) {
        new LecStudentGrade();
    }
}
