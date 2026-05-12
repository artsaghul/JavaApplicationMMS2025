import java.util.Scanner;

public class LargestSmallest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int a = input.nextInt();

        System.out.print("Enter second integer: ");
        int b = input.nextInt();

        System.out.print("Enter third integer: ");
        int c = input.nextInt();

        System.out.print("Enter fourth integer: ");
        int d = input.nextInt();

        System.out.print("Enter fifth integer: ");
        int e = input.nextInt();

        int largest = Math.max(a, Math.max(b, Math.max(c, Math.max(d, e))));
        int smallest = Math.min(a, Math.min(b, Math.min(c, Math.min(d, e))));

        System.out.printf("Largest is %d%n", largest);
        System.out.printf("Smallest is %d%n", smallest);
    }
}