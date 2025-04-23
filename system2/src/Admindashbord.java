import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        loadprofileimage();



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

    public void loadprofileimage(){
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        String sql= "SELECT Profile_Pic_Path FROM USER WHERE Username='AD0001'";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                String path = rs.getString("Profile_Pic_Path");

                if(path!=null && !path.isEmpty()){
                    ImageIcon image = new ImageIcon(path);
                    Image img = image.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                    profilePicLabel.setIcon(new ImageIcon(img));

                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error loading image:"+e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Admindashbord();
    }
}
