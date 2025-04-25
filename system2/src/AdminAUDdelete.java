import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAUDdelete {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JButton DELETEButton;
    private JButton CLEARButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;

    public AdminAUDdelete() {

        JFrame frame = new JFrame("AdminAUDdelete");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter a username to delete.");
                    return;
                }

                String sql = "DELETE FROM USER WHERE Username = ?";
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, username);
                    int result = pst.executeUpdate();

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "User deleted.");
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found.");
                    }

                    textField1.setText("");
                    pst.close();
                    //con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

                }

            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");


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
                    pst.close();
                    rs.close();
                    //con.close();
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



    public static void main(String[] args) {
        new AdminAUDdelete();

    }
}
