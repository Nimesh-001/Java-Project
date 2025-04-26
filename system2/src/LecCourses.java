import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LecCourses {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton backButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton uploadButton;
    private JButton viewButton;
    private JButton selectFileButton;
    private JButton logoutButton;

    private File selectedFile;

    public LecCourses() {
        JFrame frame = new JFrame("LecCourses");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        ADDButton.addActionListener(e -> {

        });

        DELETEButton.addActionListener(e -> {

        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new Lecturdashbord();
        });

        viewButton.addActionListener(e -> {

        });

        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Lecture Material File");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Documents (PDF, DOCX, PPT, PPTX)", "pdf", "docx", "ppt", "pptx"
            );
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getName());
            }
        });

        uploadButton.addActionListener(e -> {
            if (selectedFile != null) {
                try {
                    // Create "materials" folder if not exists
                    File materialsFolder = new File("materials");
                    if (!materialsFolder.exists()) {
                        materialsFolder.mkdir();
                    }


                    File destFile = new File(materialsFolder, selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


                    String courseCode = (comboBox1.getSelectedItem() != null) ? comboBox1.getSelectedItem().toString() : "";
                    String title = textField1.getText();
                    String description = textArea1.getText();
                    String filePath = destFile.getPath();


                    boolean inserted = insertMaterial(courseCode, title, description, filePath);

                    if (inserted) {
                        JOptionPane.showMessageDialog(null, "File uploaded and saved successfully!");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error saving data to database.");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to upload file.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a file first!");
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleToDelete = textField1.getText(); // Get the title entered in the text field

                if (titleToDelete.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Title of the material to delete.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this material?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Dbconnector dbc = new Dbconnector();
                    Connection conn = dbc.getConnection();

                    if (conn != null) {
                        try {

                            String selectSql = "SELECT File_Path FROM lecturematerials WHERE Title = ?";
                            PreparedStatement selectPst = conn.prepareStatement(selectSql);
                            selectPst.setString(1, titleToDelete);
                            var rs = selectPst.executeQuery();
                            String filePath = null;
                            if (rs.next()) {
                                filePath = rs.getString("File_Path");
                            }
                            rs.close();
                            selectPst.close();


                            String deleteSql = "DELETE FROM lecturematerials WHERE Title = ?";
                            PreparedStatement deletePst = conn.prepareStatement(deleteSql);
                            deletePst.setString(1, titleToDelete);
                            int rowsAffected = deletePst.executeUpdate();
                            deletePst.close();
                            conn.close();

                            if (rowsAffected > 0) {
                                // If successfully deleted from DB, delete the actual file
                                if (filePath != null) {
                                    File file = new File(filePath);
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Material deleted successfully!");
                                textField1.setText(""); // Clear the text field
                                textArea1.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Material not found!");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error while deleting material.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Database connection failed.");
                    }
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form();
            }
        });
    }

    private void createUIComponents() {
        ADDButton = new JButton("Add");
        DELETEButton = new JButton("Delete");
        backButton = new JButton("Back");
        uploadButton = new JButton("Upload");
        viewButton = new JButton("View");
        selectFileButton = new JButton("Select File");
    }

    private boolean insertMaterial(String courseCode, String title, String description, String filePath) {
        Dbconnector dbc = new Dbconnector();
        Connection conn = dbc.getConnection();

        if (conn != null) {
            try {
                String sql = "INSERT INTO lecturematerials (Course_Code, Title, Description, File_Path) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, courseCode);
                pst.setString(2, title);
                pst.setString(3, description);
                pst.setString(4, filePath);

                int rowsAffected = pst.executeUpdate();
                pst.close();
                conn.close();

                return rowsAffected > 0; // Insert success
            } catch (Exception ex) {
                ex.printStackTrace();
                return false; // Insert fail
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed.");
            return false;
        }
    }

    private void clearForm() {
        comboBox1.setSelectedIndex(0);
        textField1.setText("");
        textArea1.setText("");
        selectedFile = null;
    }

    public static void main(String[] args) {
        new LecCourses();
    }
}
