import javax.swing.*;

public class LecMarks {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton ADDButton;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ADDButton1;
    private JButton ADDButton2;
    private JButton ADDButton3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton ADDButton4;
    private JButton ADDButton5;
    private JButton ADDButton6;
    private JButton ADDButton7;
    private JButton ADDButton8;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecMarks");
        frame.setContentPane(new LecMarks().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
    }
}
