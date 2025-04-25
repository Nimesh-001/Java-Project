import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Marks {
    public JPanel panal1;
    private JPanel panal2;
    private JButton backButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton viewButton;
    private JButton logoutButton;
    private JComboBox comboBox3;
    private JButton viewButtonwhole;
    private JTable table1;

    public View_Marks() {
        JFrame frame = new JFrame("View_Marks");
        frame.setContentPane(new View_Marks().panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        viewButtonwhole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new View_Marks();
    }
}
