import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TOViewTimetable {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JButton backButton;
    private JButton logoutButton;
    private JButton VIEWButton;
    private JTable table1;

    public TOViewTimetable() {

        JFrame frame = new JFrame("TOViewTimetable");
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
                new ToDashbord();//technical oficer dashbord here......

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

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
    }


    public static void main(String[] args) {
        new TOViewTimetable();

    }
}
