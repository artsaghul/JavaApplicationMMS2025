package hospital.models;

import java.time.LocalDate;

public class Staff extends Person {
    protected StaffRole role;
    protected Integer departmentId;
    protected LocalDate hireDate;

    public Staff(int id, String firstName, String lastName, String gender, LocalDate dob, String phone,
                 String email, String address, StaffRole role, Integer departmentId, LocalDate hireDate) {
        super(id, firstName, lastName, gender, dob, phone, email, address);
        this.role = role; this.departmentId = departmentId; this.hireDate = hireDate;
    }
    public StaffRole getRole() { return role; }
    public Integer getDepartmentId() { return departmentId; }
    public LocalDate getHireDate() { return hireDate; }
    @Override public String toString() { return super.toString() + " | " + role; }
}
