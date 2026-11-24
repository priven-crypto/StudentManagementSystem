
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAO {

    // Record Attendance
    public boolean recordAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance "
                + "(student_id, attendance_date, status) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, attendance.getStudentId());
            pstmt.setDate(2,
                    java.sql.Date.valueOf(attendance.getAttendanceDate()));
            pstmt.setString(3, attendance.getStatus());

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error recording attendance: "
                    + e.getMessage());
            return false;
        }
    }

    // View All Attendance Records
    public ArrayList<Attendance> getAllAttendance() {

        ArrayList<Attendance> attendanceList = new ArrayList<>();

        String sql = "SELECT * FROM attendance ORDER BY attendance_date DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Attendance attendance = new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getString("student_id"),
                        rs.getDate("attendance_date").toLocalDate(),
                        rs.getString("status"));

                attendanceList.add(attendance);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving attendance: "
                    + e.getMessage());
        }

        return attendanceList;
    }

    // Search Attendance by Student ID
    public ArrayList<Attendance> searchAttendance(String studentId) {

        ArrayList<Attendance> attendanceList = new ArrayList<>();

        String sql = "SELECT * FROM attendance "
                + "WHERE student_id = ? "
                + "ORDER BY attendance_date DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Attendance attendance = new Attendance(
                            rs.getInt("attendance_id"),
                            rs.getString("student_id"),
                            rs.getDate("attendance_date").toLocalDate(),
                            rs.getString("status"));

                    attendanceList.add(attendance);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching attendance: "
                    + e.getMessage());
        }

        return attendanceList;
    }

    public int[] getAttendanceSummary(String studentId) {
        int[] counts = new int[2];

        String sql = "SELECT status, COUNT(*) AS total " +
                "FROM attendance WHERE student_id = ? GROUP BY status";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String status = rs.getString("status");
                int total = rs.getInt("total");

                if (status.equals("PRESENT")) {
                    counts[0] = total;
                } else if (status.equals("ABSENT")) {
                    counts[1] = total;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error calculating attendance summary: " + e.getMessage());
        }

        return counts;
    }
}
