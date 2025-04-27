import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecStudentMedical {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JComboBox<String> comboBox1;
    private JButton backButton;
    private JTable table1;
    private JButton logOutButton;

    public LecStudentMedical() {

        JFrame frame = new JFrame("LecStudentMedical");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);


        String[] courses = {"ICT2142", "ICT2152", "ICT2113", "ICT2133", "ICT2122"};
        comboBox1.setModel(new DefaultComboBoxModel<>(courses));


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord();
            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = textField1.getText();
                String courseCode = comboBox1.getSelectedItem().toString();


                loadMedicalData(studentId, courseCode);
            }
        });


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
    }


    private void loadMedicalData(String studentId, String courseCode) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        if (con != null) {
            try {

                String query = "SELECT * FROM Medical WHERE Student_Username = ? AND Course_code = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentId);
                pst.setString(2, courseCode);


                ResultSet rs = pst.executeQuery();


                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Medical ID");
                model.addColumn("Submission Date");
                model.addColumn("Status");
                model.addColumn("Start Date");
                model.addColumn("End Date");
                model.addColumn("Reason");
                model.addColumn("Course Code");
                model.addColumn("Student Username");
                model.addColumn("TO Username");


                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("Medical_id"),
                            rs.getString("Submission_date"),
                            rs.getString("Status"),
                            rs.getString("Start_date"),
                            rs.getString("End_date"),
                            rs.getString("Reason"),
                            rs.getString("Course_code"),
                            rs.getString("Student_Username"),
                            rs.getString("TO_Username")
                    });
                }


                table1.setModel(model);


                rs.close();
                pst.close();
                con.close();

            } catch (SQLException ex) {
                System.out.println("Error loading medical data: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new LecStudentMedical();
    }
}
