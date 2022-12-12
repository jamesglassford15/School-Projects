import java.util.*;
import java.text.*;

//-------------------CODE-------------------------------
public class Foothill
{
   final static int MAT_SIZE = 4000;
   static boolean newMat = false;

   // ------- proof of correctness --------------
   public static void main(String[] args) throws Exception
   {
      int r, randRow, randCol;
      long startTime, stopTime;
      double randFrac;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // non-sparse matrices
      double[][] mat, matAns;

      // allocate matrices
      mat = new double[MAT_SIZE][MAT_SIZE];
      matAns = new double[MAT_SIZE][MAT_SIZE];

      // generate small% of non-default values bet 0 and 1
      Random random = new Random();

      smallPercent = MAT_SIZE / 10. * MAT_SIZE;
      for (r = 0; r < smallPercent; r++)
      {
         randRow = random.nextInt(MAT_SIZE);
         randCol = random.nextInt(MAT_SIZE);
         randFrac = random.nextDouble();
         mat[randRow][randCol] = randFrac;
      }

      // 10x10 submatrix in lower right
      matShow(mat, MAT_SIZE - 10, 10);

      System.out.println();

      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      matShow(matAns, MAT_SIZE - 10, 10);

      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
   }

   public static void matMult(double[][] matA, double[][] matB, 
         double[][] matC)
   {
      int firstMatRow = matA.length;
      int firstMatCol = matA[0].length;
      int secondMatRow = matB.length;
      int secondMatCol = matB[0].length;

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

   public static void matShow(double[][] matA, int start, int size)
   {
      int matSize = start + size;

      if (start < 0 || size < 0 || matSize > matA.length ||
            matSize > matA[0].length)
         return;

      for (int matRow = start; matRow < matSize; matRow++)
      {
         for (int matCol = start; matCol < matSize; matCol++)
         {
            System.out.format("%.2f", matA[matRow][matCol]);
            System.out.print(" ");
         }
         System.out.println();
      }
   }

}

/*------------------------ RUNS -------------------------------
0.00 0.00 0.00 0.00 0.38 0.09 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.01 0.00 0.00 0.00 0.00 0.00 0.79 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.31 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.90 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.08 0.00 0.00 0.00 0.35 0.00 0.00 0.00 0.00 
0.00 0.00 0.49 0.00 0.00 0.00 0.00 0.00 0.00 0.00 

0.00 0.65 0.00 0.00 0.63 0.20 0.03 0.02 0.00 0.71 
0.15 0.31 0.35 0.00 1.31 0.36 0.12 0.17 0.72 0.12 
1.05 0.00 0.00 0.00 0.00 0.20 0.81 0.00 0.21 0.00 
0.00 0.32 0.92 0.00 0.12 0.28 0.42 0.18 0.16 0.36 
0.34 0.09 0.33 0.00 0.90 0.40 0.59 0.00 0.00 0.00 
1.10 0.07 0.12 0.00 0.06 0.68 0.18 0.00 0.01 0.21 
0.00 0.00 0.64 0.00 0.53 0.00 0.46 0.00 0.09 0.00 
0.00 1.12 0.14 0.00 0.26 0.00 0.35 0.02 0.00 0.14 
0.06 0.74 0.00 0.00 0.00 0.53 0.43 0.86 0.07 0.02 
0.00 0.99 0.67 0.00 0.00 0.00 0.57 0.18 0.12 0.04 

Size = 100 Mat. Mult. Elapsed Time: 0.0044 seconds.

0.00 0.00 0.00 0.00 0.76 0.00 0.00 0.37 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.92 0.00 0.42 0.00 0.00 0.00 0.00 0.00 0.28 
0.00 0.00 0.00 0.00 0.63 0.00 0.15 0.00 0.00 0.00 
0.00 0.00 0.00 0.07 0.47 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.83 0.00 0.07 0.00 
0.00 0.00 0.00 0.00 0.83 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 

0.52 0.83 0.51 0.05 0.82 0.03 0.61 0.42 0.20 0.00 
0.99 0.15 0.00 0.43 0.35 0.71 0.07 0.07 0.37 0.55 
1.07 0.96 0.00 0.25 1.06 1.10 0.06 0.07 0.00 1.12 
0.00 0.39 0.08 0.32 0.66 1.99 1.12 0.84 0.05 0.20 
1.05 1.22 0.00 0.47 2.28 0.87 0.68 0.14 0.00 0.35 
0.00 0.53 0.00 0.77 1.56 0.04 0.02 0.60 0.72 1.23 
0.00 0.14 0.59 0.13 1.65 0.21 1.11 0.64 0.25 0.53 
0.70 0.00 0.49 0.08 0.00 0.48 0.00 0.00 0.09 0.54 
0.92 1.24 1.83 0.55 0.66 0.36 1.52 0.56 0.32 0.25 
0.67 0.59 0.16 0.33 0.20 0.77 0.80 0.27 0.00 0.53 

Size = 200 Mat. Mult. Elapsed Time: 0.0202 seconds.

Size = 300 Mat. Mult. Elapsed Time: 0.0438 seconds.

Size = 400 Mat. Mult. Elapsed Time: 0.112 seconds.

Size = 500 Mat. Mult. Elapsed Time: 0.2067 seconds.


----------------------------QUESTIONS----------------------
PREDICTION: I predict that the time complexity of this operation relative to M
will be O(N^3). We will first need a for loop to iterate through the rows of 
the first matrix, which will run N times. We then need to nest a second loop to
iterate through the columns of the second matrix will also run N times, making 
the runtime O(N^2). Finally, we will have to add a nested third loop to add up 
the values received by multiplying all the numbers, making the total time 
complexity of the algoritm O(N^3). 

1. The smallest M that gave me a non-zero time was 11. When M is 11, the result 
is: Size = 11 Mat. Mult. Elapsed Time: 0.0001 seconds.

However, I ran this a few times and occasionally I would get a runtime of 0
seconds flat. What I imagine this means is that sometimes with 11 it runs so
fast that there are more than4 zeroes, which is all that tidy allows for. So 
the smallest M that consistently gives a non-zero time is between 11 and 12.

2. 

M = 500: Size = 500 Mat. Mult. Elapsed Time: 0.2786 seconds.

M = 1000: Size = 1000 Mat. Mult. Elapsed Time: 4.6718 seconds.

M = 2000: Size = 2000 Mat. Mult. Elapsed Time: 56.7371 seconds.

M = 4000: Size = 4000 Mat. Mult. Elapsed Time: 560.1953 seconds.


3. The maximum M size is somewhere between 16000 and 17000. At 16000, the 
program is able to create the matrix but undoubtedly takes a super long time
to multiply and return the finished matrix. At 17000, the program isn't even
able to create the first matrix.

4. The results from question 2 tell me that the run time increases 
exponentially, rather than linear, quadratic, cubic etc. I'm not really sure 
how to directly test my prediction with my results (math is far from my 
strong suit) but I am confident that it is, at the very least, roughly the 
same.
*/