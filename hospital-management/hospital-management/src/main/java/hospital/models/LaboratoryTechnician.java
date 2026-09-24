package hospital.models;

import java.time.LocalDate;

public class LaboratoryTechnician extends Staff {
    private String specialty;

    public LaboratoryTechnician(int id, String firstName, String lastName, String gender, LocalDate dob, String phone,
              String email, String address, Integer departmentId, LocalDate hireDate, String specialty) {
        super(id, firstName, lastName, gender, dob, phone, email, address, StaffRole.LAB_TECHNICIAN, departmentId, hireDate);
        this.specialty = specialty;
    }
    public String getSpecialty() { return specialty; }
}
