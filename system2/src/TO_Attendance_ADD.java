import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TO_Attendance_ADD {
    public JPanel panel1;
    private JTextField textField1;  // Course Code
    private JTextField textField2;  // Student Username
    private JTextField textField3;  // Session Type
    private JTextField textField4;  // Session Date
    private JTextField textField5;  // Status
    private JTextField textField6;  // TO Username
    private JTextField textField7;  // Medical ID
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JLabel Session_Date;
    private JLabel Status;
    private JLabel To_ID;
    private JLabel Medical_Id;
    private JTextArea textArea1;
    private JButton addButton;
    private JButton Back;

    public TO_Attendance_ADD() {

        JFrame frame = new JFrame("TO_Attendance_ADD");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String sessionType = textField3.getText().trim();
                String sessionDate = textField4.getText().trim();
                String status = textField5.getText().trim();
                String toId = textField6.getText().trim();
                String medicalId = textField7.getText().trim();

                // Check if required fields are empty
                if (courseCode.isEmpty() || studentId.isEmpty() || sessionType.isEmpty() || sessionDate.isEmpty()) {
                    textArea1.setText("Course Code, Student Username, Session Type, and Session Date are required.");
                    return;
                }

                // Assuming Dbconnector is a utility class that gets a database connection.
                Dbconnector db = new Dbconnector();
                Connection con = db.getConnection();

                // Corrected query to match table structure
                String query = "INSERT INTO Attendance (Course_code, Student_Username, Session_Type, Session_date, Status, TO_Username, Medical_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, courseCode);
                    ps.setString(2, studentId);
                    ps.setString(3, sessionType);
                    ps.setString(4, sessionDate);
                    ps.setString(5, status.isEmpty() ? null : status); // Allow NULL if status is empty
                    ps.setString(6, toId.isEmpty() ? null : toId); // Allow NULL if TO Username is empty
                    ps.setString(7, medicalId.isEmpty() ? null : medicalId); // Allow NULL if Medical ID is empty

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        textArea1.setText("Data inserted successfully!");
                        textField1.setText(""); textField2.setText(""); textField3.setText("");
                        textField4.setText(""); textField5.setText(""); textField6.setText(""); textField7.setText("");
                    } else {
                        textArea1.setText("Insertion failed.");
                    }
                } catch (SQLException ex) {
                    // Print the exception stack trace for better error tracking
                    ex.printStackTrace();
                    textArea1.setText("Database error: " + ex.getMessage());
                } finally {
                    // Close the connection (if Dbconnector doesn't handle it internally)
                    try {
                        if (con != null && !con.isClosed()) {
                            con.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current window
                new TO_Attendance(); // Open the previous page
            }
        });
    }

    public static void main(String[] args) {
        new TO_Attendance_ADD();  // Open the form
    }
}
