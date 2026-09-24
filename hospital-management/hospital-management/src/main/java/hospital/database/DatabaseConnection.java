package hospital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Override defaults with env vars HMS_DB_URL, HMS_DB_USER, HMS_DB_PASS. */
public final class DatabaseConnection {
    private static final String URL = System.getenv().getOrDefault("HMS_DB_URL", "jdbc:mysql://localhost:3306/hospital_db");
    private static final String USER = System.getenv().getOrDefault("HMS_DB_USER", "root");
    private static final String PASS = System.getenv().getOrDefault("HMS_DB_PASS", "MichealObuh123");

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
