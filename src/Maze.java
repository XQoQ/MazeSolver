import java.util.ArrayList;

public class Maze {
    private int currentRow;
    private int currentCol;
    private int[] previousPoint;
    private String[][] maze;
    private ArrayList<int[]> pathPoints;

    public Maze(String[][] maze) {
        this.maze = maze;
        this.currentRow = 0;
        this.currentCol = 0;
        this.previousPoint = new int[]{currentRow, currentCol};
        this.pathPoints = new ArrayList<int[]>();
        pathPoints.add(new int[] {0, 0});
    }

    public boolean canGoNorth() {
        try {
            if (maze[currentRow - 1][currentCol].equals(".") && ((currentRow - 1) != previousPoint[0])) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return false;
    }

    public boolean canGoEast() {
        try {
            if (maze[currentRow][currentCol + 1].equals(".") && (currentCol + 1) != previousPoint[1]) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return false;
    }

    public boolean canGoSouth() {
        try {
            if (maze[currentRow + 1][currentCol].equals(".") && (currentRow + 1) != previousPoint[0]) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return false;
    }

    public boolean canGoWest() {
        try {
            if (maze[currentRow][currentCol - 1].equals(".") && currentCol - 1 != previousPoint[1]) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return false;
    }

    public boolean isMazeSolved() {
        return (pathPoints.get(pathPoints.size() - 1)[0] == maze.length - 1) && !(pathPoints.get(pathPoints.size() - 1)[1] == maze[maze.length - 1].length - 1);
    }

    public void printSolution() {
        String str = "";
        for (int[] pathPoint : pathPoints) {
            str += "(" + pathPoint[0] + ", " + pathPoint[1] + ") ---> ";
        }

        System.out.println(str);
    }

    public void solveMaze() {
        boolean hasPath;
        boolean isEndReached = false;

        while (!isEndReached) {
            currentRow = 0;
            currentCol = 0;
            previousPoint[0] = currentRow;
            previousPoint[1] = currentCol;
            pathPoints.clear();
            hasPath = true;

            while (hasPath) {
                hasPath = false;

                if (canGoNorth()) {
                    previousPoint[0] = currentRow;
                    previousPoint[1] = currentCol;
                    currentRow -= 1;
                    hasPath = true;
                } else if (canGoEast()) {
                    previousPoint[0] = currentRow;
                    previousPoint[1] = currentCol;
                    currentCol += 1;
                    hasPath = true;
                } else if (canGoSouth()) {
                    previousPoint[0] = currentRow;
                    previousPoint[1] = currentCol;
                    currentRow += 1;
                    hasPath = true;
                } else if (canGoWest()) {
                    previousPoint[0] = currentRow;
                    previousPoint[1] = currentCol;
                    currentCol -= 1;
                    hasPath = true;
                }


            }

            if (isMazeSolved()) {
                isEndReached = true;
            } else {
                maze[currentRow][currentCol] = "#";
            }

        }

        printSolution();

    }
}