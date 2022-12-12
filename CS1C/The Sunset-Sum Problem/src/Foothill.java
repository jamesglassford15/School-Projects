import java.util.*;
import cs_1c.*;
import java.text.*;


public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      int target = 4300; 
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Integer> dataSet2 = new ArrayList<Integer>();
      ArrayList<Sublist<iTunesEntry>> choices 
      = new ArrayList<Sublist<iTunesEntry>>();
      int k, j, highScore, arraySize, choicesCount;
      boolean foundPerfect = false;
      choicesCount = 0;
      highScore = 0;

   // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime;

      
   // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
            + " for input.");
         return;
      }
      
      dataSet2.add(2); dataSet2.add(12); dataSet2.add(22); 
      dataSet2.add(5); dataSet2.add(15); dataSet2.add(25);
      dataSet2.add(9); dataSet2.add(19); dataSet2.add(29);
      

      // load the dataSet ArrayList with the iTunes:
      arraySize = tunesInput.getNumTunes();
      for (k = 0; k < arraySize; k++)
         dataSet.add(tunesInput.getTune(k));

      choices.clear();
      System.out.println("First run: iTunesEntry");
      System.out.println("Target time: " + target);

      
      choices.add(new Sublist(dataSet));
      //looping over all elements of dataSet.
      startTime = System.nanoTime();
      
         for(k = 0; k < dataSet.size(); k++) 
         {
            if(foundPerfect)
            {
               break;
            }
            int sublistArraySize = choices.size(); 
            for(j = 0; j < sublistArraySize; j++) 
            {
               if(foundPerfect)
               {
                  break;
               }
               if((choices.get(j).getSum() + dataSet.get(k).getTime()) <= 
                     target) 
               {            
                  Sublist newSublist = 
                        choices.get(j).addItem(k, dataSet.get(k).getTime());
                  if(newSublist.getSum() > highScore)
                  {
                     highScore = newSublist.getSum();
                  }
                  choices.add(newSublist);
                  choicesCount = (choices.size() - 1);

                  if((choices.get(j).getSum() + dataSet.get(k).getTime()) == 
                        target)
                  {
                     foundPerfect = true;
                     choicesCount = (choices.size() - 1);
                  }
               }
            }
         }
      stopTime = System.nanoTime();
      
      choices.get(choicesCount).showSublist();
      System.out.println("Algorithm Elapsed Time: " + 
      tidy.format((stopTime - startTime) / 1e9) + "seconds.");
      
      
      System.out.println("\n\nSecond run: integers");
      //resetting for a run of ints
      choices.clear();
      choices.add(new Sublist(dataSet2));
      choicesCount = 0;
      highScore = 0;
      foundPerfect = false;
      
      for(k = 0; k < dataSet2.size(); k++) 
      {
         if (foundPerfect)
            {
               break;
            }
         int sublistArraySize = choices.size(); 
         for(j = 0; j < sublistArraySize; j++) 
         {
            if (foundPerfect)
               {
                  break;
               }
            if((choices.get(j).getSum() + dataSet2.get(k)) <= target) 
            { 
               Sublist newSublist = choices.get(j).addItem(k, dataSet2.get(k));
               if(newSublist.getSum() > highScore)
               {
                  highScore = newSublist.getSum();
               }
               choices.add(newSublist);
               choicesCount = (choices.size() - 1);

               if((choices.get(j).getSum() + dataSet2.get(k)) == target)
               {
                  foundPerfect = true;
                  choicesCount = (choices.size() - 1);
               }
            }
         }
      }
      
      System.out.println("Target time: " + target);
      choices.get(choicesCount).showSublist();
   }
}

class Sublist <E> implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }
   
   int getSum() { return sum; }
   
   // I have done the clone() for you, since you will need clone() inside 
   // addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();
      
      return newObject;
   }
   
   Sublist addItem(int indexOfItemToAdd, int time) throws CloneNotSupportedException
   {
      Sublist newSublist;
      newSublist = (Sublist) this.clone();
      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum += time;
      return newSublist;
   }
   

   void showSublist()
   {
      ListIterator<Integer> listIterator = indices.listIterator();
      System.out.println("Sublist -----------------------------");
      System.out.println(" sum: " + this.sum);

      for (listIterator = indices.listIterator(); listIterator.hasNext();)
      {
         int value = listIterator.next();
         System.out.println(" array[" + value + "] = " 
               + originalObjects.get(value) + "," ); 
      }
   }
}

/*------------------------ run ------------------------
First run: iTunesEntry
Target time: 4300
Sublist -----------------------------
 sum: 4300
 array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
 array[1] = Carrie Underwood | Quitter |  3:40,
 array[2] = Rihanna | Russian Roulette |  3:48,
 array[3] = Foo Fighters | All My Life |  4:23,
 array[4] = Foo Fighters | Monkey Wrench |  3:50,
 array[5] = Eric Clapton | Pretending |  4:43,
 array[6] = Eric Clapton | Bad Love |  5:08,
 array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
 array[9] = Reverend Gary Davis | Samson and Delilah |  3:36,
 array[10] = Reverend Gary Davis | Twelve Sticks |  3:14,
 array[11] = Roy Buchanan | Hot Cha |  3:28,
 array[12] = Roy Buchanan | Green Onions |  7:23,
 array[13] = Janiva Magness | I'm Just a Prisoner |  3:50,
 array[14] = Janiva Magness | You Were Never Mine |  4:36,
 array[15] = John Lee Hooker | Hobo Blues |  3:07,
 array[17] = Snoop Dogg | That's The Homie |  5:43,
 array[18] = Snoop Dogg | Gangsta Luv |  4:17,
Algorithm Elapsed Time: 0.187seconds.


Second run: integers
Target time: 4300
Sublist -----------------------------
 sum: 138
 array[0] = 2,
 array[1] = 12,
 array[2] = 22,
 array[3] = 5,
 array[4] = 15,
 array[5] = 25,
 array[6] = 9,
 array[7] = 19,
 array[8] = 29,*/
 
