package hospital.models;

import java.time.*;

public record MedicalRecord(int id, int patientId, int doctorId, LocalDate recordDate, String notes) {}
