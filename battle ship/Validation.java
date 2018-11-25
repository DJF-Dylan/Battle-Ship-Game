
/**
 * Write a description of class Validation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Validation
{
    public Validation()
    {

    }

    // check the length between min and max
    public boolean checkNameLength(String name, int minLength, int maxLength)
    {
        if(name.length() == 0)
        {
            System.out.println("Name cannot be blank.");
            return true;
        }
        if((name.length() >= minLength) && (name.length() <= maxLength))
        {
            return false;
        }else{
            System.out.println("Name should between 3 and 15 characters long.");
            return true;
        }
    }
    
    //check if the user input is number
    public  boolean isNumeric(String check,String str) 
    {
        if(check.isEmpty())
        {
            System.out.println(str + " cannot be empty.");
            return false;
        }
        for (int i = 0; i < check.length(); i++) 
        {                       
            if (!Character.isDigit(check.charAt(i))) 
            {
                System.out.println(str + " must be a number.");
                return false;
            }
        }
        return true;
    }
    
    //limit the range of the number user input
    public boolean numberRange(String inputstr, int minLength, int maxLength,String str)
    {
        int input= Integer.parseInt(inputstr);
        if((input >= minLength) && (input <= maxLength))
        {
            return true;
        }else{
            System.out.println(str + " should between " + minLength + " and " + maxLength + ".");
            return false;
        }
    }
}
