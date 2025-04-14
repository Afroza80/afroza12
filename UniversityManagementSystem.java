
import java.sql.*;
import javax.swing.*;

// ------------------- DB Connection -------------------
class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// ------------------- Abstract Class: Person -------------------
abstract class Person {
    protected int id;
    protected String name;
    protected String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();
}

// ------------------- Student Class -------------------
class Student extends Person {
    private String department;

    public Student(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + ", Department: " + department);
    }
}

// ------------------- Faculty Class -------------------
class Faculty extends Person {
    private String department;

    public Faculty(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty: " + name + ", Department: " + department);
    }
}

// ------------------- Course Class -------------------
class Course {
    private int id;
    private String title;
    private String department;

    public Course(int id, String title, String department) {
        this.id = id;
        this.title = title;
        this.department = department;
    }

    public void displayCourse() {
        System.out.println("Course: " + title + " | Department: " + department);
    }
}

// ------------------- Department Class -------------------
class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void displayDept() {
        System.out.println("Department: " + name);
    }
}

// ------------------- AuthService -------------------
class AuthService {
    public static String login(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("role");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

// ------------------- AdminDashboard -------------------
class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel label = new JLabel("Welcome, Admin!");
        label.setBounds(130, 100, 150, 30);
        add(label);
        setVisible(true);
    }
}

// ------------------- FacultyDashboard -------------------
class FacultyDashboard extends JFrame {
    public FacultyDashboard() {
        setTitle("Faculty Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel label = new JLabel("Welcome, Faculty!");
        label.setBounds(130, 100, 150, 30);
        add(label);
        setVisible(true);
    }
}

// ------------------- StudentDashboard -------------------
class StudentDashboard extends JFrame {
    public StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel label = new JLabel("Welcome, Student!");
        label.setBounds(130, 100, 150, 30);
        add(label);
        setVisible(true);
    }
}

// ------------------- LoginFrame -------------------
class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login - University Management System");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        userLabel.setBounds(50, 30, 100, 25);
        userField.setBounds(150, 30, 180, 25);
        passLabel.setBounds(50, 70, 100, 25);
        passField.setBounds(150, 70, 180, 25);
        loginBtn.setBounds(150, 110, 80, 30);

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String role = AuthService.login(username, password);

            if (role == null) {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            } else {
                dispose();
                switch (role) {
                    case "admin" -> new AdminDashboard();
                    case "faculty" -> new FacultyDashboard();
                    case "student" -> new StudentDashboard();
                }
            }
        });

        setVisible(true);
    }
}

// ------------------- Main -------------------
public class UniversityManagementSystem {
    public static void main(String[] args) {
        new LoginFrame();
    }
}