import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecStudentMedical {
    private JPanel panel1;
    private JTextField textField1; // Input field for student ID
    private JButton viewButton;
    private JComboBox<String> comboBox1; // Drop-down for course code selection
    private JButton backButton;
    private JTable table1; // Table to display medical data
    private JButton logOutButton;

    public LecStudentMedical() {
        // Create a JFrame to hold the panel
        JFrame frame = new JFrame("LecStudentMedical");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Populate course codes into comboBox1 (this is just an example, replace with actual course codes)
        String[] courses = {"ICT2142", "ICT2152", "ICT2113", "ICT2133", "ICT2122"}; // Replace with actual course codes
        comboBox1.setModel(new DefaultComboBoxModel<>(courses));

        // Back Button to navigate back to dashboard
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord(); // Assuming this is your dashboard class
            }
        });

        // View Button - fetch data from database when clicked
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = textField1.getText();
                String courseCode = comboBox1.getSelectedItem().toString();

                // Call method to load the data from the medical table
                loadMedicalData(studentId, courseCode);
            }
        });

        // Log out button - assumes you have a `form` class to go back to the login screen
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form();
            }
        });
    }

    // Method to load medical data from the database based on student_id and course_code
    private void loadMedicalData(String studentId, String courseCode) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        if (con != null) {
            try {
                // SQL query to fetch data based on student and course code
                String query = "SELECT * FROM Medical WHERE Student_Username = ? AND Course_code = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentId);
                pst.setString(2, courseCode);

                // Execute the query
                ResultSet rs = pst.executeQuery();

                // Set up the table model with correct column names
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Medical ID");
                model.addColumn("Submission Date");
                model.addColumn("Status");
                model.addColumn("Start Date");
                model.addColumn("End Date");
                model.addColumn("Reason");
                model.addColumn("Course Code");
                model.addColumn("Student Username");
                model.addColumn("TO Username");

                // Populate the table with data from the result set
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("Medical_id"),
                            rs.getString("Submission_date"),
                            rs.getString("Status"),
                            rs.getString("Start_date"),
                            rs.getString("End_date"),
                            rs.getString("Reason"),
                            rs.getString("Course_code"),
                            rs.getString("Student_Username"),
                            rs.getString("TO_Username")
                    });
                }

                // Set the table model with the populated data
                table1.setModel(model);

                // Close resources
                rs.close();
                pst.close();
                con.close();

            } catch (SQLException ex) {
                System.out.println("Error loading medical data: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new LecStudentMedical();
    }
}
