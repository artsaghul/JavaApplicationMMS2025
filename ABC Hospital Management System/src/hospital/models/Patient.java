
package hospital.models;


public class Patient extends Person{
    private String patientId;
    private String bloodGroup;
    private String genotype;
    private String allergies;
    private String emergencyContact;
    private String emergencyPhone;
    
    public Patient(String patientId, String bloodGroup, String genotype, String allergies, String emergencyContact, String emergencyPhone, Patient ) {
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.genotype = genotype;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.emergencyPhone = emergencyPhone;
        
    }
}
