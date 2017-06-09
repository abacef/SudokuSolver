package model;

/**
 * Created by abacef on 6/2/17.
 */
public class Model {

    private int[][] board;

    private int counter;

    public Model() {
        board = new int[9][9];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
        counter = 0;
    }

    public void addRow(int[] row) {
        board[counter] = row;
        counter += 1;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCounter() {
        return counter;
    }
}
