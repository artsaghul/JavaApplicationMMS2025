package hospital;

import hospital.dao.*;
import hospital.models.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final PatientDAO patients = new PatientDAO();
    private static final PharmacistDAO pharmacists = new PharmacistDAO();
    private static final WardDAO wards = new WardDAO();
    private static final RoomDAO rooms = new RoomDAO();
    private static final TreatmentDAO treatments = new TreatmentDAO();
    private static final MedicalRecordDAO records = new MedicalRecordDAO();

    private static String ask(String p) { System.out.print(p + ": "); return in.nextLine().trim(); }
    private static int askInt(String p) { return Integer.parseInt(ask(p)); }
    private static LocalDate askDate(String p) { String s = ask(p + " (YYYY-MM-DD, blank=none)"); return s.isEmpty() ? null : LocalDate.parse(s); }

    public static void main(String[] args) {
        try {
            System.out.println("=== Hospital Management System ===");
            User u = new UserDAO().authenticate(ask("Username"), ask("Password"));
            if (u == null) { System.out.println("Invalid login."); return; }
            System.out.println("Welcome, " + u.username() + " (" + u.role() + ")");
            while (true) {
                System.out.println("\n1 Add patient   2 List patients  3 Delete patient\n4 Add ward      5 List wards\n"
                        + "6 Add room      7 List rooms\n8 Add pharmacist 9 List pharmacists\n"
                        + "10 Add medical record  11 Add treatment  12 List treatments\n0 Exit");
                try {
                    switch (askInt("Choose")) {
                        case 1 -> System.out.println("Saved, id=" + patients.add(new Patient(0, ask("First name"), ask("Last name"),
                                ask("Gender"), askDate("DOB"), ask("Phone"), ask("Email"), ask("Address"),
                                ask("Blood group"), ask("Emergency contact"))));
                        case 2 -> patients.getAll().forEach(System.out::println);
                        case 3 -> System.out.println(patients.delete(askInt("Patient id")) ? "Deleted." : "Not found.");
                        case 4 -> System.out.println("Saved, id=" + wards.add(new Ward(0, ask("Ward name"), null, ask("Type"))));
                        case 5 -> wards.getAll().forEach(System.out::println);
                        case 6 -> System.out.println("Saved, id=" + rooms.add(new Room(0, ask("Room number"), askInt("Ward id"), ask("Type"), true)));
                        case 7 -> rooms.getAll().forEach(System.out::println);
                        case 8 -> System.out.println("Saved, id=" + pharmacists.add(new Pharmacist(0, ask("First name"), ask("Last name"),
                                ask("Gender"), askDate("DOB"), ask("Phone"), ask("Email"), ask("Address"), null,
                                LocalDate.now(), ask("License no"))));
                        case 9 -> pharmacists.getAll().forEach(System.out::println);
                        case 10 -> System.out.println("Saved, id=" + records.add(new MedicalRecord(0, askInt("Patient id"),
                                askInt("Doctor staff id"), LocalDate.now(), ask("Notes"))));
                        case 11 -> System.out.println("Saved, id=" + treatments.add(new Treatment(0, askInt("Medical record id"),
                                ask("Treatment name"), ask("Description"), askDate("Start"), askDate("End"),
                                Double.parseDouble(ask("Cost")))));
                        case 12 -> treatments.getAll().forEach(System.out::println);
                        case 0 -> { return; }
                        default -> System.out.println("Unknown option.");
                    }
                } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
            }
        } catch (Exception e) { System.out.println("Fatal: " + e.getMessage()); }
    }
}
