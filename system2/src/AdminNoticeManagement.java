import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNoticeManagement {
    private JPanel panel1;
    private JButton ADDButton;
    private JButton VIEWButton;
    private JButton DELETEButton;
    private JButton logoutButton;
    private JTextArea textArea1;
    private JTable table1;
    private JButton backButton;

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

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
