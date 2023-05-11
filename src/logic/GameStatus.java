package logic;

import logic.Board;

/**
 * This class represents the current status of the game.
 */
public class GameStatus {

    private Board.e_GStatus status;//The status of the game board.
    private boolean turn;//The turn of the current player.

    /**
     * Creates a new instance of the {@code GameStatus} class with the specified parameters.
     *
     * @param status The status of the game board.
     * @param turn The turn of the current player.
     */
    public GameStatus(Board.e_GStatus status, boolean turn) {
        this.turn = turn;
        this.status = status;
    }

    /**
     * Returns the status of the game board.
     *
     * @return The status of the game board.
     */
    public Board.e_GStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the game board.
     *
     * @param status The status of the game board to set.
     */
    public void setStatus(Board.e_GStatus status) {
        this.status = status;
    }

    /**
     * Returns the turn of the current player.
     *
     * @return The turn of the current player.
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * Sets the turn of the current player.
     *
     * @param turn The turn of the current player to set.
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
