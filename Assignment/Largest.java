import java.util.Scanner;

public class Largest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int x = input.nextInt();

        System.out.print("Enter second integer: ");
        int y = input.nextInt();

        System.out.print("Enter third integer: ");
        int z = input.nextInt();

        int sum = x + y + z;
        int product = x * y * z;
        int average = sum / 3;
        int smallest = Math.min(x, Math.min(y, z));
        int largest = Math.max(x, Math.max(y, z));

        System.out.printf("Sum is %d%n", sum);
        System.out.printf("Average is %d%n", average);
        System.out.printf("Product is %d%n", product);
        System.out.printf("Smallest is %d%n", smallest);
        System.out.printf("Largest is %d%n", largest);
    }
}