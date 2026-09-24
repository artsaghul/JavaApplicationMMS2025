package hospital.models;

import java.time.*;

public record Diagnosis(int id, int medicalRecordId, String description, String icdCode) {}
