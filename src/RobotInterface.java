import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RobotInterface {

    private Scanner s;
    private RobotArena curArena;
    private ConsoleCanvas canvas;

    public RobotInterface() {
        s = new Scanner(System.in);

        char ch;
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
                    if (arenaStatus()) {
                        System.out.print("Please name your file >> ");
                        String name = s.nextLine();
                        save(name);
                        s = new Scanner(System.in);
                    }
                    break;
                case 'L' :
                case 'l' :
                    try {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setMultiSelectionEnabled(true);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files",
                                "txt");
                        fileChooser.setFileFilter(filter);

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File[] selected = fileChooser.getSelectedFiles();
                            load(selected[0]);
                        } else {
                            System.out.println("\nInvalid Selection. Resetting.\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    s = new Scanner(System.in);
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

    public void save(String fileN) {
        try {
            FileOutputStream saving = new FileOutputStream(new File(fileN + ".txt"));

            String data = curArena.getXAxis() + ", " + curArena.getYAxis() + "\n";
            saving.write(data.getBytes());

            for (Robot r : curArena.manyRobots) {
                data = r.getXPos() + ", " + r.getYPos() + ", " + r.getDirection() + "\n";
                saving.write(data.getBytes());
            }

            saving.flush();
            saving.close();

            System.out.println();

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void load(File fileN) {
        try {
            Scanner fileScan = new Scanner(fileN);

            if (!fileScan.hasNextLine()) {
                System.out.println("Data is missing. Something went wrong. Resetting.");
            } else {
                String cur = fileScan.nextLine();
                String[] curA = cur.split(", ");
                this.curArena = new RobotArena(Integer.parseInt(curA[0]), Integer.parseInt(curA[1]));

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
