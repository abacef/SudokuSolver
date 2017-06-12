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
        if (isGoal()) {
            possibleConfigs.add(copyBoard());
        }
        else {
            advance();
            for (int i = 1; i < 10; i++) {
                board[currRow][currColumn] = i;
                if (isValid()) {
                    backtrack();
                }
            }
        }
    }

    private boolean isValid() {
        int num = board[currRow][currColumn];
        for (int i = 0; i < board[currRow].length; i++) {
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

    public boolean determineStart() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    currRow = i;
                    currColumn = j;
                    startingBoard = copyBoard();
                    return true;
                }
            }
        }
        return false;
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

    public static void main(String[] args) {
        Model model = new Model();
        int[] a;
        a = new int[] {5, 8, 6, 3, 7, 4, 9, 1, 2};
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                model.currRow = i;
                model.currColumn = j;
                if (!model.isValid()) {
                    System.out.println(false);
                }
            }
        }
        System.out.println(true);
    }
}
