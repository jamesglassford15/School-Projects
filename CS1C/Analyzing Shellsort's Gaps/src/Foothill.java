import cs_1c.*;
import java.util.*;
import java.text.*;


public class Foothill
{
   public static void main(String[] args)
   {
      final int ARRAY_SIZE = 200000;
      int randomInt;
      
      
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      Integer[] arrayOfInts1 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts2 = new Integer[ARRAY_SIZE];
      Integer[] arrayOfInts3 = new Integer[ARRAY_SIZE];
      
      int[] gapArray = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
            2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288,
            1048576};
      
      int[] sedgewickArray = new int[30]; // to be computed using formulas
      for(int k = 0, i = 2; k < sedgewickArray.length/2; k++, i++)
      {
         int formulaOne = (9 * (int)(Math.pow(4, k)) 
                            - 9 * (int) (Math.pow(2, k)) + 1);
         int formulaTwo = ((int) (Math.pow(4, i)) 
                            - 3 * (int) (Math.pow(2, i)) + 1);

         
         sedgewickArray[k*2] = Math.abs(formulaOne);
         sedgewickArray[k*2+1] = Math.abs(formulaTwo);   
      }
      //for(int j = 0; j < sedgewickArray.length; j++)
         //System.out.println(sedgewickArray[j]);
      
      int[] knuthArray = new int[30]; 
      for (int i = 0, k = 1; k < knuthArray.length; k++, i++) 
      {
         knuthArray[i] = (int) ((Math.pow(3, k))/2);
      }
      //for(int k = 0; k <knuthArray.length; k++)
         //System.out.println(knuthArray[k]); 

      for (int j = 0; j < ARRAY_SIZE; j++)
      {
         randomInt = (int) (Math.random() * ARRAY_SIZE);
         arrayOfInts1[j] = randomInt;
         arrayOfInts2[j] = randomInt;
         arrayOfInts3[j] = randomInt;
      }

      System.out.println("Array Size: " + ARRAY_SIZE);

      startTime = System.nanoTime();
      FHsort.shellSort1(arrayOfInts1);
      stopTime = System.nanoTime();
      System.out.println("Shell's implied Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");


      startTime = System.nanoTime();
      shellSortX(arrayOfInts1, gapArray);
      stopTime = System.nanoTime();
      System.out.println("Shell's explicit Elapsed Time: " + tidy.format( 
            (stopTime - startTime) / 1e9) + " seconds.");


      startTime = System.nanoTime();
      shellSortX(arrayOfInts2, sedgewickArray);
      stopTime = System.nanoTime();
      System.out.println("Sedgewick's Elapsed Time: " + tidy.format( 
            (stopTime - startTime) / 1e9) + " seconds.");


      startTime = System.nanoTime();
      shellSortX(arrayOfInts3, knuthArray);
      stopTime = System.nanoTime();
      System.out.println("Knuth's Elapsed Time: " + tidy.format( 
            (stopTime - startTime) / 1e9) + " seconds.");
      
         
   }
   
   public static <E extends Comparable< ? super E> > void shellSortX(E[] a,
         int[] gapSequence)
   {
      int arraySize, arraySizeOfGap;
      int gap;
      int i, k, pos;
      E tmp;

      arraySize = a.length;
      arraySizeOfGap = gapSequence.length;
      for (i = arraySizeOfGap - 1; i >= 0;  i--)
      {
         gap = gapSequence[i];
         for(pos = gap; pos < arraySize; pos++ )
         {
            tmp = a[pos];
            for(k = pos; k >= gap && tmp.compareTo(a[k-gap]) < 0; k -= gap )
            {
               a[k] = a[k-gap];
            }
            a[k] = tmp;
         }        
      }
   }
}

/*---------------------RUNS------------------------------
Array Size: 10000
Shell's implied Elapsed Time: 0.0071 seconds.
Shell's explicit Elapsed Time: 0.0033 seconds.
Sedgewick's Elapsed Time: 0.003 seconds.
Knuth's Elapsed Time: 0.0028 seconds.

Array Size: 25000
Shell's implied Elapsed Time: 0.0193 seconds.
Shell's explicit Elapsed Time: 0.0108 seconds.
Sedgewick's Elapsed Time: 0.0069 seconds.
Knuth's Elapsed Time: 0.0221 seconds.

Array Size: 50000
Shell's implied Elapsed Time: 0.0344 seconds.
Shell's explicit Elapsed Time: 0.0269 seconds.
Sedgewick's Elapsed Time: 0.0141 seconds.
Knuth's Elapsed Time: 0.0295 seconds.

Array Size: 100000
Shell's implied Elapsed Time: 0.0649 seconds.
Shell's explicit Elapsed Time: 0.0377 seconds.
Sedgewick's Elapsed Time: 0.0079 seconds.
Knuth's Elapsed Time: 0.0585 seconds.

Array Size: 150000
Shell's implied Elapsed Time: 0.1235 seconds.
Shell's explicit Elapsed Time: 0.0724 seconds.
Sedgewick's Elapsed Time: 0.0358 seconds.
Knuth's Elapsed Time: 0.1144 seconds.

Array Size: 200000
Shell's implied Elapsed Time: 0.1892 seconds.
Shell's explicit Elapsed Time: 0.0882 seconds.
Sedgewick's Elapsed Time: 0.039 seconds.
Knuth's Elapsed Time: 0.1564 seconds.


-------------------------------------------------------------------------------
                   | 10,000  | 25,000  | 50,0000 | 100,000 | 150,000 | 200,000|
-------------------------------------------------------------------------------
Shell's Implicit   | 0.0071  | 0.0193  | 0.0344  | 0.0649  | 0.1235  | 0.1892 |
Shell's explicit   | 0.0033  | 0.0108  | 0.0269  | 0.0377  | 0.0724  | 0.0882 | 
Sedgewick          | 0.003   | 0.0069  | 0.0141  | 0.0079  | 0.0358  | 0.039  |
Knuth              | 0.0028  | 0.0221  | 0.0295  | 0.0585  | 0.1144  | 0.1564 |
-------------------------------------------------------------------------------

the timing results between shellSort1() and shellSortX() differ because of the
different approaches they take on the gap sequence. shellSortX() is faster as
a result of taking advantage of larger gaps to move the numbers where they
need to go.
*/

