package hospital.models;

import java.time.*;

public record Ward(int id, String name, Integer departmentId, String type) {}
