
/**
 * Write a description of class Ship here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ship
{
    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;

    public Ship()
    {
        shipName ="";
        xPos = 0;
        yPos = 0;
        noOfHitsMade = 0;
        noOfHitsNeeded = 0;
    }

    public Ship(String newShipName,int newXPos,int newYPos,int hitsMade,int hitsNeed)
    {
        shipName = newShipName;
        xPos = newXPos;
        yPos = newYPos;
        noOfHitsMade = hitsMade;
        noOfHitsNeeded = hitsNeed;
    }

    //display the ship information
    public void displayShip()
    {
        System.out.println("shipName is : "+ shipName);
        System.out.println("xPos is : "+ xPos);
        System.out.println("yPos is : "+ yPos);
        System.out.println("noOfHitsMade is : "+ noOfHitsMade);
        System.out.println("noOfHitsNeeded is : "+ noOfHitsNeeded);
    }

    //access method
    public int getNoOfHitsMade()
    {
        return noOfHitsMade;
    }

    //access method
    public int getNoOfHitsNeeded()
    {
        return noOfHitsNeeded;
    }

    //access method
    public String getShipName()
    {
        return shipName;
    }

    //access method
    public int getXPos()
    {
        return xPos;
    }

    //access method
    public int getYPos()
    {
        return yPos;
    }

    //noOfHitsMade + 1
    public void hit()
    {
        noOfHitsMade++;
    }

    //mutator method
    public void setNoOfHitsMaded(int newNoOfHitsMade)
    {
        noOfHitsMade = newNoOfHitsMade;
    }

    //mutator method
    public void setNoOfHitsNeeded(int newNoOfHitsNeeded)
    {
        noOfHitsNeeded = newNoOfHitsNeeded;
    }

    //mutator method
    public void setShipName(String newShipName)
    {
        shipName = newShipName;
    }

    //mutator method
    public void setXPos(int newXPos)
    {
        xPos = newXPos;
    }

    //mutator method
    public void setYPos(int newYPos)
    {
        yPos = newYPos;
    }
}
