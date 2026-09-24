package hospital.models;

import java.time.LocalDate;

public class Doctor extends Staff {
    private String specialization, licenseNo;

    public Doctor(int id, String firstName, String lastName, String gender, LocalDate dob, String phone,
                  String email, String address, Integer departmentId, LocalDate hireDate,
                  String specialization, String licenseNo) {
        super(id, firstName, lastName, gender, dob, phone, email, address, StaffRole.DOCTOR, departmentId, hireDate);
        this.specialization = specialization; this.licenseNo = licenseNo;
    }
    public String getSpecialization() { return specialization; }
    public String getLicenseNo() { return licenseNo; }
}
