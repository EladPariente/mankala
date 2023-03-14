public class Board {
    public static final int size=6;//size of each size of the board, not including the scoring pits
    public static final int stones=4;//amount of stone in each pit at the start of the game
    private boolean turn;//the turn of the player (A=TRUE B=FALSE)
    private int  board[];//the game board
    public enum  e_status{INVALID_INPUT, VALID, GAME_OVER}
    private enum  e_GStatus{PLAYER_A_WON, PLAYER_B_WON, DRAW, NOT_FINISHED}
    private e_GStatus gameStat;
    public Board()
    {
        this.gameStat=e_GStatus.NOT_FINISHED;
        this.board=new int[size*2+2];
        this.turn=true;
        for(int i=0;i<size*2+2;i++)
        {
            if (i != size && i != size * 2 + 1)
            {
                this.board[i] = stones;
            }
        }
        this.board[size] = 0;
        this.board[size * 2 + 1] = 0;
    }

    private boolean isGameOver()
    {
        int amountA=0;
        int amountB=0;
        boolean gameover=false;
        for(int i=0;i<size;i++)
        {
            amountA+=this.board[i];
        }
        for(int i=size+2;i<size*2+1;i++)
        {
            amountB+=this.board[i];
        }
        if(amountA==0||amountB==0)
        {
            gameover=true;
            for(int i=0;i<size*2+2;i++)
            {
                if (i != size && i != size * 2 + 1)
                {
                    board[i] = 0;
                }
            }
            this.board[size] += amountB;
            this.board[size * 2 + 1] += amountA;

        }
        return gameover;
    }

    private boolean isValidPit(int pit)
    {
        if((((this.turn && (pit>=0 && pit<size) )||( !this.turn && (pit>=size+1 && pit<size*2+1))))&&this.board[pit]>0)
        {
            return true;
        }
        return false;
    }
    public e_status move(int pit)
    {
        int pitStones;
        int lastPit;
        int opScorePit;
        int myScorePit;

        if(!this.isValidPit(pit))
            return e_status.INVALID_INPUT;


        pitStones=this.board[pit];
        this.board[pit]=0;
        lastPit=pit;
        myScorePit=this.turn?size:size*2+1;
        opScorePit=this.turn?size*2+1:size;

        while(pitStones>0)
        {
            lastPit=(lastPit+1)%(size*2+2);

            if(lastPit!=opScorePit)
            {
                this.board[lastPit]++;
                pitStones--;
            }
        }

        if(lastPit<myScorePit && lastPit>(opScorePit%(size*2+1)) && lastPit==1)
        {
            this.board[lastPit]+=this.board[12-lastPit];
            this.board[12-lastPit]=0;
        }

        this.turn=myScorePit==lastPit?this.turn:!this.turn;

        if(isGameOver())
        {
            this.calculateWinner();
            return e_status.GAME_OVER;
        }


        return e_status.VALID;
    }

    private void calculateWinner()
    {
        if(this.board[size]==this.board[size*2+1])
            this.gameStat=e_GStatus.DRAW;
        else
            this.gameStat=this.board[size]>this.board[size*2+1]?e_GStatus.PLAYER_A_WON:e_GStatus.PLAYER_B_WON;
    }

    public void printBoard()
    {
        if(this.gameStat==e_GStatus.NOT_FINISHED)
            System.out.println("next turn:" + (this.turn ? "player A" : "player B"));
        else
            System.out.println(this.gameStat);
        for(int i=0;i<size;i++)
        {
            System.out.print("  "+this.board[i]);
        }
        System.out.println();
        System.out.print(this.board[size*2+1]+"                  "+this.board[size]);
        System.out.println();
        for(int i=size*2;i>size;i--)
        {
            System.out.print("  "+this.board[i]);
        }
        System.out.println();
    }
}