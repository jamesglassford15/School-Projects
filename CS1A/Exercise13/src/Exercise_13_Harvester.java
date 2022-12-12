
//Exercise_13_Harvester.java
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;

// TODO: Do NOT change the import code above!
// TODO: Do NOT change the code below EXCEPT for adding methods that override methods in a super class
/**
 * A class of robot which can harvest a field of things. The field must be 5
 * things wide and 6 rows high. <br>
 * 
 * @author Baba Kofi Weusijana
 * @author Byron Weber Becker
 * @author James Glassford
 * @author Jean Applys Cherizol
 */
public class Exercise_13_Harvester extends Robot
{
   /**
    * Construct a new Harvester robot.
    * 
    * @param theCity
    *           the city where the robot will be created.
    * @param ave
    *           the robot's initial avenue
    * @param str
    *           the robot's initial street
    * @param dir
    *           the robot's initial direction, one of {Direction.NORTH, SOUTH,
    *           EAST, WEST}.
    * @param numThings
    *           the number of things to place in the robot's backpack.
    */
   public Exercise_13_Harvester(City theCity, int str, int ave, Direction dir, int numThings)
   {
      super(theCity, str, ave, dir, numThings);
   }

   /**
    * Turns right by turning left 3 times. <br>
    * Ignore the possible warning you might get from your IDE (like Eclipse) for
    * this turnRight method. This turnRight method does not override the inherited
    * method from the becker.robots.Robot class since that turnRight method is
    * private to any code that is not in the becker.robots package.
    */
   public synchronized void turnRight()
   {
      // Note: We are inside this method calling super.turnLeft() (in
      // becker.robots.Robot) because we don't want to call this.turnLeft() (in this
      // Exercise_13_Harvester class) that will spin around way too much!
      super.turnLeft();
      super.turnLeft();
      // This last one actually turns the robot right of where it started.
      super.turnLeft();
   }

   /**
    * Turns left after spinning around first.
    */
   @Override
   public synchronized void turnLeft()
   {
      // The first 4 times 'spins' the robot around
      super.turnLeft();
      super.turnLeft();
      super.turnLeft();
      super.turnLeft();
      // This last one actually turns the robot left of where it started.
      super.turnLeft();
   }

   /**
    * Harvest a field of things. The robot is assumed to be on the north-west
    * corner of the field.
    */
   public void harvestField()
   {
      System.out.println("Harvesting!");
      this.harvestTwoRows();
      this.positionForNextHarvest();
      this.harvestTwoRows();
      this.positionForNextHarvest();
      this.harvestTwoRows();
   }

   /**
    * Harvest two rows of the field, returning to the same avenue but one street
    * farther south.
    */
   protected void harvestTwoRows()
   {
      this.harvestOneRow();
      this.goToNextRow();
      this.harvestOneRow();
   }

   /** Harvest one row of five things. */
   protected void harvestOneRow()
   {
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
   }

   /** Go one row south and face west. The robot must be facing east. */
   private void goToNextRow()
   {
      this.turnRight();
      this.move();
      this.turnRight();
   }

   /**
    * Position the robot for the next harvest by moving one street south and facing
    * west.
    */
   private void positionForNextHarvest()
   {
      this.turnLeft();
      this.move();
      this.turnLeft();
   }

   /** Harvest ALL of the things on one intersection. */
   protected void harvestIntersection()
   {
      this.pickThing();
      System.out.print("I'm done picking up all the things at this intersection: ");
      System.out.println("(" + this.getStreet() + ", " + this.getAvenue() + ")");
   }

   // TODO: Add below methods that override methods in the super class
   // becker.robots.Robot

   // overrides pickThing so that the robot checks if there is an object there
   // first, and picks up objects until there are no more
   public void pickThing()
   {
      while (this.canPickThing())
      {
         super.pickThing();
      }
   }

   // overrides move so that if there is a wall in front of the robot, it does not
   // crash into the wall
   public void move()
   {
      if (this.frontIsClear())
      {
         super.move();
      }
   }

   // Overriding the method puThing so that the robot checks its backpack before
   // putting thing down
   public void putThing()
   {
      while (this.countThingsInBackpack() > 0)
      {
         super.putThing();
      }
   }

}
