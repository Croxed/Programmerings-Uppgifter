package Ovning5;

public class Punkt {

	private String name = "";
	private int x = 0;
	private int y = 0;
	
	public Punkt(String string, int i, int j) 
	{
		name = string;
		x = i;
		y = j;
	}
	
	public Punkt(Punkt p) 
	{
		x = p.x;
		y = p.y;
		name = p.name;
	}

	public String toString()
	{
		String s = "";
		
		s = "(" + name + ", " + x + ", " + y + ")";
		
		return s;
	}
	
	public int getX() 
	{
		return x;
	}

	public int getY() {
		return y;
	}

	public String getNamn() {
		return name;
	}

	 public double avstand(Punkt p2) {
		int newx = p2.getX() - x;
		int newy = p2.getY() - y;
		System.out.println("X: " + newx + " Y: " + newy);
		double avstand = Math.sqrt((Math.pow(newx, 2)) + (Math.pow(newy, 2)));
		return avstand;
	}

	public void setX(int i) {
		x = i;
		
	}

	public void setY(int i) {
		y = i;
	}

}
