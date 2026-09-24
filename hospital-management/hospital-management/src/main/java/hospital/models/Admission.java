package hospital.models;

import java.time.*;

public record Admission(int id, int patientId, int bedId, int doctorId, LocalDate admitDate, LocalDate dischargeDate, String reason) {}
