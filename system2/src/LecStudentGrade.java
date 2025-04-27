import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecStudentGrade {
    private JPanel panel1;
    private JButton backButton;
    private JTable table1;
    private JButton logoutButton;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton clearButton;
    private JComboBox<String> comboBox2;

    public LecStudentGrade() {
        JFrame frame = new JFrame("LecStudentGrade");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Populate comboBox1 with course codes
        comboBox1.addItem("ICT2113");
        comboBox1.addItem("ICT2122");
        comboBox1.addItem("ICT2142");
        comboBox1.addItem("ICT2152");
        comboBox1.addItem("ICT2133");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudentGrade();
            }
        });

        viewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllGrades();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                comboBox1.setSelectedIndex(0);
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
            }
        });
    }

    private void viewStudentGrade() {
        String studentId = textField1.getText();
        String courseCode = (String) comboBox1.getSelectedItem();

        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Student ID");
            return;
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Student ID");
        model.addColumn("Course Code");
        model.addColumn("Grade");
        model.addColumn("GPA");

        try (Connection con = new Dbconnector().getConnection()) {
            String sql = "SELECT * FROM marks WHERE Student_Username = ? AND Course_code = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, courseCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double totalMarks = calculateTotalMarks(rs);
                String grade = calculateGrade(totalMarks);
                double gpa = calculateGPA(grade);

                model.addRow(new Object[]{studentId, courseCode, grade, String.format("%.2f", gpa)});
            } else {
                JOptionPane.showMessageDialog(null, "No data found for the given Student ID and Course Code");
            }

            table1.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewAllGrades() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Student ID");
        model.addColumn("Final GPA");

        try (Connection con = new Dbconnector().getConnection()) {
            String sql = "SELECT DISTINCT Student_Username FROM marks";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String studentId = rs.getString("Student_Username");

                double totalQualityPoints = 0.0;
                int totalCredits = 0;

                String sqlMarks = "SELECT * FROM marks WHERE Student_Username = ?";
                PreparedStatement psMarks = con.prepareStatement(sqlMarks);
                psMarks.setString(1, studentId);
                ResultSet rsMarks = psMarks.executeQuery();

                while (rsMarks.next()) {
                    String courseCode = rsMarks.getString("Course_code");
                    double totalMarks = calculateTotalMarks(rsMarks);
                    String grade = calculateGrade(totalMarks);
                    double gpa = calculateGPA(grade);
                    int credit = getCreditValue(courseCode);

                    totalQualityPoints += (gpa * credit);
                    totalCredits += credit;
                }

                double finalGPA = totalCredits != 0 ? totalQualityPoints / totalCredits : 0.0;
                model.addRow(new Object[]{studentId, String.format("%.2f", finalGPA)});
            }

            table1.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateTotalMarks(ResultSet rs) throws SQLException {
        double quiz1 = rs.getDouble("Quiz1");
        double quiz2 = rs.getDouble("Quiz2");
        double quiz3 = rs.getDouble("Quiz3");
        double quiz4 = rs.getDouble("Quiz4");
        double assessment1 = rs.getDouble("Assessment_01");
        double assessment2 = rs.getDouble("Assessment_02");
        double midPractical = rs.getDouble("Mid_Practical");
        double midTheory = rs.getDouble("Mid_Theory");
        double endPractical = rs.getDouble("End_Practical");
        double endTheory = rs.getDouble("End_Theory");

        // Adjust the weight and total marks calculation
        return quiz1 + quiz2 + quiz3 + quiz4 + assessment1 + assessment2 + midPractical + midTheory + endPractical + endTheory;
    }

    private String calculateGrade(double totalMarks) {
        if (totalMarks >= 85) return "A";
        else if (totalMarks >= 80) return "A-";
        else if (totalMarks >= 75) return "B+";
        else if (totalMarks >= 70) return "B";
        else if (totalMarks >= 65) return "B-";
        else if (totalMarks >= 60) return "C+";
        else if (totalMarks >= 55) return "C";
        else if (totalMarks >= 50) return "C-";
        else if (totalMarks >= 45) return "D+";
        else if (totalMarks >= 40) return "D";
        else return "F";
    }

    private double calculateGPA(String grade) {
        switch (grade) {
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    private int getCreditValue(String courseCode) {
        switch (courseCode) {
            case "ICT2113": return 3;
            case "ICT2122": return 2;
            case "ICT2142": return 2;
            case "ICT2152": return 2;
            case "ICT2133": return 3;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new LecStudentGrade();
    }
}
