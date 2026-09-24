package hospital.models;

import java.time.*;

public record Treatment(int id, int medicalRecordId, String name, String description, LocalDate startDate, LocalDate endDate, double cost) {}
