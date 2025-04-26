import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class View_Marks {
    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton viewButton;
    private JButton logoutButton;
    private JComboBox comboBox3;
    private JButton viewButtonwhole;
    private JTable table1;
    private JComboBox comboBox4;

    public View_Marks() {
        JFrame frame = new JFrame("View Marks");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        viewButtonwhole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = comboBox3.getSelectedItem().toString();
                String selectedType = comboBox1.getSelectedItem().toString(); // e.g., "CA marks"
                if(selectedType.equals("CA Marks")) {
                    try {
                        DB db = new DB();
                        try (Connection con = db.getConnection()) {
                            if (con == null) {
                                JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                                return;
                            }

                            String query = "SELECT Student_id, Course_code, Quiz1, Quiz2, Quiz3, Quiz4, Assessment_Mark, Mid_Theory, Mid_Practical FROM Marks WHERE Course_code = ?";
                            PreparedStatement stmt = con.prepareStatement(query);
                            stmt.setString(1, selectedCourse);
                            ResultSet rs = stmt.executeQuery();

                            DefaultTableModel model = new DefaultTableModel(new String[]{"Student ID", "CA Marks", "Eligible"}, 0);

                            while (rs.next()) {
                                String sid = rs.getString("Student_id");
                                String code = rs.getString("Course_code");
                                float q1 = rs.getFloat("Quiz1");
                                float q2 = rs.getFloat("Quiz2");
                                float q3 = rs.getFloat("Quiz3");
                                float q4 = rs.getFloat("Quiz4");
                                float assess = rs.getFloat("Assessment_Mark");
                                float midT = rs.getFloat("Mid_Theory");
                                float midP = rs.getFloat("Mid_Practical");

                                float quizTotal;
                                if (code.equals("ICT2122") || code.equals("ICT2142")) {
                                    float least = Math.min(Math.min(q1, q2), Math.min(q3, q4));
                                    quizTotal = ((q1 + q2 + q3 + q4 - least) / 300.0f) * 10;
                                } else {
                                    float least = Math.min(q1, Math.min(q2, q3));
                                    quizTotal = ((q1 + q2 + q3 - least) / 200.0f) * 10;
                                }

                                float assessPart = (assess / 100.0f) * 10;
                                float midPart;
                                if (code.equals("ICT2113") || code.equals("TCS2133")) {
                                    midPart = ((midT + midP) / 200.0f) * 20;
                                } else {
                                    midPart = ((midT + midP) / 100.0f) * 20;
                                }

                                float ca = quizTotal + assessPart + midPart;

                                // Determine eligibility based on course code
                                String eligibility = "Not Eligible";
                                if (code.equals("ICT2152") || code.equals("ICT2113") || code.equals("ICT2133")) {
                                    if (ca > 15) {
                                        eligibility = "Eligible";
                                    }
                                } else if (code.equals("ICT2122") || code.equals("ICT2142")) {
                                    if (ca > 20) {
                                        eligibility = "Eligible";
                                    }
                                }

                                model.addRow(new Object[]{sid, ca, eligibility});
                            }

                            table1.setModel(model);
                            rs.close();
                            stmt.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error fetching marks: " + ex.getMessage());
                    }
                }else{
                    try {
                        DB db = new DB();
                        try (Connection con = db.getConnection()) {
                            if (con == null) {
                                JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                                return;
                            }

                            String query = "SELECT Student_id, Course_code, Quiz1, Quiz2, Quiz3, Quiz4, Assessment_Mark, Mid_Theory, Mid_Practical, End_Theory, End_Practical FROM Marks WHERE Course_code = ?";
                            PreparedStatement stmt = con.prepareStatement(query);
                            stmt.setString(1, selectedCourse);
                            ResultSet rs = stmt.executeQuery();

                            DefaultTableModel model = new DefaultTableModel(new String[]{"Student ID", "Final Marks"}, 0);

                            if (!rs.next()) {
                                System.out.println("No data returned from the query!");
                                return;
                            }

                            do {
                                String sid = rs.getString("Student_id");
                                // rest of your logic to calculate Final marks...

                                // Add row to model
                                model.addRow(new Object[]{sid, frame});
                            } while (rs.next());

                            table1.setModel(model);
                            table1.revalidate();
                            table1.repaint();

                            rs.close();
                            stmt.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error fetching final marks: " + ex.getMessage());
                    }
                }


            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Optional: code for a different view
            }
        });
    }

    public static void main(String[] args) {
        new View_Marks();
    }
}