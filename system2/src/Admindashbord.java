import javax.swing.*;

public class Admindashbord {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JButton timetablesButton1;
    private JButton noticesButton1;
    private JButton coursesButton1;
    private JButton userProfilesButton1;
    private JButton button1;

    public Admindashbord() {
        JFrame frame = new JFrame("Admindashbord");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Admindashbord();
    }
}
