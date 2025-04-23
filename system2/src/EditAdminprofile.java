import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditAdminprofile {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPasswordField passwordField2;
    private JTextField textField5;
    private JButton updateButton;
    private JButton resetButton;
    private JTextField textField6;
    private JButton backButton;
    private JButton logoutButton;

    public EditAdminprofile() {
        JFrame frame = new JFrame("EditAdminprofile");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = textField1.getText();
                String lastname = textField2.getText();
                String designation = textField3.getText();
                String phone = textField4.getText();
                String email = textField5.getText();
                String password = String.valueOf(passwordField2.getPassword());
                String pic_path = textField6.getText();

                if (firstname.isEmpty() || lastname.isEmpty() || designation.isEmpty() || phone.isEmpty() ||
                        email.isEmpty() || password.isEmpty() || pic_path.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }

                Dbconnector db = new Dbconnector();
                Connection con = db.getConnection();

                String sql = "UPDATE USER SET First_Name=?, Last_Name=?, Designation=?, Phone_Number=?, Profile_Pic_Path=?, Password=?, Email=? WHERE Username='AD0001'";


                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, firstname);
                    pst.setString(2, lastname);
                    pst.setString(3, designation);
                    pst.setString(4, phone);
                    pst.setString(5, email);
                    pst.setString(6, password);
                    pst.setString(7, pic_path);

                    int result = pst.executeUpdate();
                    if(result > 0) {
                        JOptionPane.showMessageDialog(null, "Admin Profile Updated");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Admin Profile Not Updated");
                    }



                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error updating profile: " + ex.getMessage());
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                passwordField2.setText("");
                textField5.setText("");
                textField6.setText("");

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admindashbord();

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
        new EditAdminprofile();
    }
}
