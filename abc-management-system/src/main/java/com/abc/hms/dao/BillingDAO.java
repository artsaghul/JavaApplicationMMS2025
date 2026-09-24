package com.abc.hms.dao;

import com.abc.hms.model.Bill;
import com.abc.hms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    private static final String BASE_SELECT =
        "SELECT b.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name " +
        "FROM billing b JOIN patients p ON b.patient_id = p.patient_id ";

    public int createBill(Bill b) throws SQLException {
        String sql = "INSERT INTO billing (patient_id, appointment_id, amount, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, b.getPatientId());
            if (b.getAppointmentId() != null) ps.setInt(2, b.getAppointmentId());
            else ps.setNull(2, Types.INTEGER);
            ps.setBigDecimal(3, b.getAmount());
            ps.setString(4, b.getStatus() != null ? b.getStatus() : "PENDING");
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public List<Bill> getAllBills() throws SQLException {
        List<Bill> list = new ArrayList<>();
        String sql = BASE_SELECT + " ORDER BY b.bill_id";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Bill> getBillsForPatient(int patientId) throws SQLException {
        List<Bill> list = new ArrayList<>();
        String sql = BASE_SELECT + " WHERE b.patient_id = ? ORDER BY b.bill_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public boolean markAsPaid(int billId) throws SQLException {
        String sql = "UPDATE billing SET status = 'PAID' WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteBill(int billId) throws SQLException {
        String sql = "DELETE FROM billing WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            return ps.executeUpdate() > 0;
        }
    }

    private Bill mapRow(ResultSet rs) throws SQLException {
        Bill b = new Bill();
        b.setBillId(rs.getInt("bill_id"));
        b.setPatientId(rs.getInt("patient_id"));
        int apptId = rs.getInt("appointment_id");
        if (!rs.wasNull()) b.setAppointmentId(apptId);
        b.setAmount(rs.getBigDecimal("amount"));
        Date d = rs.getDate("bill_date");
        if (d != null) b.setBillDate(d.toLocalDate());
        b.setStatus(rs.getString("status"));
        b.setPatientName(rs.getString("patient_name"));
        return b;
    }
}
