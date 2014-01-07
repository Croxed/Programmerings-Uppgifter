package ExtraUppgifter;

import java.util.Scanner;

public class Extra1 {

	public static void main(String[] args) {
		//En scanner 
		Scanner in = new Scanner(System.in);
		//Man får mata in hur många tal man vill räkna med
		System.out.println("Hur många tal vill du skriva in?");
		int antal = in.nextInt();
		System.out.println("Skriv in dina " + antal + " värden.");
		int min = 0;
		//Forloop för att skriva in lika många värden som man valde att arbeta med.
		for(int i = 0; i < antal; i++)
		 {
			//Varde får nästa input man matar in
			int varde = in.nextInt();
			//Om man går igenom loopen första gången så får min första värdet man matar in.
			 if(i == 0)
				min = varde;
			 //Kollar om varde är mindre än min
			 if(min > varde)
				min = varde;
		 }
		in.close();
		System.out.println("Lägsta värdet är: " + min);
	}

}
