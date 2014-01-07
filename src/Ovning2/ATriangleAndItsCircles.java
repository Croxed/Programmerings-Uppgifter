package Ovning2;
import java.util.Scanner;


public class ATriangleAndItsCircles {
	  public static void main(String[] args)
	  {
		  tria();
	  }
	  public static void tria()
	  {
		  double a, b, c = 0;
		  Scanner in = new Scanner(System.in);
		  System.out.println("Maximum angle must be less than 120.");
		  System.out.println(" - A triangle and its circles - \n");
		  System.out.print("First side: ");
		  a = in.nextDouble();
		  System.out.print("Second side: ");
		  b = in.nextDouble();
		  System.out.print("Last side: ");
		  c = in.nextDouble();
		  Triangle.berakna(a,b,c);
		  if(Triangle.berakna(a, b, c) == true)
		  {
		  System.out.println("\nVariable for calculaing the area of the triangle \n " + Triangle.sp(a, b, c));
		  System.out.println("The triangles area \n " + Triangle.area(a, b, c));
		  System.out.println("The inner circles' radius\n " + Triangle.incircle_radius(a, b, c));
		  System.out.println("The inner circles' area\n " + Triangle.incircle_area(a, b, c));
		  System.out.println("The outer circles' radius\n " + Triangle.circumcircle_radius(a, b, c));
		  System.out.println("The outer circles' area\n " + Triangle.circumcircle_area(a, b, c));
		  }
		  else
		  {
			  System.out.println("The values are not correct, try again. \n");
				try {
					  Thread.sleep(500L);
					}
					catch (Exception e) {
						e.printStackTrace();
					}  
			  tria();
		  }
		  in.close();
	  }

}