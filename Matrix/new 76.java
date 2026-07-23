import java.time.LocalDate;

public class UsingLocal Date{
	public static void main(String[] args){
		LocalDate todaysDate = LocalDate.now();
		LocalDate myBirthDate = LocalDate.of(2000,5,25);
		LocalDate resumptionDate = LocalDate.parse("2026-10-15");
		
		
		
		System.out.printf("Today's date is %s%n",todaysDate);
		System.out.printf("My birth date is %s%n",myBirthDate);h
	}
}