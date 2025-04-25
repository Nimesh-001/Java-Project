import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Todashboard {
    public JPanel panel1;
    private JButton attendenceButton1;
    private JButton medicalButton1;
    private JButton noticeButton1;
    private JButton timetableButton1;
    private JButton button10;
    private JLabel profilePicLabel;
    private JButton logoutButton;
    private JButton editProfileButton;
    private JLabel adminUsernameLabel;
    private JPanel panal2;

    public Todashboard() {
        JFrame frame = new JFrame("Todashboard");


        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        attendenceButton1.addActionListener(e -> {
            frame.dispose();
            new TO_Attendance();
        });

        medicalButton1.addActionListener(e -> {
            frame.dispose();
            new TO_Medical();
            /*
            JFrame medFrame = new JFrame("TO_Medical");
            medFrame.setContentPane(new TO_Medical().panal1);
            medFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            medFrame.setSize(1000, 500);
            medFrame.setLocationRelativeTo(null);
            medFrame.setVisible(true);

             */
        });

        timetableButton1.addActionListener(e -> {
            frame.dispose();
            /*
            JFrame ttFrame = new JFrame("TO_Timetable");
            ttFrame.setContentPane(new TO_Timetable().panal1);
            ttFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ttFrame.setSize(1000, 500);
            ttFrame.setLocationRelativeTo(null);
            ttFrame.setVisible(true);

             */
        });

        noticeButton1.addActionListener(e -> {
            frame.dispose();
            new TO_Notice_Add();
            /*
            JFrame noticeFrame = new JFrame("TO_Notice");
            noticeFrame.setContentPane(new TO_Notice_Add().panal1);
            noticeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            noticeFrame.setSize(1000, 500);
            noticeFrame.setLocationRelativeTo(null);
            noticeFrame.setVisible(true);

             */
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Edit_TO_Profile();

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
        timetableButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TO_Timetable();

            }
        });
    }

    public static void main(String[] args) {
        new Todashboard();
    }
}
