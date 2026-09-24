package hospital.models;

import java.time.*;

public record MedicationDispensing(int id, int prescriptionItemId, int pharmacistId, LocalDateTime dispensedAt, int quantity) {}
