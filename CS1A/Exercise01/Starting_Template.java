import becker.robots.*;

public class Starting_Template extends java.lang.Object 
{
	public static void main(String[] args) 
   {
	   //CREATE THE CITY, AND GIVE IT A NAME
		City cupertino = new City();
		Robot gretel = new Robot(cupertino, 0, 0, Direction.NORTH, 0);
		new Thing(cupertino, 2, 4);
		new Wall(cupertino, 1, 1, Direction.WEST);
      new Wall(cupertino, 2, 1, Direction.WEST);
      new Wall(cupertino, 3, 1, Direction.WEST);
      new Wall(cupertino, 4, 1, Direction.WEST);
      new Wall(cupertino, 3, 2, Direction.EAST); 
      new Wall(cupertino, 2, 2, Direction.EAST);
      new Wall(cupertino, 1, 2, Direction.EAST);
      new Wall(cupertino, 4, 2, Direction.EAST);
      new Wall(cupertino, 4, 4, Direction.EAST);
      new Wall(cupertino, 3, 4, Direction.EAST);
      new Wall(cupertino, 2, 2, Direction.SOUTH);
      new Wall(cupertino, 2, 1, Direction.SOUTH);
	} 
}
