import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter weight (pounds): ");
        double weight = input.nextDouble();

        System.out.print("Enter height (inches): ");
        double height = input.nextDouble();

        double bmi = (weight * 703) / (height * height);

        System.out.printf("Your BMI is %.1f%n", bmi);

        if (bmi < 18.5)
            System.out.println("BMI Values");
        if (bmi < 18.5)
            System.out.println("Underweight: less than 18.5");
        else if (bmi < 25.0)
            System.out.println("Normal:      between 18.5 and 24.9");
        else if (bmi < 30.0)
            System.out.println("Overweight:  between 25 and 29.9");
        else
            System.out.println("Obese:       30 or greater");
    }
}