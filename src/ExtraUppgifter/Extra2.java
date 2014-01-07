package ExtraUppgifter;

/*
 * --------- Beskrivning av BubbleSort ---------
  
  
 * --------- Beskrivning av BubbleSort ---------
 */

import java.util.Arrays;
import java.util.Random;

public class Extra2 {
	
    public static void main(String args[]) 
    {
    	//Scanner in = new Scanner(System.in);
    	//System.out.println("Skriv in 5st tal i valfri ordning");
    	int antal = 10;
        int[] randomArray = new int [antal];
    	for(int tal = 0; tal <= antal - 1; tal++)
    	{
    		randomArray[tal] = (new Random().nextInt(10)+1);
    	}
        sortArray(randomArray);
      //  in.close(); 
    }   
    
    public static void sortArray(int[] randomArray)
    {
    	int varv = 0;
    	int j = 0;
        System.out.println("Din lista innan vi började." + Arrays.toString(randomArray));
        for(int i=0; i<randomArray.length -1; i++){
          
            //Inre loop för att göra jämförelse och byta mellan närliggande tal
            //Efter varje iteration så sorteras ett index från sista
        	for(int l= 1; l<randomArray.length -i; l++)
            {              
                //Om nuvarande numret är större, byt dessa
                if(randomArray[l-1] > randomArray[l])
                {
                    int temp = randomArray[l];
                    randomArray[l] = randomArray[l-1];
                    randomArray[l-1] = temp;
                   // System.out.println(Arrays.toString(randomArray));
                    varv++;
                }
                j++;
            }
        }
        System.out.println("Din lista efter vi sorterade den." + Arrays.toString(randomArray) + " \nUtbytningar: " + varv);
        System.out.println("Varv: " + j);
    }

}