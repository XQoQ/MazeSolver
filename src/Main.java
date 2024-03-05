import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String[][] m1 = getMaze("data/m1");
        int[] startPoint = {0, 0};
        System.out.println(solveMaze(m1, startPoint));
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
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

    public static String solveMaze(String[][] maze, int[] startPointCord) {
        ArrayList<int[]> pathPoints = new ArrayList<int[]>();

        boolean hasPath;
        boolean hasGone;
        boolean isEndReached = false;

        int currentRow = startPointCord[0];
        int currentCol = startPointCord[1];
        int[] currentPointInMaze = {currentRow, currentCol};
        int[] previousPointInMaze = {currentPointInMaze[0], currentPointInMaze[1]};

        while (!isEndReached) {
            currentRow = 0;
            currentCol = 0;
            currentPointInMaze[0] = currentRow;
            currentPointInMaze[1] = currentCol;
            previousPointInMaze[0] = currentRow;
            previousPointInMaze[1] = currentCol;
            pathPoints.clear();
            hasPath = true;

            while (hasPath) {
                hasPath = false;
                hasGone = true;
                int[] pp = {currentRow, currentCol};
                pathPoints.add(pp);

                try {
                    if (hasGone && maze[currentRow - 1][currentCol].equals(".") && ((currentRow - 1) != previousPointInMaze[0])) {
                        previousPointInMaze[0] = currentRow;
                        previousPointInMaze[1] = currentCol;
                        currentRow -= 1;
                        hasPath = true;
                        hasGone = false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    currentRow = currentRow;
                }
                try {
                    if (hasGone && maze[currentRow][currentCol + 1].equals(".") && (currentCol + 1) != previousPointInMaze[1]) {
                        previousPointInMaze[0] = currentRow;
                        previousPointInMaze[1] = currentCol;
                        currentCol += 1;
                        hasPath = true;
                        hasGone = false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    currentRow = currentRow;
                }
                try {
                    if (hasGone && maze[currentRow + 1][currentCol].equals(".") && (currentRow + 1) != previousPointInMaze[0]) {
                        previousPointInMaze[0] = currentRow;
                        previousPointInMaze[1] = currentCol;
                        currentRow += 1;
                        hasPath = true;
                        hasGone = false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    currentRow = currentRow;
                }
                try {
                    if (hasGone && maze[currentRow][currentCol - 1].equals(".") && currentCol - 1 != previousPointInMaze[1]) {
                        previousPointInMaze[0] = currentRow;
                        previousPointInMaze[1] = currentCol;
                        currentCol -= 1;
                        hasPath = true;
                        hasGone = false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    currentRow = currentRow;
                }

                currentPointInMaze[0] = currentRow;
                currentPointInMaze[1] = currentCol;
            }

            if (!(pathPoints.get(pathPoints.size() - 1)[0] == maze.length - 1) && !(pathPoints.get(pathPoints.size() - 1)[1] == maze[maze.length - 1].length - 1)) {
                maze[currentRow][currentCol] = "#";
            } else {
                isEndReached = true;
            }

        }

        String str = "";
        for (int i = 0; i < pathPoints.size(); i++) {
            str += "(" + pathPoints.get(i)[0] + ", " + pathPoints.get(i)[1] + "), ";
        }

        return str;
    }
}










