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

    public static JFrame frame;


    public form() {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // System.out.println("Login button clicked");
                String username = TextField.getText();
                String password = String.valueOf(passwordField1.getPassword());

                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your username and password");
                    return;

                }
                Dbconnector dbc = new Dbconnector();
                Connection con = dbc.getConnection();


                try {
                    String sql = "select * from USER where Username = ? and BINARY Password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()) {
                        String designation = rs.getString("designation");
                        //JOptionPane.showMessageDialog(null,"Loggging successful.. role: "+designation);

                        switch (designation.toLowerCase()){
                            case "admin":
                                frame.dispose();
                                new Admindashbord();
                                break;


                            case "lecturer":
                                frame.dispose();
                                new Lecturdashbord();
                                break;

                            case "student":
                                frame.dispose();
                                new Studentdashboard();
                                break;

                            case "technical officer":
                                frame.dispose();
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
                finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
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
        frame = new JFrame("form");
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
