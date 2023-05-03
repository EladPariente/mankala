package logic;

public class GamePvC {
    public Board b;//board
    public GameStatus status;//the status
    private ComputerPlayer CP;
    public GamePvC()
    {
        b=new Board();
        this.CP=new ComputerPlayer(false);
    }

    public GameStatus stepGame(int index) {


        status=this.b.move(index);
        while (!status.isTurn()&&status.getStatus()== Board.e_GStatus.NOT_FINISHED) {
            index = this.CP.optimalMove(b.getBoard());
            status = this.b.move(index);

        }
        return status;

    }

    public int[] getBoard() {
        return b.getBoard();
    }
}
