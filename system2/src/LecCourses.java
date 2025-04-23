import javax.swing.*;

public class LecCourses {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecCourses");
        frame.setContentPane(new LecCourses().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
