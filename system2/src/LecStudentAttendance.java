import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class LecStudentAttendance {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton backButton;
    private JButton clearButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton logOutButton;
    private Dbconnector dbc;

    public LecStudentAttendance() {
        dbc = new Dbconnector();
        JFrame frame = new JFrame("LecStudentAttendance");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LecStudentdashbord(); // Go back to dashboard (you should define this class)
            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = textField1.getText();
                String courseCode = comboBox1.getSelectedItem().toString();

                try (Connection conn = dbc.getConnection()) {
                    String sql = "SELECT * FROM attendance WHERE Student_Username = ? AND Course_code = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, studentId);
                    pst.setString(2, courseCode);

                    ResultSet rs = pst.executeQuery();


                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Student Username");
                    model.addColumn("Course Code");
                    model.addColumn("Session Type");
                    model.addColumn("Session Date");
                    model.addColumn("Status");

                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("Student_Username"),
                                rs.getString("Course_code"),
                                rs.getString("Session_Type"),
                                rs.getString("Session_date"),
                                rs.getString("Status")
                        });
                    }

                    table1.setModel(model);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading data!");
                }
            }
        });


        viewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = comboBox2.getSelectedItem().toString();

                try (Connection conn = dbc.getConnection()) {
                    String sql = "SELECT * FROM attendance WHERE Course_code = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, courseCode);

                    ResultSet rs = pst.executeQuery();

                    // Load data into table
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Student Username");
                    model.addColumn("Course Code");
                    model.addColumn("Session Type");
                    model.addColumn("Session Date");
                    model.addColumn("Status");

                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("Student_Username"),
                                rs.getString("Course_code"),
                                rs.getString("Session_Type"),
                                rs.getString("Session_date"),
                                rs.getString("Status")
                        });
                    }

                    table1.setModel(model);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading batch data!");
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                textField1.setText("");
            }
        });


        loadCourses();
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
    }


    private void loadCourses() {
        try (Connection conn = dbc.getConnection()) {
            String sql = "SELECT DISTINCT Course_code FROM attendance";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comboBox1.addItem(rs.getString("Course_code"));
                comboBox2.addItem(rs.getString("Course_code"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading courses!");
        }
    }

    public static void main(String[] args) {
        new LecStudentAttendance();
    }
}
