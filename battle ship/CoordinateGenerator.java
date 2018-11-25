
/**
 * Write a description of class CoordinateGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoordinateGenerator
{
    private int minimunValue;
    private int maximunValue;
    
    public CoordinateGenerator()
    {
        minimunValue = 0;
        maximunValue = 0;
    }

    public CoordinateGenerator(int min,int max)
    {
        minimunValue = min;
        maximunValue = max;
    }
    
    //print a random number
    public void display()
    {
        System.out.println(randomNumber(minimunValue,maximunValue));
    }
    
    //access method
    public int getMaximunValue()
    {
        return maximunValue;
    }
    
    //access method
    public int getMinimunValue()
    {
        return minimunValue;
    }
    
    //create a random number in [min,max]
    public int randomNumber(int min, int max)
    {
        int randomNumber = min + (int)(Math.random() * (max - min + 1));
        return randomNumber;
    }
    
    //mutator method
    public void setMaximunValue(int max)
    {
        maximunValue = max;
    }
    
    //mutator method
    public void setMinimunValue(int min)
    {
        minimunValue = min;
    }    
    
    
}
