package view_controler.text;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The view for the text-based display of interacting with the Sudoku Solver
 */
public class TextView {

    private TextController controller;

    private ArrayList<int[][]> goalConfigs;

    public TextView() {
        controller = new TextController();
    }

    public void begin() {
        System.out.println("Hello, Welcome to the Sudoku solver");
        System.out.println("You will now enter your game board. You will do " +
                "this line by line.");
        System.out.println("Please enter the first row of your board with " +
                "each number (separated by spaces or not). represent an " +
                "unknown with an underscore, and press enter.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String line = scanner.nextLine();
        while (true) {
            if (!controller.parseLine(line)) {
                System.out.println("ERROR: make sure you entered 9 digits " +
                        "separated by" +
                        " one or less space.");
            }
            System.out.println("The board you entered so far:");
            printBoard(controller.getBoard());
            if (controller.boardHasBeenEntered()) {
                controller.determineStart();
                System.out.println("Your board is complete. Enter \"y\" for " +
                        "print process output or enter anything else for just" +
                        " the answer(s)");
                line = scanner.nextLine();
                goalConfigs = controller.backtrack(line);
                if (goalConfigs == null) {
                    System.out.println("Sorry, unfortunately the " +
                            "configuration you entered is not solvable");
                }
                else {
                    System.out.print("Your valid configuration" +
                            (goalConfigs.size() == 1 ? " is:" : "s are:") +
                            "\n");
                    for (int[][] intList : goalConfigs) {
                        System.out.println();
                        printBoard(intList);
                    }
                }
                System.exit(0);
            }
            System.out.println("Please enter the next line");
            line = scanner.nextLine();
            System.out.print("> ");
        }
    }

    private void printBoard(int[][] board) {
        int counter = controller.getCounter();
        for (int i = 0; i < 9; i++) {
            System.out.println();
            if (i % 3 == 0 && i != 0) {
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("  ");
                }
                if (board[i][j] == 0) {
                    if (counter <= i) {
                        System.out.print("?");
                    } else {
                        System.out.print("_");
                    }
                }
                else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
