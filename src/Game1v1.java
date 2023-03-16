public class Game1v1 {
    public Board b;
    public GameStatus status;
    public  Game1v1()
    {
        b=new Board();
    }

    public GameStatus stepGame(int index)
    {

        //b=new Board();
        status=this.b.move(index);
        return status;

    }

    public int[] getBoard() {
        return b.getBoard();
    }
}
