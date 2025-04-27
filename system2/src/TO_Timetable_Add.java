import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TO_Timetable_Add {

    public JPanel panal1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JLabel timetable_id;
    private JLabel Description;
    private JTextField textField3;
    private JLabel time;
    private JButton viewButton;
    private JPanel Edit;
    private JTextArea textArea1;
    private JButton updateButton1;
    private JButton deleteButton;
    private JButton Back;

    public TO_Timetable_Add() {

        JFrame frame = new JFrame("TO_Timetable_Add");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timetableID = textField1.getText();
                String description = textField2.getText();

                if (timetableID.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\timetable_data.txt";

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    writer.write(timetableID + ", " + description);
                    writer.newLine();
                    writer.close();

                    JOptionPane.showMessageDialog(null, "Timetable added successfully!");
                    textField1.setText("");
                    textField2.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving timetable: " + ex.getMessage());
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timetableID = textField3.getText().trim();
                String[] timetable = findTimeTableById(timetableID);

                if (timetable != null) {
                    String description = timetable[1];
                    textArea1.setText("Description: " + description);
                    Edit.setVisible(true);
                } else {
                    textArea1.setText("No timetable found");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete functionality if required
            }
        });

        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputId = textField3.getText().trim(); // Timetable ID entered by user
                String editedText = textArea1.getText().trim(); // Edited Description from textArea1

                if (inputId.isEmpty() || editedText.isEmpty()) {
                    textArea1.setText("Please enter a Timetable ID and make sure the text area is not empty.");
                    return;
                }

                String newDescription = editedText.replaceFirst("Description:", "").trim();

                File file = new File("C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\timetable_data.txt");
                List<String> updatedLines = new ArrayList<>();
                boolean updated = false;

                try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                    String line;

                    while ((line = raf.readLine()) != null) {
                        String[] timetable = line.split(","); // Split line by comma and space

                        if (timetable.length >= 2 && timetable[0].equals(inputId)) {
                            updatedLines.add(inputId + ", " + newDescription); // Update only ID and Description
                            updated = true;
                        } else {
                            updatedLines.add(line); // Keep unchanged lines
                        }
                    }

                    if (updated) {
                        raf.setLength(0); // Clear the file content
                        for (String updatedLine : updatedLines) {
                            raf.writeBytes(updatedLine + System.lineSeparator());
                        }
                        textArea1.setText("Timetable updated successfully.");
                    } else {
                        textArea1.setText("Timetable ID not found for update.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    textArea1.setText("Error updating timetable: " + ex.getMessage());
                }
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close current window
                new TO_Timetable();
            }
        });
    }

    // Method to find timetable by ID
    private String[] findTimeTableById(String timetableID) {
        String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\timetable_data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line in the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*"); // Split by comma and space

                if (parts.length >= 2 && parts[0].equals(timetableID)) {
                    return parts;  // Return the timetable ID and description
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;  // Return null if the timetable ID is not found
    }

    public static void main(String[] args) {
        new TO_Timetable_Add();

    }
}
