import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TO_Notice_Add {
    public JPanel panal1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel Notice_id;
    private JLabel Date;
    private JLabel Description;
    private JButton addButton;
    private JTextField textField4;
    private JLabel Notice_Id;
    private JButton viewButton;
    private JButton updateButton1;
    private JPanel Edit;
    private JTextArea textArea1;
    private JButton deleteButton;

    public TO_Notice_Add() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from the text fields
                String noticeID = textField1.getText();
                String date = textField2.getText();
                String description = textField3.getText();

                // Check if all fields are filled
                if (noticeID.isEmpty() || date.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                // Define file path where data will be saved
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\notice_data.txt";

                try {
                    // Create BufferedWriter to write data to the file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    // Write the data to the file (Notice ID, Date, and Description)
                    writer.write(noticeID + ", " + date + ", " + description);
                    writer.newLine(); // Move to the next line for the next entry
                    writer.close(); // Close the writer

                    // Show confirmation message
                    JOptionPane.showMessageDialog(null, "Notice added successfully!");

                    // Clear the fields
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving notice: " + ex.getMessage());
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputId = textField4.getText().trim(); // Get ID from the input field
                String[] notice = findNoticeById(inputId);

                if (notice != null) {
                    // Assuming format: ID, Content, Description
                    String content = notice[1];
                    String description = notice[2];

                    // Show content in the text area
                    textArea1.setText("Date: " + content + "\nContent: " + description);

                    Edit.setVisible(true); // make Edit panel visible if hidden
                } else {
                    textArea1.setText("Notice not found.");
                }
            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputId = textField4.getText().trim();
                String editedText = textArea1.getText().trim();

                String[] parts = editedText.split("\n");
                if (parts.length < 2) {
                    textArea1.setText("Invalid format. Please enter in 'Date: ...\\nContent: ...' format.");
                    return;
                }

                String newDate = parts[0].replaceFirst("Date: ", "").trim();
                String newContent = parts[1].replaceFirst("Content: ", "").trim();

                File file = new File("C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\notice_data.txt");
                List<String> updatedLines = new ArrayList<>();

                boolean updated = false;

                try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                    String line;

                    while ((line = raf.readLine()) != null) {
                        String[] notice = line.split(",");

                        if (notice.length >= 3 && notice[0].equals(inputId)) {
                            updatedLines.add(inputId + "," + newDate + "," + newContent);
                            updated = true;
                        } else {
                            updatedLines.add(line);
                        }
                    }

                    if (updated) {
                        // Clear file and rewrite content
                        raf.setLength(0); // truncate file
                        for (String updatedLine : updatedLines) {
                            raf.writeBytes(updatedLine + System.lineSeparator());
                        }
                        textArea1.setText("Notice updated successfully.");
                    } else {
                        textArea1.setText("Notice ID not found for update.");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    textArea1.setText("Error updating notice: " + ex.getMessage());
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputId = textField4.getText().trim();

                File file = new File("C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\notice_data.txt");

                try {
                    // Read the content of the original file
                    List<String> lines = new ArrayList<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            lines.add(line);
                        }
                    }

                    // Remove the line with the matching ID
                    boolean deleted = false;
                    for (int i = 0; i < lines.size(); i++) {
                        String[] notice = lines.get(i).split(",");
                        if (notice[0].equals(inputId)) {
                            lines.remove(i);
                            deleted = true;
                            break; // Only delete the first match
                        }
                    }

                    // If a notice was deleted, overwrite the original file with the remaining content
                    if (deleted) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            for (String line : lines) {
                                writer.write(line);
                                writer.newLine();
                            }
                            textArea1.setText("Notice deleted successfully.");
                        }
                    } else {
                        textArea1.setText("Notice ID not found.");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    textArea1.setText("Error deleting notice: " + ex.getMessage());
                }
            }
        });


    }



    private String[] findNoticeById(String noticeId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\notice_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] notice = line.split(",");  // Assuming each line is comma-separated (ID, Content, Description)

                if (notice[0].equals(noticeId)) {
                    return notice;  // Return the notice if found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Return null if the notice is not found
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Notice_Add");
        frame.setContentPane(new TO_Notice_Add().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
