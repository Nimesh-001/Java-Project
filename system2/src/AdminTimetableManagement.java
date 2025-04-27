import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminTimetableManagement {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JButton backButton;
    private JButton logoutButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton CLEARButton;
    private JButton ADDButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton DELETEButton;

    public AdminTimetableManagement() {

        JFrame frame = new JFrame("AdminTimetableManagement");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admindashbord();

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = textField1.getText();
                String courseName = textField2.getText();
                String startTime = textField3.getText();
                String endTime = textField4.getText();
                String date = textField5.getText();

                if (courseCode.isEmpty() || courseName.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }

                String query = "INSERT INTO newtimetable (course_code, course_name, start_time, end_time, date) VALUES (?, ?, ?, ?, ?)";

                try {
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, courseCode);
                    pst.setString(2, courseName);
                    pst.setString(3, startTime);
                    pst.setString(4, endTime);
                    pst.setString(5, date);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Timetable added successfully!");


                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to add timetable.");
                }

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String query = "SELECT * FROM newtimetable";

                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    DefaultTableModel model = new DefaultTableModel(
                            new String[]{"Course Code", "Course Name", "Start Time", "End Time", "Date"}, 0);

                    while (rs.next()) {
                        String courseCode = rs.getString("course_code");
                        String courseName = rs.getString("course_name");
                        String startTime = rs.getString("start_time");
                        String endTime = rs.getString("end_time");
                        String date = rs.getString("date");

                        model.addRow(new Object[]{courseCode, courseName, startTime, endTime, date});
                    }
                    table1.setModel(model);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load data.");
                }

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = textField1.getText();

                if (courseCode.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Course Code to delete.");
                    return;
                }

                String query = "DELETE FROM newtimetable WHERE course_code = ?";

                try {
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, courseCode);

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Timetable deleted successfully!");


                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Course Code not found.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to delete timetable.");
                }

            }
        });
    }

    public static void main(String[] args) {
        new AdminTimetableManagement();

    }
}
