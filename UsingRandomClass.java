import java.util.Random;

public class UsingRandomClass{
	public static void main(String[] args){
		Random random = new Random();
		
		int generatedInt = random.nextInt();
		System.out.printf("The generated number is %d%n",generatedInt);
		
		int rangeNumber = random.nextInt(100) + 1;
		System.out.printf("The number generated is %d%n,rangeNumber);
		
		boolean IsJava = random.nextBoolean();
		Syatem.out.printf("Do you love Java? %b%n"IsJava);
		
		doublle declimalNumber = random.nextDouble();
		System.out.printf("The float-point value is %f%n",decimalNumber);
	}
}