public class Q1 extends Object
{
public static void main(String[] args ) {
   int counter = 0; //will be used later in the for loop
   String police = "Police Department"; //establishing String objects
   String fire = "Fire Department";
   String urban = "Urban Development Department";
   String[][] cityDeptList; //Creating the arrays to store the cities and departments
   cityDeptList = new String[3][];
   cityDeptList[0] = new String[2];
   cityDeptList[1] = new String[1];
   cityDeptList[2] = new String[3];
   cityDeptList[0][0] = police; //Entering the departments by hand
   cityDeptList[0][1] = fire;
   cityDeptList[1][0] = urban;
   cityDeptList[2][0] = police;
   cityDeptList[2][1] = fire;
   cityDeptList[2][2] = urban;
   for(String[] city : cityDeptList) { //For loop to print out the departments
      System.out.print("City "+counter+": ");
      for(String dept: city) {
         System.out.print(dept+" ");
      }
      System.out.println("\n");
      counter++;
   }
   }
}
