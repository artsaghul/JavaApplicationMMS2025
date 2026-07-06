public class StudentGradingSystem{
	public static void main(Strings[] args){
		String names = ("John","Jack","Mary","James","Lucy");
		int[] marks = (69,80,100,70,50);
		
		for(int i = 0; i<5; i++){
			if(marks[i] >=70){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Student Grade: A");
				System.out.println("=======================\n\n\n\n")
			}
			 else if(marks[i] >=60){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Student Grade: B");
				System.out.println("=======================\n\n\n\n")
		    }  
		    if(marks[i] >=50){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Student Grade: C");
				System.out.println("=======================\n\n\n\n")
	        }
	        if(marks[i] >=40){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Student Grade: D");
				System.out.println("=======================\n\n\n\n")
					
			}
			else if(marks[i] >=70){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Student Grade: F");
				System.out.println("=======================\n\n\n\n")
}
        }//Traditional for loop ends here
		
	}
}	