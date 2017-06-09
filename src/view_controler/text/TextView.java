package view_controler.text;

import java.util.Scanner;

/**
 * The view for the text-based display of interacting with the Sudoku Solver
 */
public class TextView {

    public TextController controler;

    public TextView() {
        controler = new TextController(this);
    }

    public void begin() {
        System.out.println("Hello, Welcome to the Sudoku solver");
        System.out.println("You will now enter your game board. You will do " +
                "this line by line.");
        System.out.println("Please enter the first row of your board with " +
                "each number separated by a space, represent an unknown with " +
                "an underscore, and press enter.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("")) {
            if (!controler.parseLine(line)) {
                System.out.println("make sure you entered 9 digits separated by " +
                        "one or less space.");
            }
            System.out.println("The board you entered so far:");
            printBoard();
            System.out.println("Please enter the next line");
            line = scanner.nextLine();
        }
    }

    private void printBoard() {
        int[][] board = controler.getBoard();
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
                    System.out.print("?");
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
