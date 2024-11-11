public class Robot {

    //initializing variables needed for Robot and the enum Direction
    enum Direction {NORTH, EAST, WEST, SOUTH}
    private int xPos;
    private int yPos;
    private int id;
    private Direction go;
    private static int identity = 0;

    /**
     * Function to initialize a robot
     * @param x     x coordinate the robot is positioned at
     * @param y     y coordinate the robot is positioned at
     * @param direction     the direction the robot is going to move in
     */
    public Robot(int x, int y, Direction direction) {
        this.xPos = x;
        this.yPos = y;
        this.go = direction;
        this.id = ++identity;
    }

    /**
     * Function to retrieve the x position of the robot
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Function to retrieve the y position of the robot
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Function to check if a robot is at position (x,y)
     * @param sx    x-coordinate being checked
     * @param sy    y-coordinate being checked
     */
    public boolean isHere(int sx, int sy) {
        return (sx == this.xPos && sy == this.yPos);
    }

    /**
     * Function to display a robot on the ConsoleCanvas
     * @param c     ConsoleCanvas the robot is being added to
     */
    public void displayRobot (ConsoleCanvas c) {
        c.showIt(this.xPos, this.yPos,'R');
    }

    /**
     * Function to retrieve the direction the robot is moving
     */
    public Direction getDirection() {
        return this.go;
    }

    /**
     * Function to either move or change the robot's direction based on the arena it is in
     * @param a     Arena that the robot is in
     */
    public void tryToMove(RobotArena a) {
        //calculate new distance
        int tempX = this.xPos;
        int tempY = this.yPos;

        //moves robot based on its direction
        switch (this.go) {
            case NORTH -> tempY--;
            case EAST -> tempX++;
            case WEST -> tempX--;
            case SOUTH -> tempY++;
        }

        //checks if a robot is able to move to its new location
        if (a.canMoveHere(tempX,tempY)) {   //move the robot to its new location
            this.xPos = tempX;
            this.yPos = tempY;
        } else {    //change the direction the robot is moving in
            this.go = nextDir();
        }
    }

    /**
     * Function to change what direction the robot is going to move next
     */
    public Direction nextDir() {
        switch (this.go) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            default: return Direction.NORTH;
        }
    }

    /**
     * Adjusted toString() function that tells the robot's identity, coordinates, and direction
     */
    public String toString() {
        return "Robot " + this.id + " is at position (" + this.xPos + ", " +
                this.yPos + "). It is moving " + this.go.toString() + ".";
    }

}
