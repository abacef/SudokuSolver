package view_controler.text;

import java.util.Scanner;

/**
 * Created by abacef on 6/2/17.
 */
public class TextView {

    public TextController controler;

    public void begin() {
        controler = new TextController(this);
        System.out.println("Hello, Welcome to the Sudoku solver");
        System.out.println("You will now enter your game board. You will do " +
                "this line by line.");
        System.out.println("Please enter the first row of your board with " +
                "each number separated by a space, represent an unknown with " +
                "an underscore, and press enter.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("")) {
            controler.parseLine(line);
            line = scanner.nextLine();
        }
    }
}
