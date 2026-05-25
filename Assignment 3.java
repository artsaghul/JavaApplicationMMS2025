import java.util.Scanner;

public class UserDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        System.out.println("\n--- User Profile Summary ---");
        System.out.printf("Name:    %s%n", name);
        System.out.printf("Age:     %d years old%n", age);
        System.out.printf("Address: %s%n", address);

        scanner.close();
    }
}
