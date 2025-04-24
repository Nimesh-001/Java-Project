import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LecMarks {
    private JPanel panel1;
    private JTextField textField1;
    private JButton ADDButton;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ADDButton1;
    private JButton ADDButton2;
    private JButton ADDButton3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton ADDButton4;
    private JButton ADDButton5;
    private JButton ADDButton6;
    private JButton ADDButton7;
    private JButton ADDButton8;
    private JButton backButton;
    private JTextField textField10;

    public LecMarks() {
        JFrame frame = new JFrame("LecMarks");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);


        backButton.addActionListener(e -> {
            frame.dispose();
            new Lecturdashbord(); 
        });


        ADDButton.addActionListener(e -> insertOrUpdateMark("quiz1", textField2.getText()));
        ADDButton1.addActionListener(e -> insertOrUpdateMark("quiz2", textField3.getText()));
        ADDButton2.addActionListener(e -> insertOrUpdateMark("quiz3", textField4.getText()));
        ADDButton3.addActionListener(e -> insertOrUpdateMark("quiz4", textField5.getText()));
        ADDButton4.addActionListener(e -> insertOrUpdateMark("assessment", textField6.getText()));
        ADDButton5.addActionListener(e -> insertOrUpdateMark("mid_theory", textField7.getText()));
        ADDButton6.addActionListener(e -> insertOrUpdateMark("mid_practical", textField8.getText()));
        ADDButton7.addActionListener(e -> insertOrUpdateMark("end_theory", textField9.getText()));
        ADDButton8.addActionListener(e -> insertOrUpdateMark("end_practical", textField10.getText()));
    }

    private void insertOrUpdateMark(String column, String value) {
        String Username = textField1.getText().trim();
        String courseCode = comboBox2.getSelectedItem().toString();

        if (Username.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Student ID or Value is empty.");
            return;
        }

        try {
            Dbconnector db = new Dbconnector();
            Connection con = db.getConnection();


            String updateSQL = "UPDATE marks SET " + column + " = ? WHERE Username = ? AND course_code = ?";
            PreparedStatement pstmt = con.prepareStatement(updateSQL);
            pstmt.setString(1, value);
            pstmt.setString(2, Username);
            pstmt.setString(3, courseCode);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, column + " updated successfully.");
            } else {

                String insertSQL = "INSERT INTO marks (Username, course_code, " + column + ") VALUES (?, ?, ?)";
                pstmt = con.prepareStatement(insertSQL);
                pstmt.setString(1, Username);
                pstmt.setString(2, courseCode);
                pstmt.setString(3, value);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, column + " inserted successfully.");
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LecMarks();
    }
}
