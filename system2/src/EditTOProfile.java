import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditTOProfile {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton updateButton;
    private JButton resetButton;
    private JTextField textField6;
    private JButton backButton;
    private JButton logoutButton;

    public EditTOProfile() {

        JFrame frame = new JFrame("EditTOProfile");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //new Todashboard();


            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
        updateButton.addActionListener(new ActionListener() {
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
                    updateField("Phone_Number", textField4.getText(), username);
                }
                if (!textField5.getText().isEmpty()) {
                    updateField("Profile_Pic_Path", textField5.getText(), username);
                }
                if (!textField6.getText().isEmpty()) {
                    updateField("Email", textField6.getText(), username);
                }

                JOptionPane.showMessageDialog(null, "User details updated successfully.");
                clearFields();

            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
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

    public static void main(String[] args) {
        new EditTOProfile();//go to nitya edit profile button

    }
}
