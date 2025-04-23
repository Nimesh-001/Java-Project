import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecStudentAttendance {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton backButton;
    private JButton clearButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public LecStudentAttendance() {

        JFrame frame = new JFrame("LecStudentAttendance");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new LecStudentAttendance();
    }
}
