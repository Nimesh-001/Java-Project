import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminAUDadd {
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

                Dbconnector dbc = new Dbconnector();
                Connection con = dbc.getConnection();

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

    private void clearFields() {
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
