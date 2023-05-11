package logic;

/**
 * The GamePvC class represents a game played between a human player and a computer player.
 * It contains a board, game status, and a computer player instance to make moves for the computer player.
 */
public class GamePvC {
    public Board board; // the game board
    public GameStatus status; // the current game status
    private ComputerPlayer computerPlayer; // instance of the computer player

    /**
     * Constructs a new GamePvC object with an empty game board and initializes the computer player instance.
     */
    public GamePvC() {
        board = new Board();
        computerPlayer = new ComputerPlayer(false);
    }

    /**
     * Makes a move at the specified index for the human player and then makes a move for the computer player.
     * If the computer player's turn is not next, it keeps making moves for the computer player until it is their turn.
     *
     * @param index the index of the pit where the human player wants to make a move
     * @return the updated game status after both the human and computer players have made their moves
     *
     * @implNote This method calls the Board.move() method, which has a time complexity of O(n),
     *           where n is the number of pits on the board.
     */
    public GameStatus stepGame(int index) {

        status = board.move(index);
        if (!status.isTurn() && status.getStatus() == Board.e_GStatus.NOT_FINISHED) {

            index = computerPlayer.optimalMove(board.getBoard());
            stepGame(index);
            //status = b.move(index);
        }
        return status;
    }

    /**
     * Returns an array representing the current state of the board.
     *
     * @return an array of integers representing the number of stones in each pit on the board
     */
    public int[] getBoard() {
        return board.getBoard();
    }
}
