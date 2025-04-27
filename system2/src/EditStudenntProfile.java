import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditStudenntProfile {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField4;
    private JTextField textField5;
    private JButton updateButton;
    private JButton resetButton;
    private JButton backButton;
    private JButton logoutButton;

    public EditStudenntProfile() {

        JFrame frame = new JFrame("EditStudenntProfile");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();


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

                if (!textField4.getText().isEmpty()) {
                    updateField("Phone_Number", textField4.getText(), username);
                }

                if (!textField5.getText().isEmpty()) {
                    updateField("Profile_Pic_Path", textField5.getText(), username);
                }

                JOptionPane.showMessageDialog(null, "Student details updated successfully.");
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

    private void updateField(String column, String value, String username) {
        String sql = "UPDATE STUDENT SET " + column + " = ? WHERE Username = ?";

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

    private void clearFields() {
        textField1.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        new EditStudenntProfile();//go to chavindya..

    }
}
