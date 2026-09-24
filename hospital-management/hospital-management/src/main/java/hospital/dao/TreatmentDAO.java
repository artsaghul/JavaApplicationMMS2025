package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Treatment;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TreatmentDAO {
    private static LocalDate d(java.sql.Date x) { return x == null ? null : x.toLocalDate(); }

    private Treatment map(ResultSet r) throws SQLException {
        return new Treatment(
                r.getInt("id"),
                r.getInt("medical_record_id"),
                r.getString("name"),
                r.getString("description"),
                d(r.getDate("start_date")),
                d(r.getDate("end_date")), r.getDouble("cost"));
    }

    public int add(Treatment t) throws SQLException {
        String sql = "INSERT INTO treatments (medical_record_id, name, description, start_date, end_date, cost) VALUES (?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.medicalRecordId()); ps.setString(2, t.name()); ps.setString(3, t.description());
            ps.setDate(4, t.startDate() == null ? null : java.sql.Date.valueOf(t.startDate()));
            ps.setDate(5, t.endDate() == null ? null : java.sql.Date.valueOf(t.endDate()));
            ps.setDouble(6, t.cost());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    public List<Treatment> getAll() throws SQLException {
        List<Treatment> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             ResultSet r = c.createStatement().executeQuery("SELECT * FROM treatments ORDER BY id")) {
            while (r.next()) list.add(map(r));
        }
        return list;
    }

    public List<Treatment> getByMedicalRecord(int recordId) throws SQLException {
        List<Treatment> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM treatments WHERE medical_record_id=?")) {
            ps.setInt(1, recordId);
            try (ResultSet r = ps.executeQuery()) { while (r.next()) list.add(map(r)); }
        }
        return list;
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM treatments WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
