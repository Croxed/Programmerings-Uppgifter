package Ovning5;


public class Polylinje
{
	//Hämtar iteratorn
	public PolylinjeIterator getIterator()
	{
		return new Polylinje.PolylinjeIterator();
	}
	
	//Iterator
	public class PolylinjeIterator
	{
		private int aktuell = -1;
		public PolylinjeIterator ()
		{
			if (Polylinje.this.horn.length > 0) aktuell = 0; 
		}
		
		public boolean finnsHorn ()
		{
			return aktuell != -1; 
		}
		
		public Punkt horn ()
				throws java.util.NoSuchElementException
				{
					if (!this.finnsHorn ())
							throw new java.util.NoSuchElementException ( "slut av iterationen");
					
					Punkt horn = Polylinje.this.horn[aktuell];
					return horn; 
				}
		
		public void gaFram ()
		{
			if (aktuell >= 0 && aktuell < Polylinje.this.horn.length - 1) aktuell++;
			
			else aktuell = -1; 
			}
	}	
	
		private Punkt[] horn;
		private String farg = "svart";
		private int bredd = 1;
		
		public Polylinje ()
		{
			this.horn = new Punkt[0];
		}
		
		//Skapar en ny polylinje med färg och bredd
		public Polylinje (Punkt[] horn, String fargen)
		{
			this.horn = new Punkt[horn.length]; 
			for (int i = 0; i < horn.length; i++)
				this.horn[i] = new Punkt (horn[i]); 
			 this.horn = horn;
			farg = fargen;
		}
		
		//Skapar en klass som gör om till en sträng
		public String toString ()
		{
			String poly = "{[";
			for(int i = 0; i < this.horn.length; i++)
			{
				poly = poly + horn[i];
			}
			poly = poly + "]" + ", " + farg + ", " + bredd + "}";
			return poly;
		}
		
		//Hämtar hörnet för indexet man vill
		public Punkt getHorn (int i) 
		{
			return horn[i];
		}
		
		//Hämtar listan med punkter
		public Punkt[] getHorn()
		{
			return horn;
		}
		
		//Hämtar färg
		public String getFarg () 
		{
			return farg;
		}
		
		//Hämtar bredd
		public int getBredd () 
		{
			return bredd;
		}
		
		//Sätter en ny färg
		public void setFarg (String Nyfarg)
		{
			farg = Nyfarg;
		}
		
		//Sätter en ny bredd
		public void setBredd (int Nybredd)
		{
			bredd = Nybredd;
		}
		
		//Beräknar längden genom att använda pythagoras sats
		//Genom att ta nästa punkt minus nuvarande punkten
		public double langd () 
		{
			double langd = 0;
			for(int i = 0; i < this.horn.length-1; i++)
			{
				int currentX = this.horn[i].getX();
				int currentY = this.horn[i].getY();
				int nextX = this.horn[i+1].getX();
				int nextY = this.horn[i+1].getY();
				langd += Math.sqrt(Math.pow((nextX-currentX), 2) + Math.pow((nextY-currentY), 2));
			}
			return langd;
		}
		
		//Lägger till en punkt gneom att skapa en ny vektor med en plats till
		//Lägger sen till punkten sist i vektorn
		public void laggTill (Punkt horn)
		{
		Punkt[] h = new Punkt[this.horn.length + 1]; 
		int i=0;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i]; 
		
		h[i] = new Punkt (horn);
		this.horn = h; 
		}
		
		
		//Lägger till framför genom att hitta punkten man vill lägga framför
		//Sen byter man den punkten till den man vill ha framför
		//Går igenom vektorn från platsen man bytte och lägger till resten av punkterna efter den
		public void laggTillFramfor (Punkt hornet, String hornNamn)
		{
		Punkt[] h = new Punkt[this.horn.length + 1];
		int pos = 0;
		for (int i = 0; i < this.horn.length; i++){
			h[i] = this.horn[i];
				if(hornNamn == this.horn[i].getNamn()){
					pos = i;
					h[pos] = hornet;
				}
		}
		 for(int i = pos + 1; i < this.horn.length + 1; i++){
			 h[i] = this.horn[i - 1];
		 }
		 
		this.horn = h;
	}
	
		//Tar bort en punkt genom att hoppa över det indexet
		public void taBort (String hornNamn)
		{
		Punkt[] h = new Punkt[this.horn.length - 1];
		int i = 0;
		int j = 0;
		while (i < horn.length && j < h.length) {
		  if (hornNamn == horn[i].getNamn()) {
		    i++;
		  }
		  else {
		    h[j] = horn[i];
		    i++;
		    j++;
		  }
		}
		this.horn = h;
	}
}