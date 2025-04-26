import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TO_Medical_Add {
    public JPanel panal1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel Medical_id;
    private JLabel Submission_Date;
    private JLabel status;
    private JLabel Start_Date;
    private JLabel End_Date;
    private JLabel Reason;
    private JTextField textField8;
    private JTextField textField9;
    private JLabel Course_code;
    private JLabel Student_ID;
    private JLabel TO_ID;
    private JTextArea textArea1;
    private JButton Addbutton;
    private JButton Back;

    public TO_Medical_Add() {
        JFrame frame = new JFrame("TO_Medical_Add");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        Addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String medicalId = textField1.getText().trim();
                String submissionDate = textField2.getText().trim();
                String status = textField3.getText().trim();
                String startDate = textField4.getText().trim();
                String endDate = textField5.getText().trim();
                String reason = textField6.getText().trim();
                String courseCode = textField7.getText().trim();
                String studentId = textField8.getText().trim();
                String toId = textField9.getText().trim();

                // Check if any required field is empty
                if (medicalId.isEmpty() || submissionDate.isEmpty() || status.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty() || courseCode.isEmpty() || studentId.isEmpty() || toId.isEmpty()) {
                    textArea1.setText("Please fill in all fields.");
                    return;
                }

                // Create the SQL insert query
                String query = "INSERT INTO medical (Medical_id, Submission_date, Status, Start_date, End_date, Reason, Course_code, Student_id, TO_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try {
                    // Get a connection to the database
                    Connection con = DB.getConnection();

                    // Prepare the statement
                    PreparedStatement ps = con.prepareStatement(query);

                    // Set the parameters for the query
                    ps.setString(1, medicalId);
                    ps.setString(2, submissionDate);
                    ps.setString(3, status);
                    ps.setString(4, startDate);
                    ps.setString(5, endDate);
                    ps.setString(6, reason);
                    ps.setString(7, courseCode);
                    ps.setString(8, studentId);
                    ps.setString(9, toId);

                    // Execute the query
                    int rowsAffected = ps.executeUpdate();

                    // Check if the record was inserted successfully
                    if (rowsAffected > 0) {
                        textArea1.setText("Medical record added successfully.");
                    } else {
                        textArea1.setText("Failed to add medical record.");
                    }

                    // Close the statement and connection
                    ps.close();
                    con.close();

                } catch (SQLException ex) {
                    // Handle SQL exceptions
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

        new TO_Medical_Add();
    }
}
