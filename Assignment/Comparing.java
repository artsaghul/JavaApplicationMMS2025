import java.util.Scanner;

public class Comparing {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int x = input.nextInt();

        System.out.print("Enter second integer: ");
        int y = input.nextInt();

        if (x > y)
            System.out.printf("%d is larger%n", x);
        else if (y > x)
            System.out.printf("%d is larger%n", y);
        else
            System.out.println("These numbers are equal");
    }
}