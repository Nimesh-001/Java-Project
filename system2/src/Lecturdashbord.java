import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecturdashbord {
    private JPanel panel1;
    private JButton coursesButton;
    private JButton marksButton;
    private JButton studentsButton;
    private JButton noticeButton;
    private JButton logoutButton;
    private JButton coursesButton1;
    private JButton marksButton1;
    private JButton studentsButton1;
    private JButton noticeButton1;
    private JButton editProfileButton;
    private JButton button10;
    private JLabel profilePicLabel;


    public Lecturdashbord() {

        JFrame frame = new JFrame("Lecturdashbord");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        loadprofileimage();

        coursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecCourses();
            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditLectureProfile();
            }
        });
        marksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecMarks();
            }
        });
        studentsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });
        noticeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserViewNotice();
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

        String sql= "SELECT Profile_Pic_Path,First_Name FROM USER WHERE Username='LEC0001'";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                String path = rs.getString("Profile_Pic_Path");
                String first_name = rs.getString("First_Name");
                //adminUsernameLabel.setText("WELCOME "+first_name);

                if(path!=null && !path.isEmpty()){
                    ImageIcon image = new ImageIcon(path);
                    Image img = image.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
                    profilePicLabel.setIcon(new ImageIcon(img));

                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error loading image:"+e.getMessage());
        }
    }
    public static void main(String[] args) {
        new Lecturdashbord();

    }
}
