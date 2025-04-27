import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Student_Course {
    public JPanel panal1;
    private JButton attendenceButton;
    private JButton logoutButton;
    private JButton noticeButton;
    private JButton timetableButton;
    private JButton gradeGPAButton;
    private JButton medicalButton;
    private JButton courseButton;
    private JPanel panel2;
    private JButton DSAButton;
    private JButton EcommerceButton;
    private JButton OOPButton;
    private JButton OOADButton;
    private JButton OOPpracticumButton;
    private JButton backButton;
    private JButton logbutton;

    public Student_Course() {
        JFrame frame = new JFrame("Student_Course");
        frame.setContentPane(this.panal1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);

        DSAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create Course_Details form
                Student_Course_Details courseDetails = new Student_Course_Details();

                // Set course name in textArea1
                courseDetails.textArea1.setEditable(false);
                courseDetails.textArea1.setText("Data Structures and Algorithms");

                // Define the file path to read
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\DSA.txt";

                // Read from the file and display in textArea2
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    StringBuilder content = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    reader.close();

                    // Set content to textArea2
                    courseDetails.textArea2.setText(content.toString());

                } catch (Exception ex) {
                    courseDetails.textArea2.setText("Error reading file: " + ex.getMessage());
                }

                // Display the form
                JFrame frame = new JFrame("Course Details - DSA");
                frame.setContentPane(courseDetails.panal1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setSize(1000, 500);
                frame.setResizable(false);
            }
        });



        EcommerceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create Course_Details form
                Student_Course_Details courseDetails = new Student_Course_Details();

                // Set course name in textArea1 (optional)
                courseDetails.textArea1.setEditable(false);
                courseDetails.textArea1.setText("E-Commerce Implementation Managment and Security");

                // Read from the file
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\Ecom.txt";
                StringBuilder content = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    // Display file content in textArea2
                    courseDetails.textArea2.setText(content.toString());
                } catch (IOException ex) {
                    courseDetails.textArea2.setText("Error reading file: " + ex.getMessage());
                }

                // Show the form
                JFrame frame = new JFrame("Course Details - E-Commerce");
                frame.setContentPane(courseDetails.panal1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setSize(1000, 500);
                frame.setResizable(false);
            }
        });


        OOPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_Course_Details courseDetails = new Student_Course_Details();

                // Set course name in textArea1 (optional)
                courseDetails.textArea1.setEditable(false);
                courseDetails.textArea1.setText("Object Orianted Programming");

                // Read from the file
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\Ecom.txt";
                StringBuilder content = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    // Display file content in textArea2
                    courseDetails.textArea2.setText(content.toString());
                } catch (IOException ex) {
                    courseDetails.textArea2.setText("Error reading file: " + ex.getMessage());
                }

                // Show the form
                JFrame frame = new JFrame("Course Details - E-Commerce");
                frame.setContentPane(courseDetails.panal1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setSize(1000, 500);
                frame.setResizable(false);
            }
        });
        OOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_Course_Details courseDetails = new Student_Course_Details();

                // Set course name in textArea1 (optional)
                courseDetails.textArea1.setEditable(false);
                courseDetails.textArea1.setText("Object Oriented Analisis and Design");

                // Read from the file
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\Ecom.txt";
                StringBuilder content = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    // Display file content in textArea2
                    courseDetails.textArea2.setText(content.toString());
                } catch (IOException ex) {
                    courseDetails.textArea2.setText("Error reading file: " + ex.getMessage());
                }

                // Show the form
                JFrame frame = new JFrame("Course Details - E-Commerce");
                frame.setContentPane(courseDetails.panal1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setSize(1000, 500);
                frame.setResizable(false);
            }
        });
        OOPpracticumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_Course_Details courseDetails = new Student_Course_Details();

                // Set course name in textArea1 (optional)
                courseDetails.textArea1.setEditable(false);
                courseDetails.textArea1.setText("Object Oriented Programming Practicum");

                // Read from the file
                String filePath = "C:\\Users\\asus\\Desktop\\Java Mini Project\\java code\\Java-Project\\system2\\Ecom.txt";
                StringBuilder content = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    // Display file content in textArea2
                    courseDetails.textArea2.setText(content.toString());
                } catch (IOException ex) {
                    courseDetails.textArea2.setText("Error reading file: " + ex.getMessage());
                }

                // Show the form
                JFrame frame = new JFrame("Course Details - E-Commerce");
                frame.setContentPane(courseDetails.panal1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setSize(1000, 500);
                frame.setResizable(false);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Studentdashboard();
            }
        });
        logbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new form();
            }
        });
    }

    public static void main(String[] args) {
        new Student_Course();
    }
}
