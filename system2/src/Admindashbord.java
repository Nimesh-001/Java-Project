import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admindashbord {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JButton timetablesButton1;
    private JButton noticesButton1;
    private JButton coursesButton1;
    private JButton userProfilesButton1;
    private JLabel profilePicLabel;

    public Admindashbord() {
        JFrame frame = new JFrame("Admindashbord");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,500);



        userProfilesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        coursesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        noticesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        timetablesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditAdminprofile();

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
        new Admindashbord();
    }
}
