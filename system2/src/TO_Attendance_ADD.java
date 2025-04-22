import javax.swing.*;

public class TO_Attendance_ADD {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton attendenceButton;
    private JButton timetableButton;
    private JButton noticeButton;
    private JButton medicalButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JLabel Session_Date;
    private JLabel Status;
    private JLabel To_ID;
    private JLabel Medical_Id;
    private JButton addButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Attendance_ADD");
        frame.setContentPane(new TO_Attendance_ADD().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
