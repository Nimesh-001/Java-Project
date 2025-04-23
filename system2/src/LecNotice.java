import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecNotice {
    private JPanel panel1;
    private JButton backButton;

    public LecNotice() {

        JFrame frame = new JFrame("LecNotice");
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
                new Lecturdashbord();
            }
        });
    }

    public static void main(String[] args) {
        new LecNotice();
    }
}
