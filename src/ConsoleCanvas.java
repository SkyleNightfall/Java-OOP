public class ConsoleCanvas {

    //initializes all variables needed for ConsoleCanvas
    private int xAxis;
    private int yAxis;
    private String[][] Arena;
    private String stuID;

    /**
     * Function to initialize ConsoleCanvas
     * @param x     width of the Arena being displayed
     * @param y     height of the Arena being displayed
     * @param ID    student ID being placed within the border
     */
    public ConsoleCanvas(int x, int y, String ID) {
        //adjusting the arena to contain the border and assign values to the variables
        this.xAxis = x+2;
        this.yAxis = y+2;
        Arena = new String[this.yAxis][this.xAxis];
        this.stuID = ID;

        //create the border of the arena
        for (int i = 1; i < this.xAxis-1; i++) {
            this.Arena[0][i] = "#";
            this.Arena[this.yAxis-1][i] = "#";
        }
        for (int i = 0; i < this.yAxis; i++) {
            this.Arena[i][0] = "#";
            this.Arena[i][this.xAxis-1] = "#";
        }

        //insert the Student ID onto the top of the border
        int temp = ((this.xAxis) - stuID.length())/2;
        for (int i = 0; i < stuID.length(); i++) {
            this.Arena[0][temp + i] = "" + stuID.charAt(i);
        }
    }

    /**
     * Function to display robots within the canvas
     * @param x     x coordinate of the displayed robot
     * @param y     y coordinate of the displayed robot
     * @param robot     character representing the displayed robot
     */
    public void showIt(int x, int y, char robot) {
        this.Arena[y + 1][x + 1] = "" + robot;
    }

    /**
     * Function to print out the entirety of the canvas
     */
    public String toString() {

        String fin = "";

        for (int i = 0; i < this.yAxis; i++) {
            for (int j = 0; j < this.xAxis; j++) {
                if (this.Arena[i][j] != null) {
                    fin += this.Arena[i][j];
                } else {
                    fin += " ";
                }
            }
            fin += "\n";
        }

        return fin;
    }

}
