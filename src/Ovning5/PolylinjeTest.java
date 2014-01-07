package Ovning5;

public class PolylinjeTest {

	public static void main(String[] args){
		Polylinje p = new Polylinje();
		ValjPolylinje d = new ValjPolylinje();
		Polylinje.PolylinjeIterator pd;
		
		p.laggTill(new Punkt("A", 2, 4));
		p.laggTill(new Punkt("B", 5, 4));
		System.out.println(p.toString());
		
		p.laggTillFramfor((new Punkt("C", 4, 6)),"B");
		System.out.println(p.toString());
		
		p.setFarg("grå");
		p.setBredd(10);
		
		p.taBort("B");
		System.out.println(p.toString());
		System.out.println(p.langd() + "\n");
		
		d.generateRandomAntalPoly();
		Polylinje[] dPoly = d.generatePolyArray();
		
		for(int i = 0; i < dPoly.length; i++)
		{
			Polylinje skicka = dPoly[i];
			System.out.println(skicka.toString());
		}
		
		Polylinje kortast = d.kortastGulSamling(dPoly);
		double kortastLangd = 0;
		if(kortast != null)
		{
			kortastLangd = kortast.langd();
		}
		System.out.println("\nKortaste gula linjen är: \n" + kortast +
				"\nOch har längden: " + kortastLangd + "\n");
		
		for(int i = 0; i < dPoly.length; i++)
		{
			System.out.println((i+1) + " " + dPoly[i].langd());
		}
		System.out.println();
		
		for(int i = 0; i < dPoly.length; i++)
		{
			pd = dPoly[i].getIterator();
			boolean finns = pd.finnsHorn();
			Punkt pkt = pd.horn();
			System.out.println("Finns det hörn: " + finns);
			
			while(pd.finnsHorn())
			{
				pkt = pd.horn();
				System.out.println(pkt.toString());
				pd.gaFram();
				
				finns = pd.finnsHorn();
				System.out.println("Finns det hörn: " + finns);
			}
			System.out.println();
		}	
	}
}
