package Ovning5;

import java.util.Random;

public class ValjPolylinje {
	
	public int Vektorantal = 0;
	
	//Generar random antal poly
	public int generateRandomAntalPoly(){
		Vektorantal = (new Random().nextInt(3)+2);
		return Vektorantal;
	}
	//Generar en poly array
	public Polylinje[] generatePolyArray(){
		Polylinje[] vektor = new Polylinje[Vektorantal];
		for(int i = 0; i < Vektorantal; i++){
		vektor[i] = new Polylinje(generateRandomAntalPunkter(), generateRandomFarg());
		}
		return vektor;
	}

	//Genererar random antal polylinjer
	public Polylinje generateRandomPoly(){
		Polylinje linje = new Polylinje();
		linje = new Polylinje(generateRandomAntalPunkter(), generateRandomFarg());
		return linje;
	}
	
	//Generar random x
	public int generateRandomX(){
		int x = (new Random().nextInt(9)+1);
		return x;
	}
	
	//Generar random y
	public int generateRandomY(){
		int y = (new Random().nextInt(9)+1);
		return y;
	}

	//Generar random namn
	//Genom att slumpa ett tal mellan 0 och 25 får man stora bokstäver
	//Slumpar även längden av texten
	public String generateRandomName()
	{
		StringBuilder sBuilder = new StringBuilder();
		int antalBokstaver = (new Random().nextInt(3)+1);
		for (int i = 0; i < antalBokstaver+1; i++){
			int charBok = (new Random().nextInt(26))+65;
			sBuilder.append((char)charBok);
		}
		String name = sBuilder.toString();
		return name;
	}
	
	//Slumpar färgen mellan 3 färger
	public String generateRandomFarg()
	{
		String[] farger = {"blå", "röd", "gul"};
		int i = (new Random().nextInt(3));
		String farg = farger[i];
		return farg;
	}
	
	
	//Slumpar antal punkter
	public Punkt[] generateRandomAntalPunkter()
	{
		int antal = (new Random().nextInt(3)+2);
		Punkt []vektor = new Punkt[antal];
		for(int i = 0; i < antal; i++){
			vektor[i] = new Punkt(generateRandomName(), generateRandomX(), generateRandomY());
		}
		return vektor;
	}
	
	
	//Kollar efter den kortaste gula linjen
	//Den gör enbart en jämförelse om färgen == "gul"
	public Polylinje kortastGulSamling(Polylinje[] polylinjer){
		Polylinje valdPolylinje = null;
		int j = 0;
		double kortast = 0;
		for(int i = 0; i < polylinjer.length; i++)
		{
			if(polylinjer[i].getFarg().equals("gul"))
			{
				if(j == 0)
				{
					kortast = polylinjer[i].langd();
					valdPolylinje = polylinjer[i];
					j++;
				}
				if(polylinjer[i].langd() < kortast)
				{
					kortast = polylinjer[i].langd();
					valdPolylinje = polylinjer[i];
				}
			}
		}
		return valdPolylinje;
	}
}
