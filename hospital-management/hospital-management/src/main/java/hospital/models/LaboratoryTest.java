package hospital.models;

import java.time.*;

public record LaboratoryTest(int id, int patientId, int doctorId, Integer technicianId, String testName, LocalDate requestedDate, String result, String status, double cost) {}
