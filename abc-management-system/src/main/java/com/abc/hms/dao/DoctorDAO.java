package com.abc.hms.dao;

import com.abc.hms.model.Doctor;
import com.abc.hms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private static final String BASE_SELECT =
        "SELECT d.*, dep.name AS department_name FROM doctors d " +
        "LEFT JOIN departments dep ON d.department_id = dep.department_id ";

    public int addDoctor(Doctor d) throws SQLException {
        String sql = "INSERT INTO doctors (first_name, last_name, specialization, phone, email, department_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getSpecialization());
            ps.setString(4, d.getPhone());
            ps.setString(5, d.getEmail());
            if (d.getDepartmentId() != null) ps.setInt(6, d.getDepartmentId());
            else ps.setNull(6, Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public Doctor getDoctorById(int id) throws SQLException {
        String sql = BASE_SELECT + " WHERE d.doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = BASE_SELECT + " ORDER BY d.doctor_id";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = BASE_SELECT + " WHERE d.specialization LIKE ? ORDER BY d.doctor_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + specialization + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public boolean updateDoctor(Doctor d) throws SQLException {
        String sql = "UPDATE doctors SET first_name=?, last_name=?, specialization=?, phone=?, email=?, department_id=? " +
                     "WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getSpecialization());
            ps.setString(4, d.getPhone());
            ps.setString(5, d.getEmail());
            if (d.getDepartmentId() != null) ps.setInt(6, d.getDepartmentId());
            else ps.setNull(6, Types.INTEGER);
            ps.setInt(7, d.getDoctorId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteDoctor(int id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Doctor mapRow(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setDoctorId(rs.getInt("doctor_id"));
        d.setFirstName(rs.getString("first_name"));
        d.setLastName(rs.getString("last_name"));
        d.setSpecialization(rs.getString("specialization"));
        d.setPhone(rs.getString("phone"));
        d.setEmail(rs.getString("email"));
        int deptId = rs.getInt("department_id");
        if (!rs.wasNull()) d.setDepartmentId(deptId);
        d.setDepartmentName(rs.getString("department_name"));
        return d;
    }
}
