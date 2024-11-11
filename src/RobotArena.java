import java.util.*;

public class RobotArena {

    //initialize all variables needed for RobotArena
    private int xAxis;
    private int yAxis;
    ArrayList<Robot> manyRobots;
    private Random randomGenerator;
    private int ArenaNum;
    private static int ArenaCount = 0;

    /**
     * Function to initialize the RobotArena
     * @param x     set the width of the arena
     * @param y     set the height of the arena
     */
    public RobotArena(int x, int y) {
        this.xAxis = x;
        this.yAxis = y;
        this.manyRobots = new ArrayList<>();
        this.randomGenerator = new Random();
        this.ArenaNum = ++ArenaCount;
    }

    /**
     * Function to retrieve the width of the Arena
     */
    public int getXAxis() {
        return this.xAxis;
    }

    /**
     * Function to retrieve the height of the Arena
     */
    public int getYAxis() {
        return this.yAxis;
    }

    /**
     * Function to add a robot into the arena (randomly)
     */
    public void addRobot() {

        //generates random coordinates
        int xVal = randomGenerator.nextInt(this.xAxis);
        int yVal = randomGenerator.nextInt(this.yAxis);

        //continue randomizing the coordinates if a robot already exists at these coordinates
        do {
            xVal = randomGenerator.nextInt(this.xAxis);
            yVal = randomGenerator.nextInt(this.yAxis);
        } while (getRobotAt(xVal,yVal) != null);

        //add these robots into the arena
        this.manyRobots.add(new Robot(xVal, yVal, randomDirection()));
    }

    /**
     * Function to add a robot into the arena (with set coordinates and direction)
     * @param xVal      x-coordinate of the robot
     * @param yVal      y-coordinate of the robot
     * @param direction     Direction that the robot is moving in
     */
    public void addRobot(int xVal, int yVal, Robot.Direction direction) {
        this.manyRobots.add(new Robot(xVal, yVal, direction));
    }

    /**
     * Function to generate a random direction
     */
    public Robot.Direction randomDirection() {
        return Robot.Direction.values()[randomGenerator.nextInt(4)];
    }

    /**
     * Function tp retrieve a robot from an inputted position
     * @param x     x coordinate of the robot being searched for
     * @param y     y coordinate of the robot being searched for
     */
    public Robot getRobotAt(int x, int y) {
        for (Robot r : this.manyRobots) {       //searching for the robot
            if (r.isHere(x,y)) {
                return r;       //retrieves the robot
            }
        }
        return null;        //returns null if there is no robot at this position
    }

    /**
     * Function to check if a robot is able to move to a new location
     * @param xPos      x coordinate after the robot moves
     * @param yPos      y coordinate after the robot moves
     */
    public boolean canMoveHere(int xPos, int yPos) {
        if (xPos >= 0 && xPos < this.xAxis) {       //if the x value is within the arena boundaries
            if (yPos >= 0 && yPos < this.yAxis) {       //if the y value is within the arena boundaries
                if (getRobotAt(xPos,yPos) == null) {        //if a robot does not exist in this new location
                    return true;
                }
            }
        }
        //if any of the above conditions are not met
        return false;
    }

    /**
     * Function to move or change the direction of all robots within an Arena
     */
    public void moveAllRobots() {
        for (Robot r : this.manyRobots) r.tryToMove(this);
    }

    /**
     * Function to display all the robots within the arena into a canvas
     * @param c     canvas the arena is being displayed on
     */
    public void showRobots(ConsoleCanvas c) {
        for (Robot r : this.manyRobots) r.displayRobot(c);
    }

    /**
     * Adjusted toString() function that tells the Arena's identity, size, number of robots, and information
     * about each robot
     */
    public String toString() {

        /*  creates a string with base information about the Arena, then loops through
            the ArrayList to print out the details of each robot inside     */
        String fin = "Arena " + this.ArenaNum + " is an Arena that is " + this.xAxis + " by "
                + this.yAxis + " characters with " + this.manyRobots.size() + " robots. \n";
        for (Robot b : this.manyRobots) fin = fin + b.toString() + "\n";

        return fin;
    }

}
