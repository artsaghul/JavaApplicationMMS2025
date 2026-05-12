import java.util.Scanner;

public class NegPosZero {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int negatives = 0;
        int positives = 0;
        int zeros = 0;

        System.out.print("Enter first number: ");
        int a = input.nextInt();

        System.out.print("Enter second number: ");
        int b = input.nextInt();

        System.out.print("Enter third number: ");
        int c = input.nextInt();

        System.out.print("Enter fourth number: ");
        int d = input.nextInt();

        System.out.print("Enter fifth number: ");
        int e = input.nextInt();

        if (a > 0) positives++; else if (a < 0) negatives++; else zeros++;
        if (b > 0) positives++; else if (b < 0) negatives++; else zeros++;
        if (c > 0) positives++; else if (c < 0) negatives++; else zeros++;
        if (d > 0) positives++; else if (d < 0) negatives++; else zeros++;
        if (e > 0) positives++; else if (e < 0) negatives++; else zeros++;

        System.out.printf("Negatives: %d%n", negatives);
        System.out.printf("Positives: %d%n", positives);
        System.out.printf("Zeros: %d%n", zeros);
    }
}