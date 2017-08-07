package view_controler.GUI;

import javafx.scene.control.TextField;
import model.Model;

import java.util.ArrayList;

/**
 * Created by abacef on 6/2/17.
 */
public class GUIController {

    private Model model;

    public GUIController(Model model) {
        this.model = model;
    }

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
