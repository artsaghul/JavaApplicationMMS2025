public class FacebookGrowth {
    public static void main(String[] args) {
        double users = 1_000_000_000; // starting point
        double growthRate = 0.04; // 4% per month
        int months = 0;

        // Growth to 1.5 billion
        while (users < 1_500_000_000) {
            users *= (1 + growthRate);
            months++;
        }
        System.out.println("Months to reach 1.5 billion: " + months);

        // Continue growth to 2 billion
        while (users < 2_000_000_000) {
            users *= (1 + growthRate);
            months++;
        }
        System.out.println("Months to reach 2 billion: " + months);
    }
}
