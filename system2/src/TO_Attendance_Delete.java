import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TO_Attendance_Delete {
    public JPanel panal1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JLabel Session_Date;
    private JButton viewButton;
    private JButton deleteButton;
    private JTextArea textArea1;
    private JButton backButton;

    public TO_Attendance_Delete() {
        JFrame frame = new JFrame("TO_Attendance_Delete");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Button to view attendance record
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String sessionType = textField3.getText().trim();
                String sessionDate = textField4.getText().trim();

                if (courseCode.isEmpty() || studentId.isEmpty() || sessionType.isEmpty() || sessionDate.isEmpty()) {
                    textArea1.setText("Please enter all four key fields.");
                    return;
                }

                // Using your Dbconnector to get the connection
                Dbconnector dbc = new Dbconnector();
                try (Connection con = dbc.getConnection()) {
                    if (con == null) {
                        textArea1.setText("Failed to connect to the database.");
                        return;
                    }

                    // Query to fetch record
                    String query = "SELECT * FROM attendance WHERE Course_code = ? AND Student_id = ? AND Session_Type = ? AND Session_date = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, courseCode);
                    ps.setString(2, studentId);
                    ps.setString(3, sessionType);
                    ps.setString(4, sessionDate);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String status = rs.getString("Status");
                        String toId = rs.getString("TO_id");
                        String medicalId = rs.getString("Medical_id");

                        textArea1.setText("Course Code: " + courseCode +
                                "\nStudent ID: " + studentId +
                                "\nSession Type: " + sessionType +
                                "\nSession Date: " + sessionDate +
                                "\nStatus: " + status +
                                "\nTO ID: " + toId +
                                "\nMedical ID: " + medicalId);
                    } else {
                        textArea1.setText("No record found.");
                    }

                    rs.close();
                    ps.close();
                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });

        // Button to delete attendance record
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String sessionType = textField3.getText().trim();
                String sessionDate = textField4.getText().trim();

                if (courseCode.isEmpty() || studentId.isEmpty() || sessionType.isEmpty() || sessionDate.isEmpty()) {
                    textArea1.setText("Please enter all four key fields to delete.");
                    return;
                }

                // Using your Dbconnector to get the connection
                Dbconnector dbc = new Dbconnector();
                try (Connection con = dbc.getConnection()) {
                    if (con == null) {
                        textArea1.setText("Failed to connect to the database.");
                        return;
                    }

                    // Query to delete record
                    String deleteQuery = "DELETE FROM attendance WHERE Course_code = ? AND Student_id = ? AND Session_Type = ? AND Session_date = ?";
                    PreparedStatement ps = con.prepareStatement(deleteQuery);
                    ps.setString(1, courseCode);
                    ps.setString(2, studentId);
                    ps.setString(3, sessionType);
                    ps.setString(4, sessionDate);

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        textArea1.setText("Record deleted successfully.");
                    } else {
                        textArea1.setText("No matching record found to delete.");
                    }

                    ps.close();
                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });

        // Back button to return to previous window
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Attendance();  // This will open the previous screen.
            }
        });
    }

    public static void main(String[] args) {
        new TO_Attendance_Delete();
    }
}
