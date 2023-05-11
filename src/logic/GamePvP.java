package logic;
/**
 * The GamePvP class represents a two-player game where each player takes turns moving pieces on a game board.
 * The class encapsulates the game board and the current game status, and provides methods to execute game moves
 * and query the game state.
 *
 * @implSpec This class has a space complexity of O(n), where n is the number of pits on the board.
 *           The stepGame() method has a time complexity of O(n), where n is the number of pits on the board,
 *           due to the board.move() method being called, which searches for the appropriate pit to move pieces from.
 */
public class GamePvP {
    public Board board; // the game board
    public GameStatus status; // the current game status

    /**
     * Constructs a new GamePvP object with a new game board.
     */
    public GamePvP() {
        board = new Board();
    }

    /**
     * Executes a game move by moving pieces from the specified pit on the board and updating the game status accordingly.
     *
     * @param index the index of the pit to move pieces from
     * @return the new game status after executing the move
     *
     * @implNote This method calls the Board.move() method, which has a time complexity of O(n),
     *           where n is the number of pits on the board.
     */
    public GameStatus stepGame(int index) {
        status = this.board.move(index);
        return status;
    }

    /**
     * Retrieves the current state of the game board.
     *
     * @return an array of integers representing the number of pieces in each pit on the board
     */
    public int[] getBoard() {
        return board.getBoard();
    }
}
