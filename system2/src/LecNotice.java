import javax.swing.*;

public class LecNotice {
    private JPanel panel1;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecNotice");
        frame.setContentPane(new LecNotice().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
