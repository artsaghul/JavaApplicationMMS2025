package hospital.models;

import java.time.*;

public record User(int id, String username, String passwordHash, StaffRole role, Integer staffId) {}
