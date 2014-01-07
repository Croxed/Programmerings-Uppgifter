package OvningsUppgifter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Oscar
 *
 */

public class Temperaturer {
	
	public static void main (String[] args)
	{
	System.out.println ("TEMPERATURES\n");
	
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
	
	// minsta, största och medeltemperaturer veckovis 
	// koden ska skrivas här
		double[] matningPerVecka = new double[antalMatningarPerVecka + 1];
		//double max = 0;
		for (int vecka = 1; vecka <= antalVeckor; vecka++) 
		{
			//Skriv ut vecka
			double min = t[vecka][1];
			double mean = 0.0;
			System.out.println(" - Week " + vecka + " - ");
			System.out.println("Temperatures for week " + vecka);
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
			{
				mean += t[vecka][matning];
				matningPerVecka[matning] = t[vecka][matning];
				//Skriver ut mätningarna
				System.out.print(matningPerVecka[matning] + " ");
			}
			mean = mean / antalMatningarPerVecka;
			Arrays.sort(matningPerVecka);
			System.out.println("Min = " + min);
			System.out.println("\nHighest temperature for week " + vecka + ": " + matningPerVecka[antalMatningarPerVecka]);
			System.out.println("Lowest temperature for week " + vecka + ": " + matningPerVecka[1]);
			System.out.println("Mean value for week " + vecka + ": " + mean + "\n");
		} 
	// den minsta, den största och medeltemperaturen i hela mätperioden
	// koden ska skrivas här
		ArrayList<Double> matningTotalt = new ArrayList<Double>();
		double meanMain = 0.0;
		for(int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			for(int matning = 1; matning <= antalMatningarPerVecka; matning++)
			{
				//Loopar alla mÃ¤tningar igen
				matningPerVecka[matning] = t[vecka][matning];
				//Gör en ny variabel som får mÃ¤tningen
				double totalaTemp = matningPerVecka[matning];
				//LÃ¤gger till vÃ¤rdet i en ArrayList
				matningTotalt.add(totalaTemp);
				//Adderar temperaturen till en variabel som skall beräkna ut medelvärdet
				meanMain += totalaTemp; 
			}
		}
		//Beräknar medelvärdet
		meanMain = meanMain / (antalMatningarPerVecka * antalVeckor);
		//Sorterar listan
		Collections.sort(matningTotalt);
		//Skriver ut alla värden.
		System.out.println(" - Temperatures for the entire period - \n" + matningTotalt + " - ");
		System.out.println("Highest temperature for the entire peroid : " + matningTotalt.get((antalMatningarPerVecka * antalVeckor)-1));
		System.out.println("Lowest temperature for the entire period : " + matningTotalt.get(0));
		System.out.println("Mean value for the entire period " + meanMain);
	// visa de typiska temperaturerna
	// koden ska skrivas här 
		//SE ALL KOD OVAN
	}

}

