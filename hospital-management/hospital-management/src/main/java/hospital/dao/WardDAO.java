package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Ward;
import java.sql.*;
import java.util.*;

public class WardDAO {
    public int add(Ward w) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO wards (name, department_id, type) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, w.name()); ps.setObject(2, w.departmentId()); ps.setString(3, w.type());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    public List<Ward> getAll() throws SQLException {
        List<Ward> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             ResultSet r = c.createStatement().executeQuery("SELECT * FROM wards ORDER BY id")) {
            while (r.next())
                list.add(new Ward(r.getInt("id"), r.getString("name"), (Integer) r.getObject("department_id"), r.getString("type")));
        }
        return list;
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM wards WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
