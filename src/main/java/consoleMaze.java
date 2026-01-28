public class consoleMaze {
    public static void main(String[] args) {
        Maze maze = new Maze(new String[15][15]);
        while (true) {
            Movement.move(maze);
        }
    }
}
