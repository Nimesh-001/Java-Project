import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class AdminNoticeManagement {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JButton ADDButton;
    private JButton VIEWButton;
    private JButton DELETEButton;
    private JButton logoutButton;
    private JTextArea textArea1;
    private JButton backButton;
    private JTextArea textArea2;

    public AdminNoticeManagement() {

        JFrame frame = new JFrame("AdminNoticeManagement");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,500);

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newNotice = textArea1.getText().trim();
                if (!newNotice.isEmpty()) {
                    try {
                        FileWriter writer = new FileWriter("notices.txt", true);
                        writer.write(newNotice + "\n");
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Notice Added Successfully!");
                        textArea1.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a notice!");
                }

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("notices.txt"));
                    String line;
                    textArea2.setText(""); // Clear before loading
                    while ((line = reader.readLine()) != null) {
                        textArea2.append(line + "\n\n"); // show each notice separately
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("notices.txt");
                    writer.write("");
                    writer.close();
                    textArea2.setText("");
                    JOptionPane.showMessageDialog(null, "All notices deleted!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admindashbord();

            }
        });
    }

    public static void main(String[] args) {
        new AdminNoticeManagement();

    }
}
