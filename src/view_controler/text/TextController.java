package view_controler.text;

import model.Model;

import java.util.ArrayList;

/**
 * things
 */
public class TextController {

    private Model model;

    public TextController() {
        this.model = new Model();
    }

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

    public ArrayList<int[][]> backtrack(String debugging) {
        model.setPrint(debugging);
        model.backtrack();
        return model.getPossibleConfigs();
    }

    public boolean boardHasBeenEntered() {
        return model.getCounter() == 9;
    }

    public void determineStart() {
        model.determineStart();
    }

    public int[][] getBoard() {
        return model.getBoard();
    }

    public int getCounter() {
        return model.getCounter();
    }
}
