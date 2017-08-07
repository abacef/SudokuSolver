package view_controler.GUI;

/**
 * Created by abacef on 7/16/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

public class GUIView extends Application {

    /** A reference to the controller */
    private GUIController controller;

    /** The array of text fields to be able to easily reference */
    private TextField[][] boxArray;

    /**
     * The method that sets everything up for the user to start using. Sets
     * up all the text fields for entering the numbers, gives some text
     * instructions, and gives 2 buttons. One to quit, and one to solve.
     *
     * @param primaryStage The given basic stage
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new GUIController(new Model());
        primaryStage.setTitle("Sudoku Solver");
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(40));
        Text header = new Text("Welcome to the Sudoku Solver" +
                ". Enter your current puzzle with empty boxes for unknowns, " +
                "then press solve.");
        header.setWrappingWidth(25 * 9 + 20 * 8);
        mainBox.getChildren().add(header);
        VBox index1 = new VBox(20);
        HBox index2;
        boxArray = new TextField[9][9];
        TextField currBox;
        for (int i = 0; i < 9; i++) {
            index2 = new HBox(20);
            for (int j = 0; j < 9; j++) {
                currBox = new TextField();
                currBox.setPrefWidth(22);
                currBox.setPrefHeight(22);
                boxArray[i][j] = currBox;
                index2.getChildren().add(currBox);
            }
            index1.getChildren().add(index2);
        }
        mainBox.getChildren().add(index1);

        HBox buttonBox = new HBox(50);
        Button solve = new Button("Solve");
        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.parseBoxes(boxArray);
            }
        });
        Button quit = new Button("Quit");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        buttonBox.getChildren().add(quit);
        buttonBox.getChildren().add(solve);
        mainBox.getChildren().add(buttonBox);
        primaryStage.setScene(new Scene(mainBox));
        primaryStage.show();
    }

    /**
     * Runs the program
     *
     * @param args Argument passed into the program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
