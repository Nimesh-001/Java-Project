import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TO_Notice {
    public JPanel panal1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel panal3;  // This panel will hold the form inside panal1

    public TO_Notice() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panal3.removeAll(); // clear previous content

                // Create form components
                JTextArea textArea = new JTextArea(10, 30);
                JButton saveButton = new JButton("Save");

                // Layout setup for form
                panal3.setLayout(new BorderLayout());
                panal3.add(new JLabel("Enter Notice Below:"), BorderLayout.NORTH);
                panal3.add(new JScrollPane(textArea), BorderLayout.CENTER);
                panal3.add(saveButton, BorderLayout.SOUTH);

                // Refresh panal3
                panal3.revalidate();
                panal3.repaint();

                // Save action
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame("TO_Notice_Add");
                        frame.setContentPane(new TO_Notice_Add().panal1);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                        frame.setSize(1000,500);
                        frame.setResizable(false);
                        frame.setLocationRelativeTo(null);
                    }
                        /*String dataToSave = textArea.getText();
                        if (dataToSave != null && !dataToSave.trim().isEmpty()) {
                            File file = new File("C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\notice.dat");
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                                writer.write(dataToSave);
                                writer.newLine();
                                JOptionPane.showMessageDialog(null, "Notice saved successfully!");
                                textArea.setText(""); // clear after save
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error saving notice: " + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No data entered to save.");
                        }
                    }
                });*/
            });

    }

    public JPanel getPanel() {
        return panal1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Notice");
        TO_Notice noticeUI = new TO_Notice();
        frame.setContentPane(noticeUI.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
