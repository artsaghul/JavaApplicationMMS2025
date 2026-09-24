package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Pharmacist;
import java.sql.*;
import java.util.*;

public class PharmacistDAO {
    /** Inserts into staff + pharmacists inside one transaction. */
    public int add(Pharmacist p) throws SQLException {
        String s1 = "INSERT INTO staff (first_name,last_name,gender,date_of_birth,phone,email,address,role,department_id,hire_date) VALUES (?,?,?,?,?,?,?, 'PHARMACIST',?,?)";
        try (Connection c = DatabaseConnection.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement(s1, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.getFirstName()); ps.setString(2, p.getLastName()); ps.setString(3, p.getGender());
                ps.setDate(4, p.getDateOfBirth() == null ? null : java.sql.Date.valueOf(p.getDateOfBirth()));
                ps.setString(5, p.getPhone()); ps.setString(6, p.getEmail()); ps.setString(7, p.getAddress());
                ps.setObject(8, p.getDepartmentId());
                ps.setDate(9, p.getHireDate() == null ? null : java.sql.Date.valueOf(p.getHireDate()));
                ps.executeUpdate();
                int id;
                try (ResultSet k = ps.getGeneratedKeys()) { k.next(); id = k.getInt(1); }
                try (PreparedStatement p2 = c.prepareStatement("INSERT INTO pharmacists (staff_id, license_no) VALUES (?,?)")) {
                    p2.setInt(1, id); p2.setString(2, p.getLicenseNo()); p2.executeUpdate();
                }
                c.commit();
                return id;
            } catch (SQLException e) { c.rollback(); throw e; }
        }
    }

    public List<Pharmacist> getAll() throws SQLException {
        String sql = "SELECT s.*, ph.license_no FROM staff s JOIN pharmacists ph ON ph.staff_id = s.id ORDER BY s.id";
        List<Pharmacist> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); ResultSet r = c.createStatement().executeQuery(sql)) {
            while (r.next()) {
                java.sql.Date dob = r.getDate("date_of_birth"), hire = r.getDate("hire_date");
                list.add(new Pharmacist(r.getInt("id"), r.getString("first_name"), r.getString("last_name"),
                        r.getString("gender"), dob == null ? null : dob.toLocalDate(), r.getString("phone"),
                        r.getString("email"), r.getString("address"), (Integer) r.getObject("department_id"),
                        hire == null ? null : hire.toLocalDate(), r.getString("license_no")));
            }
        }
        return list;
    }

    public boolean delete(int staffId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM staff WHERE id=? AND role='PHARMACIST'")) {
            ps.setInt(1, staffId);
            return ps.executeUpdate() > 0;
        }
    }
}
