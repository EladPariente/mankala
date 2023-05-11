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

    /**
     * Checks if the game is over by counting the total number of stones on each side of the board.
     * If either side has no stones left, the remaining stones are added to the corresponding scoring pit
     * and the game ends.
     *
     * @return true if the game is over, false otherwise
     *
     * Time complexity: O(size), where size is the size of each side of the board
     */
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


    /**
     * Checks if a given pit is a valid choice for the current player to make a move from.
     * @param pit The index of the pit to check.
     * @return True if the pit is a valid choice, false otherwise.
     *
     * Complexity: O(1)
     */
    private boolean isValidPit(int pit) {
        if((((this.turn && (pit>=0 && pit<size) )||( !this.turn && (pit>=size+1 && pit<size*2+1))))&&this.board[pit]>0)
        {
            return true;
        }
        return false;
    }
    /**
     * Performs a move by a player on the game board.
     *
     * @param pit The index of the pit that the player has chosen to move from
     * @return A GameStatus object representing the result of the move and the current game state
     *
     * Time Complexity: O(n), where n is the number of stones in the pit being moved.
     *                  The while loop that moves the stones runs n times in the worst case.
     * Space Complexity: O(1), as the function uses a constant amount of additional memory
     */
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
        if ((this.turn && lastPit>=0 && lastPit<=5 && this.board[lastPit]==1 && this.board[12-lastPit]!=0)||(!this.turn && lastPit>=7 && lastPit<=12 && this.board[lastPit]==1 && this.board[12-lastPit]!=0))
        {
            this.board[myScorePit]+=this.board[lastPit]+this.board[12-lastPit];
            this.board[lastPit]=0;
            this.board[12-lastPit]=0;
        }

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


    /**
     * Calculates the winner of the game by comparing the number of stones in each player's score pit.
     * If both score pits have the same number of stones, the game is a draw.
     * Otherwise, the player with more stones in their score pit is declared the winner.
     * This function has a constant time complexity of O(1), as it performs a fixed set of operations regardless of the size of the board.
     */
    private void calculateWinner() {
        if (this.board[size] == this.board[size*2+1])
            this.gameStat = e_GStatus.DRAW;
        else
            this.gameStat = this.board[size] > this.board[size*2+1] ? e_GStatus.PLAYER_B_WON : e_GStatus.PLAYER_A_WON;
    }


//    public void printBoard() {
//        if(this.gameStat==e_GStatus.NOT_FINISHED)
//            System.out.println("next turn:" + (this.turn ? "player A" : "player B"));
//        else
//            System.out.println(this.gameStat);
//        for(int i=0;i<size;i++)
//        {
//            System.out.print("  "+this.board[i]);
//        }
//        System.out.println();
//        System.out.print(this.board[size*2+1]+"                  "+this.board[size]);
//        System.out.println();
//        for(int i=size*2;i>size;i--)
//        {
//            System.out.print("  "+this.board[i]);
//        }
//        System.out.println();
//    }

    public int[] getBoard()
    {
        return board;
    }

    /**
     * Calculates the destination pit index based on the source pit index and the amount of stones being moved.
     * @param src The index of the source pit.
     * @param amount The number of stones being moved.
     * @return The index of the destination pit.
     * The complexity of this function is O(1).
     */
    public static int getNextPit(int src, int amount) {
        if (src >= 0 && src <= 5) {
            return ((amount + src) % 14 + (amount + src) / 14) % 14;
        } else {
            return (int) (((amount + src) % 14 + Math.floor(0.5 * (amount + src) / 7)) % 14);
        }
    }

    /**
     * Returns the index of the pit that a stone will end up in after being moved from a given source pit with a specified amount.
     * If the source pit is on the left side (player A), the returned pit will always be in the range [0, 6].
     * If the source pit is on the right side (player B), the returned pit will always be in the range [7, 13].
     * If the stone lands in a player's own scoring pit (pit 6 or pit 13), the returned pit will be -1.
     * If the stone lands in the opponent's scoring pit, the returned pit will be 5 (if the source pit is on the right side) or 12 (if the source pit is on the left side).
     * If the stone would have gone past the opponent's scoring pit, it will wrap around and continue on the same side of the board.
     *
     * @param src the index of the source pit
     * @param amount the number of stones being moved from the source pit
     * @return the index of the pit that the last stone will end up in
     *
     * The complexity of this function is O(1).
     */
    public static int getDestinationPitIndex(int src, int amount)
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

    /**
     * Returns the starting pit index for the given player turn.
     *
     * @param turn a boolean value indicating the player's turn (true for Player A, false for Player B)
     * @return the starting pit index for the given player turn (0 for Player A, 7 for Player B)
     *
     * @implSpec This method has a time complexity of O(1).
     */
    public static int startPit(boolean turn) {
        if(turn) {
            return 0;
        } else {
            return 7;
        }
    }

    /**
     * Returns the ending pit index for the given player turn.
     *
     * @param turn a boolean value indicating the player's turn (true for Player A, false for Player B)
     * @return the ending pit index for the given player turn (5 for Player A, 12 for Player B)
     *
     * @implSpec This method has a time complexity of O(1).
     */
    public static int endPit(boolean turn) {
        if(turn) {
            return 5;
        } else {
            return 12;
        }
    }


}
