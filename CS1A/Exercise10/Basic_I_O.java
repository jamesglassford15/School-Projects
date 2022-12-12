import becker.robots.*;
import java.util.Scanner;
//Starting Template:
//This file was created in order to provide you with a pre made 'starter' program
// */

public class Basic_I_O extends Object
{
   public static void main(String[] args)
   {

      City toronto = new City();
      Robot jo = new Robot(toronto, 3, 0, Direction.EAST, 0);
      new Thing(toronto, 3, 2);

      Scanner keyboard = new Scanner(System.in);
      int userChoice;
      int backPack = 0;
      boolean loGan = true;
      /* Your code should after here: */
      while (loGan == true)
      {
         System.out.println("What would you like the robot to do?");
         System.out.println("Type 1 if you want the robot to turn left");
         System.out.println("Type 2 if you want the robot to move forwards");
         System.out.println("Type 3 if you want the robot to pick a Thing up");
         System.out.println("Type 4 if you want the robot to put a Thing down");
         System.out.println("Type 5 to quit the program");
         if (keyboard.hasNextInt())
         {
            userChoice = keyboard.nextInt();
            if (userChoice == 1)
            {
               jo.turnLeft();
            } else if (userChoice == 2)
            {
               jo.move();
            } else if (userChoice == 3)
            {
               if (jo.canPickThing())
               {
                  jo.pickThing();
                  backPack++;
               }
            } else if (userChoice == 4)
            {
               if (backPack > 0)
               {
                  jo.putThing();
                  backPack--;
               }
            } else if (userChoice == 5)
            {
               loGan = false;
            }
         } else
         {
            System.out.println("That's not an integer!");
         }

      }
      keyboard.nextLine();
      keyboard.close();
   }
}
