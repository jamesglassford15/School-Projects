import becker.robots.*;

/*
CS1A: Exercise03: - What's wrong with this code?
The program is supposed to make jo perform diving acrobatics - but it's not yet working. 
Find, document in a Program Debug Table, and correct all the errors & get jo into the pool.
*/

public class Exercise03 extends Object {
	public static void main(String[] args)
    { 
        // Create the robot on the diving board...
        City bothell = new City();
        Robot jo = new Robot(bothell, 1, 1, Direction.NORTH, 0);
        
        //pool
        new Wall(bothell, 2, 3, Direction.EAST);
        new Wall(bothell, 2, 3, Direction.SOUTH);
        new Wall(bothell, 2, 3, Direction.WEST);
		
        new Thing(bothell, 2, 3);
        
        //diving board
        new Wall(bothell, 1, 1, Direction.SOUTH);
        new Wall(bothell, 2, 1, Direction.WEST);
        new Wall(bothell, 3, 1, Direction.WEST);
        
        

        // jo first leaps off the board, turns right, moves beyond the diving board,
        // faces downwards, goes to the bottom of the pool (without crashing), and
        // then faces upwards.
        jo.move();
        jo.turnLeft();
        jo.turnLeft();
        jo.turnLeft();
        

	
        // Dive jo, DIVE!
        jo.move();
        jo.move();
        jo.turnLeft();
        jo.turnLeft();
        jo.turnLeft();
        jo.move();
        jo.move();
		
        jo.turnLeft();
        jo.turnLeft();
    }
}
