package Ovning1;
// HanteraTal.java

// ett program som p? olika s?tt hanterar ett antal tal


/**********************************************************************

Ett antal tal matas in fr?n standardinmatningsenheten. Det minsta av
dessa tal best?ms, och skrivs ut till standardutmatningsenheten. Talen
sorteras sedan, och skrivs ut i stigande ordning.

**********************************************************************/


class HanteraTal
{
    public static void main (String[] args)
	{
        System.out.println ("HANTERA TAL");
        System.out.println ();

		// inmatningsverktyg
		java.util.Scanner    in = new java.util.Scanner (System.in);
		in.useLocale (java.util.Locale.US);

        // mata in ett antal tal
        double[]    tal = new double[10];
        System.out.println ("ange precis 10 tal:");
        for (int pos = 0; pos < tal.length; pos++)
            tal[pos] = in.nextDouble ();
        in.close();
        // best?m det minsta av talen
        double    min = tal[0];
        for (int pos = 1; pos < tal.length; pos++)
            if (tal[pos] < min)
                min = tal[pos];

        // visa det minsta av talen
        System.out.println ("\ndet minsta av talen: " + min);

        // sortera talen i stigande ordning
        int    minPos = 0;
        double    t = 0;
        for (int pos = 0; pos < tal.length - 1; pos++)
        {
			minPos = pos;
			for (int p = pos + 1; p < tal.length; p++)
			    if (tal[p] < tal[minPos])
			        minPos = p;
			t = tal[pos];
			tal[pos] = tal[minPos];
			tal[minPos] = t;
		}

		// visa talen efter sorteringen
		System.out.println ("\ntalen i stigande ordning:");
		for (double q : tal)
		    System.out.print (q + "  ");
		System.out.println ();
	}
}
