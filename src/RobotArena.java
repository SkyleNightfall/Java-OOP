import java.util.*;

public class RobotArena {

    private int xAxis;
    private int yAxis;
    ArrayList<Robot> manyRobots;
    private Random randomGenerator;
    private int ArenaNum;
    private static int ArenaCount = 0;

    public RobotArena(int x, int y) {
        this.xAxis = x;
        this.yAxis = y;
        this.manyRobots = new ArrayList<>();
        this.randomGenerator = new Random();
        this.ArenaNum = ++ArenaCount;
    }

    public int getXAxis() {
        return this.xAxis;
    }

    //
    public int getYAxis() {
        return this.yAxis;
    }

    public void addRobot() {

        //initialises variables and assign random values
        int xVal = randomGenerator.nextInt(this.xAxis);
        int yVal = randomGenerator.nextInt(this.yAxis);

        if (this.manyRobots.size() != 0) {
            while (isHere(xVal,yVal)) {
                xVal = randomGenerator.nextInt(this.xAxis);
                yVal = randomGenerator.nextInt(this.yAxis);
            }
        }

        this.manyRobots.add(new Robot(xVal,yVal));
    }

    public boolean isHere(int sx, int sy) {
        return getRobotAt(sx,sy) != null;
    }

    public Robot getRobotAt(int x, int y) {

        for (Robot r : this.manyRobots) {
            if (r.getXPos() == x && r.getYPos() == y) {
                return r;
            }
        }
        return null;

    }

    public String toString() {

        /*  creates a string with base information about the Arena, then loops through
            the ArrayList to print out the details of each robot inside     */
        String fin = "Arena " + this.ArenaNum + " is an Arena that is " + this.xAxis + " by "
                + this.yAxis + " characters with " + this.manyRobots.size() + " robots. \n";
        for (Robot b : this.manyRobots) fin += b.toString() + "\n";

        return fin;
    }

    public static void main(String[] args) {
        RobotArena a = new RobotArena(20,5);
        a.addRobot();
        a.addRobot();
        a.addRobot();
        a.addRobot();
        a.addRobot();
        System.out.println(a.toString());
    }

}
