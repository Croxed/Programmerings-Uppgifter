package Forelasningar;
//Föreläsning 2 - Variabler


public class datalagring 
{
	
	byte minByteVariabel; //8bit
	short minShortVariabel; //16bit
	int minIntVariabel; //32bit
	long minLongVariabel = 999999999999999999L; //64bit (lägg till L)
	boolean minBooleanVariabel; //True, false 
	char minCharVariabel; //16bit 0-64535
	
	//Automatiska typomvandlingar
	float minFloatVariabel = 0.045f; //32bit flyt-tal (lägg till f)
	double minDoubleVariabel = 1; //64bit flyt-tal
	
	//Explicit typomvandling
	int n = 45;
	short h = (short) n;
	int m = (int) minDoubleVariabel;
	
	//Objektdatatyper
	Object minObjectRef = null;
		
	public static void main(String[] args)
	{
		String message = new String("Hello world!");
		String meddelande = "Hej hopp";
		System.out.println(message);
		String t = message + " I'm alive!";
		System.out.println(meddelande);
		System.out.println(t);
	}
}
