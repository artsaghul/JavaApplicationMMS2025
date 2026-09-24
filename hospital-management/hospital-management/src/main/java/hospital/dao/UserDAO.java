package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.StaffRole;
import hospital.models.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;

public class UserDAO {
    public static String hash(String password) {
        try {
            byte[] b = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte x : b) sb.append(String.format("%02x", x));
            return sb.toString();
        } catch (Exception e) { throw new IllegalStateException(e); }
    }

    public int create(String username, String password, StaffRole role, Integer staffId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO users (username, password_hash, role, staff_id) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username); ps.setString(2, hash(password));
            ps.setString(3, role.name()); ps.setObject(4, staffId);
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    /** Returns the User on success, or null on bad credentials. */
    public User authenticate(String username, String password) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE username=? AND password_hash=?")) {
            ps.setString(1, username); ps.setString(2, hash(password));
            try (ResultSet r = ps.executeQuery()) {
                return r.next() ? new User(r.getInt("id"), r.getString("username"), r.getString("password_hash"),
                        StaffRole.valueOf(r.getString("role")), (Integer) r.getObject("staff_id")) : null;
            }
        }
    }
}
