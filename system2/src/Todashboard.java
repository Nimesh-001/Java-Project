import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Todashboard {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JButton attendenceButton1;
    private JButton medicalButton1;
    private JButton noticeButton1;
    private JButton timetableButton1;
    private JButton button10;
    private JPanel panal2;

    public Todashboard() {
        attendenceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("TO_Attendance");
                frame.setContentPane(new TO_Attendance().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000,500);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        });
        medicalButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("TO_Medical");
                frame.setContentPane(new TO_Medical().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000,500);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        });
        medicalButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("TO_Medical");
                frame.setContentPane(new TO_Medical().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000,500);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        });

        timetableButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("TO_Timetable");
                frame.setContentPane(new TO_Timetable().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000,500);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        });
        noticeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("TO_Notice");
                frame.setContentPane(new TO_Notice().panal1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000,500);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Todashboard");
        frame.setContentPane(new Todashboard().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
