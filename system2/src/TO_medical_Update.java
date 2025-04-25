import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TO_medical_Update {
    public JPanel panal1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel Medical_id;
    private JLabel Student_ID;
    private JTextField textField3;
    private JLabel Course_code;
    private JTextArea textArea1;
    private JTextField textField4;
    private JButton viewButton;
    private JButton updateButton;
    private JButton Back;

    public TO_medical_Update() {
        JFrame frame = new JFrame("TO_medical_Update");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);


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
                        textField4.setText(status); // Pre-fill the status field with the current value
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the new status value from the textField4
                String newStatus = textField4.getText().trim();

                if (newStatus.isEmpty()) {
                    textArea1.setText("Please enter a new status.");
                    return;
                }

                // Retrieve the input values for the other fields
                String medicalId = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String courseCode = textField3.getText().trim();

                // Validate if the required fields are filled
                if (medicalId.isEmpty() || studentId.isEmpty() || courseCode.isEmpty()) {
                    textArea1.setText("Please fill in Medical ID, Student ID, and Course Code.");
                    return;
                }

                // Create the update query
                String query = "UPDATE medical SET Status = ? WHERE Medical_id = ? AND Student_id = ? AND Course_code = ?";

                try {
                    // Get database connection
                    Connection con = DB.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, newStatus);
                    ps.setString(2, medicalId);
                    ps.setString(3, studentId);
                    ps.setString(4, courseCode);

                    // Execute the update
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        textArea1.setText("Medical record updated successfully.");
                    } else {
                        textArea1.setText("Failed to update the medical record.");
                    }

                    // Close resources
                    ps.close();
                    con.close();

                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current window
                new TO_Medical();
            }
        });
    }

    public static void main(String[] args) {
        new TO_medical_Update();
    }
}
