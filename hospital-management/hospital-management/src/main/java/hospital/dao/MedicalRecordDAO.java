package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.MedicalRecord;
import java.sql.*;
import java.util.*;

public class MedicalRecordDAO {
    public int add(MedicalRecord m) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO medical_records (patient_id, doctor_id, record_date, notes) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.patientId()); ps.setInt(2, m.doctorId());
            ps.setDate(3, java.sql.Date.valueOf(m.recordDate())); ps.setString(4, m.notes());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    public List<MedicalRecord> getByPatient(int patientId) throws SQLException {
        List<MedicalRecord> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM medical_records WHERE patient_id=? ORDER BY record_date DESC")) {
            ps.setInt(1, patientId);
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) list.add(new MedicalRecord(r.getInt("id"), r.getInt("patient_id"),
                        r.getInt("doctor_id"), r.getDate("record_date").toLocalDate(), r.getString("notes")));
            }
        }
        return list;
    }
}
