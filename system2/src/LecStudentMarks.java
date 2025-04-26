import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecStudentMarks {
    private JPanel panel1;
    private JButton backButton;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JButton viewButton;
    private JTable table1;

    public LecStudentMarks() {

        JFrame frame = new JFrame("LecStudentMarks");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        String[] courses = {"ICT2142", "ICT2152", "ICT2113","ICT2122","ICT2133"};
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

                loadData(studentId, courseCode);
            }
        });
    }


    private void loadData(String studentId, String courseCode) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        if (con != null) {
            try {
                String query = "SELECT * FROM Marks WHERE Student_Username = ? AND Course_code = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentId);
                pst.setString(2, courseCode);

                ResultSet rs = pst.executeQuery();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Student ID");
                model.addColumn("Course Code");
                model.addColumn("Quiz 1");
                model.addColumn("Quiz 2");
                model.addColumn("Quiz 3");
                model.addColumn("Quiz 4");
                model.addColumn("Assessment 1");
                model.addColumn("Assessment 2");
                model.addColumn("Mid Practical");
                model.addColumn("Mid Theory");
                model.addColumn("End Practical");
                model.addColumn("End Theory");

                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("Student_Username"),
                            rs.getString("Course_code"),
                            rs.getInt("Quiz1"),
                            rs.getInt("Quiz2"),
                            rs.getInt("Quiz3"),
                            rs.getInt("Quiz4"),
                            rs.getInt("Assessment_01"),
                            rs.getInt("Assessment_02"),
                            rs.getInt("Mid_Practical"),
                            rs.getInt("Mid_Theory"),
                            rs.getInt("End_Practical"),
                            rs.getInt("End_Theory")
                    });
                }

                table1.setModel(model);

                rs.close();
                pst.close();
                con.close();

            } catch (SQLException ex) {
                System.out.println("Error loading data: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new LecStudentMarks();
    }
}
