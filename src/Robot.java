public class Robot {

    private int xPos;
    private int yPos;
    private int id;
    private static int identity = 0;

    public Robot(int x, int y) {
        this.xPos = x;
        this.yPos = y;
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

    public String toString() {
        return "Robot " + this.id + " is at position (" + this.xPos + ", " +
                this.yPos + ").";
    }

    public static void main(String[] args) {
        Robot d1 = new Robot(5,1);
        Robot d2 = new Robot(3,6);
        Robot d3 = new Robot(1,10);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }

}
