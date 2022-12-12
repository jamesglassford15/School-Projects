// notice that we don't have "import becker.robots.*;" anymore
import java.io.*;
import java.util.*;
public class TipCalc extends Object
{
   
   public static boolean verifyIdentity(String one,String two)
   {
      if(one.equals(two)) {
         return true;
      }
      else {
         return false;
      }
   }
   
   public static void main(String[] args)
   { 
      try {
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String item1 = null;
      String item2 = null;
      
      System.out.println("Enter a String:");
      item1 = reader.readLine();
      System.out.println("Enter another String:");
      item2 = reader.readLine();
      if(verifyIdentity(item1,item2) == true) {
         System.out.println("They're identical!");
      }
      else { System.out.println("These are not identitcal.");}
      

      } catch(Exception e) {
         e.printStackTrace();
         System.out.println("Something wenty wrong.");
      }
      
   }
}


