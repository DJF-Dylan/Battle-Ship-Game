
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{    
    private ShipList playerShips;
    private ShipList computerShips;

    public Game()
    {
        playerShips = new ShipList();
        computerShips = new ShipList();
    }
    
    public Game(ShipList player, ShipList computer)
    {
        playerShips = player;
        computerShips = computer;
    }
    
    //check setting file in case of wrong spelling or wrong format
    public void checkSettingFile(String[] setting)
    {
        try
        {
            int grid = Integer.parseInt(setting[0]);
            boolean multiHit = Boolean.parseBoolean(setting[1]);
            boolean visible = Boolean.parseBoolean(setting[2]);
            int no = Integer.parseInt(setting[3]);
            if (no > grid * grid)
            {
                System.out.print("The number of ship is too large.");
                System.exit(0);
            }
        }catch(Exception e)
        {
            System.out.println("Setting File Format is wrong. Please rewrite and try again.");
            System.exit(0);
        }

    }

    //clean screen
    public void cleanScreen()
    {
        System.out.print('\f');
    }

    //computer use random number to attack player. If the ship distory, distoried ship's name will be return.
    public String computerAttack(int grid)
    {
        Input in = new Input();
        Validation va = new Validation();
        CoordinateGenerator co = new CoordinateGenerator();
        String shipname = null;
        System.out.println("Computer to make a guess\n");
        grid--;
        int x = co.randomNumber(0,grid);
        int y = co.randomNumber(0,grid);
        System.out.println("Computer x guess " + x);
        System.out.println("Computer y guess " + y + "\n");
        if(playerShips.attack(x,y))
            shipname = playerShips.getShip(x,y).getShipName();
        return shipname;
    }
    
    //create conputer ships
    public void createComputerShips(int grid,int no,boolean mHit)
    {
        System.out.println("Loading computer settings:");
        Input in = new Input();
        Validation va = new Validation();
        CoordinateGenerator co = new CoordinateGenerator();
        for(int i = 0; i < no;i++)
        {
            String name ="ship" + (i+1);
            int x;
            int y;
            do{           
                x = co.randomNumber(0,grid - 1);
                y = co.randomNumber(0,grid - 1);
            }while(computerShips.checkPos(x, y));
            int hn;
            int hm = 0;            
            if (mHit)            
                hn = co.randomNumber(1,5);
            else
                hn = 1;
            computerShips.addShip(name,x,y,hm,hn);    
        }
        System.out.println("Computer settings generated!");
    }
    
    // create player ships
    public void createPlayerShips(int grid,int no,boolean mHit)
    {
        System.out.println("Loading player settings:");
        Input in = new Input();
        Validation va = new Validation();
        CoordinateGenerator co = new CoordinateGenerator();
        for(int i = 0; i < no;i++)
        {
            System.out.println("Please enter the details for the " + (i + 1) + " ship:");
            String name;
            do{
                name = in.input("Ship name:");
            }while(va.checkNameLength(name,3,15));                                
            String XPos;
            String YPos;
            int x;
            int y;
            do
            {   
                int[] xy = inputXYPos(grid);
                x = xy[0];
                y = xy[1];
                if(playerShips.checkPos(x, y))
                    System.out.println("This position has already has a ship.");
            }while(playerShips.checkPos(x, y));
            int hn;
            int hm = 0;            
            if (mHit)            
                hn = co.randomNumber(1,5);
            else
                hn = 1;
            playerShips.addShip(name,x,y,hm,hn);    
        }        
    }
   
    //main method to play the game
    public void gameStart()
    {
        FileIO file = new FileIO();
        Input input = new Input();
        int playerScore = 0;
        int computerScore = 0;
        int round = 1;
        boolean distoryed = false;
        String csname = null;
        String psname = null;
        String[] settings = file.getFile("gamesettings.txt");
        checkSettingFile(settings);
        int grid = Integer.parseInt(settings[0]);
        boolean multiHit = Boolean.parseBoolean(settings[1]);
        boolean visible = Boolean.parseBoolean(settings[2]);
        int no = Integer.parseInt(settings[3]);        
        welcome(grid,multiHit,visible,no);
        input.input("Press any key to continue...");
        createPlayerShips(grid, no, multiHit);
        createComputerShips(grid, no, multiHit);
        input.input("Press any key to continue...");
        while(playerShips.ifShipDistoryed() && computerShips.ifShipDistoryed())
        {
            cleanScreen();
            System.out.println("Beginning Round " + round);
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println("ENEMY SHIPS ONLY SHOWN WHEN DEMO MODE IS ON!!");
            System.out.println("Displaying the Player Grid");
            printVisibleGrid(grid, playerShips);
            System.out.println(" ");
            splitLine(40,"-");
            System.out.println(" ");
            System.out.print("Displaying the Computer Grid");
            if(visible)
                printVisibleGrid(grid, computerShips);
            else
                printInvisibleGrid(grid, computerShips);
            System.out.println(" ");
            if (csname != null)
            {
                System.out.println("Unfortunately,computer " + csname + " has been destroyed!");
                csname = null;
            }
            if (psname != null)
            {
                System.out.println("Unfortunately,computer " + psname + " has been destroyed!");
                psname = null;
            }
            System.out.println(" ");
            csname = playerAttack(grid);
            psname = computerAttack(grid);
            playerScore = computerShips.countScore();
            computerScore = playerShips.countScore();
            input.input("Press any key to continue...");
            round++;
        }
        String winner = "";
        if(playerShips.ifShipDistoryed() == false)
        {
            System.out.println("Unfortunately!! Computer Win!");
            winner = "Computer";
        }else if(computerShips.ifShipDistoryed() == false)
        {
            System.out.println("Congratulations!! Player Win!!");
            winner = "Player";
        }
        String output =  winner + " wins. Final Score Player (" + playerScore + ") and Computer (" + computerScore + ")";
        file.setFile("gameoutcome.txt",output);
    }
    
    //access method
    public ShipList getComputerShips()
    {
        return computerShips;
    }
    
    //access method
    public ShipList getPlayerShip()
    {
        return playerShips;
    }
    
    //Validation of XY position. It will return valid XY position.
    public int[] inputXYPos(int grid)
    {   Input in = new Input();
        Validation va = new Validation();
        boolean flag =  true;               
        String XPos;
        String YPos;
        grid--;
        do
        {
            flag =  true;                  
            XPos = in.input("Ship x Position (0 - " + grid + " )");
            if(va.isNumeric(XPos,"Ship x Position"))
            {   
                if(va.numberRange(XPos, 0, grid, "Ship x Position"))
                    flag = false;
            }
        }while(flag);     
        do
        {
            flag =  true;                  
            YPos = in.input("Ship y Position (0 - " + grid + " )");
            if(va.isNumeric(YPos,"Ship y Position"))
            {   
                if(va.numberRange(YPos, 0, grid, "Ship y Position"))
                    flag = false;
            }
        }while(flag);
        int x = Integer.parseInt(XPos);
        int y = Integer.parseInt(YPos);
        int[] xy = {x,y};
        return xy;
    }
    
    //play input XY position to attack computer. If the ship distory, distoried ship's name will be return.
    public String playerAttack(int grid)
    {
        Input in = new Input();
        Validation va = new Validation();
        String shipname = null;
        System.out.println("Player to make a guess");
        int[] xy = inputXYPos(grid);
        int x = xy[0];
        int y = xy[1];
        if(computerShips.attack(x,y))
            shipname = computerShips.getShip(x,y).getShipName();
        return shipname;
    }
    
    //print the grid and ships are invisible
    public void printInvisibleGrid(int grid, ShipList a)
    {
        for(int i = 0; i < grid; i++)
        {
            System.out.println(" ");
            for(int j = 0; j < grid; j++)
            {   
                if(a.checkPos(j,i) && a.printShipState(j,i) != "O")
                    System.out.print(a.printShipState(j,i));
                else
                    System.out.print("~");
            }            
        }
    }
   
    //print the grid and ships are visible
    public void printVisibleGrid(int grid, ShipList a)
    {
        for(int i = 0; i < grid; i++)
        {
            System.out.println(" ");
            for(int j = 0; j < grid; j++)
            {   
                if(a.checkPos(j,i))
                    System.out.print(a.printShipState(j,i));
                else
                    System.out.print("~");
            }
        }
    }
   
    //mutator method
    public void setComputerShip(ShipList list)
    {
        computerShips = list;
    }
    
    //mutator method
    public void setPlayerShip(ShipList list)
    {
        playerShips = list;
    }
    
    //print a line with specific mark
    public void splitLine(int length,String type)
    {
        for(int i = 0; i < length; i++)
        {
            System.out.print(type);
        }
    }

    //print welcome message
    public void welcome(int grid,boolean multiHit,boolean visible,int no)
    {
        System.out.print("+");
        splitLine(73,"=");
        System.out.print("+\n|");
        splitLine(73," ");
        System.out.print("|\n|");
        splitLine(14," ");
        System.out.print("Welcome to the Battleship Game -- With a Twist!!");
        System.out.print("           |\n|");
        splitLine(73," ");
        System.out.print("|\n+");
        splitLine(73,"=");
        System.out.println("+");
        System.out.println("The game will use the grid size defined in the settings file.");
        System.out.println("Playing grid size set as (" + grid + " x " + grid +")");
        System.out.println("Maximum number of ships allowed as " + no);
        System.out.println("Multiple hits allowed per ships set as " + multiHit);
        if (visible)
            System.out.println("Computer Ships Visible : ON");
        else
            System.out.println("Computer Ships Visible : OFF");       
    }  
}
