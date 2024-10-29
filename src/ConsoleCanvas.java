public class ConsoleCanvas {

    private int xAxis;
    private int yAxis;
    private String[][] Arena;
    private String stuID;

    public ConsoleCanvas(int x, int y, String ID) {
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

    public void showIt(int x, int y, char robot) {
        this.Arena[y + 1][x + 1] = "" + robot;
    }

    public String toString() {

        String fin = "";

        for (int i = 0; i < this.yAxis; i++) {
            for (int j = 0; j < this.xAxis; j++) {
                String cur = this.Arena[i][j];
                if (cur != null) {
                    fin += cur;
                } else {
                    fin += " ";
                }
            }
            fin += "\n";
        }

        return fin;
    }

    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas(20,5,"YS015995");
        c.showIt(4,3,'R');
        System.out.println(c.toString());
    }

}
