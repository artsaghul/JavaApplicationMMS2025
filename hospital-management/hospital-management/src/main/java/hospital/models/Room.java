package hospital.models;

import java.time.*;

public record Room(int id, String roomNumber, int wardId, String type, boolean available) {}
