import java.util.Scanner;

public class Userinput{
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	
	System.out.printf("Enter your name:  ");
	String name = input.nextline();
	
	System.out.printf("Enter your gender");
	String gender = input.next();
	
	
	System.out.print("Enter your age: ");
	int age = input.nextByte();
	
	System.out.print("Enter the number of students in your class: ");
	short numberOfStdents = input.nextShort();
	
	System.out.print("Do u love learning java(true/faluse): ");
	boolean loveJava = input.nextBoolean();
	
	System.out.println("");
	System.out.printf("Information about %s$n",name");
	System.out.println ("===========");
	
	System.out.printf("Hello %s, oyu are welcome to NIIT%n" ,name);
	System.ou.printf("You are a %s and tyou are %d years old%n" , gender,age);
	System.out.printf("There are %d students in your class%n" , numberofStudent);
	System.out.printf("THe total number of students in your school is %,d%n",totalNumber);
	System.out.printf("Your grade is %c%n" ,grade);
	System.out.printf("Do u love Java?%b%n" , loveJava);
  }
}	