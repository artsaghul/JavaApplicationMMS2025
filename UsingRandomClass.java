import java.util.Random;

public class UsingRandomClass{
	public static void main(String[] args){
		Random random = new Random();
		
		int generatedInt = random.nextInt();
		System.out.printf("The generated number is %d%n",generatedInt);
	}
}