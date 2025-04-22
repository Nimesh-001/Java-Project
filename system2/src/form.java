import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class form {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton LOGINButton;
    private JButton CLEARButton;
    private JTextField TextField;


    public form() {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = TextField.getText();
                String password = String.valueOf(passwordField1.getPassword());

                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your username and password");

                }
                Dbconnector dbc = new Dbconnector();
                Connection con = dbc.getConnection();


                try {
                    String sql = "select * from user where username = ? and password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()) {
                        String designation = rs.getString("designation");
                       // JOptionPane.showMessageDialog(null,"Loggging successful.. role: "+designation);

                        switch (designation.toLowerCase()){
                            case "Admin":
                                new Admindashbord();
                                break;


                            case "Lecturer":
                                new Lecturdashbord();
                                break;

                            case "student":
                                new Studentdashboard();
                                break;

                            case "Technical Officer":
                                new Todashboard();
                                break;

                            default:
                                JOptionPane.showMessageDialog(null,"Invalid designation.. Unknown role: "+designation);
                                break;


                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid username or password");
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    JOptionPane.showMessageDialog(null,"database error..");
                }


            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextField.setText("");
                passwordField1.setText("");


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("form");
        frame.setContentPane(new form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Login form");
        frame.setResizable(false);

    }
}
