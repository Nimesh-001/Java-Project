import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Student_Attendance {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton backButton;
    private JComboBox comboBox1; // For Course Selection
    private JComboBox comboBox2; // For Other Selection (e.g., Semester)
    private JButton viewButton;
    private JButton logoutButton;
    private JComboBox comboBox3; // For Whole Course Selection
    private JButton viewButtonwhole;
    private JTable table1;
    private JPanel panal2;

    public Student_Attendance() {
        JFrame frame = new JFrame("Student_Attendance");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Action for viewButtonwhole
        viewButtonwhole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) comboBox3.getSelectedItem();
                if (selectedCourse == null || selectedCourse.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a course.");
                    return;
                }

                Dbconnector db = new Dbconnector();
                try (Connection con = db.getConnection()) { // Use try-with-resources to auto-close connection
                    if (con == null) {
                        JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                        return;
                    }

                    // SQL query to fetch attendance data for a specific course
                    String sql = "SELECT Student_id, Status FROM attendance WHERE Course_code = ? ORDER BY Student_id";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, selectedCourse);
                        try (ResultSet rs = pst.executeQuery()) {
                            // Create a table model
                            DefaultTableModel model = new DefaultTableModel();
                            model.addColumn("Student ID");
                            model.addColumn("Attendance (%)");
                            model.addColumn("Eligibility");

                            // Set to track unique student IDs
                            Set<String> studentIds = new HashSet<>();

                            while (rs.next()) {
                                String studentId = rs.getString("Student_id");
                                String status = rs.getString("Status");

                                // Check if the student is already processed
                                if (!studentIds.contains(studentId)) {
                                    studentIds.add(studentId);

                                    // Reset the attendance counts for the student
                                    int presentCount = 0;
                                    int totalCount = 0;

                                    // Query for the specific student's attendance data
                                    String studentAttendanceQuery = "SELECT Status FROM attendance WHERE Student_id = ? AND Course_code = ?";
                                    try (PreparedStatement pst2 = con.prepareStatement(studentAttendanceQuery)) {
                                        pst2.setString(1, studentId);
                                        pst2.setString(2, selectedCourse);
                                        try (ResultSet rs2 = pst2.executeQuery()) {
                                            while (rs2.next()) {
                                                String studentStatus = rs2.getString("Status");
                                                if ("Present".equalsIgnoreCase(studentStatus)) {
                                                    presentCount++;
                                                }
                                                totalCount++;
                                            }
                                        }
                                    }

                                    // Calculate attendance percentage
                                    int percentage = totalCount > 0 ? (int) (presentCount * 100.0 / totalCount) : 0;

                                    // Determine eligibility
                                    String eligibility = percentage >= 80 ? "Eligible" : "Not Eligible";

                                    // Add data to the table
                                    model.addRow(new Object[]{studentId, percentage + "%", eligibility});
                                }
                            }

                            // Set the table model to display the data
                            table1.setModel(model);

                            // Optional: Adjust column widths (you can customize these values)
                            table1.getColumnModel().getColumn(0).setPreferredWidth(100); // Student ID column width
                            table1.getColumnModel().getColumn(1).setPreferredWidth(150); // Attendance percentage column width
                            table1.getColumnModel().getColumn(2).setPreferredWidth(100); // Eligibility column width
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + ex.getMessage());
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected course and semester from combo boxes
                String selectedCourse = (String) comboBox1.getSelectedItem();
                String selectedSemester = (String) comboBox2.getSelectedItem();

                // Validate the selections
                if (selectedCourse == null || selectedCourse.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a course.");
                    return;
                }
                if (selectedSemester == null || selectedSemester.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a semester.");
                    return;
                }

                Dbconnector db = new Dbconnector();
                try (Connection con = db.getConnection()) {
                    if (con == null) {
                        JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                        return;
                    }

                    // SQL query to fetch attendance data for the selected course and semester
                    String sql = "SELECT Student_id, Status FROM attendance WHERE Course_code = ? AND Semester = ? ORDER BY Student_id";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, selectedCourse);
                        pst.setString(2, selectedSemester);
                        try (ResultSet rs = pst.executeQuery()) {
                            // Create a table model
                            DefaultTableModel model = new DefaultTableModel();
                            model.addColumn("Student ID");
                            model.addColumn("Attendance (%)");
                            model.addColumn("Eligibility");

                            // Set to track unique student IDs
                            Set<String> studentIds = new HashSet<>();

                            while (rs.next()) {
                                String studentId = rs.getString("Student_id");
                                String status = rs.getString("Status");

                                // Check if the student is already processed
                                if (!studentIds.contains(studentId)) {
                                    studentIds.add(studentId);

                                    // Reset the attendance counts for the student
                                    int presentCount = 0;
                                    int totalCount = 0;

                                    // Query for the specific student's attendance data
                                    String studentAttendanceQuery = "SELECT Student_id, Status FROM attendance WHERE Course_code = ? AND Student_id = ? ORDER BY Student_id\n";
                                    try (PreparedStatement pst2 = con.prepareStatement(studentAttendanceQuery)) {
                                        pst2.setString(1, studentId);
                                        pst2.setString(2, selectedCourse);
                                        pst2.setString(3, selectedSemester);
                                        try (ResultSet rs2 = pst2.executeQuery()) {
                                            while (rs2.next()) {
                                                String studentStatus = rs2.getString("Status");
                                                if ("Present".equalsIgnoreCase(studentStatus)) {
                                                    presentCount++;
                                                }
                                                totalCount++;
                                            }
                                        }
                                    }

                                    // Calculate attendance percentage
                                    int percentage = totalCount > 0 ? (int) (presentCount * 100.0 / totalCount) : 0;

                                    // Determine eligibility
                                    String eligibility = percentage >= 80 ? "Eligible" : "Not Eligible";

                                    // Add data to the table
                                    model.addRow(new Object[]{studentId, percentage + "%", eligibility});
                                }
                            }

                            // Set the table model to display the data
                            table1.setModel(model);

                            // Optional: Adjust column widths (you can customize these values)
                            table1.getColumnModel().getColumn(0).setPreferredWidth(100); // Student ID column width
                            table1.getColumnModel().getColumn(1).setPreferredWidth(150); // Attendance percentage column width
                            table1.getColumnModel().getColumn(2).setPreferredWidth(100); // Eligibility column width
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + ex.getMessage());
                }
            }
        });

    }

    public static void main(String[] args) {
        new Student_Attendance();
    }
}
