import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TO_Timetable {
    public JPanel panal1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton Back;
    private JLabel profilePicLabel;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JLabel adminUsernameLabel;

    public TO_Timetable() {

        JFrame frame = new JFrame("TO_Timetable");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(1000, 500);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Timetable_Add();

                /*
                JFrame frame = new JFrame("TO_Timetable_Add");
                frame.setContentPane(new TO_Timetable_Add().panal1);
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
                new TO_Timetable_Add();

                /*
                JFrame frame = new JFrame("TO_Timetable_Update");
                frame.setContentPane(new TO_Timetable_Update().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                 // Close current window
                new Todashboard();
                
                
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Timetable_Add();

            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Edit_TO_Profile();
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
        new TO_Timetable();

    }
}
