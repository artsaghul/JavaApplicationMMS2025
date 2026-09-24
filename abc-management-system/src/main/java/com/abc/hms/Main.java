package com.abc.hms;

import com.abc.hms.dao.AppointmentDAO;
import com.abc.hms.dao.BillingDAO;
import com.abc.hms.dao.DoctorDAO;
import com.abc.hms.dao.PatientDAO;
import com.abc.hms.model.Appointment;
import com.abc.hms.model.Bill;
import com.abc.hms.model.Doctor;
import com.abc.hms.model.Patient;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * ABC Management System — console entry point.
 * A menu-driven Hospital Management System backed by MySQL via JDBC.
 */
public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final PatientDAO patientDAO = new PatientDAO();
    private static final DoctorDAO doctorDAO = new DoctorDAO();
    private static final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static final BillingDAO billingDAO = new BillingDAO();
    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Welcome to ABC Management System (Hospital)");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = SC.nextLine().trim();
            try {
                switch (choice) {
                    case "1": patientMenu(); break;
                    case "2": doctorMenu(); break;
                    case "3": appointmentMenu(); break;
                    case "4": billingMenu(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid option, try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment Management");
        System.out.println("4. Billing");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // ---------------------------------------------------------------
    // PATIENTS
    // ---------------------------------------------------------------
    private static void patientMenu() throws SQLException {
        System.out.println("\n-- Patient Management --");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Search Patient by Name");
        System.out.println("4. Update Patient");
        System.out.println("5. Delete Patient");
        System.out.print("Choose an option: ");
        String choice = SC.nextLine().trim();

        switch (choice) {
            case "1": {
                System.out.print("First name: "); String fn = SC.nextLine();
                System.out.print("Last name: "); String ln = SC.nextLine();
                System.out.print("Date of birth (yyyy-MM-dd): "); LocalDate dob = LocalDate.parse(SC.nextLine());
                System.out.print("Gender (M/F/Other): "); String gender = SC.nextLine();
                System.out.print("Phone: "); String phone = SC.nextLine();
                System.out.print("Email: "); String email = SC.nextLine();
                System.out.print("Address: "); String address = SC.nextLine();
                Patient p = new Patient(fn, ln, dob, gender, phone, email, address);
                int id = patientDAO.addPatient(p);
                System.out.println("Patient added with ID: " + id);
                break;
            }
            case "2": {
                List<Patient> patients = patientDAO.getAllPatients();
                patients.forEach(System.out::println);
                break;
            }
            case "3": {
                System.out.print("Search keyword: "); String kw = SC.nextLine();
                patientDAO.searchByName(kw).forEach(System.out::println);
                break;
            }
            case "4": {
                System.out.print("Patient ID to update: "); int id = Integer.parseInt(SC.nextLine());
                Patient existing = patientDAO.getPatientById(id);
                if (existing == null) { System.out.println("Not found."); break; }
                System.out.print("New phone (" + existing.getPhone() + "): ");
                String phone = SC.nextLine();
                if (!phone.isBlank()) existing.setPhone(phone);
                System.out.print("New email (" + existing.getEmail() + "): ");
                String email = SC.nextLine();
                if (!email.isBlank()) existing.setEmail(email);
                System.out.print("New address (" + existing.getAddress() + "): ");
                String address = SC.nextLine();
                if (!address.isBlank()) existing.setAddress(address);
                boolean ok = patientDAO.updatePatient(existing);
                System.out.println(ok ? "Updated." : "Update failed.");
                break;
            }
            case "5": {
                System.out.print("Patient ID to delete: "); int id = Integer.parseInt(SC.nextLine());
                System.out.println(patientDAO.deletePatient(id) ? "Deleted." : "Delete failed.");
                break;
            }
            default: System.out.println("Invalid option.");
        }
    }

    // ---------------------------------------------------------------
    // DOCTORS
    // ---------------------------------------------------------------
    private static void doctorMenu() throws SQLException {
        System.out.println("\n-- Doctor Management --");
        System.out.println("1. Add Doctor");
        System.out.println("2. View All Doctors");
        System.out.println("3. Search Doctor by Specialization");
        System.out.println("4. Delete Doctor");
        System.out.print("Choose an option: ");
        String choice = SC.nextLine().trim();

        switch (choice) {
            case "1": {
                System.out.print("First name: "); String fn = SC.nextLine();
                System.out.print("Last name: "); String ln = SC.nextLine();
                System.out.print("Specialization: "); String spec = SC.nextLine();
                System.out.print("Phone: "); String phone = SC.nextLine();
                System.out.print("Email: "); String email = SC.nextLine();
                System.out.print("Department ID (blank for none): "); String deptStr = SC.nextLine();
                Integer deptId = deptStr.isBlank() ? null : Integer.parseInt(deptStr);
                Doctor d = new Doctor(fn, ln, spec, phone, email, deptId);
                int id = doctorDAO.addDoctor(d);
                System.out.println("Doctor added with ID: " + id);
                break;
            }
            case "2": {
                doctorDAO.getAllDoctors().forEach(System.out::println);
                break;
            }
            case "3": {
                System.out.print("Specialization keyword: "); String kw = SC.nextLine();
                doctorDAO.getDoctorsBySpecialization(kw).forEach(System.out::println);
                break;
            }
            case "4": {
                System.out.print("Doctor ID to delete: "); int id = Integer.parseInt(SC.nextLine());
                System.out.println(doctorDAO.deleteDoctor(id) ? "Deleted." : "Delete failed.");
                break;
            }
            default: System.out.println("Invalid option.");
        }
    }

    // ---------------------------------------------------------------
    // APPOINTMENTS
    // ---------------------------------------------------------------
    private static void appointmentMenu() throws SQLException {
        System.out.println("\n-- Appointment Management --");
        System.out.println("1. Book Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Appointments for a Patient");
        System.out.println("4. View Appointments for a Doctor");
        System.out.println("5. Cancel Appointment");
        System.out.println("6. Mark Appointment Completed");
        System.out.print("Choose an option: ");
        String choice = SC.nextLine().trim();

        switch (choice) {
            case "1": {
                System.out.print("Patient ID: "); int pid = Integer.parseInt(SC.nextLine());
                System.out.print("Doctor ID: "); int did = Integer.parseInt(SC.nextLine());
                System.out.print("Date/time (yyyy-MM-dd HH:mm): ");
                LocalDateTime dt = LocalDateTime.parse(SC.nextLine(), DT_FMT);
                System.out.print("Reason: "); String reason = SC.nextLine();
                Appointment a = new Appointment(pid, did, dt, reason);
                int id = appointmentDAO.bookAppointment(a);
                System.out.println("Appointment booked with ID: " + id);
                break;
            }
            case "2": {
                appointmentDAO.getAllAppointments().forEach(System.out::println);
                break;
            }
            case "3": {
                System.out.print("Patient ID: "); int pid = Integer.parseInt(SC.nextLine());
                appointmentDAO.getAppointmentsForPatient(pid).forEach(System.out::println);
                break;
            }
            case "4": {
                System.out.print("Doctor ID: "); int did = Integer.parseInt(SC.nextLine());
                appointmentDAO.getAppointmentsForDoctor(did).forEach(System.out::println);
                break;
            }
            case "5": {
                System.out.print("Appointment ID to cancel: "); int id = Integer.parseInt(SC.nextLine());
                System.out.println(appointmentDAO.cancelAppointment(id) ? "Cancelled." : "Failed.");
                break;
            }
            case "6": {
                System.out.print("Appointment ID to complete: "); int id = Integer.parseInt(SC.nextLine());
                System.out.println(appointmentDAO.updateStatus(id, "COMPLETED") ? "Marked completed." : "Failed.");
                break;
            }
            default: System.out.println("Invalid option.");
        }
    }

    // ---------------------------------------------------------------
    // BILLING
    // ---------------------------------------------------------------
    private static void billingMenu() throws SQLException {
        System.out.println("\n-- Billing --");
        System.out.println("1. Create Bill");
        System.out.println("2. View All Bills");
        System.out.println("3. View Bills for a Patient");
        System.out.println("4. Mark Bill as Paid");
        System.out.print("Choose an option: ");
        String choice = SC.nextLine().trim();

        switch (choice) {
            case "1": {
                System.out.print("Patient ID: "); int pid = Integer.parseInt(SC.nextLine());
                System.out.print("Appointment ID (blank for none): "); String apptStr = SC.nextLine();
                Integer apptId = apptStr.isBlank() ? null : Integer.parseInt(apptStr);
                System.out.print("Amount: "); BigDecimal amount = new BigDecimal(SC.nextLine());
                Bill b = new Bill(pid, apptId, amount);
                int id = billingDAO.createBill(b);
                System.out.println("Bill created with ID: " + id);
                break;
            }
            case "2": {
                billingDAO.getAllBills().forEach(System.out::println);
                break;
            }
            case "3": {
                System.out.print("Patient ID: "); int pid = Integer.parseInt(SC.nextLine());
                billingDAO.getBillsForPatient(pid).forEach(System.out::println);
                break;
            }
            case "4": {
                System.out.print("Bill ID to mark paid: "); int id = Integer.parseInt(SC.nextLine());
                System.out.println(billingDAO.markAsPaid(id) ? "Marked paid." : "Failed.");
                break;
            }
            default: System.out.println("Invalid option.");
        }
    }
}
