import java.util.Scanner;

public class FairTaxCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] categories = {"Housing", "Food", "Clothing", "Transportation", "Education", "Health care", "Vacations"};
        double totalExpenses = 0;

        for (String category : categories) {
            System.out.print("Enter your expenses for " + category + ": ");
            totalExpenses += input.nextDouble();
        }

        double fairTax = totalExpenses * 0.23; // 23% consumption tax
        System.out.printf("Your estimated FairTax is: %.2f%n", fairTax);
    }
}
