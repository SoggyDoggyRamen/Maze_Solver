import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String[][] maze = getMaze("data/maze");
        int currx = 0;
        int curry = 0;
        int lastForkx = 0;
        int lastForky = 0;
        String lastDirection = "";
        String[] lastdirectionNotTaken = new String[2];
        ArrayList<String> directions = checkSurrounding(maze, currx, curry, "South");
        if (direction.size() == 2) {
            lastDirection = directions.get(0);
            lastDirectionNotTaken =
        }
        while (!(currx == maze.length)) {
            String direction = checkSurrounding(maze, currx, curry, String )
        }

    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }


    public static ArrayList<String> checkSurrounding(String[][] maze, int x, int y, String lastDirection) {
        ArrayList<String> directions = new ArrayList<String>();
        if (lastDirection.contains("North")) {
            //move north
            if (!(x == 0)) {
                if (maze[x-1][y].equals(".")) {
                    directions.add("North");
                }
                else if (!(maze[x-1][y].equals("#"))) {
                    directions.add("North" + maze[x - 1][y]);
                }
            }

            //move east
            if (!(y == maze[0].length - 1)) {
                if (maze[x][y+1].equals(".")) {
                    directions.add("East");
                }
                else if (!(maze[x][y+1].equals("#"))) {
                    directions.add("East" + maze[x][y+1]);
                }
            }

            //move west
            if (!(y == 0)) {
                if (maze[x][y-1].equals(".")) {
                    directions.add("West");
                }
                else if (!(maze[x][y-1].equals("#"))) {
                    directions.add("West" + maze[x][y-1]);
                }
            }
        }

        if (lastDirection.contains("East")) {
            //move north
            if (!(x == 0)) {
                if (maze[x-1][y].equals(".")) {
                    directions.add("North");
                }
                else if (!(maze[x-1][y].equals("#"))) {
                    directions.add("North" + maze[x - 1][y]);
                }
            }

            //move east
            if (!(y == maze[0].length - 1)) {
                if (maze[x][y+1].equals(".")) {
                    directions.add("East");
                }
                else if (!(maze[x][y+1].equals("#"))) {
                    directions.add("East" + maze[x][y+1]);
                }
            }

            //move south
            if (!(x == maze.length - 1)) {
                if (maze[x+1][y].equals(".")) {
                    directions.add("South");
                }
                else if (!(maze[x+1][y].equals("#"))) {
                    directions.add("South" + maze[x+1][y]);
                }
            }
        }

        if (lastDirection.contains("South")) {
            //move east
            if (!(y == maze[0].length - 1)) {
                if (maze[x][y+1].equals(".")) {
                    directions.add("East");
                }
                else if (!(maze[x][y+1].equals("#"))) {
                    directions.add("East" + maze[x][y+1]);
                }
            }

            //move south
            if (!(x == maze.length - 1)) {
                if (maze[x+1][y].equals(".")) {
                    directions.add("South");
                }
                else if (!(maze[x+1][y].equals("#"))) {
                    directions.add("South" + maze[x+1][y]);
                }
            }

            //move west
            if (!(y == 0)) {
                if (maze[x][y-1].equals(".")) {
                    directions.add("West");
                }
                else if (!(maze[x][y-1].equals("#"))) {
                    directions.add("West" + maze[x][y-1]);
                }
            }
        }

        if (lastDirection.contains("West")) {
            //move north
            if (!(x == 0)) {
                if (maze[x-1][y].equals(".")) {
                    directions.add("North");
                }
                else if (!(maze[x-1][y].equals("#"))) {
                    directions.add("North" + maze[x - 1][y]);
                }
            }

            //move south
            if (!(x == maze.length - 1)) {
                if (maze[x+1][y].equals(".")) {
                    directions.add("South");
                }
                else if (!(maze[x+1][y].equals("#"))) {
                    directions.add("South" + maze[x+1][y]);
                }
            }

            //move west
            if (!(y == 0)) {
                if (maze[x][y-1].equals(".")) {
                    directions.add("West");
                }
                else if (!(maze[x][y-1].equals("#"))) {
                    directions.add("West" + maze[x][y-1]);
                }
            }
        }

        return directions;
    }

}