package Ovning3;

/*
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * Det finns i ett trafiksystem fyra zoner: Z1, Z2, Z3 och Z4. 
 * I zonen Z1 finns endast stationen X, och zonen Z4 omfattar bara stationen Y. 
 * I zonen Z2 finns stationerna Ui (1 ≤ i ≤ m), och zonen Z3 omfattar stationerna Vj (1 ≤ j ≤ n).
 * Det finns direkta vägar mellan stationen X och alla stationer i zonen Z2. 
 * Längden av vägen mellan stationen X och stationen Ui är ai (1 ≤ i ≤ m). 
 * Zonerna Z2 och Z3 är väl kopplade med varandra: 
 * det finns en direkt väg mellan vilken station som helst i den ena zonen och en godtycklig station i den andra zonen. 
 * Längden av vägen mellan stationen Ui och Vj är bij (1 ≤ i ≤ m, 1 ≤ j ≤ n). 
 * Det finns även en direkt väg mellan vilken station som helst i zonen Z3 och stationen Y. 
 * Längden av vägen mellan stationen Vj och stationen Y är cj (1 ≤ j ≤ n).
 * En väg mellan stationerna X och Y går genom en station i zonen Z2 och en station i zonen Z3. 
 * En mellanstation i var och en av zonerna Z2 och Z3 ska väljas, så att vägen mellan stationen X och stationen Y blir så kort som möjligt.
 * 
 * 
 * 
 * Uppgifter i samband med problemet
 * 
 * 1. Bestäm en instans av det här problemet i fallet att m = 3 och n = 4 – välj väglängderna. 
 * Illustrera den instansen. Rita tabeller som innehåller relevanta uppgifter. 
 * Lös denna instans med papper och penna: undersök alla möjliga vägar och bestäm den kortaste.
 * 
 * 2. Hitta en minneseffektiv algoritm som löser det här problemet i ett allmänt fall – använd uppdateringsstrategi. 
 * Beskriv den algoritmen på två olika sätt: med ord och med pseudokod.
 * 
 * 3. Skapa ett Javaprogram som kan lösa olika instanser av det här problemet. 
 * Använd programmet i samband med två instanser, och beskriv de resultat som erhålls.

---------------------------------------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------------------------------------
Den kortaste vägen.
Beskrvning av algoritmen:

Först så skapar programmet alla olika vägar mellan zonerna 2 och 3. 
Det gör den genom att välja första stationen i zon 2 och slumpar alla vägar till alla stationer i zon 3.
Den gör detta för alla stationer i zon 2. 
Detta leder till att antal vägar mellan zon 2 och 3 blir antalet stationer i zon 2 gånger antal stationer i zon 3.

Den slumpar även fram alla vägar från stationerna i zon 3 till Y, och för alltså lika många värden som det finns stationer där. 
Detta är eftersom oavsett hur man tar sig till den stationen kommer den sista sträckan vara densamma. 
Samma gäller för vägarna mellan X och zon 2.

Efter att ha slumpat fram allt detta, summerar datorn detta och summerar det och lägger det i en matris. Matrisen beror på följande: 
int matris [Station i Zon 2] [Station i Zon 3]. 
Därefter kan resultaten vara följande:
int matris [1][1]
int matris [1][2]
int matris [1][3]
int matris [1][4]

Vilket symbolerar alla vägar från station 1 till stationerna i zon 3 (ifall det är 4 stationer i zon 3).
Sen upprepar den mönstret för resten av stationerna i zon 2.

Summerigen sker på följande sätt. 
Först ett slumpat tal mellan station x i zon 2 och station y i zon 3, för sedan addera sträckan från X till station x 
i zon 2 och sträckan från y till station y i zon 3. Detta gör så att man för den avståndet X - Y för varje möjliga väg.
---------------------------------------------------------------------------------------------------------------------------------------------------

FÖRVILKOR
 Det skall finnas 4 zoner, X, M, N och Y.
 Zonerna X och Y har enbart en station. 
 
 Zonerna M och N har en godtycklig mängd stationer, som i detta fallet kan bli slumpade eller inmatade innan programmet körs.
 
 Algoritmen körs utan att användaren matar in bestämda längder mellan zonerna och stationerna. Den slumpar fram längderna.


EFTERVILKOR
 Efter att algoritmen har körts så skriver den ut den kortaste vägen man kan färdas från zon X till zon Y.
 Programmet skriver även ut alla vägar som man kan gå, och skriver ut avståndet det resulterar i.
 
 Algoritmen skriver även ut vilka station man skall gå igenom i zon M och zon N för att färdas den koraste sträckan.
 Utöver det får användaren även en lista på alla avstånd mellan X och M samt N och Y.

---------------------------------------------------------------------------------------------------------------------------------------------------
Den kortaste vägen.
Pseudokod.

program kortasteVagen
	
	int min = vagar[0][0]
	String vagZon = ""
		
	for each MNi
		for each MNj
			int kortastVag = vagar[i][j]
			if kortastVag < min
				min = kortastVag
				vagZon = i + " " + j
		j++
	i++
		
	print(min + vagZon)

---------------------------------------------------------------------------------------------------------------------------------------------------
*/

import java.util.Arrays;
import java.util.Random;

public class DenKortasteVagen {
	
	public static void main(String[] args) {
		
		// Skapar värden
		int n = (new Random().nextInt(10)+1);
		int m = (new Random().nextInt(10)+1);
		int [] NtoY = new int[n];
		int [] XtoM = new int[m];
		int [][] vagar = new int[m][n];		
			for(int i = 0; i < m; i++)
			{
				XtoM [i] = (new Random().nextInt(10)+1);
					for(int i2 = 0; i2 < n; i2++)
					{	
						vagar[i][i2] =  (new Random().nextInt(30)+1);
					}
			}	
			for(int i = 0; i < n; i++)
				NtoY[i] = (new Random().nextInt(10)+1);	
			
			// Utför beräkning 
			berakning(m, n, NtoY, XtoM, vagar);
	}
	
	public static void berakning(int m, int n, int[] NtoY, int[] XtoM, int[][] vagar)
	{
		int min = vagar[0][0] + XtoM[0] + NtoY[0];
		String Zon = "station 1 och station 1. Vilket är på avståndet: " + min + ".";
		for(int i = 0; i < m; i++)
		{
			for(int i2 = 0; i2 < n; i2++)
			{
				int kortvag = vagar[i][i2] + XtoM[i] + NtoY[i2];
				System.out.println("Station " + (i+1) + " och station " + (i2+1) + ". Avståndet: " + kortvag);
				if(kortvag < min)
				{
					min = kortvag;
					Zon = "station " + (i+1) + " och station " + (i2+1) + ". Vilket är på avståndet " + min + ".";			
				}
			}
			System.out.println();
		}
		System.out.println(
				  "Avstånd från X till M: " + Arrays.toString(XtoM)
				+ "\nAvstånd från Y till N: " + Arrays.toString(NtoY) 
				+ "\nAntal stationer i första zonen: " + m 
				+ "\nAntal stationer i andra zonen: " + n
				+ "\nKortaste vägen från X till Y är genom " + Zon
				);
	}
}
