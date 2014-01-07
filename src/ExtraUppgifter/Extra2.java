package ExtraUppgifter;

/* --------------------------------------------------------------------------------------------------------------


					ALGORITM: utbytessortering
					
					Förvillkor:
					U är en mängd vars element kan jämföras med operatorn <.
					n/ ε N ,m/ ≥ Xn/ ≥ 1 , Xn/ = { X1/, X2/, ...,Xn/ } ⊂ U
					för 1 ≤ Xn/ ≤ m/ betecknar (in) det element som finns på positionen i
					{ X1 <= X2 <= ... <= Xn }
					
					Eftervillkor:
					Sorterad mängd vars minsta tal återfinns på index n = 0 om 
					ursprungliga mängden var: m > 0.
					
					BEVIS AV UTBYTESSORTING:
					
					INRE LOOPEN:
					 Innan inre loopen så gäller:
					  Xm = M{Xi, X(i+1), ..., Xn}
					
					 För inre loopen gäller följande invariant:
					         X1 < X2 < ... < X(i - 1) , X(i - 1) < Xk där i <= k <= n
					 Alltså ska det första elementet vara mindre än alla de andra
					 elementen i mängden M.
					
					 Det är denna invariant vi behöver bevisa för att kunna
					 bevisa den inre loopen i algoritmen. Därför antar vi att
					 efter ett godtyckligt antal iterationer så gäller följande
					 påstående i början på en ny iteration:
					         X1/ < X2/ < ... < X(i/ - 1)/ , X(i/ - 1)/ < Xk/ där i/ <= k <= n
					 I slutet av denna godtyckliga iteration kommer följande gälla:
					         /X1 < /X2 < ... < /X(/i - 1), /Xk, 
					         
					 Förändringen efter en iteration är att två stycken element
					 byter plats samt att index värdet i ökar med värdet 1, vilket
					 uttrycks så här:
					         /X(i/) = Xm/ , /i = i/ + 1
					 Vi kan skriva om /i = i/ + 1 till i/ = /i - 1 vilket ger oss:
					         /X(/i - 1) = Xm/
					 Eftersom Xm/ = M{X(i/)/, X(i/ + 1)/, ..., X(n)/} och även:
					         /X(/i) = Xm/ = M{/X(/i - 1), /X(/i), ..., /X(n)}
					 Detta innebär också att /X(/i - 1) < X(k) för /i <= k <= n (1)
					 
					 Elementen i första "delmängden" ändras inte under iterationen.
					 Därför kan man anta att loopinvarianten även kan skrivas så här i början av
					 passet:
					         /X1 < /X2 < ... < /X(/i - 2) , /X(/i - 2) < /Xk för /i - 1 <= k <= n.
					 Andra delen i påståendet gäller även då k = /i - 1, alltså:
					         /X(/i - 2) < /X(/i -1)  ( Alltså är det föregående talet mindre än nästkommande) (2)
					         
					 Därmed ger påstende (1) & (2) allt som krävs för att bevisa:
					         /X1 < /X2 < ... < /X(/i - 1) , /X(/i - 1) < /Xk för /i <= k <= n.
					         
					 Beviset är alltså meningsfullt först när i > 1 och osäkert då i = 1. Därefter
					 blir dock loopinvarianten säkert i slutet av loopen och därmed i början av nästa
					 och i sin tur i alla efterkommande iterationer.
					 
					YTTER LOOPEN:
					
					  Det enda som händer i ytter loopen är följande:
					   i/ < n
					  Sedan i slutet av loopen är följande även sant:
					   /i < n
					
					ALGORITMENS TIDSKOMPLEXITET:
					
					JÄMFÖRELSE MELLAN URVALSSORTERING & UTBYTESSORTERING:
					
					Om vi tar och beaktar ett värsta fall scenario ur de båda algoritmernas synvinkel så kommer vi få
					fram vilken typ av exekveringstid det rör sig om. I våra algoritmers synvinkel vore det sämsta
					läget att behöva byta värde för varje värde i mängden M - alltså n antal gånger byten. Om då
					mängden också är n antal så blir då också antal utbytena n * n - alltså n^2. Detta gäller
					för de båda algoritmerna.
					
					Urvalssortering & utbytessortering är en O(n^2) algoritm i exekveringstid och
					tillhör därmed mängden O(n^2).
					
					Antalet tilldelningar (i värsta fall): n 
					
					Antalet tilldelningar (i värsta fall) är: n
-------------------------------------------------------------------------------------------------------------- */

import java.util.Arrays;
import java.util.Random;

public class Extra2 
{
        /*
         * 1. Åskådliggör algoritmen.
         * 2. Bestäm algoritmens tidskomplexitet när det gäller antalet jämförelser.
         * 3. Jämför tidskomplexiteten mellan urvalssorteringen och utbytessorteringen. Både antalet jämförelser och antalet tilldelningar ska betraktas.
         * 4. Bevisa algoritmen.
         */

        public static void main(String[] args) 
        {
                sort(generateRandoms());
        }

        public static int[] generateRandoms()
        {
                // Generate a random set of values
                int[] randomSet = new int[new Random().nextInt(4) + 2];

                // Fill the set with values
                for(int i = 0; i < randomSet.length; i++)
                {
                        randomSet[i] = new Random().nextInt(10);
                }

                System.out.println("Innan sortering: " + Arrays.toString(randomSet));

                return randomSet;
        }

        public static void sort(int[] randomSet)
        {
                int i = 0;
                int m = 0;
                while(i < randomSet.length)
                {
                        int j = i + 1;
                        m = randomSet[i];

                        while(j < randomSet.length)
                        {
                                if(randomSet[j] < randomSet[i])
                                {
                                        m = randomSet[i];
                                        randomSet[i] = randomSet[j];
                                        randomSet[j] = m;
                                }
                                j++;
                        }
                        System.out.println("Efter " + (i + 1) + " iterationer " + Arrays.toString(randomSet));
                        i++;
                }
                System.out.println("Efter sortering: " + Arrays.toString(randomSet));
        }
}