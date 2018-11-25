import java.util.Scanner;

public class Input
{
    public Input()
    {
    
    }
    
    //read user input and return
    public String input(String words)
    {    
        System.out.println(words);
        Scanner console = new Scanner(System.in);        
        return console.nextLine();
    }
}
