//FHhashQPwFind.java----------------------------------------------
import cs_1c.*;
import java.util.*;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType> >
   extends FHhashQP<E>
{
   
   E find(KeyType key)
   {
      int temp = findPosKey(key);
      HashEntry<E> empData = mArray[temp];
      
      if(empData.state == ACTIVE)
      {
         return empData.data;
      }
      else
      {
         throw new NoSuchElementException();
      }
   }

   
   protected int myHashKey (KeyType key)
   {
      int hashVal;
      hashVal = key.hashCode() % mTableSize;
      if(hashVal < 0)
         hashVal += mTableSize;
      
      return hashVal;
   }
   
   protected int findPosKey (KeyType key)
   {
      int kthOddNum = 1;
      int index = myHashKey(key);

      while(mArray[index].state != EMPTY 
            && !(mArray[index].data.compareTo(key) == 0))
      {
         index += kthOddNum; // k squared = (k-1) squared + kth odd #
         kthOddNum += 2;     // compute next odd #
         if ( index >= mTableSize )
            index -= mTableSize;
      }
      return index;
   }
}