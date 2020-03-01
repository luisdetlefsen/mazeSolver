
import java.text.NumberFormat;
import java.util.*;

public class Solver {

    enum DIRECTION {EAST, SOUTH, WEST, NORTH}

    private List<String> solutions;
    private int iterations;

    public Solver() {

    }

    public String[] solve(Maze maze) {
        iterations = 0;
        solutions = new ArrayList<>();
        final Stack<String> pathToExit = new Stack<>();
        final int exitCell = maze.getExitSpace();
        final int h = maze.getHeight();
        final int w = maze.getWidth();
        final int startCell = maze.getStartSpace();
        final int maxSteps = maze.getMaxMoves();
        walk(maze, exitCell, 0, maxSteps, startCell, pathToExit, h, w, exitCell);

        System.out.println("Total iterations: " + NumberFormat.getNumberInstance(Locale.US).format(iterations));
        return solutions.toArray(new String[0]);
    }

    private void addCurrentPathToSolution(final Stack<String> pathToExit) {
        final Stack<String> tmp = (Stack<String>) pathToExit.clone();
        final List<String> ls = tmp.subList(0, tmp.size());
        Collections.reverse(ls);
        final String result = String.join(", ", ls);
        solutions.add("[" + result + "]");
    }

    private boolean canFindExitBeforeMaxSteps(final int currentCell, final int mazeHeight, final int mazeWidth, final int exitCell, final int currentStepCount, final int maxSteps, final int startCell, final int pathToExitSize) {
        int xi = currentCell / mazeHeight;
        int yi = currentCell / mazeWidth;
        int xf = exitCell / mazeHeight;
        int yf = exitCell / mazeWidth;

        int minStepsRemainingToExit = Math.abs(xf - xi) + Math.abs(yf - yi);

        if ((startCell == currentCell && pathToExitSize > 1) || (minStepsRemainingToExit + currentStepCount > maxSteps + 1)) {
            return false;
        }

        if (currentStepCount >= maxSteps) {
            return false;
        }
        return true;
    }

    private void walk(final Maze maze, final int currentCell, final int currentStepCount, final int maxSteps, final int exitCell, final Stack<String> pathToExit, final int mazeHeight, final int mazeWidth, final int startCell) {
        iterations++;
        pathToExit.push(String.valueOf(currentCell));

        if (exitCell == currentCell && currentStepCount <= maxSteps) {
            addCurrentPathToSolution(pathToExit);
//            final Stack<String> tmp = (Stack<String>) pathToExit.clone();
//            final List<String> ls = tmp.subList(0, tmp.size());
//            Collections.reverse(ls);
//            final String result = String.join(", ", ls);
//            solutions.add("[" + result + "]");
        }

        if (!canFindExitBeforeMaxSteps(currentCell, mazeHeight, mazeWidth, exitCell, currentStepCount, maxSteps, startCell, pathToExit.size())) {
            pathToExit.pop();
            return;
        }
//
//        int xi = currentCell / mazeHeight;
//        int yi = currentCell / mazeWidth;
//        int xf = exitCell / mazeHeight;
//        int yf = exitCell / mazeWidth;
//
//        int minStepsRemainingToExit = Math.abs(xf - xi) + Math.abs(yf - yi);
//
//        if ((startCell == currentCell && pathToExit.size() > 1) || (minStepsRemainingToExit + currentStepCount > maxSteps + 1)) {
//            pathToExit.pop();
//            return;
//        }
//
//        if (currentStepCount >= maxSteps) {
//            pathToExit.pop();
//            return;
//        }

        for (DIRECTION direction : DIRECTION.values()) {
            int newPos = -1;
            switch (direction) {
                case EAST:
                    newPos = maze.moveEast(currentCell);
                    break;
                case SOUTH:
                    newPos = maze.moveSouth(currentCell);
                    break;
                case WEST:
                    newPos = maze.moveWest(currentCell);
                    break;
                case NORTH:
                    newPos = maze.moveNorth(currentCell);
                    break;
            }

            if (newPos != currentCell) {
                walk(maze, newPos, currentStepCount + 1, maxSteps, exitCell, pathToExit, mazeHeight, mazeWidth, startCell);
            }
        }

        if (!pathToExit.empty()) {
            pathToExit.pop();
        }
    }
}