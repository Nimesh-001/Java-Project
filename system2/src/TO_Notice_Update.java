import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class TO_Notice_Update {
    public JPanel panal1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JTextField textField4;
    private JLabel Notice_Id;
    private JButton viewButton;
    private JButton updateButton1;
    private JPanel Edit;
    private JTextArea textArea1;
    private JTextField editTextField1;  // for content
    private JTextField editTextField2;  // for description


    public TO_Notice_Update() {
        // View button action
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

        // Update button action (you can fill this later)
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
        JFrame frame = new JFrame("TO_Notice_Update");
        frame.setContentPane(new TO_Notice_Update().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
