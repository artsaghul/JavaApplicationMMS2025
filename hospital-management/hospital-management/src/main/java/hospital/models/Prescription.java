package hospital.models;

import java.time.*;

public record Prescription(int id, int patientId, int doctorId, LocalDate prescriptionDate, String notes) {}
