import java.util.Scanner;

public class Equation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        double a = input.nextDouble();

        System.out.print("Enter value of x: ");
        double x = input.nextDouble();

        double y = a * x * x * x + 7;

        System.out.println("y = " + y);
    }
}