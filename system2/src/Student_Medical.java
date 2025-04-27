import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

public class Student_Medical {
    public JPanel panal1;
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
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Display medical records from the 'medical' table
        Dbconnector dbc = new Dbconnector();  // Using your Dbconnector class
        Connection con = dbc.getConnection();

        if (con != null) {
            try {
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
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            } finally {
                try {
                    con.close(); // Always close the connection after use
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed.");
        }
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
    }

    public static void main(String[] args) {
        new Student_Medical();
    }
}
