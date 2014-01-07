package Ovning4;

/*
 * ---------------------- Addition Beskrivning ----------------------
 * Psuedokod
 * 
 * 
 * langd = text1.length
 * matchLength(text2.length)
 * 
 * for each i or carry == 1
 * 	int rakna
 * 		if i < langd
 * 			rakna = (text1.charAt(i) - 48) + (text2.charAt(i) - 48) + carry
 * 
 * 		else
 * 			rakna = carry
 * 
 * 	carry = 0
 * 		
 * 		if temp > 9
 * 			carry = 1
 * 			buildString (temp % 10)
 * 
 * 		else
 * 			buildString (temp)
 * 
 * ---------------------- Addition Beskrivning ----------------------
 * 
 * ---------------------- Subtraktion Beskrivning ----------------------
 * Psuedokod
 * 
 * 
 * langd = text1.length
 * matchLength(text2.length)
 * 
 * for each i or carry == 1
 * 	int rakna
 * 		if i < langd
 * 			rakna = (text1.charAt(i) - 48) - (text2.charAt(i) - 48) - carry
 * 
 * 		else
 * 			rakna = carry
 * 
 * 	carry = 0
 * 		
 * 		if temp < 0
 * 			carry = 1
 * 			buildString (10 + temp)
 * 
 * 		else
 * 			buildString (temp)
 * 
 * ---------------------- Subtraktion Beskrivning ----------------------
 * 
 * ---------------------- Beskrivning av problemet ---------------------
 * 
 * Två naturliga heltal är givna som teckensträngar av godtycklig längd.
 * 
 * Utför olika aritmetiska operationer i samband med dessa heltal.
 * Tecken i strängarna ska användas, och ett resultat i form av en ny teckensträng ska skapas.
 * 
 * ---------------------- Beskrivning av problemet ---------------------
 */

import java.util.Scanner;

public class RaknaMedStrangar {
	public static void main(String[] args)
	{
		rakna();
	}
	
	public static void rakna()
	{
		Scanner in = new Scanner(System.in); //Scanner
		System.out.println("Skriv in första värdet");
		String first = in.next(); //Första värdet
		System.out.println("Skriv in + eller - beroende på vilken operator du vill använda");
		String operator = in.next(); //Operatorn
		System.out.println("Skriv in andra värdet");
		String second = in.next(); //Andra värdet
		//Skickar iväg talen för beräkning
		RaknaMedStrangarBerakning svar = new RaknaMedStrangarBerakning(first, operator, second);
	}
}
