package view_controler.text;

import model.Model;

/**
 * Created by abacef on 6/2/17.
 */
public class TextController {

    private TextView view;

    private Model model;

    public TextController(TextView view) {
        this.view = view;
        this.model = new Model();
    }

    public boolean parseLine(String line) {
        int[] lineArray = new int[9];
        int index = 0;
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character != ' ') {
                if (character == '_') {
                    lineArray[index] = 0;
                }
                else {
                    lineArray[index] = Character.getNumericValue(character);
                }
                index += 1;
            }
        }
        if (index != 9) {
            return false;
        }
        model.addRow(lineArray);
        return true;
    }

    public int[][] getBoard() {
        return model.getBoard();
    }
}
