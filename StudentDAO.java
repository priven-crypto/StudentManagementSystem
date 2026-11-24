
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {

    // =================================
    // 1. REGISTER STUDENT
    // =================================
    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(student_id, full_name, course, study_year, phone) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, student.getId());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getCourse());
                pstmt.setInt(4, student.getYear());
                pstmt.setString(5, student.getPhone());

                int rows = pstmt.executeUpdate();

                return rows > 0;
            }

        } catch (SQLException e) {

            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }


    // =================================
    // 2. VIEW ALL STUDENTS
    // =================================
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> studentList = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return studentList;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Student student = new Student(
                            rs.getString("student_id"),
                            rs.getString("full_name"),
                            rs.getString("course"),
                            rs.getInt("study_year"),
                            rs.getString("phone")
                    );

                    studentList.add(student);
                }
            }

        } catch (SQLException e) {

            System.out.println("Error viewing students: " + e.getMessage());
        }

        return studentList;
    }


    // =================================
    // 3. SEARCH STUDENT
    // =================================
    public Student searchStudent(String id) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return null;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, id);

                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {

                        return new Student(
                                rs.getString("student_id"),
                                rs.getString("full_name"),
                                rs.getString("course"),
                                rs.getInt("study_year"),
                                rs.getString("phone")
                        );
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("Search failed: " + e.getMessage());
        }

        return null;
    }


    // =================================
    // 4. UPDATE STUDENT
    // =================================
    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET " +
                "full_name = ?, course = ?, study_year = ?, phone = ? " +
                "WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, student.getName());
                pstmt.setString(2, student.getCourse());
                pstmt.setInt(3, student.getYear());
                pstmt.setString(4, student.getPhone());
                pstmt.setString(5, student.getId());

                int rows = pstmt.executeUpdate();

                return rows > 0;
            }

        } catch (SQLException e) {

            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }


    // =================================
    // 5. DELETE STUDENT
    // =================================
    public boolean deleteStudent(String id) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, id);

                int rows = pstmt.executeUpdate();

                return rows > 0;
            }

        } catch (SQLException e) {

            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }
}
