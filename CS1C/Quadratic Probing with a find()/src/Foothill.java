//Foothill.java-----------------------------------------------------------
import java.util.*;
import cs_1c.*;

// ----------- wrapper classes -------------

class EBookCompInt
   implements Comparable<Integer>
{
     EBookEntry data;
     public EBookCompInt(EBookEntry e) {data = e;}
     public String toString() {return data.toString();}
     
     public int compareTo(Integer key)
     {
        return data.getETextNum() - key;
     }
     
     public boolean equals (EBookCompInt rhs)
     {
        return data.equals(rhs.data);
     }
     
     public int hashCode()
     {
        return data.getETextNum();
     }
}

class EBookCompString 
   implements Comparable<String>
{
   EBookEntry data;
   public EBookCompString(EBookEntry e) {data = e;}
   public String toString() {return data.toString();}
   
   public int compareTo(String key)
   {
      return data.getTitle().compareTo(key);
   }
   
   public boolean equals(EBookCompString rhs)
   {
      return data.equals(rhs.data);
   }
   
   public int hashCode()
   {
      return data.getTitle().hashCode();
   }
}

//------------------------------------------------------
public class Foothill
{
 public static final int NUM_RANDOM_INDICES = 25;

 // -------  main --------------
 public static void main(String[] args) throws Exception
 {
    EBookEntryReader bookInput 
    = new EBookEntryReader("catalog-short4.txt");
    int arraySize, k;
    int[] randomIndices = new int[NUM_RANDOM_INDICES];

    //FHhashQPwFind< Integer, EBookCompInt> hashTable 
    //= new FHhashQPwFind<Integer, EBookCompInt>();

     FHhashQPwFind< String, EBookCompString> hashTable 
        = new FHhashQPwFind<String, EBookCompString>();

    // read data from file
    if (bookInput.readError())
    {
       System.out.println("couldn't open " 
             + bookInput.getFileName()
             + " for input.");
       return;
    }     

    System.out.println(bookInput.getFileName());
    System.out.println(bookInput.getNumBooks());

    //EBookCompInt bookResult;
     EBookCompString bookResult;

    // generate some random indices into the EBookEntryReader array
    arraySize = bookInput.getNumBooks();
    System.out.print("Random indices generated: ");

    for (k = 0; k < NUM_RANDOM_INDICES; k++)
    {
       randomIndices[k] = (int)(Math.random() * arraySize);
       System.out.print(randomIndices[k] + " ");
    }
    System.out.println("\n");

    // create a QP hash table of EBookCompInts or EBookCompStrings
    for (k = 0; k < arraySize; k++)
        hashTable.insert( new EBookCompString( bookInput.getBook(k) ) );
       //hashTable.insert( new EBookCompInt( bookInput.getBook(k) ) );

    // display NUM_RANDOM_INDICES books from array
    System.out.println("Some random books from the EBookEntryReader ");
    for (k = 0; k < NUM_RANDOM_INDICES; k++)
       System.out.print( bookInput.getBook(randomIndices[k]) );


    System.out.println( "The same random books from the hash table " );
    for (k = 0; k < NUM_RANDOM_INDICES; k++)
    {
       System.out.println( "Book #" + randomIndices[k] + " " );
       try
       {
           bookResult = hashTable.find( 
              bookInput.getBook(randomIndices[k]).getCreator() );
          //bookResult = hashTable.find( 
          //      bookInput.getBook(randomIndices[k]).getETextNum() );
          System.out.println( "YES: " + bookResult);
       }
       catch (NoSuchElementException e)
       {
          System.out.println( "no." );
       }
       System.out.println();
    }

    // test known failures exceptions:
    try
    {
        bookResult = hashTable.find( "Jack Kerouac" );
       //bookResult = hashTable.find( -3 );  
       System.out.println( "YES: " 
             + bookResult.data.getCreator().substring(0,8) + " "
             + bookResult.data.getTitle().substring(0,10) );
    }
    catch (NoSuchElementException e)
    {
       System.out.println( "no.");
    }

    try
    {
        bookResult = hashTable.find( " no one" );
       //bookResult = hashTable.find( 9000 );  
       System.out.println( "YES: " 
             + bookResult.data.getCreator().substring(0,8) + " "
             + bookResult.data.getTitle().substring(0,10) );
    }
    catch (NoSuchElementException e)
    {
       System.out.println( "no.");
    }

    try
    {
        bookResult = hashTable.find( "Leacock, Stephen, 2010 - 2009" );
       //bookResult = hashTable.find( 5000 );  
       System.out.println( "YES: " 
             + bookResult.data.getCreator().substring(0,8) + " "
             + bookResult.data.getTitle().substring(0,10) );
    }
    catch (NoSuchElementException e)
    {
       System.out.println( "no.");
    }
 }
}


/*------------------- RUN WITH INTS --------------------
catalog-short4.txt
4863
Random indices generated: 1528 1241 1668 4023 4282 592 1709 1896 3981 1630 3965 
2061 4317 1298 801 4292 4360 1121 1542 3896 4743 2665 1998 70 3052 

Some random books from the EBookEntryReader 
   # 28771  ---------------
   "Child Maidelvoldand other ballads"
   by (no data found)
   re: PR

   # 29562  ---------------
   "The Clock that Had no HandsAnd Nineteen Other Essays About Advertising"
   by Kaufman, Herbert, 1878-1947
   re: Advertising

   # 29612  ---------------
   "Treatise on the Diseases of Women"
   by Pinkham, Lydia Estes, 1819-1883
   re: Women -- Health and hygiene

   # 26089  ---------------
   "Punch, or the London Charivari, Vol. 93, September 24, 1887"
   by Various
   re: English wit and humor -- Periodicals

   # 25713  ---------------
   "The Judas Valley"
   by Garrett, Randall, 1927-1987
   re: Science fiction

   # 28790  ---------------
   "Harper's Young People, April 20, 1880An Illustrated Weekly"
   by Various
   re: Children's periodicals, American

   # 28204  ---------------
   "How Mr. Rabbit Lost his TailHollow Tree Stories"
   by Paine, Albert Bigelow, 1861-1937
   re: Animals -- Juvenile fiction

   # 29822  ---------------
   "Rescue Squad"
   by O'Hara, Thomas J.
   re: Science fiction

   # 26150  ---------------
   "The Proverbs of Scotland"
   by Hislop, Alexander, 1807-1865
   re: Proverbs, Scottish

   # 29582  ---------------
   "The Tricks of the Town: or, Ways and Means of getting Money"
   by Thomson, John, fl. 1732
   re: London (England) -- Social life and customs

   # 26170  ---------------
   "Diaries of Sir Moses and Lady Montefiore, Volume IComprising Their Life and 
Work as Recorded in Their DiariesFrom 1812 to 1883"
   by Montefiore, Judith Cohen, Lady, 1784-1862
   re: Jews -- Biography

   # 6363  ---------------
   "Life Is a Dream"
   by Calderón de la Barca, Pedro, 1600-1681
   re: (no data found)

   # 25659  ---------------
   "A Week of Instruction and Amusement,or, Mrs. Harley's birthday present to he
r daughter :interspersed with short stories, outlines of sacred andprophane hist
ory, geography &amp;c."
   by Unknown
   re: Children's stories

   # 27781  ---------------
   "Revised Edition of Poems"
   by Bill o'th' Hoylus End, 1836-1897
   re: Poetry

   # 5950  ---------------
   "The Fortunes of Nigel"
   by Scott, Walter, Sir, 1771-1832
   re: (no data found)

   # 25698  ---------------
   "Think Before You Speakor, The Three Wishes"
   by Leprince de Beaumont, Madame (Jeanne-Marie), 1711-1780
   re: Children's poetry

   # 25611  ---------------
   "A Child's Garden of Verses"
   by Stevenson, Robert Louis, 1850-1894
   re: Children's poetry, English

   # 29839  ---------------
   "The House in the WaterA Book of Animal Stories"
   by Roberts, Charles George Douglas, Sir, 1860-1943
   re: Animals -- Anecdotes

   # 28656  ---------------
   "Typee"
   by Melville, Herman, 1819-1891
   re: Adventure stories

   # 26252  ---------------
   "The Prince and the Pauper"
   by Twain, Mark, 1835-1910
   re: Historical fiction

   # 24937  ---------------
   "Mike MarbleHis Crotchets and Oddities."
   by Woodworth, Francis C. (Francis Channing), 1812-1859
   re: Conduct of life -- Juvenile fiction

   # 3302  ---------------
   "The Second-Story Man"
   by Sinclair, Upton, 1878-1968
   re: American drama -- 20th century

   # 29993  ---------------
   "Poems"
   by Howells, William Dean, 1837-1920
   re: American poetry

   # 29666  ---------------
   "Hymns and Hymnwriters of Denmark"
   by Aaberg, J. C. (Jens Christian), 1877-1970
   re: Hymns, Danish -- History and criticism

   # 27642  ---------------
   "Lectures in Navigation"
   by Draper, Ernest Gallaudet, 1885-1954
   re: Navigation

The same random books from the hash table 
Book #1528 
YES:    # 28771  ---------------
   "Child Maidelvoldand other ballads"
   by (no data found)
   re: PR



Book #1241 
YES:    # 29562  ---------------
   "The Clock that Had no HandsAnd Nineteen Other Essays About Advertising"
   by Kaufman, Herbert, 1878-1947
   re: Advertising



Book #1668 
YES:    # 29612  ---------------
   "Treatise on the Diseases of Women"
   by Pinkham, Lydia Estes, 1819-1883
   re: Women -- Health and hygiene



Book #4023 
YES:    # 26089  ---------------
   "Punch, or the London Charivari, Vol. 93, September 24, 1887"
   by Various
   re: English wit and humor -- Periodicals



Book #4282 
YES:    # 25713  ---------------
   "The Judas Valley"
   by Garrett, Randall, 1927-1987
   re: Science fiction



Book #592 
YES:    # 28790  ---------------
   "Harper's Young People, April 20, 1880An Illustrated Weekly"
   by Various
   re: Children's periodicals, American



Book #1709 
YES:    # 28204  ---------------
   "How Mr. Rabbit Lost his TailHollow Tree Stories"
   by Paine, Albert Bigelow, 1861-1937
   re: Animals -- Juvenile fiction



Book #1896 
YES:    # 29822  ---------------
   "Rescue Squad"
   by O'Hara, Thomas J.
   re: Science fiction



Book #3981 
YES:    # 26150  ---------------
   "The Proverbs of Scotland"
   by Hislop, Alexander, 1807-1865
   re: Proverbs, Scottish



Book #1630 
YES:    # 29582  ---------------
   "The Tricks of the Town: or, Ways and Means of getting Money"
   by Thomson, John, fl. 1732
   re: London (England) -- Social life and customs



Book #3965 
YES:    # 26170  ---------------
   "Diaries of Sir Moses and Lady Montefiore, Volume IComprising Their Life and 
Work as Recorded in Their DiariesFrom 1812 to 1883"
   by Montefiore, Judith Cohen, Lady, 1784-1862
   re: Jews -- Biography



Book #2061 
YES:    # 6363  ---------------
   "Life Is a Dream"
   by Calderón de la Barca, Pedro, 1600-1681
   re: (no data found)



Book #4317 
YES:    # 25659  ---------------
   "A Week of Instruction and Amusement,or, Mrs. Harley's birthday present to he
r daughter :interspersed with short stories, outlines of sacred andprophane hist
ory, geography &amp;c."
   by Unknown
   re: Children's stories



Book #1298 
YES:    # 27781  ---------------
   "Revised Edition of Poems"
   by Bill o'th' Hoylus End, 1836-1897
   re: Poetry



Book #801 
YES:    # 5950  ---------------
   "The Fortunes of Nigel"
   by Scott, Walter, Sir, 1771-1832
   re: (no data found)



Book #4292 
YES:    # 25698  ---------------
   "Think Before You Speakor, The Three Wishes"
   by Leprince de Beaumont, Madame (Jeanne-Marie), 1711-1780
   re: Children's poetry



Book #4360 
YES:    # 25611  ---------------
   "A Child's Garden of Verses"
   by Stevenson, Robert Louis, 1850-1894
   re: Children's poetry, English



Book #1121 
YES:    # 29839  ---------------
   "The House in the WaterA Book of Animal Stories"
   by Roberts, Charles George Douglas, Sir, 1860-1943
   re: Animals -- Anecdotes



Book #1542 
YES:    # 28656  ---------------
   "Typee"
   by Melville, Herman, 1819-1891
   re: Adventure stories



Book #3896 
YES:    # 26252  ---------------
   "The Prince and the Pauper"
   by Twain, Mark, 1835-1910
   re: Historical fiction



Book #4743 
YES:    # 24937  ---------------
   "Mike MarbleHis Crotchets and Oddities."
   by Woodworth, Francis C. (Francis Channing), 1812-1859
   re: Conduct of life -- Juvenile fiction



Book #2665 
YES:    # 3302  ---------------
   "The Second-Story Man"
   by Sinclair, Upton, 1878-1968
   re: American drama -- 20th century



Book #1998 
YES:    # 29993  ---------------
   "Poems"
   by Howells, William Dean, 1837-1920
   re: American poetry



Book #70 
YES:    # 29666  ---------------
   "Hymns and Hymnwriters of Denmark"
   by Aaberg, J. C. (Jens Christian), 1877-1970
   re: Hymns, Danish -- History and criticism



Book #3052 
YES:    # 27642  ---------------
   "Lectures in Navigation"
   by Draper, Ernest Gallaudet, 1885-1954
   re: Navigation



no.
no.
no.
*/

/*-------------------------- RUN WITH STRINGS ----------------------------
catalog-short4.txt
4863
Random indices generated: 2452 40 2205 2996 3016 2467 1906 268 205 132 2566 2841
 2740 1219 437 2955 324 1958 750 2932 4123 3171 223 47 3507 

Some random books from the EBookEntryReader 
   # 29952  ---------------
   "The American MindThe E. T. Earl Lectures"
   by Perry, Bliss, 1860-1954
   re: American literature -- History and criticism

   # 28320  ---------------
   "A Tramp's Walletstored by an English goldsmith during his wanderings in Germ
any and France"
   by Duthie, William
   re: Germany -- Description and travel

   # 2783  ---------------
   "The Trampling of the Lilies"
   by Sabatini, Rafael, 1875-1950
   re: France -- History -- Revolution, 1789-1799 -- Fiction

   # 27739  ---------------
   "Poetical Works of Matthew Arnold"
   by Arnold, Matthew, 1822-1888
   re: English poetry

   # 27707  ---------------
   "Saint AthanasiusThe Father of Orthodoxy"
   by Forbes, F. A. (Frances Alice), 1869-1936
   re: Christian saints -- Biography

   # 3796  ---------------
   "Rilla of Ingleside"
   by Montgomery, L. M. (Lucy Maud), 1874-1942
   re: World War, 1914-1918 -- Prince Edward Island -- Juvenile fiction

   # 28391  ---------------
   "True To His Colors"
   by Castlemon, Harry, 1842-1915
   re: United States -- History -- Civil War, 1861-1865 -- Fiction

   # 28472  ---------------
   "Patrician and PlebeianOr The Origin and Development of the Social Classes of
 the Old Dominion"
   by Wertenbaker, Thomas Jefferson, 1879-1966
   re: Virginia -- History -- Colonial period, ca. 1600-1775

   # 28087  ---------------
   "Tuskegee &amp; Its People: Their Ideals and Achievements"
   by (no data found)
   re: Tuskegee Institute

   # 28972  ---------------
   "The Works Of Louis BeckeA Linked Index to the Project Gutenberg Editions"
   by Becke, Louis, 1855-1913
   re: Indexes

   # 26883  ---------------
   "The Sword and the Atopen"
   by Greenfield, Taylor H.
   re: Science fiction

   # 29754  ---------------
   "Whittier-landA Handbook of North Essex, Containing Many Anecdotes of and Poe
ms by John Greenleaf Whittier Never Before Collected."
   by Pickard, Samuel T. (Samuel Thomas), 1828-1915
   re: Whittier, John Greenleaf, 1807-1892 -- Homes and haunts -- Massachusetts 
-- Essex County

   # 20220  ---------------
   "The Mind and Its Education"
   by Betts, George Herbert, 1868-1934
   re: (no data found)

   # 28644  ---------------
   "Beyond the Door"
   by Dick, Philip K., 1928-1982
   re: Fantasy

   # 29721  ---------------
   "Philo Gubb, Correspondence-School Detective"
   by Butler, Ellis Parker, 1869-1937
   re: Detective and mystery stories

   # 27810  ---------------
   "Chips From A German Workshop, Vol. V.Miscellaneous Later Essays"
   by Müller, F. Max (Friedrich Max), 1823-1900
   re: (no data found)

   # 28668  ---------------
   "The Christian Foundation, Or, Scientific and Religious Journal, Volume I, No
. 7, July, 1880"
   by Various
   re: Religion and science -- Periodicals

   # 19801  ---------------
   "The Drummer's Coat"
   by Fortescue, J. W. (John William), 1859-1933
   re: (no data found)

   # 29418  ---------------
   "The Man from Time"
   by Long, Frank Belknap, 1903-1994
   re: Science fiction

   # 27860  ---------------
   "The Message"
   by Dawson, A. J. (Alec John), 1872-1952
   re: England -- Fiction

   # 25952  ---------------
   "The Celtic Magazine, Vol. 1, No. 1, November 1875A Monthly Periodical Devote
d to the Literature, History,Antiquities, Folk Lore, Traditions, and the Social 
andMaterial Interests of the Celt at Home and Abroad."
   by Various
   re: Clans -- Scotland -- Periodicals

   # 27445  ---------------
   "Love of Brothers"
   by Tynan, Katharine, 1861-1931
   re: PR

   # 28363  ---------------
   "MindGamesShort Fiction about Bizarre Mental Health Disorders"
   by Vaknin, Samuel, 1961-
   re: (no data found)

   # 28304  ---------------
   "Harper's Young People, January 13, 1880An Illustrated Weekly"
   by Various
   re: Children's periodicals, American

   # 26842  ---------------
   "The Sense of BeautyBeing the Outlines of Aesthetic Theory"
   by Santayana, George, 1863-1952
   re: Aesthetics

The same random books from the hash table 
Book #2452 
no.

Book #40 
no.

Book #2205 
no.

Book #2996 
no.

Book #3016 
no.

Book #2467 
no.

Book #1906 
no.

Book #268 
no.

Book #205 
no.

Book #132 
YES:    # 25106  ---------------
   "Becke, Louis, 1855-1913"
   by Becke, Louis, 1855-1913
   re: Sea stories



Book #2566 
no.

Book #2841 
no.

Book #2740 
no.

Book #1219 
no.

Book #437 
no.

Book #2955 
no.

Book #324 
no.

Book #1958 
no.

Book #750 
no.

Book #2932 
no.

Book #4123 
no.

Book #3171 
no.

Book #223 
no.

Book #47 
no.

Book #3507 
no.

no.
no.
no.*/
