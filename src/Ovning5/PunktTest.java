package Ovning5;

import java.io.PrintWriter; // PrintWriter
class PunktTest
{
public static void main (String[] args)
{
PrintWriter out = new PrintWriter (System.out, true);

Punkt p1 = new Punkt ("A", 3, 4); 
Punkt p2 = new Punkt ("B", 5, 6); 
out.println (p1 + " " + p2);

out.println (p1);

double d = p1.avstand (p2); 
out.println (d);
boolean b = p1.equals (p2);
out.println (b);

p2.setX (1); 
p2.setY (2); 
out.println(p2);

Punkt p = new Punkt (p1);
out.println(p);
	}
}