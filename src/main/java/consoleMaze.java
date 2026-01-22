import java.util.Scanner;

public class consoleMaze {
    public static void main(String[] args) {
        Maze maze = new Maze(new String[15][15]);

        int[] coordinates = {1, 0};
        String player = "@";

        Scanner sc = new Scanner(System.in);
        while (true){
            String[][] original = maze.getMaze();
            String[][] mazeInRealTime = new String[original.length][];
            for (int i = 0; i < original.length; i++) {
                mazeInRealTime[i] = original[i].clone();
            }

            mazeInRealTime[coordinates[0]][coordinates[1]] = player;
            try {
                printMaze(mazeInRealTime);
                System.out.print("Enter your move (w a s d): ");
                String move = sc.nextLine();
                if (move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d")){
                    switch (move){
                        case "w":
                            if (mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals(" ") || mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals("S") || mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals("E")){
                                coordinates[0] = coordinates[0] - 1;
                            } else {
                                throw new IllegalArgumentException("Illegal move");
                            }
                            break;
                        case "a":
                            if (mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals(" ") || mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals("S") || mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals("E")){
                                coordinates[1] = coordinates[1] - 1;
                            } else {
                                throw new IllegalArgumentException("Illegal move");
                            }
                            break;
                        case "s":
                            if (mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals(" ") || mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals("S") || mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals("E")){
                                coordinates[0] = coordinates[0] + 1;
                            } else {
                                throw new IllegalArgumentException("Illegal move");
                            }
                            break;
                        case "d":
                            if (mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals(" ") || mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals("S") || mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals("E")){
                                coordinates[1] = coordinates[1] + 1;
                            } else {
                                throw new IllegalArgumentException("Illegal move");
                            }
                            break;
                    }
                } else {
                    throw new IllegalArgumentException("Illegal move");
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                clear();
                System.out.println("Illegal move");
            } finally {
                clear();
                if (maze.getMaze()[coordinates[0]][coordinates[1]].equals("E")){
                    original = maze.getMaze();
                    mazeInRealTime = new String[original.length][];
                    for (int i = 0; i < original.length; i++) {
                        mazeInRealTime[i] = original[i].clone();
                    }
                    mazeInRealTime[coordinates[0]][coordinates[1]] = player;
                    printMaze(mazeInRealTime);
                    System.out.println("You Win!");
                    break;
                }
            }
        }
    }

    static void printMaze(String[][] mazeInRealTime){
        for (int i = 0; i < mazeInRealTime.length; i++) {
            for(int j = 0; j < mazeInRealTime[i].length; j++){
                System.out.print(mazeInRealTime[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Maze{
    String[][] maze;

    Maze(String[][] maze) {
        this.maze = maze;
        createMaze();
    }

    public String[][] getMaze() {
        return maze;
    }

    void createMaze() {
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

    void mazeGenerator(){
        /*maze = new String[15][15];*/
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
}
