import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Vector;

public class ViewLectureMaterials {
    private JPanel panel1;
    private JTable table1;
    private JButton viewLectureMaterialsButton;
    private JButton backButton;
    private JButton downloadButton;
    private JComboBox<String> comboBox1;

    public ViewLectureMaterials() {


        JFrame frame = new JFrame("View Lecture Materials");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1000, 500);


        comboBox1.addItem("ICT2113");
        comboBox1.addItem("ICT2122");
        comboBox1.addItem("ICT2142");
        comboBox1.addItem("ICT2152");
        comboBox1.addItem("ICT2133");

        viewLectureMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLectureMaterials();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();
              //  new LecCourses();
            }
        });


        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    String filePath = (String) table1.getValueAt(selectedRow, 4); // File path column index = 4

                    File fileToDownload = new File(filePath);
                    if (fileToDownload.exists()) {
                        String downloadDirectory = System.getProperty("user.home") + "/Downloads/";
                        File destinationFile = new File(downloadDirectory + fileToDownload.getName());

                        try {
                            Files.copy(fileToDownload.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(null, "Download successful! Saved to: " + destinationFile.getAbsolutePath());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error downloading file: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "File does not exist!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a material to download.");
                }
            }
        });
    }


    private void loadLectureMaterials() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Dbconnector dbc = new Dbconnector();
            conn = dbc.getConnection();

            if (conn != null) {
                String selectedCourse = (String) comboBox1.getSelectedItem(); // Get selected course code
                String query = "SELECT * FROM lecturematerials WHERE Course_Code = ?";

                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, selectedCourse);
                rs = pstmt.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Material ID");
                model.addColumn("Course Code");
                model.addColumn("Title");
                model.addColumn("Description");
                model.addColumn("File Path");
                model.addColumn("Uploaded Date");

                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(String.valueOf(rs.getInt("Material_ID")));
                    row.add(rs.getString("Course_Code"));
                    row.add(rs.getString("Title"));
                    row.add(rs.getString("Description"));
                    row.add(rs.getString("File_Path"));
                    row.add(rs.getString("Uploaded_Date"));
                    model.addRow(row);
                }

                table1.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading materials from database.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new ViewLectureMaterials();
    }
}
