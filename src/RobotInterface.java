import java.util.*;

public class RobotInterface {

    private Scanner s;
    private RobotArena curArena;

    public RobotInterface() {
        s = new Scanner(System.in);
        curArena = new RobotArena(20,6);

        char ch = ' ';
        do {

            System.out.print("Enter (A)dd Robot, Get (I)nformation, or E(x)it  >> ");
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
                case 'x' :
                    ch = 'X';
                    break;
            }

        } while (ch != 'X');

        s.close();

    }

    public static void main(String[] args) {
        RobotInterface inter = new RobotInterface();
    }

}
