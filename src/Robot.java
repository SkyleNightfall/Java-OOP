public class Robot {

    enum Direction {NORTH, EAST, WEST, SOUTH}
    private int xPos;
    private int yPos;
    private int id;
    private Direction go;
    private static int identity = 0;

    public Robot(int x, int y, Direction direction) {
        this.xPos = x;
        this.yPos = y;
        this.go = direction;
        this.id = ++identity;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getID() {
        return id;
    }

    public boolean isHere(int sx, int sy) {
        return (sx == this.xPos && sy == this.yPos);
    }

    public void displayRobot (ConsoleCanvas c) {
        c.showIt(this.xPos, this.yPos,'R');
    }

    public Direction getDirection() {
        return this.go;
    }

    public void tryToMove(RobotArena a) {
        //calculate new distance
        int tempX = this.xPos;
        int tempY = this.yPos;

        switch (this.go) {
            case NORTH -> tempY--;
            case EAST -> tempX++;
            case WEST -> tempX--;
            case SOUTH -> tempY++;
        }

        if (a.canMoveHere(tempX,tempY)) {
            this.xPos = tempX;
            this.yPos = tempY;
        } else {
            this.go = nextDir();
        }
    }

    public Direction nextDir() {
        switch (this.go) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            default: return Direction.NORTH;
        }
    }

    public String toString() {
        return "Robot " + this.id + " is at position (" + this.xPos + ", " +
                this.yPos + "). It is moving " + this.go.toString() + ".";
    }

    public static void main(String[] args) {
        /*Robot d1 = new Robot(5,1);
        Robot d2 = new Robot(3,6);
        Robot d3 = new Robot(1,10);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);*/
    }

}
