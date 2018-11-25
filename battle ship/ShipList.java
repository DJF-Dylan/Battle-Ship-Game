import java.util.ArrayList;
/**
 * Write a description of class ShipList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShipList
{
    private ArrayList<Ship> ships;

    public ShipList()
    {
        ships = new ArrayList();       
    }

    public ShipList(int initialCapacity)
    {
        ships = new ArrayList(initialCapacity);
    }

    //create a ship and put it into ships arraylist
    public void addShip(String shipName,int xPos,int yPos,int noOfHitsMade,int noOfHitsNeeded)
    {
        Ship ship = new Ship();
        ship.setShipName(shipName);
        ship.setXPos(xPos);
        ship.setYPos(yPos);
        ship.setNoOfHitsMaded(noOfHitsMade);
        ship.setNoOfHitsNeeded(noOfHitsNeeded);
        ships.add(ship);
    }

    //check user and computer attack hit or miss, if ship distory return true if not return false)
    public boolean attack(int x, int y)
    {
        if(checkPos(x,y) && printShipState(x,y) != "X")
        {
            System.out.println("HITTTTT!!!!");
            getShip(x,y).hit();
            if(printShipState(x,y) == "X")
                return true;
        }
        else       
            System.out.println("MISSSSS!!!!");        
        return false;
    }

    //check if the position avaliable
    public boolean checkPos(int xPos,int yPos)
    {
        boolean repeat = false;
        if(ships.size()>0)
        {
            for(Ship a:ships)
            {
                if(a.getXPos() == xPos && a.getYPos() == yPos)
                {
                    repeat = true;
                }
            }        
        }
        return repeat;
    }

    // count the Score
    public int countScore()
    {
        int sum = 0;
        for(Ship a:ships)
        {
            sum = sum + a.getNoOfHitsMade();  
        }
        sum = sum * 10;
        return sum;
    }

    //return the ship which is in the parameter position
    public Ship getShip(int x, int y)
    {
        for(int i = 0; i < ships.size(); i++)
        {
            if(ships.get(i).getXPos() == x && ships.get(i).getYPos() == y)
            {
                Ship ship = ships.get(i);
                return ship;
            }
        } 
        return null;
    }

    //access method
    public ArrayList<Ship> getShipList()
    {
        return ships;
    }

    //check if all the ship are distoryed
    public boolean ifShipDistoryed()
    {
        boolean distory = false;
        for(Ship a:ships)
        {
            if(a.getNoOfHitsMade() < a.getNoOfHitsNeeded())
                distory = true;
        }
        return distory;
    }

    //check the ship state O D or X and return it
    public String printShipState(int xPos,int yPos)
    {
        String state = "";
        for(Ship a:ships)
        {
            if(a.getXPos() == xPos && a.getYPos() == yPos)
            {
                if(a.getNoOfHitsMade() == a.getNoOfHitsNeeded())                   
                    state = "X";
                else if(a.getNoOfHitsMade() == 0)
                    state = "O";
                else
                    state = "D";
            }   
        }
        return state;
    }

    //mutator method
    public void setShip(ArrayList<Ship> newShips)
    {
        ships = newShips;
    }
}