package hospital.models;

import java.time.*;

public record Medication(int id, String name, String description, double unitPrice, int stock) {}
