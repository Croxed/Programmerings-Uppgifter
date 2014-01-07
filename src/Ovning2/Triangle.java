package Ovning2;

public class Triangle {
	
	public static boolean berakna(double a, double b, double c)
		{
			boolean calcklar = true;
			double sinab = 0;
			double sinbc = 0;
			double sinca = 0;
			if(a+b>c  && b+c>a && a+c>b)
			{
				if(a<b)
				{
					sinab = Math.toDegrees(Math.asin(a/b));
				}
				else if(a>b)
				{
					sinab = Math.toDegrees(Math.asin(b/a));
				}
				
				if(b<c)
				{
					sinbc = Math.toDegrees(Math.asin(b/c));
				}
				else if(b>c)
				{
					sinbc = Math.toDegrees(Math.asin(c/b));
				}
				
				if(a<c)
				{
					sinca = Math.toDegrees(Math.asin(a/c));
				}
				else if(a>c)
				{
					sinca = Math.toDegrees(Math.asin(c/a));
				}
	
				if(sinbc != 0 && sinca !=0 && sinab !=0)
					{
					if(sinbc >= 120 && sinca >= 120 && sinab >=120)
						{
						calcklar = false;
						return calcklar;
						}
					else
						{
						calcklar = true;
						}
					}
				else
					{
					calcklar = false;
					return calcklar;
					}
			}
			else
			{
				calcklar = false;
				return calcklar;
			}

			return calcklar;
		}
			  
	public static double sp(double a, double b, double c)
	  {
	    double rv = 0.5D * (a + b + c);
	    return rv;
	  }

	public static double area(double a, double b, double c)
	  {
	    double rv = sp(a, b, c) * (sp(a, b, c) - a) * (sp(a, b, c) - b) * (sp(a, b, c) - c);
	    rv = Math.sqrt(rv);
	    return rv;
	  }

	public static double incircle_radius(double a, double b, double c)
	  {
	    double sp = sp(a, b, c);

	    double radius = (sp - a) * (sp - b) * (sp - c) / sp;
	    radius = Math.sqrt(radius);
	    return radius;
	  }

	public static double incircle_area(double a, double b, double c)
	  {
	    double rv = incircle_radius(a, b, c) * incircle_radius(a, b, c) * Math.PI;
	    return rv;
	  }

	public static double circumcircle_radius(double a, double b, double c)
	  {
	    double diameter = a* b * c / (2.0D * area(a, b, c));
	    double radius = diameter / 2.0D;
	    return radius;
	  }

	public static double circumcircle_area(double a, double b, double c)
	  {
	    double rv = circumcircle_radius(a, b, c) * circumcircle_radius(a, b, c) * Math.PI;
	    return rv;
	  }
}


