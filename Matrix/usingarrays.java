import java.utl.Arrays;

public class UsingArraysClass{
	public static void main(String[] args){
		int[] numbers= {5,8,2,3,9,4,1,6,7,10};
		
		Arrays.sort(numbers);
		System.out.println("The elements of the array are");
		
		for(int number : numbers){
			System.out.printf("%d%n",numbers);
		}
		System.out.println("Binary search");
		int index = Arrays.binarysearch(numbers,9);
		system.out.printf("The index number of the element 9 is %d%n,index");
		
		boolean isEquadl = Array.equaps("a,b");
		System.out.printf(THe result is %b%n, isEqual);
		
		System.out.println("My fill arrays are");
		for(int number : myFillArr){
			System.out.printf("%d%n",number);
		}
		int[] evenumbers = {2,4,6,8,10,12,14,16,18,20};
		int[] copyarr = Arrays.copyOf(evenNumbers);
		System.out.println("The elementd of the array are");
		for(itn arr : copyArr){
			System.out.printf("%d%n",arr);
			
		}
		
		System.out.println(Array.toString(evenNumbers);
		
		int[] multi = {
			{9,8,6}
			{6,7,4}
			{9,3,2}	
		};
		System.out.println(Arrays.deepToString(multi));
		
		
	}
}