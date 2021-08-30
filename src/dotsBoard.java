/**
 * Author: Paul Lee
 * Revised: March 22, 2020
 *
 * Description: Dots Board class
 */

import java.util.Random;

/**
 * @brief A class for the model module for the Dots Board.
 */
public class dotsBoard {
    // Size of the board
    private final int SIZE = 6;
    // Board
    private Colour[][] board;

    // State Variables
    private int movesLeft;
    private int score;
    // For random Colour
    private Random random = new Random();

    /**
     * @brief Initializes the Dots Board
     */
    public dotsBoard() {
        movesLeft = 30;
        score = 0;
        board = new Colour[SIZE][SIZE];

        // Randomize every dot in the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Colour.values()[random.nextInt(Colour.values().length)];
            }
        }
    }

    /**
     * @brief Updates the board upon the user's input only if the input is valid.
     * @param input User's input of cell positions. Positions are separated by spaces.
     */
    public void doMove(String input) {
        // Split input by spaces
        String[] moves = input.split(" ");

        // If only one cell was inputted, or inputted cell row or column is bigger than SIZE, do nothing
        if (moves.length == 1 || Character.getNumericValue(moves[0].charAt(0)) >= SIZE || Character.getNumericValue(moves[0].charAt(1)) >= SIZE) {
            System.out.println("Invalid move.");
            return;
        }

        // Save first inputted cell colour
        Colour colour = board[Character.getNumericValue(moves[0].charAt(0))][Character.getNumericValue(moves[0].charAt(1))];

        // Checks that every move is beside previous move, and that the colour at the specified cell is the same as the first one
        for (int i = 1; i < moves.length; i++) {
            int[] prevPos = new int[]{Character.getNumericValue(moves[i - 1].charAt(0)), Character.getNumericValue(moves[i - 1].charAt(1))};
            int[] currentPos = new int[]{Character.getNumericValue(moves[i].charAt(0)), Character.getNumericValue(moves[i].charAt(1))};
            Colour checkColour = board[currentPos[0]][currentPos[1]];
            if (!checkColour.equals(colour) || !isBeside(prevPos, currentPos)) {
                System.out.println("Invalid move.");
                return;
            }
        }

        // Set every inputted cell as null
        for (String move : moves) {
            int[] pos = new int[]{Character.getNumericValue(move.charAt(0)), Character.getNumericValue(move.charAt(1))};
            board[pos[0]][pos[1]] = null;
        }

        // Move every cell that is above a null cell to the lowest null cell
        for (int col = 0; col < SIZE; col++) {
            int bottom = -1;
            for (int row = SIZE - 1; row >= 0; row--) {
                // If the bottom was never set, set it as the first occurrence of a null cell starting from the bottom of the board
                if (bottom == -1 && board[row][col] == null)
                    bottom = row;
                // If bottom was set, and current cell isn't null, swap the null cell and current cell, and move the bottom up one
                if (bottom != -1 && board[row][col] != null) {
                    Colour temp = board[row][col];
                    board[bottom][col] = temp;
                    board[row][col] = null;
                    bottom -= 1;
                }
            }
        }

        // Set every null cell as a random Colour object
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == null)
                    board[row][col] = Colour.values()[random.nextInt(Colour.values().length)];
            }
        }
        // Decrease the moves left by 1 and add the number of cells in moves to the score.
        this.movesLeft -= 1;
        score += moves.length;
    }

    /**
     * @brief Checks to see if two cells at the inputted coordinates on the board are beside each other
     * @param prev Previous cell's coordinates on the board
     * @param current Current cell's coordinates on the board
     * @return True if the two cells are besides each other. False otherwise
     */
    public boolean isBeside(int[] prev, int[] current) {
        if (prev[0] == current[0] || prev[1] == current[1])
            return true;
        return false;
    }

    /**
     * @brief Getter for number of moves left
     * @return Number of moves left
     */
    public int getMovesLeft() {
        return movesLeft;
    }

    /**
     * @brief Getter for the score
     * @return User's score
     */
    public int getScore() {
        return score;
    }

    /**
     * @brief Getter for the board size
     * @return Board size
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * @brief Getter for the board
     * @return Object's board
     */
    public Colour[][] getBoard() {
        return board;
    }
}

