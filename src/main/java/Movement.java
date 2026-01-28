import java.util.Scanner;

public class Movement extends Player{
    static String move;
    static String[][] mazeInRealTime;

    static void move(Maze maze) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);
        cloneMaze();
        maze.printMaze(mazeInRealTime);
        try {
            System.out.print("Enter your move (w a s d): ");
            move = sc.nextLine();
            if (move.equalsIgnoreCase("w") || move.equalsIgnoreCase("a") || move.equalsIgnoreCase("s") || move.equalsIgnoreCase("d")){
                movePlayer();
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
            clear();
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            clear();
            System.out.println("Illegal move");
        }
        if (Maze.getMaze()[coordinates[0]][coordinates[1]].equals("E")){
            cloneMaze();
            maze.printMaze(mazeInRealTime);
            System.out.println("You Win!");
            System.exit(0);
        }
    }

    private static void movePlayer() throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        switch (move){
            case "w", "W":
                if (mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals(" ") || mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals("S") || mazeInRealTime[coordinates[0] - 1][coordinates[1]].equals("E")){
                    coordinates[0] = coordinates[0] - 1;
                } else {
                    throw new IllegalArgumentException("Illegal move");
                }
                break;
            case "a", "A":
                if (mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals(" ") || mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals("S") || mazeInRealTime[coordinates[0]][coordinates[1] - 1].equals("E")){
                    coordinates[1] = coordinates[1] - 1;
                } else {
                    throw new IllegalArgumentException("Illegal move");
                }
                break;
            case "s", "S":
                if (mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals(" ") || mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals("S") || mazeInRealTime[coordinates[0] + 1][coordinates[1]].equals("E")){
                    coordinates[0] = coordinates[0] + 1;
                } else {
                    throw new IllegalArgumentException("Illegal move");
                }
                break;
            case "d", "D":
                if (mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals(" ") || mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals("S") || mazeInRealTime[coordinates[0]][coordinates[1] + 1].equals("E")){
                    coordinates[1] = coordinates[1] + 1;
                } else {
                    throw new IllegalArgumentException("Illegal move");
                }
                break;
        }
    }

    private static void cloneMaze() {
        mazeInRealTime = new String[Maze.getMaze().length][];
        for (int i = 0; i < mazeInRealTime.length; i++) {
            mazeInRealTime[i] = Maze.getMaze()[i].clone();
        }
        mazeInRealTime[coordinates[0]][coordinates[1]] = player;
    }

    private static void clear() {
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
