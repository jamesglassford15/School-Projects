// CIS 1C Assignment #4
// Instructor Solution Client

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class Foothill
{
// -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k, arraySize;
      FHsearch_tree<EBookEntry> bookTree = new FHsearch_tree<EBookEntry>();
      PrintObject<EBookEntry> bookPrinter = new PrintObject<EBookEntry>();

      // read the data from file
      EBookEntryReader bookInput = new EBookEntryReader("catalog-short4.txt");
      if (bookInput.readError())
      {
         System.out.println("couldn't open " + bookInput.getFileName()
            + " for input.");
         return;
      }

      // changing  sort key to SORT_BY_TITLE will not allow duplicate titles
      EBookEntry.setSortType(EBookEntry.SORT_BY_ID);
      arraySize = bookInput.getNumBooks();
      
      // build the tree
      for (k = 0; k < arraySize; k++)
         if ( !bookTree.insert(bookInput.getBook(k)) )
         {
            System.out.print( "NOT INSERTED: " );
            bookPrinter.visit( bookInput.getBook(k) );
         }
      
      // confirm the size:
      System.out.print( "Num Books: " + arraySize
         + ", Tree Size: " + bookTree.size() );
      
      // test finds
      int START = 1000, STOP = 1020;
      System.out.println( "\nAttempting " + (STOP - START) + " finds: \n");
      for (k = START; k < STOP; k++)
         if (!bookTree.contains( bookInput.getBook(k) ))
            System.out.println( " !!! NOT FOUND: " + k + ": " 
                  + bookInput.getBook(k).getTitle());
         else
         {
            System.out.println( "Found: ");
            bookPrinter.visit(bookInput.getBook(k));
         }

      // test removals
      System.out.println( "\nAttempting " + (STOP - START) + " removals: \n");
      for (k = START; k < STOP; k++)
         if (!bookTree.remove( bookInput.getBook(k) ))
            System.out.println( " !!! NOT FOUND: " + k + ": " 
                  + bookInput.getBook(k).getTitle());

      System.out.println( "\nnew size: " + bookTree.size());
   }
}