import javax.swing.*;

public class Course_Details {
    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    public JTextArea textArea1;
    public JTextArea textArea2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course_Details");
        frame.setContentPane(new Course_Details().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
    }
}
