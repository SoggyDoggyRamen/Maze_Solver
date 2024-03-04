import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String[][] maze = getMaze("data/maze");
        int currX = 0;
        int currY = 0;
        ArrayList<Fork> forks = new ArrayList<Fork>();
        String lastDirection = "South";
        while (!(currX == maze.length - 1 && currY == maze[0].length - 1)) {
            System.out.print("(" + currX + ", " + currY + ") ---->");
            ArrayList<String> directions = checkSurrounding(maze, currX, currY, lastDirection);
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
                    if (fork2.getForkx() < fork1.getForkx()) {
                        maze[fork2.getForkx() - 1][fork2.getForky()] = "#";
                    }
                    if (fork2.getForkx() > fork1.getForkx()) {
                        maze[fork2.getForkx() + 1][fork2.getForky()] = "#";
                    }
                    if (fork2.getForky() < fork1.getForky()) {
                        maze[fork2.getForkx()][fork2.getForky() + 1] = "#";
                    }
                    if (fork2.getForky() > fork1.getForky()) {
                        maze[fork2.getForkx()][fork2.getForky() - 1] = "#";
                    }
                    currX = fork2.getForkx();
                    currY = fork2.getForky();
                    direction = fork2.getDirection();
                }
                else {
                    currX = fork1.getForkx();
                    currY = fork1.getForky();
                    direction = fork1.getDirection();
                }
            }
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
            lastDirection = direction;
        }
        for (String[] row: maze) {
            for (String col: row) {
                System.out.print(col);
            }
            System.out.println();
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