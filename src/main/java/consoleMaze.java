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
        maze = new String[15][15];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                double randomDouble = Math.random();
                if ((i == 0 && j == 0) || (randomDouble < 0.5)) {
                    maze[i][j] = " ";
                } else if (randomDouble > 0.5){
                    if (j > 0) {
                        maze[i][j] = maze[i][j-1].equals(" ") ? "#" : " ";
                    } else {
                        maze[i][j] = " ";
                    }

                }
            }
        }


//        for(int i = 0; i < maze.length; i++){
//            for(int j = 0; j < maze[i].length; j++){
//                if ((i == 0 || i == maze.length-1) || (j == 0 || j == maze[i].length-1)) {
//                    maze[i][j] = "#";
//                    continue;
//                }
//                maze[i][j] = " ";
//            }
//        }
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
