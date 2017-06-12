package model;

import java.util.ArrayList;
import java.util.Arrays;

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
    }

    public void addRow(int[] row) {
        board[counter] = row;
        counter += 1;
    }

    public ArrayList<int[][]> backtrack(int[][] config) {
        if (isGoal(config)) {
            possibleConfigs.add(config);
        }
        else {
            for (int i = 1; i < 10; i++) {
                config[currRow][currColumn] = i;
                backtrack(config);
            }

        }
        return null;
    }

    private boolean isValid(int[][] config) {
        return false;
    }

    private boolean isGoal(int[][] config) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; i++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean determineStart() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    currRow = i;
                    currColumn = j;
                    copyBoard();
                    return true;
                }
            }
        }
        return false;
    }

    private void copyBoard() {
        startingBoard = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            startingBoard[i] = Arrays.copyOf(board[i], 9);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCounter() {
        return counter;
    }
}
