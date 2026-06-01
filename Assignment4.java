import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int[] num = new int[10];

        for (int i = 0; i < 10; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            num[i] = input.nextInt();
        }

        int sum1 = num[0] + num[4] + num[9];
        int sum2 = num[2] + num[7] + num[1];
        int multiply = sum1 * sum2;
       int sum3 = num[3] + num[6] + num[5] + num[8];
        int finalResult = sum3 - multiply;

        System.out.println("Final Result = " + finalResult);

        if (finalResult >= 100) {
            System.out.println("Hurray I did it");
        } else if (finalResult < 100) {
            System.out.println("No, you didn't do it properly");
            System.out.println("I still need to learn more in Java");
        }
    }
}
