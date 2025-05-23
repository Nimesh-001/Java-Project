import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAUDupdate {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton UPDATEButton;
    private JButton CLEARButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;

    public AdminAUDupdate() {

        JFrame frame = new JFrame("AdminAUDupdate");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Username to update.");
                    return;
                }

                if (!textField2.getText().isEmpty()) {
                    updateField("First_Name", textField2.getText(), username);
                }
                if (!textField3.getText().isEmpty()) {
                    updateField("Last_Name", textField3.getText(), username);
                }
                if (!textField4.getText().isEmpty()) {
                    updateField("Designation", textField4.getText(), username);
                }
                if (!textField5.getText().isEmpty()) {
                    updateField("Phone_Number", textField5.getText(), username);
                }
                if (!textField6.getText().isEmpty()) {
                    updateField("Email", textField6.getText(), username);
                }

                JOptionPane.showMessageDialog(null, "User details updated successfully.");

                clearFields();

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


                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Username");
                    model.addColumn("First Name");
                    model.addColumn("Last Name");
                    model.addColumn("Designation");
                    model.addColumn("Phone Number");
                    model.addColumn("Email");


                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("Username"),
                                rs.getString("First_Name"),
                                rs.getString("Last_Name"),
                                rs.getString("Designation"),
                                rs.getString("Phone_Number"),
                                rs.getString("Email")
                        });
                    }


                    table1.setModel(model);
                    rs.close();
                    //pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
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


    public void updateField(String column, String value, String username) {
        String sql = "UPDATE USER SET " + column + " = ? WHERE Username = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, value);
            pst.setString(2, username);
            pst.executeUpdate();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Update failed for " + column + ": " + ex.getMessage());
        }
    }


    public void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    public static void main(String[] args) {
        new AdminAUDupdate();

    }
}
