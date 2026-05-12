import java.util.Scanner;

public class CarPool {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter total miles driven per day: ");
        double miles = input.nextDouble();

        System.out.print("Enter cost per gallon of gasoline: ");
        double costPerGallon = input.nextDouble();

        System.out.print("Enter average miles per gallon: ");
        double milesPerGallon = input.nextDouble();

        System.out.print("Enter parking fees per day: ");
        double parking = input.nextDouble();

        System.out.print("Enter tolls per day: ");
        double tolls = input.nextDouble();

        double gasolineCost = (miles / milesPerGallon) * costPerGallon;
        double totalCost = gasolineCost + parking + tolls;

        System.out.printf("%nDaily Driving Cost Breakdown:%n");
        System.out.printf("Gasoline cost:  $%.2f%n", gasolineCost);
        System.out.printf("Parking fees:   $%.2f%n", parking);
        System.out.printf("Tolls:          $%.2f%n", tolls);
        System.out.printf("Total cost per day: $%.2f%n", totalCost);
    }
}