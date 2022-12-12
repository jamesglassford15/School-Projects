import becker.robots.*;

// This file is the starting point for Lecture 2.
public class Flowchart extends Object
{
   public static void main(String[] args)
   { 
		// Create a new city		
		City MikeVille = new City();
		Robot Bob = new Robot(MikeVille, 1, 1, Direction.EAST, 0);
		new Thing(MikeVille, 1, 2);
		new Thing(MikeVille, 2, 2);
		new Thing(MikeVille, 3, 2);
		new Wall(MikeVille, 3, 2, Direction.EAST);
		
		/* Your code should go here: */
		Bob.move();//Bob moves forward
		//turn bob to the right by turning left 3 times
		Bob.turnLeft();
      Bob.turnLeft();
      Bob.turnLeft();
      Bob.pickThing();//Bob picks the item up
      Bob.move();//Bob moves forward in the intersection
      Bob.pickThing();//Bob picks up a second obj
      Bob.move();//Bob moves forward once again
      Bob.pickThing();//Bob picks up a 3rd obj
      //Bob now has to turn 180 degrees
      Bob.turnLeft();
      Bob.turnLeft();
      //Now we move Bob forward through 2 intersections
      Bob.move();
      Bob.move();
      Bob.move();
      Bob.turnLeft();//Bob turns left
      Bob.move();//Bob's final step forward
   }
}



