import java.util.*;

public class RobotInterface {

    private Scanner s;
    private RobotArena curArena;
    private ConsoleCanvas canvas;

    public RobotInterface() {
        s = new Scanner(System.in);
        curArena = new RobotArena(20,6);

        char ch = ' ';
        do {

            System.out.print("Enter (A)dd Robot, Get (I)nformation, (D)isplay" +
                    " Arena, (M)ove all Robots, or E(x)it  >> ");
            ch = s.nextLine().charAt(0);
            switch (ch) {
                case 'A' :
                case 'a' :
                    curArena.addRobot();
                    break;
                case 'I' :
                case 'i' :
                    System.out.println("\n" + curArena.toString());
                    break;
                case 'd' :
                case 'D' :
                    doDisplay();
                    break;
                case 'M' :
                case 'm' :
                    curArena.moveAllRobots();
                    doDisplay();
                    break;
                case 'x' :
                    ch = 'X';
                    break;
            }

        } while (ch != 'X');

        s.close();

    }

    public void doDisplay() {
        this.canvas = new ConsoleCanvas(curArena.getXAxis(), curArena.getYAxis(), "YS015995");
        this.curArena.showRobots(canvas);
        System.out.println(this.canvas.toString());
    }

    public static void main(String[] args) {
        RobotInterface inter = new RobotInterface();
    }

}
