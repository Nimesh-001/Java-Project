import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Student_Medical {
    public JPanel panel1;
    private JTable table1;
    private JTextField textField7; // For future use
    private JButton tables;
    private JPanel panal2;
    private JButton backButton;
    private JComboBox comboBox1;
    private JButton logoutButton;
    private JButton viewButton;

    public Student_Medical() {
        JFrame frame = new JFrame("Student_Medical");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Display medical records from the 'medical' table
        try {
            Dbconnector db = new Dbconnector(); // Assuming this is your connection class
            Connection con = db.getConnection();

            if (con != null) {
                String sql = "SELECT Medical_id, Submission_date, Status, Start_date, End_date, Reason, Course_code, Student_id FROM medical ORDER BY Submission_date DESC";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Medical ID");
                model.addColumn("Submission Date");
                model.addColumn("Status");
                model.addColumn("Start Date");
                model.addColumn("End Date");
                model.addColumn("Reason");
                model.addColumn("Course Code");
                model.addColumn("Student ID");

                while (rs.next()) {
                    String mid = rs.getString("Medical_id");
                    String subDate = rs.getString("Submission_date");
                    String status = rs.getString("Status");
                    String start = rs.getString("Start_date");
                    String end = rs.getString("End_date");
                    String reason = rs.getString("Reason");
                    String course = rs.getString("Course_code");
                    String student = rs.getString("Student_id");


                    model.addRow(new Object[]{mid, subDate, status, start, end, reason, course, student});
                }

                table1.setModel(model);

                // Optional: Adjust column widths
                table1.getColumnModel().getColumn(0).setPreferredWidth(100); // Medical ID
                table1.getColumnModel().getColumn(5).setPreferredWidth(200); // Reason
            } else {
                JOptionPane.showMessageDialog(null, "Database connection failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Student_Medical();
    }
}
