import java.util.ArrayList;

public class MazeUtilities {
    private static int currX;
    private static int currY;
    public void addRoadBlock(String[][] maze, Fork fork) {
        if (fork.getLastDirection().equals("North")) {
            maze[fork.getForkx() - 1][fork.getForky()] = "#";
        }
        else if (fork.getLastDirection().equals("East")) {
            maze[fork.getForkx()][fork.getForky() + 1] = "#";
        }
        else if (fork.getLastDirection().equals("South")) {
            maze[fork.getForkx() +1][fork.getForky()] = "#";
        }
        else if (fork.getLastDirection().equals("West")) {
            maze[fork.getForkx()][fork.getForky() - 1] = "#";
        }
    }

    public void printMaze(String[][] maze) {
        for (String[] row: maze) {
            for (String col: row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public void move(String direction) {
        if (direction.equals("North")) {
            currX --;
        }
        else if (direction.equals("East")) {
            currY ++;
        }
        else if (direction.equals("South")) {
            currX ++;
        }
        else if (direction.equals("West")) {
            currY --;
        }
    }

    public String[][] solveMaze(String[][] maze) {
        String[][] editedMaze = new String[maze.length][maze[0].length];
        for (int row = 0; row < maze.length; row ++) {
            for (int col = 0; col < maze[0].length; col ++) {
                editedMaze[row][col] = maze[row][col];
            }
        }
        ArrayList<Fork> forks = new ArrayList<Fork>();
        MazeUtilities utilities = new MazeUtilities();
        currY = 0;
        currX = 0;
        String lastDirection = "South";
        while (!(currX == editedMaze.length - 1 && currY == editedMaze[0].length - 1)) {
            ArrayList<String> directions = checkSurrounding(editedMaze, currX, currY, lastDirection);
            String direction = "";
            //If there are multiple directions there is a fork
            if (directions.size() > 1) {
                Fork fork = new Fork(currX, currY, directions);
                forks.add(fork);
                direction = fork.getDirection();
            }

            //If there is only one direction just go there
            else if (directions.size() == 1) {
                direction = directions.get(0);
            }

            //If directions is empty, no area to go to
            else {
                Fork fork1 = forks.get(forks.size() - 1);
                //Remove fork1 from the list and remove that path in the maze
                if (fork1.isEmpty()) {
                    forks.remove(forks.size() - 1);
                    Fork fork2 = forks.get(forks.size() - 1);
                    utilities.addRoadBlock(editedMaze, fork2);
                    currX = fork2.getForkx();
                    currY = fork2.getForky();
                    direction = fork2.getDirection();
                }
                else {
                    utilities.addRoadBlock(editedMaze, fork1);
                    currX = fork1.getForkx();
                    currY = fork1.getForky();
                    direction = fork1.getDirection();
                }
            }
            move(direction);
            lastDirection = direction;
        }
        return editedMaze;
    }

    public void printSinglePathSolution(String[][] maze) {
        String lastDirection = "South";
        currY = 0;
        currX = 0;
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";
        while (!(currX == maze.length - 1 && currY == maze[0].length - 1)) {
            maze[currX][currY] = GREEN + "V" + RESET;
            System.out.print("(" + currX + ", " + currY + ") ----> ");
            ArrayList<String> directions = checkSurrounding(maze, currX, currY, lastDirection);
            String direction = directions.get(0);
            lastDirection = direction;
            move(direction);
        }
        maze[currX][currY] = GREEN + "V" + RESET;
    }
    public static ArrayList<String> checkSurrounding(String[][] maze, int x, int y, String lastDirection) {
        ArrayList<String> directions = new ArrayList<String>();
        if (lastDirection.contains("North")) {
            //move north
            addNorth(x, y, directions, maze);

            //move east
            addEast(x, y, directions, maze);

            //move west
            addWest(x, y, directions, maze);
        }

        if (lastDirection.contains("East")) {
            //move north
            addNorth(x, y, directions, maze);

            //move east
            addEast(x, y, directions, maze);

            //move south
            addSouth(x, y, directions, maze);
        }

        if (lastDirection.contains("South")) {
            //move east
            addEast(x, y, directions, maze);

            //move south
            addSouth(x, y, directions, maze);

            //move west
            addWest(x, y, directions, maze);
        }

        if (lastDirection.contains("West")) {
            //move north
            addNorth(x, y, directions, maze);

            //move south
            addSouth(x, y, directions, maze);

            //move west
            addWest(x, y, directions, maze);
        }
        return directions;
    }

    public static void addNorth(int x, int y, ArrayList<String> directions, String[][] maze) {
        if (!(x == 0)) {
            if (maze[x-1][y].equals(".")) {
                directions.add("North");
            }
        }
    }

    public static void addEast(int x, int y, ArrayList<String> directions, String[][] maze) {
        if (!(y == maze[0].length - 1)) {
            if (maze[x][y+1].equals(".")) {
                directions.add("East");
            }
        }
    }

    public static void addWest(int x, int y, ArrayList<String> directions, String[][] maze) {
        if (!(y == 0)) {
            if (maze[x][y-1].equals(".")) {
                directions.add("West");
            }
        }
    }

    public static void addSouth(int x, int y, ArrayList<String> directions, String[][] maze) {
        if (!(x == maze.length - 1)) {
            if (maze[x+1][y].equals(".")) {
                directions.add("South");
            }
        }
    }
}
