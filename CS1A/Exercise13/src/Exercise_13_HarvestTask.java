//Exercise_13_HarvestTask.java
import becker.robots.*;


/**
 * This program uses Exercise_13_Harvest.txt to build a city full of things and then
 * creates and orders the harvester robot to harvest it. <br>
 * TODO: Do NOT change any of the code in this class!
 * 
 * @author Baba Kofi Weusijana
 * @author Byron Weber Becker
 */
public class Exercise_13_HarvestTask extends Object
{
    /**
     * TODO: Do NOT change any of the code in this method!
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        City stLouis = new City("Exercise_13_Harvest.txt");
        Exercise_13_Harvester mark = new Exercise_13_Harvester(stLouis, 1, 0, Direction.EAST, 0);

        mark.move();
        mark.harvestField();
        mark.move();
        for (int tries = 0; tries < 40; ++tries)
        {
            mark.putThing();
        }
    }
}
