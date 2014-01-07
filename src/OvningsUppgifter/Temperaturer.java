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
	// m�tningar
	
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
	
	// minsta, st�rsta och medeltemperaturer veckovis 
	// koden ska skrivas h�r
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
				//Skriver ut m�tningarna
				System.out.print(matningPerVecka[matning] + " ");
			}
			mean = mean / antalMatningarPerVecka;
			Arrays.sort(matningPerVecka);
			System.out.println("Min = " + min);
			System.out.println("\nHighest temperature for week " + vecka + ": " + matningPerVecka[antalMatningarPerVecka]);
			System.out.println("Lowest temperature for week " + vecka + ": " + matningPerVecka[1]);
			System.out.println("Mean value for week " + vecka + ": " + mean + "\n");
		} 
	// den minsta, den st�rsta och medeltemperaturen i hela m�tperioden
	// koden ska skrivas h�r
		ArrayList<Double> matningTotalt = new ArrayList<Double>();
		double meanMain = 0.0;
		for(int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			for(int matning = 1; matning <= antalMatningarPerVecka; matning++)
			{
				//Loopar alla mätningar igen
				matningPerVecka[matning] = t[vecka][matning];
				//G�r en ny variabel som f�r mätningen
				double totalaTemp = matningPerVecka[matning];
				//Lägger till värdet i en ArrayList
				matningTotalt.add(totalaTemp);
				//Adderar temperaturen till en variabel som skall ber�kna ut medelv�rdet
				meanMain += totalaTemp; 
			}
		}
		//Ber�knar medelv�rdet
		meanMain = meanMain / (antalMatningarPerVecka * antalVeckor);
		//Sorterar listan
		Collections.sort(matningTotalt);
		//Skriver ut alla v�rden.
		System.out.println(" - Temperatures for the entire period - \n" + matningTotalt + " - ");
		System.out.println("Highest temperature for the entire peroid : " + matningTotalt.get((antalMatningarPerVecka * antalVeckor)-1));
		System.out.println("Lowest temperature for the entire period : " + matningTotalt.get(0));
		System.out.println("Mean value for the entire period " + meanMain);
	// visa de typiska temperaturerna
	// koden ska skrivas h�r 
		//SE ALL KOD OVAN
	}

}

