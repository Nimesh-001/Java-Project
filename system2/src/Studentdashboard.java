import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Studentdashboard {


    private JPanel panal1;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JButton attendenceButton1;
    private JButton medicalButton1;
    private JButton courseButton1;
    private JButton gradeGPAButton1;
    private JButton timetableButton1;
    private JButton button13;
    private JButton button14;
    private JPanel panel2;
    //private JLabel adminUsernameLabel;
    private JLabel profilePicLabel;

    public Studentdashboard() {
        JFrame frame = new JFrame("Studentdashboard");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        loadprofileimage();
// Create one instance of Student_Attendance and reuse it
        attendenceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Student_Attendance();
                // Create an instance of Student_Attendance only when the button is clicked
                /*
                Student_Attendance studentAttendance = new Student_Attendance();

                JFrame frame = new JFrame("Student_Attendence");
                frame.setContentPane(studentAttendance.panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });


        medicalButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Student_Medical();
                /*
                JFrame frame = new JFrame("Student_Medical");
                frame.setContentPane(new Student_Medical().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */


            }
        });

        timetableButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StudentViewTimetable();

            /*
                JFrame frame = new JFrame("Student_Timetable");
                frame.setContentPane(new Student_Timetable().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

             */


            }
        });
        courseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Student_Course();
                /*
                JFrame frame = new JFrame("Student_Course");
                frame.setContentPane(new Student_Course().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */
            }
        });
        gradeGPAButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new View_Marks();
                /*


                JFrame frame = new JFrame("Student_GPA");
                frame.setContentPane(new View_Marks().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                 */


            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StudentViewNotice();
            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditStudenntProfile();
            }
        });
    }

    public void loadprofileimage(){
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        String sql= "SELECT Profile_Pic_Path,First_Name FROM USER WHERE Username='TG1301'";
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
        new Studentdashboard();
    }

}
