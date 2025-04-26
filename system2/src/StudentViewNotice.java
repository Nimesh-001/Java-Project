import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentViewNotice {
    private JPanel panel1;
    private JButton backButton;
    private JButton logoutButton;
    private JButton VIEWButton;
    private JTextArea textArea1;

    public StudentViewNotice() {

        JFrame frame = new JFrame("StudentViewNotice");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();//chavindya code should call here..

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("notices.txt"));
                    String line;
                    textArea1.setText("");
                    while ((line = reader.readLine()) != null) {
                        textArea1.append(line + "\n\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading notices!");
                }

            }
        });
    }

    public static void main(String[] args) {
        new StudentViewNotice();

    }
}
