public class RecursiveMethod{
	public static void main(String[] args){
		displayName("JOhn Doe");
	}
	public static void displayName(String name){
		String name = "John Doe";
		System.out.printf("Your name is %s%n",name);
		
		
		
		displayName("John Doe");
	}
}