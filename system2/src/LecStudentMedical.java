import javax.swing.*;

public class LecStudentMedical {
    private JPanel panel1;
    private JTextField textField1;
    private JButton viewButton;
    private JComboBox comboBox1;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecStudentMedical");
        frame.setContentPane(new LecStudentMedical().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
