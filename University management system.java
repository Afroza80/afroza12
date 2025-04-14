
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static AuthSystem authSystem = new AuthSystem();

    public static void main(String[] args) {
        System.out.println("=== University Management System ===");

        if (!authSystem.login()) {
            System.out.println("Authentication failed. Exiting...");
            return;
        }

        String role = authSystem.getCurrentRole();

        switch (role) {
            case "admin":
                new AdminDashboard().showOptions();
                break;
            case "faculty":
                new FacultyDashboard().showOptions();
                break;
            case "student":
                new StudentDashboard().showOptions();
                break;
            default:
                System.out.println("Invalid user type.");
        }
    }
}

// --------- Authentication System ---------
class AuthSystem {
    private String currentRole;

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (user.equals("admin") && pass.equals("admin123")) {
            currentRole = "admin";
            return true;
        } else if (user.equals("faculty") && pass.equals("faculty123")) {
            currentRole = "faculty";
            return true;
        } else if (user.equals("student") && pass.equals("student123")) {
            currentRole = "student";
            return true;
        }

        return false;
    }

    public String getCurrentRole() {
        return currentRole;
    }
}

// --------- Student Class ---------
class Student {
    String name;
    int id;
    String department;

    Student(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.department = dept;
    }

    public String toString() {
        return id + " - " + name + " (" + department + ")";
    }
}

// --------- Faculty Class ---------
class Faculty {
    String name;
    String department;

    Faculty(String name, String dept) {
        this.name = name;
        this.department = dept;
    }

    public String toString() {
        return name + " - " + department;
    }
}

// --------- Course Class ---------
class Course {
    String code;
    String title;

    Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String toString() {
        return code + " - " + title;
    }
}

// --------- Grade Class ---------
class Grade {
    int studentId;
    String courseCode;
    String grade;

    Grade(int id, String code, String grade) {
        this.studentId = id;
        this.courseCode = code;
        this.grade = grade;
    }

    public String toString() {
        return "Student ID: " + studentId + ", Course: " + courseCode + ", Grade: " + grade;
    }
}

// --------- Admin Dashboard ---------
class AdminDashboard {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Faculty> faculties = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Grade> grades = new ArrayList<>();
    static ArrayList<String> departments = new ArrayList<>();

    public void showOptions() {
        int choice;
        do {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Faculty");
            System.out.println("3. Manage Courses");
            System.out.println("4. Manage Grades");
            System.out.println("5. Manage Departments");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageFaculty();
                    break;
                case 3:
                    manageCourses();
                    break;
                case 4:
                    manageGrades();
                    break;
                case 5:
                    manageDepartments();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    void manageStudents() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        students.add(new Student(id, name, dept));
        System.out.println("Student added.");
    }

    void manageFaculty() {
        System.out.print("Enter Faculty Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        faculties.add(new Faculty(name, dept));
        System.out.println("Faculty added.");
    }

    void manageCourses() {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        courses.add(new Course(code, title));
        System.out.println("Course added.");
    }

    void manageGrades() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();
        grades.add(new Grade(id, code, grade));
        System.out.println("Grade assigned.");
    }

    void manageDepartments() {
        System.out.print("Enter Department Name: ");
        String name = sc.nextLine();
        departments.add(name);
        System.out.println("Department added.");
    }
}

// --------- Faculty Dashboard ---------
class FacultyDashboard {
    public void showOptions() {
        System.out.println("Welcome to Faculty Dashboard");
        System.out.println("1. View Courses");
        System.out.println("2. View Assigned Grades");

        // Logic can be expanded as needed
    }
}

// --------- Student Dashboard ---------
class StudentDashboard {
    public void showOptions() {
        System.out.println("Welcome to Student Dashboard");
        System.out.println("1. View My Courses");
        System.out.println("2. View My Grades");

        // Logic can be expanded as needed
    }
}