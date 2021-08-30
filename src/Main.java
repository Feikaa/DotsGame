/**
 * Author: Paul Lee
 * Revised: March 23, 2020
 *
 * Description: Main class. Used as Controller
 */

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @brief A class used as the controller module of the Dots game
 */
public class Main {
    /**
     * @brief Runs the Dots game
     */
    public static void main(String[] args) throws InterruptedException {
        // Initialize game board and display
        dotsBoard board = new dotsBoard();
        displayBoard display = new displayBoard(board);

        // Initialize keyboard input
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        // Ask player if they want the Dots to have colour
        System.out.print("Enable coloured text? Y/N (May not work on every computer): ");
        String input = keyboard.nextLine();
        if (input.toUpperCase().equals("Y") || input.toUpperCase().equals("YES")) {
            System.out.println("Enabling coloured text.");
            display.setDisplayColours(true);
        }
        else if (input.toUpperCase().equals("N") || input.toUpperCase().equals("NO"))
            System.out.println("Disabling coloured text.");
        else
            System.out.println("Unknown input. Disabling coloured text.");

        // Instructions on how to play
        System.out.println("Input Row (left side) first, then Column (right side). Different dots are separated by a space. Ex: 12 22. Match at least 2 of the same colour in a row");
        TimeUnit.SECONDS.sleep(3);

        // Loop until player is out of moves
        while (board.getMovesLeft() > 0) {
            // Display game board
            System.out.println("               Dots");
            display.display();
            // Get player input
            System.out.print("Input: ");
            input = keyboard.nextLine();

            // Do move, and then update board
            board.doMove(input);
            display.update(board);
        }
        // Display one last time and print out game over text
        display.display();
        System.out.println("Out of moves!");
        System.out.println("Game Over! Score: " + board.getScore());
    }
}

