package hospital.models;

import java.time.LocalDate;

public class Pharmacist extends Staff {
    private String licenseNo;

    public Pharmacist(int id, String firstName, String lastName, String gender, LocalDate dob, String phone,
              String email, String address, Integer departmentId, LocalDate hireDate, String licenseNo) {
        super(id, firstName, lastName, gender, dob, phone, email, address, StaffRole.PHARMACIST, departmentId, hireDate);
        this.licenseNo = licenseNo;
    }
    public String getLicenseNo() { return licenseNo; }
}
