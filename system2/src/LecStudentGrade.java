import javax.swing.*;

public class LecStudentGrade {
    private JPanel panel1;
    private JButton viewResultsButton;
    private JButton backButton;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentGrade");
        frame.setContentPane(new LecStudentGrade().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
