import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class CourseManagementForm {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;
    private JButton clearButton;
    private JComboBox comboBox1;

    public CourseManagementForm() {
        JFrame frame = new JFrame("CourseManagementForm");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                comboBox1.setSelectedIndex(0);
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admindashbord();

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() ||
                        textField4.getText().isEmpty() || textField5.getText().isEmpty() ||
                        textField6.getText().isEmpty() || textField7.getText().isEmpty() ||
                        textField8.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields to add a course.");
                    return;
                }

                String sql= "INSERT INTO Course_unit (Course_code, CourseName, Course_type, Theory_hours, Practical_hours, Credits, Lecturer_Username, Admin_Username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, textField1.getText());
                    pst.setString(2, textField2.getText());
                    pst.setString(3, comboBox1.getSelectedItem().toString());
                    pst.setString(4, String.valueOf(Integer.parseInt(textField4.getText())));
                    pst.setString(5, String.valueOf(Integer.parseInt(textField5.getText())));
                    pst.setString(6, String.valueOf(Integer.parseInt(textField6.getText())));
                    pst.setString(7,textField7.getText());
                    pst.setString(8,textField8.getText());

                    pst.execute();
                    JOptionPane.showMessageDialog(null,"success");
                    pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"error.."+ex.getMessage());
                }

            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseCode = textField1.getText();

                if (courseCode.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Course Code to update.");
                    return;
                }

                try {
                    if (!textField2.getText().isEmpty()) {
                        updateField("CourseName", textField2.getText(), courseCode);
                    }
                    if (comboBox1.getSelectedIndex() != 0) {
                        updateField("Course_type", comboBox1.getSelectedItem().toString(), courseCode);
                    }
                    if (!textField4.getText().isEmpty()) {
                        updateField("Theory_hours", textField4.getText(), courseCode);
                    }
                    if (!textField5.getText().isEmpty()) {
                        updateField("Practical_hours", textField5.getText(), courseCode);
                    }
                    if (!textField6.getText().isEmpty()) {
                        updateField("Credits", textField6.getText(), courseCode);
                    }
                    if (!textField7.getText().isEmpty()) {
                        updateField("Lecturer_Username", textField7.getText(), courseCode);
                    }
                    if (!textField8.getText().isEmpty()) {
                        updateField("Admin_Username", textField8.getText(), courseCode);
                    }

                    JOptionPane.showMessageDialog(null, "Course details updated successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseCode = textField1.getText();
                if (courseCode.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter a Course Code to delete.");
                    return;
                }

                String sql = "DELETE FROM Course_unit WHERE Course_code = ?";
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, courseCode);
                    int result = pst.executeUpdate();

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Course deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Course not found.");
                    }

                    textField1.setText("");
                    pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT * FROM Course_unit";

                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    // Create a new DefaultTableModel with columns
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Course_code");
                    model.addColumn("CourseName");
                    model.addColumn("Course_type");
                    model.addColumn("Theory_hours");
                    model.addColumn("Practical_hours");
                    model.addColumn("Credits");
                    model.addColumn("Lecturer_Username");
                    model.addColumn("Admin_Username");

                    // Iterate through the ResultSet and add each row to the model
                    while (rs.next()) {
                        String courseCode = rs.getString("Course_code");
                        String courseName = rs.getString("CourseName");
                        String courseType = rs.getString("Course_type");
                        String theoryHours = rs.getString("Theory_hours");
                        String practicalHours = rs.getString("Practical_hours");
                        String credits = rs.getString("Credits");
                        String lecturerUsername = rs.getString("Lecturer_Username");
                        String adminUsername = rs.getString("Admin_Username");

                        model.addRow(new Object[]{
                                courseCode, courseName, courseType,
                                theoryHours, practicalHours, credits,
                                lecturerUsername, adminUsername
                        });
                    }

                    // Set the table model
                    table1.setModel(model);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
    }

    public void updateField(String column, String value, String courseCode) {
        String sql = "UPDATE Course_unit SET " + column + " = ? WHERE Course_code = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, value);
            pst.setString(2, courseCode);
            pst.executeUpdate();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Update failed for " + column + ": " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        new CourseManagementForm();

    }
}
