package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Room;
import java.sql.*;
import java.util.*;

public class RoomDAO {
    private Room map(ResultSet r) throws SQLException {
        return new Room(r.getInt("id"), r.getString("room_number"), r.getInt("ward_id"), r.getString("type"), r.getBoolean("available"));
    }

    public int add(Room room) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO rooms (room_number, ward_id, type, available) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, room.roomNumber()); ps.setInt(2, room.wardId());
            ps.setString(3, room.type()); ps.setBoolean(4, room.available());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next() ? k.getInt(1) : -1; }
        }
    }

    public List<Room> getAll() throws SQLException {
        List<Room> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             ResultSet r = c.createStatement().executeQuery("SELECT * FROM rooms ORDER BY id")) {
            while (r.next()) list.add(map(r));
        }
        return list;
    }

    public List<Room> getByWard(int wardId) throws SQLException {
        List<Room> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM rooms WHERE ward_id=?")) {
            ps.setInt(1, wardId);
            try (ResultSet r = ps.executeQuery()) { while (r.next()) list.add(map(r)); }
        }
        return list;
    }

    public boolean setAvailability(int id, boolean available) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE rooms SET available=? WHERE id=?")) {
            ps.setBoolean(1, available); ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }
}
