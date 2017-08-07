package view_controler.text;

import model.Model;

/**
 * This class interacts with the view by returning stuff from the model
 */
public class TextController {

    /** an instance of the current model to call methods on */
    private Model model;

    public TextController() {
        this.model = new Model();
    }

    /**
     * does the work of parsing a line of user-entered text
     * @param line a string of a line to the game board that the user entered
     * @return if the string is in the correct format to be added to the game
     * board
     */
    public boolean parseLine(String line) {
        int[] lineArray = new int[9];
        int index = 0;
        for (int i = 0; i < line.length(); i++) {
            if (index == 9) {
                return false;
            }
            char character = line.charAt(i);
            if (character != ' ') {
                if (character == '_') {
                    lineArray[index] = 0;
                }
                else {
                    int entry = Character.getNumericValue(character);
                    if (entry == -1) {
                        return false;
                    }
                    lineArray[index] = entry;
                }
                index += 1;
            }
        }
        if (index < 9) {
            return false;
        }
        model.addRow(lineArray);
        return true;
    }

    /**
     * Calls the backtracking algorithm
     * @param debugging if the user wants debugging or not
     * @return the winning configuration or lack thereof
     */
    public int[][] backtrack(String debugging) {
        model.setPrint(debugging);
        model.backtrack();
        return model.getWinConfig();
    }

    /**
     * @return if the counter is on the first imajinary line of the game
     * board, therefore the other lines have been entered
     */
    public boolean boardHasBeenEntered() {
        return model.getCounter() == 9;
    }

    /** @return the current board configuration from the model */
    public int[][] getBoard() {
        return model.getBoard();
    }

    /** @return the counter from the model */
    public int getCounter() {
        return model.getCounter();
    }
}
