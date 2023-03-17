package logic;

public class Game1v1 {
    public Board b;//board
    public GameStatus status;//the status
    public  Game1v1()
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
