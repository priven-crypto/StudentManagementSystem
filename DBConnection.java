
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";

    private static final String USER = "sms_user";

    private static final String PASSWORD = System.getenv("SMS_DB_PASSWORD");

    // Method to establish database connection
    public static Connection getConnection() {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connected successfully!");

            return connection;

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());

            return null;
        }
    }
}
