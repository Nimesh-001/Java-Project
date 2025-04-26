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

    public LecStudentMedical() {

        JFrame frame = new JFrame("LecStudentMedical");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        // Populate course codes into comboBox1 (this is just an example, replace with actual course codes)
        String[] courses = {"ICT2142", "ICT2152", "ICT2113","ICT2133","ICT2122"}; // Replace with actual course codes
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
    }

    // Method to load medical data from the database based on student_id and course_code
    private void loadMedicalData(String studentId, String courseCode) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        if (con != null) {
            try {
                String query = "SELECT * FROM Medical WHERE Student_Username = ? AND Course_code = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentId);
                pst.setString(2, courseCode);

                ResultSet rs = pst.executeQuery();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Student ID");
                model.addColumn("Course Code");
                model.addColumn("Medical ID");
                model.addColumn("Medical Condition");
                model.addColumn("Medication");

                // Populate the table with data from the result set
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("Student_Username"),
                            rs.getString("Course_code"),
                            rs.getString("Medical_id"),
                            rs.getString("Medical_condition"),
                            rs.getString("Medication")
                    });
                }

                table1.setModel(model); // Set the table model with fetched data

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
