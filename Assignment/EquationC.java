public class EquationC {
    public static void main(String[] args) {
        int x = 3 * 9 * (3 + (9 * 3 / 3));
        System.out.println("Step 1: 9 * 3 = 27");
        System.out.println("Step 2: 27 / 3 = 9");
        System.out.println("Step 3: 3 + 9 = 12");
        System.out.println("Step 4: 3 * 9 = 27");
        System.out.println("Step 5: 27 * 12 = 324");
        System.out.println("x = " + x);
    }
}