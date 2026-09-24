package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Patient;
import java.sql.*;
import java.util.*;

public class PatientDAO {
    private Patient map(ResultSet r) throws SQLException {
        java.sql.Date dob = r.getDate("date_of_birth");
        return new Patient(r.getInt("id"), r.getString("first_name"), r.getString("last_name"), r.getString("gender"),
                dob == null ? null : dob.toLocalDate(), r.getString("phone"), r.getString("email"),
                r.getString("address"), r.getString("blood_group"), r.getString("emergency_contact"));
    }

    public int add(Patient p) throws SQLException {
        String sql = "INSERT INTO patients (first_name,last_name,gender,date_of_birth,phone,email,address,blood_group,emergency_contact) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getFirstName()); ps.setString(2, p.getLastName()); ps.setString(3, p.getGender());
            ps.setDate(4, p.getDateOfBirth() == null ? null : java.sql.Date.valueOf(p.getDateOfBirth()));
            ps.setString(5, p.getPhone()); ps.setString(6, p.getEmail()); ps.setString(7, p.getAddress());
            ps.setString(8, p.getBloodGroup()); ps.setString(9, p.getEmergencyContact());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    public List<Patient> getAll() throws SQLException {
        List<Patient> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             ResultSet r = c.createStatement().executeQuery("SELECT * FROM patients ORDER BY id")) {
            while (r.next()) list.add(map(r));
        }
        return list;
    }

    public Patient getById(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM patients WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet r = ps.executeQuery()) { return r.next() ? map(r) : null; }
        }
    }

    public boolean update(Patient p) throws SQLException {
        String sql = "UPDATE patients SET first_name=?,last_name=?,gender=?,date_of_birth=?,phone=?,email=?,address=?,blood_group=?,emergency_contact=? WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName()); ps.setString(2, p.getLastName()); ps.setString(3, p.getGender());
            ps.setDate(4, p.getDateOfBirth() == null ? null : java.sql.Date.valueOf(p.getDateOfBirth()));
            ps.setString(5, p.getPhone()); ps.setString(6, p.getEmail()); ps.setString(7, p.getAddress());
            ps.setString(8, p.getBloodGroup()); ps.setString(9, p.getEmergencyContact()); ps.setInt(10, p.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM patients WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
