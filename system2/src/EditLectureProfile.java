import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditLectureProfile {

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


    public EditLectureProfile() {

        JFrame frame = new JFrame("EditLectureProfile");
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
                //wasantha code

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
        new EditLectureProfile();//go to lecture editprofile(wasanth)

    }
}
