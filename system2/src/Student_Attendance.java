import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Student_Attendance {
    public JPanel panal1;
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

    public Student_Attendance() {
        JFrame frame = new JFrame("Student_Attendance");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        viewButtonwhole.addActionListener(e -> {
            String selectedCourse = (String) comboBox3.getSelectedItem();
            String selectedMarkType = (String) comboBox1.getSelectedItem();

            if (selectedCourse == null || selectedCourse.isEmpty() || selectedMarkType == null) {
                JOptionPane.showMessageDialog(null, "Please select both course and mark type.");
                return;
            }

            if (!selectedMarkType.equalsIgnoreCase("CA marks")) {
                JOptionPane.showMessageDialog(null, "This option only displays CA Marks.");
                return;
            }

            DB db = new DB();
            try (Connection con = db.getConnection()) {
                if (con == null) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                    return;
                }

                String sql = "SELECT m.Student_id, m.Course_code, " +
                        "CASE " +
                        "WHEN m.Course_code IN ('ICT2122', 'ICT2142', 'ICT2152', 'ICT2133') THEN " +
                        "    (q.final_quiz_mark) + ((m.Assessment_Mark / 100.0) * 10) + ((m.Mid_Theory + m.Mid_Practical) / 100.0) * 20 " +
                        "WHEN m.Course_code IN ('ICT2113', 'TCS2133') THEN " +
                        "    (q.final_quiz_mark) + ((m.Assessment_Mark / 100.0) * 10) + ((m.Mid_Theory + m.Mid_Practical) / 200.0) * 20 " +
                        "ELSE 0 END AS CA_marks " +
                        "FROM Mark m JOIN QUIZ_mark q ON m.Student_id = q.Student_id AND m.Course_code = q.Course_code " +
                        "WHERE m.Course_code = ?";

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, selectedCourse);
                    try (ResultSet rs = pst.executeQuery()) {

                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Student ID");
                        model.addColumn("Course Code");
                        model.addColumn("CA Marks");
                        model.addColumn("Eligibility");

                        while (rs.next()) {
                            String studentId = rs.getString("Student_id");
                            String courseCode = rs.getString("Course_code");
                            float caMark = rs.getFloat("CA_marks");

                            String eligibility = caMark >= 50 ? "Eligible" : "Not Eligible";

                            model.addRow(new Object[]{studentId, courseCode, String.format("%.2f", caMark), eligibility});
                        }

                        table1.setModel(model);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
        });


        viewButton.addActionListener(e -> {
            String selectedCourse = (String) comboBox1.getSelectedItem();
            String selectedSemester = (String) comboBox2.getSelectedItem();

            if (selectedCourse == null || selectedCourse.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a course.");
                return;
            }
            if (selectedSemester == null || selectedSemester.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a semester.");
                return;
            }

            DB db = new DB();
            try (Connection con = db.getConnection()) {
                if (con == null) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                    return;
                }

                String sql = "SELECT Student_id, Status FROM attendance WHERE Course_code = ? AND Semester = ? ORDER BY Student_id";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, selectedCourse);
                    pst.setString(2, selectedSemester);
                    try (ResultSet rs = pst.executeQuery()) {
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Student ID");
                        model.addColumn("Attendance (%)");
                        model.addColumn("Eligibility");

                        Set<String> studentIds = new HashSet<>();

                        while (rs.next()) {
                            String studentId = rs.getString("Student_id");
                            if (!studentIds.contains(studentId)) {
                                studentIds.add(studentId);

                                int presentCount = 0;
                                int totalCount = 0;

                                String studentAttendanceQuery = "SELECT Status FROM attendance WHERE Student_id = ? AND Course_code = ? AND Semester = ?";
                                try (PreparedStatement pst2 = con.prepareStatement(studentAttendanceQuery)) {
                                    pst2.setString(1, studentId);
                                    pst2.setString(2, selectedCourse);
                                    pst2.setString(3, selectedSemester);
                                    try (ResultSet rs2 = pst2.executeQuery()) {
                                        while (rs2.next()) {
                                            if ("Present".equalsIgnoreCase(rs2.getString("Status"))) {
                                                presentCount++;
                                            }
                                            totalCount++;
                                        }
                                    }
                                }

                                int percentage = totalCount > 0 ? (int) (presentCount * 100.0 / totalCount) : 0;
                                String eligibility = percentage >= 80 ? "Eligible" : "Not Eligible";
                                model.addRow(new Object[]{studentId, percentage + "%", eligibility});
                            }
                        }

                        table1.setModel(model);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new Student_Attendance();
    }
}
