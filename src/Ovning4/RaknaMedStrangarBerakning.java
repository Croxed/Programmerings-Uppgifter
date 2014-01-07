package Ovning4;

public class RaknaMedStrangarBerakning {
	public RaknaMedStrangarBerakning(String first, String operator, String second)
	{
		if("-".equals(operator)) //Kollar om det är subtraktion den skall utföra
			System.out.println(subraction(first, second));
		if("+".equals(operator)) //Kollar om det är addition den skall utföra
			System.out.println(addition(first, second));
		else if(!"+".equals(operator) && !"-".equals(operator)) 
			System.out.println("Ogiltig operator");
	}
	public String addition(String first, String second)
	{
		StringBuilder sBuilder =  new StringBuilder(); //En stringbuilder
		int length = first.length();
		second = matchLength(length, second); //Skickar iväg andra strängen för att matcha längd
		int carry = 0;
		int i = 0;
		
		if(second.length() > length) //Kollar om längden på second är längre än first.
		{
			length = second.length();
			first = matchLength(length, first); //Matchar längderna
		}
		
		first = new StringBuilder(first).reverse().toString();  //Vänder på strängen för en enklare while-loop
		second = new StringBuilder(second).reverse().toString(); //-||-
		
		while(i < length || carry == 1) //Körs så länge i är mindre än längden och carry == 1
		{
			int temp; //Deklarerar en int för värdet som skall vara värdet av additionen
			if(i < length)
			{
				//Adderar alla värden
				temp = (first.charAt(i) - 48) //Genom att ta karaktärens värde i ascii minus 48 får man "rätta" värdet
						+ (second.charAt(i) - 48) 
						+ carry; //Lägger till carry 
			}else
			{
				temp = carry; 
			}

			carry = 0;

			if(temp > 9) //Om temp är större än tio behöver vi en carry
			{
				carry = 1;
				sBuilder.append(temp % 10);  //Modulus på temp med 10 och ger lägger till resten
			}else
			{
				sBuilder.append(temp);
			}
			i++;
		}
		return new StringBuilder(sBuilder.toString()).reverse().toString(); //Vänder och returnerar strängen som byggdes
	}
	
	public String subraction(String first, String second)
	{
		StringBuilder sBuilder = new StringBuilder(); //Stringbuilder
		int length = first.length(); 
		second = matchLength(length, second); //Matchar längder
		int carry = 0;
		int i = 0;

		if(second.length() > length)  //Om second är längre än first
		{
			length = second.length(); //Lägger length till second
			first = matchLength(length, first); //Skicka iväg för matchas
		}
		
		first = new StringBuilder(first).reverse().toString();  //Vänder på strängen för en enklare while-loop
		second = new StringBuilder(second).reverse().toString(); //-||-

		while(i < length || carry == 1) //Körs så länge i är mindre än längden och carry == 1
		{
			int temp; //Deklarerar en int för värdet som skall vara summan av subtraktionen
			if(i < length)
			{
				//Subtraherar alla värden
				temp = (first.charAt(i) - 48) //Genom att ta karaktärens värde i ascii minus 48 får man "rätta" värdet
						- (second.charAt(i) - 48)
						- carry; //Subtraherar carry
			}else
			{
				temp = carry;
			}

			carry = 0;

			if(temp < 0) //Om temp är mindre än 0 behöver vi låna 10
			{
				carry = 1;
				sBuilder.append(10 + temp); //Lägger till 10 på temp, får därefter rätt värde
			}else
			{
				sBuilder.append(temp);
			}
			i++;
		}
		if(sBuilder.length() != 1) //Om längden är större än 1
		{
		if(sBuilder.charAt(sBuilder.length() - 1) == 48) //Kolla om sista karaktären är en 0a
		{
			sBuilder.deleteCharAt(sBuilder.length() - 1); //Tar bort denna
		}
		}
		return new StringBuilder(sBuilder.toString()).reverse().toString(); //Vänder och returnerar strängen som byggdes
	}
	
	public String matchLength(int length, String tal)
	{
		StringBuilder sBuilder = new StringBuilder(); //Stringbuilder
		while(sBuilder.length() < (length - tal.length())) //Medan stringbuilders längd är mindre än längd - en av talens längder
		{
			sBuilder.append(0); //Lägg till 0or
		}
		sBuilder.append(tal); //Lägg till talet
		return sBuilder.toString(); //Returnera strängen
	}
}
