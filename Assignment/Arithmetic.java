import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int x = input.nextInt();

        System.out.print("Enter second integer: ");
        int y = input.nextInt();

        System.out.printf("Sum is %d%n", x + y);
        System.out.printf("Product is %d%n", x * y);
        System.out.printf("Difference is %d%n", x - y);
        System.out.printf("Quotient is %d%n", x / y);
    }
}