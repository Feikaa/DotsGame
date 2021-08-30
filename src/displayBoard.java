/**
 * Author: Paul Lee
 * Revised: March 23, 2020
 *
 * Description: Class to display Dots board to viewer
 */

/**
 * @brief A class for the view module of the Dots Board
 */
public class displayBoard {
    // The game board
    private dotsBoard board;
    // Indicator is user wants Dots to be displayed with colour
    private boolean displayColours;

    /**
     * @brief Initializes the Dots Display
     * @param board The Dots board to be displayed
     */
    public displayBoard(dotsBoard board) {
        this.board = board;
        displayColours = false;
    }

    /**
     * @brief Displays the Dots board to the user.
     */
    public void display() {
        // Counter to display the row numbers to make inputting easier
        int rowNum = 0;
        System.out.println("Moves left: " + board.getMovesLeft() + "      " + "Score: " + board.getScore());
        System.out.print("  ");

        // Displays the column number to make inputting easier
        for (int i = 0; i < board.getSIZE(); i++)
            System.out.print(i + " ");


        System.out.println();
        // Display the row number to make inputting easier
        for (Colour[] row : board.getBoard()) {
            System.out.print(rowNum + " ");
            // If displayColours is true, then display the Dots in colour. If not, display the Dots without colour
            // Uses ANSI colour codes to display Dots in colour
            for (Colour colour : row) {
                if (isDisplayColours())
                    System.out.print(colourOf(colour));
                System.out.print(colour + " ");
                if (isDisplayColours())
                    System.out.print("\033[0m");
            }
            // Increases row number until reaches the last row
            rowNum += 1;
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @brief Setter for the displayColours
     * @param b Sets the displayColours to true or false depending on the user's preference
     */
    public void setDisplayColours(boolean b) {
        displayColours = b;
    }

    /**
     * @brief Getter for the displayColours
     * @return True if user wants to display Dots with colour. False otherwise
     */
    private boolean isDisplayColours() {
        return displayColours;
    }

    /**
     * @brief Finds the ANSI colour code for the given Colour
     * @param colour Colour to find its respective ANSI colour code
     * @return String of the ANSI colour code
     */
    public String colourOf(Colour colour) {
        if (colour.equals(Colour.R))
            return "\033[38;5;1m";
        else if (colour.equals(Colour.B))
            return "\033[38;5;14m";
        else if (colour.equals(Colour.G))
            return "\033[0;32m";
        else if (colour.equals(Colour.O))
            return "\033[38;5;202m";
        else if (colour.equals(Colour.P))
            return "\033[38;5;13m";
        return "";
    }

    /**
     * @brief Updates the board when the game board is changed
     * @param board Main game board that is being played on
     */
    public void update(dotsBoard board) {
        this.board = board;
    }
}

