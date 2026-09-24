package hospital.models;

import java.time.*;

public record Bed(int id, String bedNumber, int roomId, boolean occupied) {}
