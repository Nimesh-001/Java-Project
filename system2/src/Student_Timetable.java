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

        try {
            // Database connection and data fetching
            Dbconnector db = new Dbconnector();
            Connection con = db.getConnection();

            String sql = "SELECT Content FROM timetable";
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

            // Adjust column width (use 0, not 2)
            table1.getColumnModel().getColumn(0).setPreferredWidth(100);

        } catch (SQLException e) {
            e.printStackTrace(); // Good for debugging
        }

        // Back Button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();
            }
        });

        // Logout Button action
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