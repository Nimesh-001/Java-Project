import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LecMarks {
    private JPanel panel1;
    private JTextField textField1; // Student_Username (manually entered)
    private JComboBox comboBox1; // Lecturer_Username
    private JComboBox comboBox2; // Course_code
    private JTextField textField2; // Quiz1
    private JTextField textField3; // Quiz2
    private JTextField textField4; // Quiz3
    private JTextField textField5; // Quiz4
    private JTextField textField6; // Assessment_01
    private JTextField textField7; // Assessment_02
    private JTextField textField8; // Mid_Practical
    private JTextField textField9; // Mid_Theory
    private JTextField textField10; // End_Practical
    private JTextField textField11; // End_Theory
    private JButton addMarksButton;
    private JButton backButton;
    private JButton logOutButton;
    private JComboBox comboBox3; // For selecting Student_Username to delete
    private JComboBox comboBox4; // For selecting Course_code to delete
    private JButton DELETEButton;
    private JTextField textField12; // (not used in the provided code)

    public LecMarks() {
        JFrame frame = new JFrame("LecMarks");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        loadCourseCodes(); // only course codes are loaded

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Lecturdashbord();
            }
        });

        addMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMarks();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });

        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMarks();
            }
        });
    }

    private void loadCourseCodes() {
        try {
            Dbconnector dbc = new Dbconnector();
            Connection conn = dbc.getConnection();
            String sql = "SELECT DISTINCT Course_code FROM marks";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                comboBox4.addItem(rs.getString("Course_code"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addMarks() {
        String studentUsername = textField1.getText().trim();
        String lecturerUsername = comboBox1.getSelectedItem().toString(); // lecturer is still selected from comboBox1
        String courseCode = comboBox2.getSelectedItem().toString();

        if (studentUsername.isEmpty() || lecturerUsername.isEmpty() || courseCode.isEmpty() ||
                textField2.getText().isEmpty() || textField3.getText().isEmpty() ||
                textField4.getText().isEmpty() || textField5.getText().isEmpty() ||
                textField6.getText().isEmpty() || textField7.getText().isEmpty() ||
                textField8.getText().isEmpty() || textField9.getText().isEmpty() ||
                textField10.getText().isEmpty() || textField11.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float quiz1 = Float.parseFloat(textField2.getText());
            float quiz2 = Float.parseFloat(textField3.getText());
            float quiz3 = Float.parseFloat(textField4.getText());
            float quiz4 = Float.parseFloat(textField5.getText());
            float assessment1 = Float.parseFloat(textField6.getText());
            float assessment2 = Float.parseFloat(textField7.getText());
            float midPractical = Float.parseFloat(textField8.getText());
            float midTheory = Float.parseFloat(textField9.getText());
            float endPractical = Float.parseFloat(textField10.getText());
            float endTheory = Float.parseFloat(textField11.getText());

            if (quiz1 < 0 || quiz1 > 100 || quiz2 < 0 || quiz2 > 100 || quiz3 < 0 || quiz3 > 100 ||
                    quiz4 < 0 || quiz4 > 100 || assessment1 < 0 || assessment1 > 100 || assessment2 < 0 || assessment2 > 100 ||
                    midPractical < 0 || midPractical > 100 || midTheory < 0 || midTheory > 100 ||
                    endPractical < 0 || endPractical > 100 || endTheory < 0 || endTheory > 100) {
                JOptionPane.showMessageDialog(null, "Marks should be between 0 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Dbconnector dbc = new Dbconnector();
            Connection conn = dbc.getConnection();

            String sql = "INSERT INTO marks (Student_Username, Lecturer_Username, Course_code, " +
                    "Quiz1, Quiz2, Quiz3, Quiz4, Assessment_01, Assessment_02, Mid_Practical, Mid_Theory, End_Practical, End_Theory) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, studentUsername);
            pst.setString(2, lecturerUsername);
            pst.setString(3, courseCode);
            pst.setFloat(4, quiz1);
            pst.setFloat(5, quiz2);
            pst.setFloat(6, quiz3);
            pst.setFloat(7, quiz4);
            pst.setFloat(8, assessment1);
            pst.setFloat(9, assessment2);
            pst.setFloat(10, midPractical);
            pst.setFloat(11, midTheory);
            pst.setFloat(12, endPractical);
            pst.setFloat(13, endTheory);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Marks Added Successfully!");

            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values for marks.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void deleteMarks() {
        String studentUsername = textField12.getText().trim();
        String courseCode = (String) comboBox4.getSelectedItem();

        if (studentUsername == null || courseCode == null) {
            JOptionPane.showMessageDialog(null, "Please select Student Username and Course Code.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Dbconnector dbc = new Dbconnector();
                Connection conn = dbc.getConnection();

                String sql = "DELETE FROM marks WHERE Student_Username = ? AND Course_code = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, studentUsername);
                pst.setString(2, courseCode);

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Marks deleted successfully!");
                    textField12.setText("");
                    comboBox4.removeItem(courseCode);
                } else {
                    JOptionPane.showMessageDialog(null, "Record not found!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
        textField10.setText("");
        textField11.setText("");
    }

    public static void main(String[] args) {
        new LecMarks();
    }
}
