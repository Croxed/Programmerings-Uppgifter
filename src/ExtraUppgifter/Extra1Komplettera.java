package ExtraUppgifter;

import java.util.Arrays;
import java.util.Scanner;


public class Extra1Komplettera {
	// min returnerar det minsta elementet i en sekventiell
	// samling. Om samlingen är tom, kastas ett undantag
	// av typen IllegalArgumentException.
	public static int min(int[] element) 
			throws IllegalArgumentException{
		
			if (element.length == 0){
				throw new IllegalArgumentException ("tom samling");
			}
				int[] sekvens = element;
				int antaletPar = sekvens.length / 2;
				int antaletOparadeElement = sekvens.length % 2;
				int antaletTankbaraElement = antaletPar + antaletOparadeElement;
				int[] delsekvens = new int[antaletTankbaraElement];
				int i = 0;
				int j = 0;
				System.out.println(Arrays.toString(sekvens));
				while (sekvens.length > 1)
					{
					// skilj ur en delsekvens med de tänkbara elementen
					j = 0;
					i = 0;
					while (j < antaletPar)
					{
					delsekvens[j++] = (sekvens[i] < sekvens[i + 1]) ? sekvens[i] : sekvens[i + 1];
					i += 2;
					}
					if (antaletOparadeElement == 1)
						delsekvens[j] = sekvens[sekvens.length - 1];
			// utgå nu ifrån delsekvensen
				sekvens = delsekvens;
				antaletPar = antaletTankbaraElement / 2;
				antaletOparadeElement = antaletTankbaraElement % 2;
				antaletTankbaraElement = antaletPar + antaletOparadeElement;
				delsekvens = new int[antaletTankbaraElement];
				System.out.println(Arrays.toString(sekvens));

		}
		
		// sekvens[0] är det enda återstående tänkbara elementet
		// - det är det minsta elementet
		return sekvens[0];
		}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Hur många tal vill du arbeta med?");
		int antalTal =  in.nextInt();
		int [] element = new int[antalTal];
		System.out.println("Skriv in dina tal.");
		for(int i = 0; i < antalTal; i++)
		{
			element[i] = in.nextInt();
		}
	int m = min(element);
	in.close();
	System.out.println(m);
}
}