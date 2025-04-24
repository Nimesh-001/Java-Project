import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class LecStudentDetails {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable table1;

    public LecStudentDetails() {
        JFrame frame = new JFrame("LecStudentDetails");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);


        table1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Username", "First_Name", "Last_Name", "Email"}
        ));

        backButton.addActionListener(e -> {
            frame.dispose();
            new LecStudentdashbord();
        });

        viewButton.addActionListener(e -> {
            String studentId = textField1.getText().trim();
            if (!studentId.isEmpty()) {
                fetchStudentData(studentId);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a Student ID.");
            }
        });

        clearButton.addActionListener(e -> {
            textField1.setText("");
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
        });
    }

    private void fetchStudentData(String studentId) {
        Dbconnector dbc = new Dbconnector();
        Connection conn = dbc.getConnection();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.");
            return;
        }

        try {
            String query = "SELECT * FROM USER WHERE Username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, studentId);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Username"));
                row.add(rs.getString("First_Name"));
                row.add(rs.getString("Last_Name"));
                row.add(rs.getString("Email"));
                model.addRow(row);
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
            return;
        }

        SwingUtilities.invokeLater(() -> new LecStudentDetails());
    }
}
