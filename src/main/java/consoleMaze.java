public class consoleMaze {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.mazeGenerator();
        maze.printMaze();
    }
}

class Maze{
    String[][] maze;
    public void mazeGenerator(){
        maze = new String[17][17];
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                if ((i == 0 || i == maze.length-1) || (j == 0 || j == maze[i].length-1)) {
                    maze[i][j] = "#";
                    continue;
                }
                maze[i][j] = " ";
            }
        }
    }
    public void printMaze(){
        for (int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}
