// 5. Main class
public class Main {
    public static void main(String[] args) {
        // Create one Teacher object and one Student object
        Person teacher = new Teacher("Mr. Smith", 42);
        Person student = new Student("Alice", 20);

        // Call methods for Teacher
        System.out.println("--- Teacher Details ---");
        teacher.displayDetails();
        teacher.performDuty();

        System.out.println(); // Blank line for spacing

        // Call methods for Student
        System.out.println("--- Student Details ---");
        student.displayDetails();
        student.performDuty();
    }
}