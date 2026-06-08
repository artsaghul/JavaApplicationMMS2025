public class CompoundInterest {
    public static void main(String[] args) {
        double principal = 1000.0;

        for (int rate = 5; rate <= 10; rate++) {
            System.out.println("Interest rate: " + rate + "%");
            double r = rate / 100.0;

            for (int year = 1; year <= 10; year++) {
                double amount = principal * Math.pow(1.0 + r, year);
                System.out.printf("Year %d: %.2f%n", year, amount);
            }
            System.out.println();
        }
    }
}
