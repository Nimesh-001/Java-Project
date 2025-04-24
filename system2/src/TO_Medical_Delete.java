import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TO_Medical_Delete {
    private JPanel panal1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JButton viewButton;
    private JButton deleteButton;
    private JTextArea textArea1;

    public TO_Medical_Delete() {
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the input values
                String medicalId = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String courseCode = textField3.getText().trim();

                // Validate the input fields
                if (medicalId.isEmpty() || studentId.isEmpty() || courseCode.isEmpty()) {
                    textArea1.setText("Please enter all the required fields (Medical ID, Student ID, and Course Code).");
                    return;
                }

                // Create the query to fetch medical details based on input values
                String query = "SELECT * FROM medical WHERE Medical_id = ? AND Student_id = ? AND Course_code = ?";

                try {
                    // Get database connection
                    Connection con = DB.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, medicalId);
                    ps.setString(2, studentId);
                    ps.setString(3, courseCode);

                    // Execute query
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        // Display data in the text area
                        String status = rs.getString("Status");
                        String startDate = rs.getString("Start_date");
                        String endDate = rs.getString("End_date");
                        String reason = rs.getString("Reason");

                        textArea1.setText("Medical ID: " + medicalId +
                                "\nStudent ID: " + studentId +
                                "\nCourse Code: " + courseCode +
                                "\nStatus: " + status +
                                "\nStart Date: " + startDate +
                                "\nEnd Date: " + endDate +
                                "\nReason: " + reason);

                        // Enable editing the 'Status' in the textField4
                        //textField4.setText(status); // Pre-fill the status field with the current value
                    } else {
                        textArea1.setText("No medical record found.");
                    }

                    // Close resources
                    rs.close();
                    ps.close();
                    con.close();

                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                String medicalId = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String courseCode = textField3.getText().trim();

                // Check if all fields are filled
                if (medicalId.isEmpty() || studentId.isEmpty() || courseCode.isEmpty()) {
                    textArea1.setText("Please enter all the required fields (Medical ID, Student ID, and Course Code).");
                    return;
                }

                // Create delete SQL query
                String deleteQuery = "DELETE FROM medical WHERE Medical_id = ? AND Student_id = ? AND Course_code = ?";

                try {
                    // Get database connection
                    Connection con = DB.getConnection();
                    PreparedStatement ps = con.prepareStatement(deleteQuery);

                    // Set query parameters
                    ps.setString(1, medicalId);
                    ps.setString(2, studentId);
                    ps.setString(3, courseCode);

                    // Execute deletion
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        textArea1.setText("Medical record deleted successfully.");
                    } else {
                        textArea1.setText("No matching medical record found to delete.");
                    }

                    // Close connection
                    ps.close();
                    con.close();

                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Medical_Delete");
        frame.setContentPane(new TO_Medical_Delete().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
