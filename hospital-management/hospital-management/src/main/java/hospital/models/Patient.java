package hospital.models;

import java.time.LocalDate;

public class Patient extends Person {
    private String bloodGroup, emergencyContact;

    public Patient(int id, String firstName, String lastName, String gender, LocalDate dob,
                   String phone, String email, String address, String bloodGroup, String emergencyContact) {
        super(id, firstName, lastName, gender, dob, phone, email, address);
        this.bloodGroup = bloodGroup; this.emergencyContact = emergencyContact;
    }
    public String getBloodGroup() { return bloodGroup; }
    public String getEmergencyContact() { return emergencyContact; }
    @Override public String toString() { return super.toString() + " | " + phone + " | Blood: " + bloodGroup; }
}
