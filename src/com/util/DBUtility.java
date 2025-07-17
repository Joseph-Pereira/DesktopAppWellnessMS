package db;

import java.sql.*;

public class DBUtility {
    private static final String DB_URL = "jdbc:derby:WellnessDB;create=true";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void createTables() {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute("CREATE TABLE Appointments (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "student VARCHAR(100), " +
                    "counselor VARCHAR(100), " +
                    "date DATE, " +
                    "time TIME, " +
                    "status VARCHAR(50))");

            stmt.execute("CREATE TABLE Counselors (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(100), " +
                    "specialization VARCHAR(100), " +
                    "availability BOOLEAN)");

            stmt.execute("CREATE TABLE Feedback (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "student VARCHAR(100), " +
                    "rating INT, " +
                    "comments VARCHAR(255))");

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Tables already exist.");
            } else {
                System.out.println("Error creating tables: " + e.getMessage());
            }
        }
    }
}
