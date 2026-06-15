import java.util.Scanner;

public class EvenOddCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        for (number % 2 == 0) {
            System.out.println(number + " is an even number.");
        } int{
            System.out.println(number + " is an odd number.");
        }

        scanner.close();
    }
}
