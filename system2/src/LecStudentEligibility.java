import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecStudentEligibility {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable table1;
    private JButton logoutButton;
    private JButton viewButton1;

    public LecStudentEligibility() {
        JFrame frame = new JFrame("LecStudentEligibility");
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
                String studentId = textField1.getText();
                if (studentId.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a student ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Student ID");
                model.addColumn("Course Code");
                model.addColumn("Eligibility Status");


                try (Connection con = new Dbconnector().getConnection()) {
                    String sql = "SELECT Student_Username, Course_code, Eligibility_Status FROM eligibility_view WHERE Student_Username = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, studentId);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String studentUsername = rs.getString("Student_Username");
                        String courseCode = rs.getString("Course_code");
                        String eligibilityStatus = rs.getString("Eligibility_Status");


                        model.addRow(new Object[]{studentUsername, courseCode, eligibilityStatus});
                    }

                    table1.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textField1.setText("");
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });


        viewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Student ID");
                model.addColumn("Course Code");
                model.addColumn("Eligibility Status");


                try (Connection con = new Dbconnector().getConnection()) {
                    String sql = "SELECT Student_Username, Course_code, Eligibility_Status FROM eligibility_view";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        String studentUsername = rs.getString("Student_Username");
                        String courseCode = rs.getString("Course_code");
                        String eligibilityStatus = rs.getString("Eligibility_Status");


                        model.addRow(new Object[]{studentUsername, courseCode, eligibilityStatus});
                    }

                    table1.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        new LecStudentEligibility();
    }
}
