import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQlitetest {
    private static final String URL = "jdbc:sqlite:inventory.db"; // SQLite database file

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("✅ SQLite connected successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ SQLite connection failed: " + e.getMessage());
        }
    }
}