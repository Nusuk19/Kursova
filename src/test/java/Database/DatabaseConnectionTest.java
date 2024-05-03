package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    void testConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

    @Test
    void testInvalidCredentials() {
        String invalidUrl = "jdbc:mysql://localhost:3306/invalid_db";
        String invalidUsername = "invalid_user";
        String invalidPassword = "invalid_password";

        assertThrows(SQLException.class, () -> {
            DriverManager.getConnection(invalidUrl, invalidUsername, invalidPassword);
        });
    }

    @Test
    void testCloseConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
            connection.close();
            assertTrue(connection.isClosed());
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

    @Test
    void testGetConnectionTwice() {
        try {
            Connection connection1 = DatabaseConnection.getConnection();
            Connection connection2 = DatabaseConnection.getConnection();
            assertNotNull(connection1);
            assertNotNull(connection2);
            assertNotEquals(connection1, connection2);
            assertFalse(connection1.isClosed());
            assertFalse(connection2.isClosed());
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}
