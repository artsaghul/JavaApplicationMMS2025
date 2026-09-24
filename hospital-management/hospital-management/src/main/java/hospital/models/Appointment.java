package hospital.models;

import java.time.*;

public record Appointment(int id, int patientId, int doctorId, LocalDateTime appointmentTime, String status, String reason) {}
