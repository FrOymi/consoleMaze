public class consoleMaze {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.createMaze();
        maze.printMaze();
    }
}

class Maze{
    String[][] maze;

    public void createMaze() {
        mazeGenerator();
        String[][] playingBoard = new String[maze.length][maze[0].length];
        for (int i = 0; i < playingBoard.length; i++) {
            for (int j = 0; j < playingBoard[0].length; j++) {
                if (i == 0 || i == playingBoard[0].length - 1 || j == 0 || j == playingBoard[0].length - 1) {
                    playingBoard[i][j] = "#";
                    continue;
                }
                playingBoard[i][j] = maze[i - 1][j - 1];
            }
        }
        playingBoard[1][0] = "S";
        playingBoard[playingBoard.length-2][playingBoard[0].length-1] = "E";
        maze = playingBoard;
    }

    void mazeGenerator(){
        maze = new String[15][15];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                double randomDouble = Math.random();
                if (i == 0) {
                    if (j == 0 || (randomDouble < 0.5)) {
                        maze[i][j] = " ";
                    } else if (randomDouble > 0.5) {
                        maze[i][j] = maze[i][j-1].equals(" ") ? "#" : " ";
                    }
                } else if (j == 0 || j == maze[i].length - 1) {
                    if (randomDouble < 0.5) {
                        maze[i][j] = " ";
                    } else if (randomDouble > 0.5) {
                        if (j == 0) {
                            maze[i][j] = maze[i-1][j].equals(" ") && maze[i-1][j + 1].equals(" ") ? "#" : " ";
                        } else {
                            maze[i][j] = maze[i-1][j].equals(" ") && i != maze[i].length - 1 ? "#" : " ";
                        }
                    }
                } else {
                    if (randomDouble < 0.5) {
                        if (i != maze.length - 1 && maze[i - 1][j + 1].equals(" ")) {
                            maze[i][j] = (maze[i][j-1].equals(" ") && maze[i-1][j-1].equals(" ") && maze[i-1][j].equals(" ")) ? "#" : " ";
                        } else {
                            maze[i][j] = " ";
                        }
                    }  else if (randomDouble > 0.5) {
                        maze[i][j] = maze[i-1][j+1].equals(" ") && (maze[i][j-1].equals(" ") || maze[i-1][j-1].equals(" ") || maze[i-1][j].equals(" ")) ? "#" : " ";
                    }
                }
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
