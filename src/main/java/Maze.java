public class Maze {
    private static String[][] maze;

    Maze(String[][] maze) {
        this.maze = maze;
        createMaze();
    }

    public static String[][] getMaze() {
        return maze;
    }

    void printMaze(String[][] mazeInRealTime) {
        for (int i = 0; i < mazeInRealTime.length; i++) {
            for(int j = 0; j < mazeInRealTime[i].length; j++){
                System.out.print(mazeInRealTime[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void createMaze() {
        mazeGenerator();
        String[][] playingBoard = new String[maze.length + 2][maze[0].length + 2];
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

    private void mazeGenerator(){
        /*maze = new String[15][15];*/
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                double randomDouble = Math.random();
                if (i == 0) { // Initialize the first maze row, avoiding adjacent wall placement
                    if (j == 0 || (randomDouble < 0.5)) {
                        maze[i][j] = " ";
                    } else if (randomDouble > 0.5) {
                        maze[i][j] = maze[i][j-1].equals(" ") ? "#" : " ";
                    }
                } else if (j == 0 || j == maze[i].length - 1) { // // For the first and last maze columns, avoid adjacent walls to prevent isolated blocks
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
                    if (randomDouble < 0.5) { // When generating paths, try to avoid creating 2x2  blocks
                        if (i != maze.length - 1 && maze[i - 1][j + 1].equals(" ")) {
                            maze[i][j] = (maze[i][j-1].equals(" ") && maze[i-1][j-1].equals(" ") && maze[i-1][j].equals(" ")) ? "#" : " ";
                        } else {
                            maze[i][j] = " ";
                        }
                    }  else if (randomDouble > 0.5) { // When placing walls, check for an available path
                        // to prevent the maze from becoming unsolvable
                        maze[i][j] = maze[i-1][j+1].equals(" ") && (maze[i][j-1].equals(" ") || maze[i-1][j-1].equals(" ") || maze[i-1][j].equals(" ")) ? "#" : " ";
                    }
                }
            }
        }
    }
}
