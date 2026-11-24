
import java.util.Scanner;

public class StudentManagementSystem {

    // Admin login credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        boolean loginSuccessful = login();

        if (loginSuccessful) {
            System.out.println("\nLogin successful!");
            System.out.println("Welcome to the system.");

            mainMenu();
        } else {
            System.out.println("\nLogin attempts exceeded.");
            System.out.println("Program terminated.");
        }
    }

    // Admin Login
    public static boolean login() {

        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {

            System.out.println("\n------ ADMIN LOGIN ------");

            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(ADMIN_USERNAME)
                    && password.equals(ADMIN_PASSWORD)) {

                return true;

            } else {

                attempts++;

                System.out.println("Incorrect username or password.");
                System.out.println("Attempts remaining: "
                        + (maxAttempts - attempts));
            }
        }

        return false;
    }

    // Main Menu
    public static void mainMenu() {

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("         MAIN MENU");
            System.out.println("==================================");
            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Attendance Management");
            System.out.println("7. Exit");
            System.out.println("==================================");

            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    registerStudent();
                    break;
                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    attendanceMenu();
                    break;

                case 7:
                    System.out.println("Exiting Student Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);
    }

    // Attendance Menu
    public static void attendanceMenu() {

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("      ATTENDANCE MANAGEMENT");
            System.out.println("==================================");
            System.out.println("1. Record Attendance");
            System.out.println("2. View Attendance Records");
            System.out.println("3. Search Student Attendance");
            System.out.println("4. Attendance Summary");
            System.out.println("5. Return to Main Menu");
            System.out.println("==================================");

            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    recordAttendance();
                    break;

                case 2:
                    viewAttendance();
                    break;

                case 3:
                    searchStudentAttendance();
                    break;

                case 4:
                    attendanceSummary();
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    // Register Student
    public static void registerStudent() {

        System.out.println("\n------ REGISTER STUDENT ------");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Study Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        // Create Student object
        Student student = new Student(id, name, course, year, phone);

        // Create DAO object
        StudentDAO dao = new StudentDAO();

        // Save student to database
        boolean success = dao.addStudent(student);

        if (success) {
            System.out.println("\nStudent registered successfully!");
        } else {
            System.out.println("\nRegistration failed!");
        }
    }

    // View All Students
    public static void viewStudents() {

        StudentDAO dao = new StudentDAO();

        // Retrieve all students from database
        java.util.ArrayList<Student> students = dao.getAllStudents();

        System.out.println("\n========== STUDENT RECORDS ==========");

        if (students.isEmpty()) {

            System.out.println("No students registered yet.");

        } else {

            for (Student student : students) {

                System.out.println("-----------------------------------");
                student.displayDetails();
            }

            System.out.println("-----------------------------------");
            System.out.println("Total students: " + students.size());
        }
    }

    // Search Student
    public static void searchStudent() {

        System.out.println("\n------ SEARCH STUDENT ------");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        StudentDAO dao = new StudentDAO();

        Student student = dao.searchStudent(id);

        if (student != null) {

            System.out.println("\nStudent found!");
            System.out.println("----------------------------");
            student.displayDetails();
            System.out.println("----------------------------");

        } else {

            System.out.println("\nStudent not found!");
            System.out.println("Please check the Student ID.");
        }
    }

    // Update Student
    public static void updateStudent() {

        System.out.println("\n------ UPDATE STUDENT ------");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        StudentDAO dao = new StudentDAO();

        // Find the student first
        Student student = dao.searchStudent(id);

        if (student == null) {

            System.out.println("Student not found!");
            return;
        }

        // Display existing details
        System.out.println("\nCurrent Student Details:");
        student.displayDetails();

        System.out.println("\nEnter New Details");

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Study Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        // Create updated student object
        Student updatedStudent = new Student(
                id, name, course, year, phone);

        // Update database
        boolean success = dao.updateStudent(updatedStudent);

        if (success) {
            System.out.println("\nStudent updated successfully!");
        } else {
            System.out.println("\nStudent update failed!");
        }
    }

    // Delete Student
    public static void deleteStudent() {

        System.out.println("\n------ DELETE STUDENT ------");

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        StudentDAO dao = new StudentDAO();

        // Check whether student exists
        Student student = dao.searchStudent(id);

        if (student == null) {
            System.out.println("\nStudent not found!");
            return;
        }

        // Display student details before deletion
        System.out.println("\nStudent Details:");
        student.displayDetails();

        // Ask for confirmation
        System.out.print("\nAre you sure you want to delete this student? (Y/N): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            boolean success = dao.deleteStudent(id);

            if (success) {
                System.out.println("\nStudent deleted successfully!");
            } else {
                System.out.println("\nFailed to delete student.");
            }

        } else {
            System.out.println("\nDeletion cancelled.");
        }
    }

    // Record Student Attendance
    public static void recordAttendance() {

        System.out.println("\n------ RECORD ATTENDANCE ------");

        // Ask for Student ID
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        // Check whether student exists
        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.searchStudent(studentId);

        if (student == null) {
            System.out.println("\nStudent not found!");
            System.out.println("Please register the student first.");
            return;
        }

        // Display student information
        System.out.println("\nStudent found!");
        student.displayDetails();

        // Ask for attendance status
        String status;

        while (true) {

            System.out.print("\nEnter attendance status (PRESENT/ABSENT): ");
            status = scanner.nextLine().trim();

            if (status.equalsIgnoreCase("PRESENT")
                    || status.equalsIgnoreCase("ABSENT")) {
                break;
            }

            System.out.println("Invalid status. Enter PRESENT or ABSENT.");
        }

        // Get today's date automatically
        java.time.LocalDate date = java.time.LocalDate.now();

        // Create Attendance object
        Attendance attendance = new Attendance(
                studentId, date, status);

        // Save attendance in MySQL
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        boolean success = attendanceDAO.recordAttendance(attendance);

        if (success) {
            System.out.println("\nAttendance recorded successfully!");
            System.out.println("Student ID: " + studentId);
            System.out.println("Date: " + date);
            System.out.println("Status: " + status.toUpperCase());
        } else {
            System.out.println("\nAttendance recording failed.");
            System.out.println("Check whether attendance was already recorded today.");
        }

    }

    // View All Attendance Records
    public static void viewAttendance() {

        System.out.println("\n------ ATTENDANCE RECORDS ------");

        // Create AttendanceDAO object
        AttendanceDAO dao = new AttendanceDAO();

        // Retrieve all attendance records
        java.util.ArrayList<Attendance> records = dao.getAllAttendance();

        // Check whether records exist
        if (records.isEmpty()) {

            System.out.println("No attendance records found.");

        } else {

            // Display every attendance record
            for (Attendance attendance : records) {

                System.out.println("\n----------------------------");

                attendance.displayAttendance();
            }

            System.out.println("\n----------------------------");
            System.out.println("Total attendance records: "
                    + records.size());
        }
    }

    // Search Student Attendance
    public static void searchStudentAttendance() {

        System.out.println("\n------ SEARCH STUDENT ATTENDANCE ------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        // Check whether the student exists
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.searchStudent(studentId);

        if (student == null) {
            System.out.println("\nStudent not found!");
            return;
        }

        // Retrieve attendance history
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        java.util.ArrayList<Attendance> records = attendanceDAO.searchAttendance(studentId);

        System.out.println("\nStudent: " + studentId);
        System.out.println("Name: " + student.getName());

        if (records.isEmpty()) {

            System.out.println("\nNo attendance records found.");

        } else {

            System.out.println("\n------ ATTENDANCE HISTORY ------");

            for (Attendance attendance : records) {

                System.out.println("----------------------------");
                attendance.displayAttendance();
            }

            System.out.println("----------------------------");
            System.out.println("Total records: " + records.size());
        }
    }

    // Attendance Summary
    public static void attendanceSummary() {

        System.out.println("\n------ ATTENDANCE SUMMARY ------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        // Check whether student exists
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.searchStudent(studentId);

        if (student == null) {
            System.out.println("\nStudent not found!");
            return;
        }

        // Retrieve attendance counts
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        int[] counts = attendanceDAO.getAttendanceSummary(studentId);

        int present = counts[0];
        int absent = counts[1];
        int total = present + absent;

        System.out.println("\nStudent ID: " + studentId);
        System.out.println("Name: " + student.getName());

        System.out.println("\nTotal Attendance Records: " + total);
        System.out.println("Days Present: " + present);
        System.out.println("Days Absent: " + absent);

        if (total > 0) {

            double percentage = (present * 100.0) / total;

            System.out.printf("Attendance Percentage: %.2f%%%n",
                    percentage);

        } else {

            System.out.println("Attendance Percentage: 0.00%");
            System.out.println("No attendance records available.");
        }
    }

}
