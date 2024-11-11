import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RobotInterface {

    //initializing all the variables
    private Scanner s;
    private RobotArena curArena;
    private ConsoleCanvas canvas;

    public RobotInterface() {
        s = new Scanner(System.in);

        char ch;
        do {

            //printing out the input options on the terminal
            System.out.print("Enter (N)ew Arena, (S)ave Arena, (L)oad Arena, (A)dd Robot, Get" +
                    " (I)nformation, (D)isplay Arena, (M)ove all Robots, Simulate all (R)obots," +
                    " or E(x)it  >> ");

            ch = s.nextLine().charAt(0);
            switch (ch) {
                //create a new arena
                case 'N' :
                case 'n' :
                    System.out.print("\nEnter the size of the X-Axis >> ");
                    int x = s.nextInt();    //retrieves inputted arena width
                    System.out.print("Enter the size of the Y-Axis >> ");
                    int y = s.nextInt();    //retrieves inputted arena height
                    curArena = new RobotArena(x,y);     //creates the arena based on inputted values
                    s = new Scanner(System.in);     //reset the scanner
                    System.out.print("\n");
                    break;
                //save the current arena
                case 'S' :
                case 's' :
                    if (arenaStatus()) {
                        System.out.print("Please name your file >> ");
                        String name = s.nextLine();     //retrieve the inputted file name
                        save(name);     //saves file under the inputted name
                        s = new Scanner(System.in);     //reset the scanner
                    }
                    break;
                //load an arena
                case 'L' :
                case 'l' :
                    try {
                        //create filter to only accept text files (.txt)
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setMultiSelectionEnabled(true);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files",
                                "txt");
                        fileChooser.setFileFilter(filter);

                        //checks if the selected file is the correct format and loads. Rejects if otherwise.
                        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            load(fileChooser.getSelectedFile());
                        } else {
                            System.out.println("\nInvalid Selection. Resetting.\n");
                        }
                    } catch (Exception e) {     //error handling
                        e.printStackTrace();
                    }
                    s = new Scanner(System.in);     //reset the scanner
                    break;
                //add a robot to the arena
                case 'A' :
                case 'a' :
                    if (arenaStatus()) {
                        curArena.addRobot();        //adds a new robot into the arena
                    }
                    break;
                //get information about the arena
                case 'I' :
                case 'i' :
                    if (arenaStatus()) {
                        System.out.println("\n" + curArena);        //displays the arena information
                    }
                    break;
                //display the arena
                case 'd' :
                case 'D' :
                    if (arenaStatus()) {
                        doDisplay();        //displays the arena in a canvas
                    }
                    break;
                //move all the robots once
                case 'M' :
                case 'm' :
                    if (arenaStatus()) {
                        simulate(1);
                    }
                    break;
                //move all the robots 10 times, showing animation
                case 'R' :
                case 'r' :
                    if (arenaStatus()) {
                        simulate(10);
                    }
                    break;
                //terminate the program
                case 'x' :
                    ch = 'X';
                    break;
                //if none of the above options are inputted
                default :
                    System.out.println("\nInvalid Input. Please Try Again.\n");
                    break;
            }
        } while (ch != 'X');

        s.close();

    }

    /**
     * Function to display the arena with all of its information onto the terminal
     */
    public void doDisplay() {
        this.canvas = new ConsoleCanvas(curArena.getXAxis(), curArena.getYAxis(), "YS015995");
        this.curArena.showRobots(canvas);
        System.out.println("\n" + this.canvas);
    }

    /**
     * Function to move the robots in the arena x times
     * @param x     number of actions the robot performs
     */
    public void simulate (int x) {
        for (int i = 0; i < x; i++) {
            this.curArena.moveAllRobots();      //move the robots
            doDisplay();        //display the updated arena in the terminal
            try {
                TimeUnit.MILLISECONDS.sleep(200);       //pause for 200 milliseconds before continuing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function to check if an Arena currently exists / has been initialised
     */
    public boolean arenaStatus() {
        if (this.curArena == null) {
            System.out.println("\nPlease Create or Load a Arena.\n");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Function to save the current Arena with all of its contents into a file
     * @param fileN     the name of the file that the Arena would be saved into
     */
    public void save(String fileN) {
        try {
            //create the file to be saved into
            FileOutputStream saving = new FileOutputStream(new File(fileN + ".txt"));

            //save the size of the arena
            String data = curArena.getXAxis() + ", " + curArena.getYAxis() + "\n";
            saving.write(data.getBytes());

            //save the information of each robot in the file
            for (Robot r : curArena.manyRobots) {
                data = r.getXPos() + ", " + r.getYPos() + ", " + r.getDirection() + "\n";
                saving.write(data.getBytes());
            }

            //finalise and close the newly created file
            saving.flush();
            saving.close();

            System.out.println();

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    /**
     * Function to load an Arena and all of its robots from an inputted file
     * @param fileN     the file that contains the Arena data
     */
    public void load(File fileN) {
        try {
            Scanner fileScan = new Scanner(fileN);

            //ensures that the file is not empty
            if (!fileScan.hasNextLine()) {
                System.out.println("Data is missing. Something went wrong. Resetting.");
            } else {
                //creates the arena based on the data in the file
                String cur = fileScan.nextLine();
                String[] curA = cur.split(", ");
                this.curArena = new RobotArena(Integer.parseInt(curA[0]), Integer.parseInt(curA[1]));

                //creates and add all the robots based on data in the file
                while (fileScan.hasNext()) {
                    cur = fileScan.nextLine();
                    curA = cur.split(", ");
                    this.curArena.addRobot(Integer.parseInt(curA[0]), Integer.parseInt(curA[1]),
                            stringToDir(curA[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("File was not found. Resetting to Menu.");
        }
    }

    /**
     * Function to convert cardinal directions from String form to eNum form
     * @param dir   cardinal direction in String form
     */
    public Robot.Direction stringToDir(String dir) {
        if (dir.equals("NORTH")) {
            return Robot.Direction.NORTH;
        } else if (dir.equals("EAST")) {
            return Robot.Direction.EAST;
        } else if (dir.equals("SOUTH")) {
            return Robot.Direction.SOUTH;
        } else {
            return Robot.Direction.WEST;
        }
    }

    public static void main(String[] args) {
        RobotInterface inter = new RobotInterface();
    }

}
