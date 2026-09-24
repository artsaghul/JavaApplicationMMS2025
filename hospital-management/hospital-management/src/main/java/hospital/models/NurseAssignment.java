package hospital.models;

import java.time.*;

public record NurseAssignment(int id, int nurseId, int patientId, int wardId, LocalDate assignmentDate, String shift) {}
