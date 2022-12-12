import becker.robots.*;

// This robot will be able to keep track of how many
// moves it's made, and then be able to print a message to
// the user saying that.
class MoveRobot extends Robot
{
   private int numberOfMovesMade = 0; // added "private int," syntax error
   private int countLeftTurn = 0;

   MoveRobot(City c, int st, int ave, Direction dir, int num)
   {
      super(c, st, ave, dir, num);
   }

   public void moveCounted()
   {
      this.move();
      this.numberOfMovesMade = this.numberOfMovesMade + 1; // changed from "*2" to "+1," logic error
   }

   public void printNumberOfMoves()
   {
      System.out.println("Since I started counting, I moved:");
      System.out.println(this.numberOfMovesMade);
      System.out.println("times!");
   }

   public void leftTurnCounted() // method will have mary turn left, then add a left turn to the counter
   {
      this.turnLeft();
      this.countLeftTurn = this.countLeftTurn + 1;
   }

   public void printNumberOfLeftTurns() // When called, will print the number of left turns counted along with a
                                        // friendly message
   {
      System.out.println("Number of left turns made:");
      System.out.println(countLeftTurn);
   }

}

public class ICE_12_Errors_2 extends Object
{
   public static void main(String[] args)
   {
      City ForgetsVille = new City();
      MoveRobot mary = new MoveRobot(ForgetsVille, 4, 1, Direction.EAST, 0);
      new Wall(ForgetsVille, 2, 5, Direction.NORTH);
      // CityFrame frame = new CityFrame(ForgetsVille); commented out the line of code
      // - not sure if this is meant to be kept in, but could not figure out what
      // "cityFrame" is, we're assuming it's a mistake - syntax error

      // First keep track of these 4
      mary.moveCounted();
      mary.moveCounted();
      mary.moveCounted();
      mary.moveCounted();
      mary.printNumberOfMoves();

      mary.leftTurnCounted();

      while (mary.frontIsClear())// remove ";" syntax error
      {
         mary.moveCounted(); // add () after the name of the method syntax error
      }
      mary.printNumberOfMoves(); // add the missing "." syntax error
      mary.printNumberOfLeftTurns();
   }
}
