import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TO_Attendance_ADD {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
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

                if (courseCode.isEmpty() || studentId.isEmpty() || sessionType.isEmpty() || sessionDate.isEmpty()) {
                    textArea1.setText("Course Code, Student ID, Session Type, and Session Date are required.");
                    return;
                }

                Dbconnector db = new Dbconnector();
                Connection con = db.getConnection();

                String query = "INSERT INTO attendance (Course_code, Student_id, Session_Type, Session_date, Status, TO_id, Medical_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                try  {


                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, courseCode);
                    ps.setString(2, studentId);
                    ps.setString(3, sessionType);
                    ps.setString(4, sessionDate);
                    ps.setString(5, status);
                    ps.setString(6, toId);
                    ps.setString(7, medicalId);

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        textArea1.setText("Data inserted successfully!");
                        textField1.setText(""); textField2.setText(""); textField3.setText("");
                        textField4.setText(""); textField5.setText(""); textField6.setText(""); textField7.setText("");
                    } else {
                        textArea1.setText("Insertion failed.");
                    }
                } catch (SQLException ex) {
                    textArea1.setText("Database error: " + ex.getMessage());
                }
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current window
                new TO_Attendance();
            }
        });
    }

    public static void main(String[] args) {

        new TO_Attendance_ADD();
    }
}
