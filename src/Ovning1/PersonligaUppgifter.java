package Ovning1;
// PersonligaUppgifter.java

// ett program som hanterar personliga uppgifter

/**********************************************************************

Anv�ndaren matar in sina personliga uppgifter. Dessa uppgifter sparas
sedan i en fil.

**********************************************************************/

import java.io.PrintWriter;
import java.util.Scanner;

class PersonligaUppgifter
{
    public static void main (String[] args)
                           throws Exception    // (1)
	{
		System.out.println ("PERSONLIGA UPPGIFTER");
		System.out.println ();

		// inmatningsverktyg
		Scanner in = new Scanner (System.in);

		// mata in personliga uppgifter
		System.out.print ("Fodelsear: ");
		int    ar = in.nextInt ();
		in.nextLine ();    // (2)
 		System.out.print ("Ditt fornamn och efternamn: ");
		String    namn = in.nextLine ();
		System.out.println ();
		in.close();

		// spara uppgifter i en fil
		PrintWriter fout = new PrintWriter ("/Users/Oscar/persUpp.txt");
		fout.println (namn + ": " + ar);
		fout.flush ();
		fout.close();


		// ett meddelande
		System.out.println ("Oppna filen persUpp.txt!");
	}
}
