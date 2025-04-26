import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TO_Attendance_Delete {
    public JPanel panal1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
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

    public TO_Attendance_Delete() {
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

                        // show current status in editable field
                    } else {
                        textArea1.setText("No record found.");

                    }

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
                String courseCode = textField1.getText().trim();
                String studentId = textField2.getText().trim();
                String sessionType = textField3.getText().trim();
                String sessionDate = textField4.getText().trim();

                if (courseCode.isEmpty() || studentId.isEmpty() || sessionType.isEmpty() || sessionDate.isEmpty()) {
                    textArea1.setText("Please enter all four key fields to delete.");
                    return;
                }

                /*int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this attendance record?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }*/

                try {
                    Connection con = DB.getConnection();
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
                    con.close();
                } catch (SQLException ex) {
                    textArea1.setText("Error: " + ex.getMessage());
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Attendance_Delete");
        frame.setContentPane(new TO_Attendance_Delete().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
