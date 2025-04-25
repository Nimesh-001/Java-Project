import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AdminAUDadd {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField8;
    private JButton ADDButton;
    private JButton CLEARButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;
    private JPasswordField passwordField1;



    public AdminAUDadd() {

        JFrame frame = new JFrame("AdminAUDadd");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);



        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() ||
                        textField4.getText().isEmpty() || textField5.getText().isEmpty() || textField6.getText().isEmpty() ||
                        passwordField1.getPassword().length == 0 || textField8.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill all the fields.");
                    return;
                }



                String sql = "INSERT INTO USER (Username, First_Name, Last_Name, Designation, Phone_Number, Email, Password, Profile_Pic_Path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, textField1.getText());
                    pst.setString(2, textField2.getText());
                    pst.setString(3, textField3.getText());
                    pst.setString(4, textField4.getText());
                    pst.setString(5, textField5.getText());
                    pst.setString(6, textField6.getText());
                    pst.setString(7, new String(passwordField1.getPassword()));
                    pst.setString(8, textField8.getText());

                    pst.execute();
                    JOptionPane.showMessageDialog(null, "User added successfully!");
                    pst.close();

                    clearFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage());
                }


            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();

            }
        });

        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT * FROM USER";

                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    // Create a new DefaultTableModel with columns
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Username");
                    model.addColumn("First_Name");
                    model.addColumn("Last_Name");
                    model.addColumn("Designation");
                    model.addColumn("Phone_Number");
                    model.addColumn("Email");
                    //model.addColumn("Password");
                    //model.addColumn("Profile_Pic_Path");

                    // Iterate through the ResultSet and add each row to the model
                    while (rs.next()) {
                        String username = rs.getString("Username");
                        String firstName = rs.getString("First_Name");
                        String lastName = rs.getString("Last_Name");
                        String designation = rs.getString("Designation");
                        String phoneNumber = rs.getString("Phone_Number");
                        String email = rs.getString("Email");
                        //String password = rs.getString("Password");
                       // String profilePicPath = rs.getString("Profile_Pic_Path");

                        model.addRow(new Object[]{username, firstName, lastName, designation, phoneNumber, email,/* password, profilePicPath*/});
                    }

                    // Set the table model
                    table1.setModel(model);

                } catch (Exception ex) {
                    // Handle any errors that may occur
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminAUD();

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

    public void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        passwordField1.setText("");
        textField8.setText("");
    }

    public static void main(String[] args) {
        new AdminAUDadd();

    }
}
