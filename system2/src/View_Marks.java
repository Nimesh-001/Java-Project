import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class View_Marks {
    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    private JComboBox<String> comboBox1; // Mark Type (CA Marks / Final Marks)
    private JComboBox<String> comboBox2; // Course Code
    private JButton viewButton;
    private JButton logoutButton;
    private JComboBox<String> comboBox3; // Course Code for whole table view
    private JButton viewButtonwhole;
    private JTable table1;
    private JComboBox<String> comboBox4; // Student ID

    public View_Marks() {
        JFrame frame = new JFrame("View Marks");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        // View full table (for course and mark type)
        // View full table (for course and mark type)
        viewButtonwhole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = comboBox3.getSelectedItem().toString();
                String selectedType = comboBox4.getSelectedItem().toString(); // Mark Type

                System.out.println("Selected Type: " + selectedType);  // Debugging to confirm the selected value

                // Check the selected Mark Type
                if (selectedType.equals("CA Marks")) {
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
                } else if (selectedType.equals("Final Marks")) {
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
                                float endT = rs.getFloat("End_Theory");
                                float endP = rs.getFloat("End_Practical");

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
                                float endPart;

                                if (code.equals("ICT2113") || code.equals("TCS2133")) {
                                    midPart = ((midT + midP) / 200.0f) * 20;
                                    endPart = ((endT + endP) / 200.0f) * 60;
                                } else {
                                    midPart = ((midT + midP) / 100.0f) * 20;
                                    endPart = ((endT + endP) / 100.0f) * 60;
                                }

                                float finalMark = quizTotal + assessPart + midPart + endPart;

                                model.addRow(new Object[]{sid, finalMark});
                            }

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
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a valid Mark Type (CA Marks or Final Marks).");
                }
            }
        });



        // View single student result
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentId = comboBox1.getSelectedItem() != null ? comboBox1.getSelectedItem().toString() : "";
                String courseCode = comboBox2.getSelectedItem() != null ? comboBox2.getSelectedItem().toString() : "";

                if (studentId.isEmpty() || courseCode.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select both Student ID and Course Code.");
                    return;
                }

                try {
                    DB db = new DB();
                    try (Connection con = db.getConnection()) {
                        if (con == null) {
                            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                            return;
                        }

                        String sql = "{CALL GetCAMarks(?, ?)}";
                        try (CallableStatement stmt = con.prepareCall(sql)) {
                            stmt.setString(1, studentId);
                            stmt.setString(2, courseCode);

                            try (ResultSet rs = stmt.executeQuery()) {
                                DefaultTableModel model = new DefaultTableModel(
                                        new String[]{"CA Marks", "Eligibility"}, 0
                                );

                                boolean foundData = false;
                                while (rs.next()) {
                                    int caMarks = rs.getInt("CAMarks");
                                    String eligibility = rs.getString("Eligibility");
                                    model.addRow(new Object[]{caMarks, eligibility});
                                    foundData = true;
                                }

                                if (!foundData) {
                                    JOptionPane.showMessageDialog(null, "No data found for the selected Student ID and Course Code.");
                                }

                                table1.setModel(model);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving marks: " + ex.getMessage());
                }
            }
        });


    }

    public static void main(String[] args) {
        new View_Marks();
    }
}
