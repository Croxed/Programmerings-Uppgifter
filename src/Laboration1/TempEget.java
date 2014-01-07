package Laboration1;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Oscar
 *
 */

public class TempEget {
	
	public static void main (String[] args)
	{
	System.out.println ("Calculating temperatures\n");
	
	// inmatningsverktyg
	Scanner in = new Scanner (System.in); 
	in.useLocale (Locale.US);
	
	// mata in uppgifter om antalet veckor och antalet 
	// mätningar
	
	System.out.print ("Number of weeks: ");
	int antalVeckor = in.nextInt (); 
	System.out.print ("Number of measurs per week: "); 
	int antalMatningarPerVecka = in.nextInt ();
	
	// plats att lagra temperaturer
	double[][] t = new double[antalVeckor +1][antalMatningarPerVecka +1];
	
	// mata in temperaturerna
	for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			System.out.println ("Temperatures week " + vecka + ":"); 
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++) 		
			t[vecka][matning] = in.nextDouble ();
		}
	System.out.println ();
	
	// visa temperaturerna
	System.out.println ("The temperatures:");
	for (int vecka = 1; vecka <= antalVeckor; vecka++) 
		{
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
			System.out.print (t[vecka][matning] + " "); 
		} 
	System.out.println ("\n");
	in.close();
	
	
	//MIN KOD
	
	//För varje vecka
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			//Min blir första värdet i varje vecka för att kunna jämföra med något
			double min = t[vecka][1];
			
			//Max blir första värdet i varje vecka för att kunna jämföra med något
			double max = t[vecka][1];
			double meanWeek = 0;

			System.out.println("\n - Week " + vecka + " - ");
			System.out.println("All values for week " + vecka);
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
			{
				//en Double för vaje temperatur
				double tempWeek = t[vecka][matning];
				
				//Medeltemperatur för veckan
				meanWeek += tempWeek;
				
					//Kollar om den hittar ett värde som är större än max
					if(tempWeek > max )
						max = tempWeek;
					
					//Kollar om den hittar ett värde som är mindre än min
					if(tempWeek < min)
						min = tempWeek;
					
					//Skriver ut temperaturerna
					System.out.print(tempWeek + " ");
			}
		
		//Delar summan av alla temperaturer på antal mätningar per vecka för att få medelvärdet	
		meanWeek = meanWeek / antalMatningarPerVecka;
		System.out.println("\n\nMax = " + max);
		System.out.println("Min = " + min);
		System.out.println("Mean = " + meanWeek + "\n---------------");
		}
		
		
		//Totalt
		double meanMain = 0;
		//Max för hela perioden blir första värdet i första veckan för att jämföra med något
		double maxMain = t[1][1];
		
		//Min -||-
		double minMain = t[1][1];
		
		System.out.println("\n - Total - ");
		System.out.println("All values for the period");
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
			{
				//En double blir varje temperatur
				double tempTotal = t[vecka][matning];
				
				//Lägger till temperaturerna till medeltemperaturen
				meanMain += tempTotal;
				
					//Kollar om någon temperatur är större än den angivna
					if(tempTotal > maxMain)
						maxMain = tempTotal;
					
					//Kollar om någon temperatur är mindre än den angivna
					if(tempTotal < minMain)
						minMain = tempTotal;
					//Klar
					System.out.print(tempTotal + " ");
			}
		}
		//Delar summan av alla temperaturer med antal temperaturer för alla veckor
		meanMain = meanMain / (antalMatningarPerVecka * antalVeckor);
		System.out.println("\nTotal max = " + maxMain);
		System.out.println("Total min = " + minMain);
		System.out.println("Total mean = " + meanMain);
	}
}