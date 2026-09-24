package hospital.models;

import java.time.LocalDate;

public class Nurse extends Staff {
    private String shift;

    public Nurse(int id, String firstName, String lastName, String gender, LocalDate dob, String phone,
              String email, String address, Integer departmentId, LocalDate hireDate, String shift) {
        super(id, firstName, lastName, gender, dob, phone, email, address, StaffRole.NURSE, departmentId, hireDate);
        this.shift = shift;
    }
    public String getShift() { return shift; }
}
