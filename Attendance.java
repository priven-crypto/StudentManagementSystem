
import java.time.LocalDate;

public class Attendance {

    // Encapsulated fields
    private int attendanceId;
    private String studentId;
    private LocalDate attendanceDate;
    private String status;

    // Constructor for recording new attendance
    public Attendance(String studentId, LocalDate attendanceDate, String status) {
        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        setStatus(status);
    }

    // Constructor for attendance retrieved from database
    public Attendance(int attendanceId, String studentId,
            LocalDate attendanceDate, String status) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        setStatus(status);
    }

    // Getters
    public int getAttendanceId() {
        return attendanceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setStatus(String status) {

        if (status != null &&
                (status.equalsIgnoreCase("PRESENT")
                        || status.equalsIgnoreCase("ABSENT"))) {

            this.status = status.toUpperCase();

        } else {
            throw new IllegalArgumentException(
                    "Attendance status must be PRESENT or ABSENT.");
        }
    }

    // Display attendance details
    public void displayAttendance() {
        System.out.println("Attendance ID: " + attendanceId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Date: " + attendanceDate);
        System.out.println("Status: " + status);
    }
}
