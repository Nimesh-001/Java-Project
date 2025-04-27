import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student_Timetable {

    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    private JButton logoutButton;
    private JTable table1;
    private JPanel panel2;

    public Student_Timetable() {
        JFrame frame = new JFrame("Student_Timetable");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        // Database connection and data fetching
        try {
            DB db = new DB(); // Assuming Dbconnector is your connection class
            Connection con = db.getConnection(); // Get connection from Dbconnector

            if (con != null) {
                String sql = "SELECT Content FROM timetable";  // Modify the SQL query as needed
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                // Create DefaultTableModel and add columns
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Content");

                // Loop through the result set and add rows to the model
                while (rs.next()) {
                    String content = rs.getString("Content");

                    model.addRow(new Object[]{content});
                }

                // Set the model to the JTable
                table1.setModel(model);

                // Optional: Adjust column widths for better display
                table1.getColumnModel().getColumn(2).setPreferredWidth(100); // Content column width
            } else {
                JOptionPane.showMessageDialog(null, "Database connection failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

        // Back Button action
        backButton.addActionListener(e -> {
            // Handle back button click (implement action here)
        });

        // Logout Button action
        logoutButton.addActionListener(e -> {
            // Handle logout button click (implement action here)
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
    }

    public static void main(String[] args) {

        new Student_Timetable();
    }
}
