package logic;

public class Board {
    public static final int size=6;//size of each size of the board, not including the scoring pits
    public static final int stones=4;//amount of stone in each pit at the start of the game
    private boolean turn;//the turn of the player (A=TRUE B=FALSE)
    private int  board[]=new int[size*2+2];;//the game board
    //public enum  e_status{INVALID_INPUT, VALID, GAME_OVER}
    public enum  e_GStatus{PLAYER_A_WON, PLAYER_B_WON, DRAW, NOT_FINISHED}
    private e_GStatus gameStat;
    public Board() {
        this.gameStat=e_GStatus.NOT_FINISHED;
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

    private boolean isGameOver() {
        int amountA=0;
        int amountB=0;
        boolean gameover=false;
        for(int i=0;i<size;i++)
        {
            amountA+=this.board[i];
        }
        for(int i=size+1;i<size*2+1;i++)
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
            this.board[size] += amountA;
            this.board[size * 2 + 1] += amountB;

        }
        return gameover;
    }

    private boolean isValidPit(int pit) {
        if((((this.turn && (pit>=0 && pit<size) )||( !this.turn && (pit>=size+1 && pit<size*2+1))))&&this.board[pit]>0)
        {
            return true;
        }
        return false;
    }
    public GameStatus move(int pit) {
        int pitStones;
        int lastPit;
        int opScorePit;
        int myScorePit;
        GameStatus status;
        if(!this.isValidPit(pit))
        {
            status=new GameStatus(this.gameStat,turn);
            return status;
        }



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
        if ((this.turn && lastPit>=0 && lastPit<=5 && this.board[lastPit]==1)||(!this.turn && lastPit>=7 && lastPit<=12 && this.board[lastPit]==1))
        {
            this.board[myScorePit]+=this.board[lastPit]+this.board[12-lastPit];
            this.board[lastPit]=0;
            this.board[12-lastPit]=0;
        }
//        if(lastPit>(myScorePit%(size*2+1)) && lastPit<(opScorePit) && this.board[lastPit]==1)
//        {
//            this.board[lastPit]+=this.board[12-lastPit];
//            this.board[12-lastPit]=0;
//        }

        this.turn=myScorePit==lastPit?this.turn:!this.turn;

        if(isGameOver())
        {
            this.calculateWinner();
            status=new GameStatus(this.gameStat,turn);
            return status;
        }


        status=new GameStatus(this.gameStat,turn);
        return status;
    }

    private void calculateWinner() {
        if(this.board[size]==this.board[size*2+1])
            this.gameStat=e_GStatus.DRAW;
        else
            this.gameStat=this.board[size]>this.board[size*2+1]?e_GStatus.PLAYER_B_WON:e_GStatus.PLAYER_A_WON;
    }

    public void printBoard() {
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

    public int[] getBoard()
    {
        return board;
    }

    public static int dest(int src,int amount)
    {

        if(src>=0&&src<=5)
        {
            return ((amount+src)%14+(amount+src)/14)%14;
        }
        else
        {
            return (int)(((amount+src)%14+Math.floor(0.5*(amount+src)/7))%14);
        }
    }

    public static int Pit(int src,int amount)
    {
        if(src<6)
        {
            if(src+amount>=13)
                return 12;
            else
                if(src+amount<=6)
                    return 6;
                else
                    return src+amount;
        }
        else {
            if(src+amount<20)
                return 5;
            else
                if(amount+src<13)
                    return -1;
                else
                    return (src+amount)%14;
        }
    }
    public static int startPit(boolean turn)
    {
        if(turn)
            return 0;
        else
            return 7;
    }
    public static int endPit(boolean turn)
    {
        if(turn)
            return 5;
        else
            return 12;
    }

}
