package view_controler.text;

import java.util.Scanner;

/**
 * Created by abacef on 6/2/17.
 */
public class TextView {

    public TextController controler;

    public void begin() {
        controler = new TextController(this);
        System.out.println("Hello, Welcome to the Sudoku solver\n");
        System.out.println("You will now enter your game board. You will do " +
                "this line by line.\n");
        System.out.println("Please enter the first row of your board and " +
                "press enter.");

        Scanner scanner = new Scanner(System.in);



    }
}
