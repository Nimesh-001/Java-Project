import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CourseManagementForm {

    Dbconnector dbc = new Dbconnector();
    Connection con = dbc.getConnection();

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton VIEWButton;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;
    private JButton clearButton;
    private JComboBox comboBox1;

    public CourseManagementForm() {
        JFrame frame = new JFrame("CourseManagementForm");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000,500);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                comboBox1.setSelectedIndex(0);
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admindashbord();

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();

            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new CourseManagementForm();

    }
}
