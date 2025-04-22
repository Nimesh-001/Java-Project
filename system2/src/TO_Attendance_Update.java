import javax.swing.*;

public class TO_Attendance_Update {
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
    private JLabel Course_code;
    private JLabel StudentID;
    private JLabel Session_Type;
    private JLabel Session_Date;
    private JLabel Status;
    private JButton updateButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TO_Attendance_Update");
        frame.setContentPane(new TO_Attendance_Update().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
