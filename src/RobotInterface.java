import java.util.*;
import java.util.concurrent.TimeUnit;

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
                    " Arena, (M)ove all Robots, (S)imulate the Robots, or E(x)it  >> ");
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
                    simulate(1);
                    break;
                case 'S' :
                case 's' :
                    simulate(10);
                    break;
                case 'x' :
                    ch = 'X';
                    break;
                default :
                    System.out.println("Invalid Input. Please Try Again.");
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

    public void simulate (int times) {
        for (int i = 0; i < times; i++) {
            this.curArena.moveAllRobots();
            doDisplay();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RobotInterface inter = new RobotInterface();
    }

}
