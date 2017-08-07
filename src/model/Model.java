package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * The backend goodness that both the text based and the GUI application
 * share. This is the internal state representation of the puzzle which also
 * holds the components to backtrack (solve) when needed to.
 */
public class Model {

    /** The game board to work with */
    private int[][] board;

    /** The game board that the user entered. Used as a reference to not
     * change those cells on the new game board */
    private int[][] startingBoard;

    /** A reference to how many lines of the game board have been entered */
    private int counter;

    /** The winning configuration */
    private int[][] winConfig;

    /** The current row that the backtracker is modifying */
    private int currRow;

    /** The current column that the backtracker is modifying */
    private int currColumn;

    /** Does the user want to print the process of finding the winConfig? */
    private boolean print;

    public Model() {
        board = new int[9][9];
        counter = 0;
        winConfig = null;
        startingBoard = null;
        currRow = -1;
        currColumn = 8;
        print = false;
    }

    /**
     * Adds a row to the model's internal state
     * @param row the row to add
     */
    public void addRow(int[] row) {
        board[counter] = row;
        counter += 1;
        if (counter == 9) {
            startingBoard = copyBoard();
        }
    }

    /**
     * The algorithm that finds the winning configuration using
     * recursive backtracking.
     */
    public void backtrack() {
        advance();
        if (isGoal()) {
            if (print) {
                System.out.println("\nGOAL!");
            }
            winConfig = copyBoard();
            deadvance();
        }
        else {
            for (int i = 1; i < 10; i++) {
                board[currRow][currColumn] = i;
                if (print) {
                    printThings(i);
                }
                if (isValid()) {
                    backtrack();
                }
            }
            board[currRow][currColumn] = 0;
            deadvance();
        }
    }

    /**
     * @return if the configuration is valid so far. Checks that the row and
     * column does not have the same number in it, then checks the appropriate
     * 3x3 square that the current cell is in.
     */
    private boolean isValid() {
        int num = board[currRow][currColumn];
        for (int i = 0; i < 9; i++) {
            if (i != currColumn && board[currRow][i] == num) {
                return false;
            }
            if (i != currRow && board[i][currColumn] == num) {
                return false;
            }
        }
        return checkSquare();
    }

    /** @return if the current 3x3 square does not contain the same number */
    private boolean checkSquare() {
        int topLeftRow;
        int topLeftCol;
        switch (currRow / 3) {
            case 0:
                topLeftRow = 0;
                break;
            case 1:
                topLeftRow = 3;
                break;
            default:
                topLeftRow = 6;
                break;
        }
        switch (currColumn / 3) {
            case 0:
                topLeftCol = 0;
                break;
            case 1:
                topLeftCol = 3;
                break;
            default:
                topLeftCol = 6;
                break;
        }
        HashSet<Integer> repeat = new HashSet<>();
        boolean b;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b = repeat.add(board[i + topLeftRow][j + topLeftCol]);
                if (board[i + topLeftRow][j + topLeftCol] != 0 && !b) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return is the current cell that the backtracker is checking the first
     * imaginary square? If it is, that means that every cell entered in the
     * board so far has been valid.
     */
    private boolean isGoal() {
        return currRow == 9 && currColumn == 0;
    }

    /**
     * Advances the currColumn and currRow to the next cell. left to right,
     * then top to bottom. Advances again if the starting board comes with an
     * already filled number.
     */
    private void advance() {
        if (currColumn == 8) {
            currColumn = 0;
            currRow++;
        }
        else {
            currColumn++;
        }
        if (!(currRow == 9 && currColumn == 0)) {
            if (startingBoard[currRow][currColumn] != 0) {
                advance();
            }
        }
    }

    /**
     * the opposite of advancing. Deadvances the currColumn and currRow to the
     * next cell. right to left, then bottom to top. Deadvances again if the
     * starting board comes with an already filled number.
     */
    private void deadvance() {
        if (currColumn == 0) {
            if (currRow != 0) {
                currColumn = 8;
                currRow--;
            }
        } else {
            currColumn--;
        }
        if (!(currRow == 0 && currColumn == 0)) {
            if (startingBoard[currRow][currColumn] != 0) {
                deadvance();
            }
        }
    }

    /** @return a copy of the current board */
    private int[][] copyBoard() {
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = Arrays.copyOf(board[i], 9);
        }
        return newBoard;
    }

    /** @return the board */
    public int[][] getBoard() {
        return board;
    }

    /** @return the row counter */
    public int getCounter() {
        return counter;
    }

    /** @return The winning configuration */
    public int[][] getWinConfig() {
        return winConfig;
    }

    /** @return The starting board */
    public int[][] getStartingBoard() {
        return startingBoard;
    }

    /**
     * Sets if the user wants print debugging or not
     * @param yorn A string that could contain "y" or not
     */
    public void setPrint(String yorn) {
        print = yorn.trim().equals("y");
    }

    /**
     * prints a current output of the status of backtracking the board
     * @param i the number just placed into the current cell
     */
    private void printThings(int i) {
        System.out.println("\nTrying:");
        System.out.println("currRow: " + currRow);
        System.out.println("currColumn: " + currColumn);
        System.out.print("Number = " + i);
        printBoard(board);
    }

    /** A copy of the board printing algorithm to use */
    private void printBoard(int[][] beard) {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            if (i % 3 == 0 && i != 0) {
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("  ");
                }
                if (beard[i][j] == 0) {
                    if (counter <= i) {
                        System.out.print("?");
                    } else {
                        System.out.print("_");
                    }
                }
                else {
                    System.out.print(beard[i][j]);
                }
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * tests the model methods. Easier than entering the board row by row.
     * @param args unused parameter
     */
    public static void main(String[] args) {
        Model model = new Model();
        int[] a;
        a = new int[] {0, 0, 5, 9, 0, 0, 0, 7, 6};
        model.addRow(a);
        a = new int[] {0, 4, 0, 3, 5, 0, 0, 2, 0};
        model.addRow(a);
        a = new int[] {0, 0, 8, 0, 0, 0, 0, 0, 0};
        model.addRow(a);
        a = new int[] {4, 0, 0, 7, 2, 0, 0, 0, 0};
        model.addRow(a);
        a = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        model.addRow(a);
        a = new int[] {0, 0, 0, 0, 8, 9, 0, 0, 4};
        model.addRow(a);
        a = new int[] {0, 0, 0, 0, 0, 0, 8, 0, 0};
        model.addRow(a);
        a = new int[] {0, 8, 0, 0, 1, 3, 0, 9, 0};
        model.addRow(a);
        a = new int[] {6, 3, 0, 0, 0, 2, 1, 0, 0};
        model.addRow(a);
        long time = System.currentTimeMillis();
        model.backtrack();
        time = System.currentTimeMillis() - time;
        System.out.println("\nThis is the possible configuration:");
        model.printBoard(model.winConfig);
        System.out.println("took " + time * Math.pow(10, -3) + " seconds");
    }
}
