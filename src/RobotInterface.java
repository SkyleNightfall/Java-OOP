import java.util.*;
import java.util.concurrent.TimeUnit;

public class RobotInterface {

    private Scanner s;
    private RobotArena curArena;
    private ConsoleCanvas canvas;

    public RobotInterface() {
        s = new Scanner(System.in);

        char ch = ' ';
        do {

            System.out.print("Enter (N)ew Arena, (S)ave Arena, (L)oad Arena, (A)dd Robot, Get" +
                    " (I)nformation, (D)isplay Arena, (M)ove all Robots, Simulate all (R)obots," +
                    " or E(x)it  >> ");

            ch = s.nextLine().charAt(0);
            switch (ch) {
                case 'N' :
                case 'n' :
                    System.out.print("\nEnter the size of the X-Axis >> ");
                    int x = s.nextInt();
                    System.out.print("Enter the size of the Y-Axis >> ");
                    int y = s.nextInt();
                    curArena = new RobotArena(x,y);
                    s = new Scanner(System.in);
                    System.out.print("\n");
                    break;
                case 'S' :
                case 's' :
                    save();
                    break;
                case 'L' :
                case 'l' :
                    load();
                    break;
                case 'A' :
                case 'a' :
                    if (arenaStatus()) {
                        curArena.addRobot();
                    }
                    break;
                case 'I' :
                case 'i' :
                    if (arenaStatus()) {
                        System.out.println("\n" + curArena);
                    }
                    break;
                case 'd' :
                case 'D' :
                    if (arenaStatus()) {
                        doDisplay();
                    }
                    break;
                case 'M' :
                case 'm' :
                    if (arenaStatus()) {
                        simulate(1);
                    }
                    break;
                case 'R' :
                case 'r' :
                    if (arenaStatus()) {
                        simulate(10);
                    }
                    break;
                case 'x' :
                    ch = 'X';
                    break;
                default :
                    System.out.println("\nInvalid Input. Please Try Again.\n");
                    break;
            }
        } while (ch != 'X');

        s.close();

    }

    public void doDisplay() {
        this.canvas = new ConsoleCanvas(curArena.getXAxis(), curArena.getYAxis(), "YS015995");
        this.curArena.showRobots(canvas);
        System.out.println("\n" + this.canvas);
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

    public boolean arenaStatus() {
        if (this.curArena == null) {
            System.out.println("\nPlease Create or Load a Arena.\n");
            return false;
        } else {
            return true;
        }
    }

    public void save() {

    }

    public void load() {

    }

    public static void main(String[] args) {
        RobotInterface inter = new RobotInterface();
    }

}
