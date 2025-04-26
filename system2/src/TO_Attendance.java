import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TO_Attendance {
    public JPanel panel1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton Back;
    private JLabel profilePicLabel;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JLabel adminUsernameLabel;

    public TO_Attendance() {

        JFrame frame = new JFrame("TO_Attendance");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Attendance_ADD();
                /*
                JFrame frame = new JFrame("TO_Attendance_ADD");
                frame.setContentPane(new TO_Attendance_ADD().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Attendance_Update();
                /*
                JFrame frame = new JFrame("TO_Attendance_Update");
                frame.setContentPane(new TO_Attendance_Update().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Attendance_Delete();
                /*
                JFrame frame = new JFrame("TO_Attendance_Delete");
                frame.setContentPane(new TO_Attendance_Delete().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current window
                new Todashboard();
            }
        });
    }

    public static void main(String[] args) {
        new TO_Attendance();

    }
}
