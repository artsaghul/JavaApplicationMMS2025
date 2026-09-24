package hospital.models;

import java.time.*;

public record PrescriptionItem(int id, int prescriptionId, int medicationId, String dosage, int quantity, String instructions) {}
