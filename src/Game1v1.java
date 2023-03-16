public class Game1v1 {
    public Board b;

    public  Game1v1()
    {
        b=new Board();
        Board.e_status stat= Board.e_status.VALID;
    }

    public boolean stepGame(int index)
    {
        Board.e_status stat;
        //b=new Board();
        stat=this.b.move(index);
        if (stat== Board.e_status.GAME_OVER)
            return true;
        return false;
    }

    public int[] getBoard() {
        return b.getBoard();
    }
}
