package logic;

public class GamePvP {
    public Board b;//board
    public GameStatus status;//the status

    public GamePvP()
    {
        b=new Board();

    }

    public GameStatus stepGame(int index) {


        status=this.b.move(index);
        return status;

    }

    public int[] getBoard() {
        return b.getBoard();
    }
}
