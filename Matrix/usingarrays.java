import java.utl.Arrays;

public class UsingArraysClass{
	public static void main(String[]a rgs){
		int[] numbers= {5,8,2,3,9,4,1,6,7,10};
		
		Arrays.sort(numbers);
		System.out.println("The elements of the array are");
		
		for(int number : numbers){
			System.out.printf("%d%n",numbers);
		}
	}
}