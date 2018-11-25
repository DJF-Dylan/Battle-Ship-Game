import java.io.*;
import java.util.Scanner;
public class FileIO
{
    private String fileName;

    public FileIO()
    {
        fileName = "";
    }

    public FileIO(String name)
    {
        fileName = name;
    }     

    //read settings file and return the settings
    public String[] getFile(String name)
    {
        fileName = name;
        try
        {        
            FileReader inputFile = new FileReader(fileName);
            Scanner input = new Scanner(inputFile);
            String contend = input.nextLine();
            String[] file = contend.split(",");
            inputFile.close();
            return file;
        }
        catch(IOException e)
        {
            System.out.print(e.toString());
            System.exit(1);
        }
        return null;
    }

    //access method
    public String getFileName()
    {
        return fileName;
    }
    
    //write the game result in the output file
    public void setFile(String name,String message)
    {
        fileName = name;
        
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            outputFile.println(message);
            outputFile.close();
        }
        catch(IOException e) 
        {
            System.out.print(e.toString());
            System.exit(1);
        }
    }
    
    //mutator method
    public void setFileName(String name)
    {
        fileName = name;
    }
}
