import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

public class TO_Notice_View {
    private JPanel panal1;
    private JTextField textField3;
    private JLabel time;
    private JButton viewButton;
    private JPanel Edit;
    private JTextArea textArea1;
    private JButton Back;
    private JTable table1;

    public TO_Notice_View() {
        // Set up the table with data
        loadNoticesToTable();

        // Button action for viewing the notice
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSelectedNoticeContent();  // Load content of the selected notice
            }
        });

        // Back button action
        Back.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panal1);
            topFrame.dispose();
            new Todashboard(); // Go back to dashboard
        });
    }

    // Load notices into JTable from the database
    private void loadNoticesToTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Notice ID", "Title", "Date", "Admin ID"});

        try (Connection con = DB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM notice")) {

            while (rs.next()) {
                String id = rs.getString("Notice_id");
                String title = rs.getString("Title");
                String date = rs.getString("Publish_date");
                String adminId = rs.getString("Admin_id");
                model.addRow(new Object[]{id, title, date, adminId});
            }

            table1.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading notices from database.");
        }
    }

    // Load content of the selected notice based on its ID
    private void loadSelectedNoticeContent() {
        int selectedRow = table1.getSelectedRow();  // Get selected row

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a notice to view.");
            return;
        }

        String noticeId = table1.getValueAt(selectedRow, 0).toString(); // Get Notice ID
        String fileName = noticeId + ".txt";  // Construct file name based on Notice ID

        // Build the correct file path
        String basePath = System.getProperty("C:\\Users\\Dell PC\\Desktop\\java project\\Java-Project\\system2") + File.separator + "notices";
        File file = new File(basePath, fileName);  // Full correct file path

        System.out.println("Trying to read from: " + file.getAbsolutePath());

        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File not found: " + fileName + "\nPlease make sure it exists in the 'notices' folder.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea1.setText(content.toString());  // Set the content to JTextArea

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to load notice content from file: " + fileName);
        }
    }

    // Main method to initialize the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Notice_View");
        frame.setContentPane(new TO_Notice_View().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
