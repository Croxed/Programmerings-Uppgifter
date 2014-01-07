package Ovning1;
// Kostnad.java

// ett program som utf?r en enkel ber?kning


/**********************************************************************

Det h?r programmet ber?knar kostnaden f?r ett antal likadana b?cker.
B?de antalet och pris p? b?ckerna matas in fr?n standardinmatnings-
enheten. Resultatet skrivs ut till standardutmatningsenheten.

**********************************************************************/


class Kostnad
{
    public static void main (String[] args)
	{
		System.out.println ("KOSTNAD");
		System.out.println ();

		// inmatningsverktyg
		java.util.Scanner    in = new java.util.Scanner (System.in);
		in.useLocale (java.util.Locale.US);

		// mata in uppgifter om antalet b?cker och deras pris
		System.out.print ("Antalet bocker: ");
		int    antal = in.nextInt ();
 		System.out.print ("Pris pa bockerna: ");
		double    pris = in.nextDouble ();
		in.close();
		// ber?kna den totala kostnaden
		double    kostnad = antal * pris;

		// visa resultatet
		System.out.println ("Den totala kostnaden: " + kostnad);
	}
}


/*----------------------------------------------------------------------

Programmets input och output vid en exekvering

KOSTNAD

Antalet bocker: 25
Pris pa bockerna: 50
Den totala kostnaden: 1250.0


Programmets input och output vid en annan exekvering

KOSTNAD

Antalet bocker: 47
Pris pa bockerna: 230
Den totala kostnaden: 10810.0

----------------------------------------------------------------------*/
