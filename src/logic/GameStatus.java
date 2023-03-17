package logic;

import logic.Board;

public class GameStatus {

    private Board.e_GStatus status;//status of the board
    private boolean turn;//the turn

    public GameStatus(Board.e_GStatus status,boolean turn) {
        this.turn=turn;
        this.status = status;
    }

    public Board.e_GStatus getStatus() {
        return status;
    }

    public void setStatus(Board.e_GStatus status) {
        this.status = status;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
