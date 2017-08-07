package view_controler.GUI;

import javafx.scene.control.TextField;
import model.Model;

/**
 * The class that mediates between the GUI and the internal state
 */
public class GUIController {

    private Model model;

    /**
     * Constructor to have a reference to access the internal state.
     * @param model the internal state reference to assign.
     */
    public GUIController(Model model) {
        this.model = model;
    }

    /**
     * This method gets called when the solve button is pressed. It builds
     * the internal state, backtracks, and then populates the empty boxes
     * with the appropriate number. It also changes that number to be red
     * just for the user to hae better readability.
     *
     * @param boxes
     */
    public void parseBoxes(TextField[][] boxes) {
        for (TextField[] line : boxes) {
            int[] modelLine = new int[9];
            for (int i = 0; i < line.length; i++) {
                TextField box = line[i];
                if (box.getText().length() > 1) {
                    box.setText(box.getText().substring(box.getText().length()
                            - 1));
                }
                else if (box.getText().equals("")) {
                    box.setText("0");
                }
                modelLine[i] = Integer.parseInt(box.getText());
            }
            model.addRow(modelLine);
        }
        model.backtrack();
        int[][] board = model.getWinConfig();
        int[][] startingBoard = model.getStartingBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxes[i][j].setText(String.valueOf(board[i][j]));
                if (startingBoard[i][j] == 0) {
                    boxes[i][j].setStyle("-fx-text-fill: red;");
                }
            }
        }
    }
}
