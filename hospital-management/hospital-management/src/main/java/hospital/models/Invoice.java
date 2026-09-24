package hospital.models;

import java.time.*;

public record Invoice(int id, int patientId, LocalDate invoiceDate, double total, String status) {}
