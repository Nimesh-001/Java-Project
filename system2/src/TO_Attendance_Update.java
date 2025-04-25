import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TO_Attendance_Update {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JLabel Session_Date;
    private JButton viewButton;
    private JTextArea textArea1;
    private JTextField textField5;
    private JButton updateButton;
    private JButton backButton;


    public TO_Attendance_Update() {
        JFrame frame = new JFrame("TO_Attendance_Update");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

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

                try {
                    Connection con = DB.getConnection();
                    String query = "SELECT * FROM attendance WHERE Course_code = ? AND Student_id = ? AND Session_Type = ? AND Session_date = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, courseCode);
                    ps.setString(2, studentId);
                    ps.setString(3, sessionType);
                    ps.setString(4, sessionDate);

                    java.sql.ResultSet rs = ps.executeQuery();

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

                        textField5.setText(status); // show current status in editable field
                    } else {
                        textArea1.setText("No record found.");
                        textField5.setText("");
                    }

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
                String courseCode = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String sessionType = textField3.getText().trim();
                String sessionDate = textField4.getText().trim();
                String newStatus = textField5.getText().trim();

                if (newStatus.isEmpty()) {
                    textArea1.setText("Please enter a new status.");
                    return;
                }

                try {
                    Connection con = DB.getConnection();
                    String updateQuery = "UPDATE attendance SET Status = ? WHERE Course_code = ? AND Student_id = ? AND Session_Type = ? AND Session_date = ?";
                    PreparedStatement ps = con.prepareStatement(updateQuery);
                    ps.setString(1, newStatus);
                    ps.setString(2, courseCode);
                    ps.setString(3, studentId);
                    ps.setString(4, sessionType);
                    ps.setString(5, sessionDate);

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        textArea1.setText("Status updated successfully.");
                    } else {
                        textArea1.setText("Update failed. Record not found.");
                    }

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

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Attendance();

            }
        });
    }

    public static void main(String[] args) {
        new TO_Attendance_Update();
    }
}
