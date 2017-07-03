package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by abacef on 6/2/17.
 */
public class Model {

    private int[][] board;

    private int[][] startingBoard;

    private int counter;

    private ArrayList<int[][]> possibleConfigs;

    private int currRow;

    private int currColumn;

    private boolean print = true;

    public Model() {
        board = new int[9][9];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
        counter = 0;
        possibleConfigs = new ArrayList<>();
        startingBoard = null;
        currRow = 0;
        currColumn = 0;
    }

    public void addRow(int[] row) {
        board[counter] = row;
        counter += 1;
    }

    public void backtrack() {
        advance();
        if (isGoal()) {
            if (print) {
                System.out.println("\nGOAL!");
            }
            possibleConfigs.add(copyBoard());
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
        boolean b = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b = repeat.add(board[i + topLeftRow][j + topLeftCol]);
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGoal() {
        return currRow == 9 && currColumn == 0;
    }

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

    private void deadvance() {
        if (currColumn == 0) {
            if (currRow != 0) {
                currColumn = 8;
                currRow--;
            }
        }
        else {
            currColumn--;
        }
        if (!(currRow == 0 && currColumn == 0)) {
            if (startingBoard[currRow][currColumn] != 0) {
                deadvance();
            }
        }
    }

    public void determineStart() {
        currRow = -1;
        currColumn = 8;
        startingBoard = copyBoard();
    }

    private int[][] copyBoard() {
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = Arrays.copyOf(board[i], 9);
        }
        return newBoard;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCounter() {
        return counter;
    }

    public ArrayList<int[][]> getPossibleConfigs() {
        return possibleConfigs;
    }

    public void setPrint(String yorn) {
        print = yorn.trim().equals("y");
    }

    private void printThings(int i) {
        System.out.println("\nTrying:");
        System.out.println("currRow: " + currRow);
        System.out.println("currColumn: " + currColumn);
        System.out.print("Number = " + i);
        printBoard(board);
    }

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

    public static void main(String[] args) {
        Model model = new Model();
        int[] a;
        a = new int[] {5, 8, 6, 3, 7, 4, 9, 1, 0};
        model.addRow(a);
        a = new int[] {1, 3, 7, 9, 5, 2, 8, 6, 4};
        model.addRow(a);
        a = new int[] {2, 4, 9, 8, 1, 6, 5, 7, 3};
        model.addRow(a);
        a = new int[] {8, 7, 2, 5, 4, 3, 1, 9, 6};
        model.addRow(a);
        a = new int[] {6, 9, 3, 7, 8, 1, 2, 4, 5};
        model.addRow(a);
        a = new int[] {4, 1, 5, 6, 2, 9, 7, 3, 8};
        model.addRow(a);
        a = new int[] {9, 5, 4, 2, 3, 7, 6, 8, 1};
        model.addRow(a);
        a = new int[] {7, 2, 1, 4, 6, 8, 3, 5, 9};
        model.addRow(a);
        a = new int[] {3, 6, 8, 1, 9, 5, 4, 2, 7};
        model.addRow(a);
        model.determineStart();
        model.backtrack();
        for (int[][] item : model.possibleConfigs) {
            System.out.println("\nThese are the possible configurations:");
            model.printBoard(item);
        }
    }
}
