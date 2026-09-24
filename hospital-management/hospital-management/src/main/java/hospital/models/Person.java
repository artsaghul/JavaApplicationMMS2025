package hospital.models;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String firstName, lastName, gender, phone, email, address;
    protected LocalDate dateOfBirth;

    protected Person(int id, String firstName, String lastName, String gender,
                     LocalDate dateOfBirth, String phone, String email, String address) {
        this.id = id; this.firstName = firstName; this.lastName = lastName; this.gender = gender;
        this.dateOfBirth = dateOfBirth; this.phone = phone; this.email = email; this.address = address;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getFullName() { return firstName + " " + lastName; }
    @Override public String toString() { return "#" + id + " " + getFullName(); }
}
