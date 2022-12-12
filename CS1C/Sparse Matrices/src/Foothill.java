/*-------------------- PART B ------------------------------------------

----------------------- code -----------------*/
// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 100000;
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k;
      SparseMat<Double> mat 
         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); 

      // test mutators
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, k*1.);
         mat.set(4, k, k*10.);
         mat.set(k, 4, -k*10.);
      }
      mat.showSubSquare(0, 12);
      System.out.println();
      
      SparseMat<Double> mat2 = (SparseMat<Double>)mat.clone();
      
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, 1.);
         mat.set(4, k, 10.);
         mat.set(k, 4, -10.);
      }
      
      mat.showSubSquare(0, 12);
      System.out.println();
      mat2.showSubSquare(0, 12);
   }
}

class SparseMat <E> implements Cloneable
{
   private static int DEFAULT_ROWS = 100;
   private static int DEFAULT_COLUMNS = 100;
   protected int numberOfRows, numberOfColumns;
   protected E defaultValue;
   protected FHarrayList<FHlinkedList<MatNode>> rows;
   public SparseMat(int numRows, int numCols, E defaultVal) 
   {
      if(numRows >= 1 && numCols >= 1)
      {
         this.numberOfRows = numRows;
         this.numberOfColumns = numCols;
      }
      else
      {
         this.numberOfRows = DEFAULT_ROWS;
         this.numberOfColumns = DEFAULT_COLUMNS;
      }
      
      defaultValue = defaultVal;
      allocateEmptyMatrix();
   }
   
   public E get(int r, int c) throws IndexOutOfBoundsException
   {
      ListIterator<MatNode> listIterator;
      
      for(listIterator = this.rows.get(r).listIterator(); 
            listIterator.hasNext();)
      {
         if(listIterator.next().getCol() == c)
            return listIterator.previous().getData();
      }
      return defaultValue;
   }
   
   public boolean set(int r, int c, E x) throws IndexOutOfBoundsException
   {
         ListIterator<MatNode> listIterator;

         if(!(x.equals(defaultValue)))
         {
            for(listIterator = this.rows.get(r).listIterator(); 
                  listIterator.hasNext();)
            {
               if(listIterator.next().col == c)
               {
                  listIterator.previous().setData(x);
                  return true;
               }
            }            
            this.rows.get(r).add(new MatNode(c, x));
            return true;           
         }
         else
         {
            for(listIterator = this.rows.get(r).listIterator(); 
                  listIterator.hasNext();)
            {
               if(listIterator.next().col == c)
               {
                  this.rows.get(r).remove(listIterator.previous());
                  return true;
               }
            }                        
            return true;            
         }
   }
   
   public void clear()
   {
      for(int i = 0; i <= rows.size(); i++)
      {
         rows.get(i).clear();
      }
   }
   
   public void showSubSquare(int start, int size)
   {
      for(int k = start; k <= (start + size - 1); k++)
      {
         for(int l = start; l <= (start + size - 1); l++)
         {
            System.out.format("%6s", get(k, l));
         }
         System.out.println();
      }
   }
   
   private void allocateEmptyMatrix()
   {
      rows = new FHarrayList<FHlinkedList<MatNode>>(numberOfRows);
      
      for (int i = 0; i < numberOfRows; i++)
      {
         FHlinkedList<MatNode> column = new FHlinkedList<MatNode>();
         rows.add(column);
      }
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      int size;
      SparseMat<E> clone = (SparseMat<E>)super.clone();
      clone.allocateEmptyMatrix();
      
      for(int z = 0; z < this.rows.size(); z++)
      {
         size = (this.rows.get(z).size());
         for(int y = 0; y < size; y++)
         {
            clone.rows.get(z).add((MatNode)this.rows.get(z).get(y).clone());
         }
      }
      return clone;
   }
// protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;
      
      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }
      
      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode)super.clone();
         return (Object) newObject;
      }
      
      public int getCol()
      {
         return this.col;
      }
      
      public E getData()
      {
         return this.data;
      }
      
      public void setCol(int newCol)
      {
         this.col = newCol;
      }
      
      public void setData(E newData)
      {
         this.data = newData;
      }
   };
   
   public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
   {
      
   }
}

class SparseMatWMult extends SparseMat<Double>
{
   
   int firstMatRow, firstMatCol, secondMatRow, secondMatCol;
   boolean newMat;
   
   public   (int numRows, int numCols, double val, double[][] matA,
         double[][] matB, double[][] matC)
   
   {
      super(numRows, numCols, val);
      firstMatRow = matA.length;
      firstMatCol = matA[0].length;
      secondMatRow = matB.length;
      secondMatCol = matB[0].length;
      matC = new double[]
   }
  
   // multiply:
   public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
   {
      if (firstMatRow == secondMatRow)
      {
         if (newMat)
            matC = new double[MAT_SIZE][MAT_SIZE];
         for (int i = 0; i < firstMatRow; i++)
         {
            for (int j = 0; j < secondMatCol; j++)
            {
               matC[i][j] = 0;
               for (int k = 0; k < firstMatCol; k++)
               {
                  matC[i][j] += (matA[i][k] * matB[k][j]);
               }
            }
         }
         newMat = true;
      }
   }
}

/*---------------------- run ----------------------------
 0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0
   0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

   1.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   1.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   1.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
  10.0  10.0  10.0  10.0 -10.0  10.0  10.0  10.0  10.0  10.0   0.0   0.0
   0.0   0.0   0.0   0.0 -10.0   1.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -10.0   0.0   1.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -10.0   0.0   0.0   1.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   1.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   1.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0
   0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
*/
