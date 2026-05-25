 
public class NumberCalculations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        double product = 1;

        System.out.println("Enter 5 numbers:");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Number " + i + ": ");
            double num = scanner.nextDouble();
            sum += num;
            product *= num;
        }

        double average = sum / 5;

        System.out.println("\nResults:");
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Product: " + product);
        
        scanner.close();
    }
}
