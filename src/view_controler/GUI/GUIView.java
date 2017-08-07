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

    private Model model;

    private GUIController controller;

    private TextField[][] boxArray;

    @Override
    public void start(Stage primaryStage) {
        model = new Model();
        controller = new GUIController(model);
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

    public static void main(String[] args) {
        launch(args);
    }
}
