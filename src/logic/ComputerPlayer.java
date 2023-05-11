package logic;

import java.util.LinkedList;

/**
 * The `ComputerPlayer` class represents an AI player for the game of mancala.
 * It uses decision-making algorithm to determine the
 * optimal move to make in each turn.
 */
public class ComputerPlayer {

    private int[] board=new int[Board.size*2+2];// the game board
    private boolean turn;// the turn of the computer

    /**
     * The `info` array contains information about the state of the game board.
     * The array has length 4, and each element is a linked list that contains
     * the indices of the pits that match the corresponding category:
     *
     * - info[0]: Pits that will allow the player to take another turn
     * - info[1]: Pits that are valid moves for the current player
     * - info[2]: Pits that will capture the opponent's stones
     * - info[3]: Pits that will allow the opponent to capture the current player's stones
     */
    private LinkedList<Integer>[] info = new LinkedList[4];



    public ComputerPlayer(boolean turn)
    {
        this.turn=turn;

    }

    /**
     * Determines the optimal move to make given the current board configuration.
     *
     * @param board the current board configuration represented as an integer array
     * @return the index of the optimal move to make
     *
     * Complexity Analysis:
     * - The method initializes a linked list for each of the four move types, which takes O(1) time for each list.
     * - The method then iterates through the 14 positions on the board and sets the corresponding position on the internal board array to the value of the corresponding position on the input board array, which takes O(14) = O(1) time.
     * - The method then calls the scanBoard() method, which takes O(n) time, where n is the number of positions on the board.
     * - The method then calls the rules() method, which takes O(n) time.
     * - Therefore, the overall time complexity of the method is O(n).
     */
    public int optimalMove(int[] board) {
        for(int i=0;i<this.info.length;i++) {
            this.info[i]=new LinkedList<>();
        }
        for(int i=0;i<14;i++) {
            this.board[i]=board[i];
        }

        scanBoard(); // O(n)
        return decisionMaking(); // O(n)
    }



    /**
     * Scans the current game board and generates a list of all possible moves and captures for the current player.
     * This function updates the 'info' attribute of the current object with four linked lists, each containing the
     * indexes of pits on the board that correspond to a specific type of move:
     *
     * info[0] - anther turn moves
     * info[1] - potential moves
     * info[2] - capture moves
     * info[3] - capture opponent moves
     *
     * Complexity: O(n), where 'n' is the number of pits on the game board.
     */
    private void scanBoard() {
        int startM= Board.startPit(turn);
        int startO=Board.startPit(!turn);
        int endM=Board.endPit(turn);
        int endO=Board.endPit(!turn);
        int dest;
        int maxT=0;
        int maxTP=0;

        for(int i=startM;i<=endM;i++)//computer loop
        {

            if(this.board[i]+ i%7 ==6 )
            {
                this.info[0].add(i);//all the anther turn moves
            }

            if(this.board[i]!=0)
            {
                this.info[1].add(i);//all the potential moves
            }

            dest=Board.getNextPit(i,this.board[i]);
            if(this.board[i]!=0 && dest>=startM && dest<=endM && this.board[12-dest]!=0 && this.board[dest]==0)
            {
                this.info[2].add(i);//all the capture moves

            }

        }

        for(int i=startO;i<=endO;i++)//opponent loop
        {

            dest=Board.getNextPit(i,this.board[i]);

            if(this.board[i]!=0 && dest>=startO && dest<=endO && this.board[12-dest]!=0 && this.board[12-dest]+1>maxT &&this.board[dest]==0)
            {
                maxT=this.board[12-dest]+1;
                maxTP=i;

            }
        }
        if(maxT!=0)
            this.info[3].add(maxTP);//all the capture opponent moves

    }

    /**
     * Determines the optimal move for the current state of the game according to a set of rules.
     *
     * @return The pit index of the optimal move.
     *
     * @complexity O(N), where N is the number of pits on the board. The function uses nested loops
     *             to iterate over the pits.
     */
    private int decisionMaking() {
        int optimalMove;
        int capture;
        int hits;
        int amountO;
        int amountM;
        int danger;


        //check the first and the last pit
        optimalMove=(this.info[1].getLast()==5||this.info[1].getLast()==12)?this.info[1].getLast():this.info[1].getFirst();

        //checks for potential of another move
        if(!this.info[0].isEmpty())
        {
            optimalMove=this.info[0].getFirst();
        }

        //checks for potential of capture
        if(!this.info[2].isEmpty())
        {
            capture=this.findPitWithMaxCapture();
            hits=0;
            amountM=this.board[12-Board.getNextPit(capture,this.board[capture])]+1;
            for (int x:this.info[0])
            {
                hits+=capture<x?1:0;
            }
            amountM+=this.info[0].size()-hits;
            if(amountM>hits)
            {
                optimalMove=capture;
            }

        }

        //checks for potential for the opponent to capture
        if(!this.info[3].isEmpty())
        {

            danger=12-Board.getNextPit(this.info[3].getFirst(),this.board[this.info[3].getFirst()]);

            amountO=this.board[danger]+1;


            if(!this.info[2].isEmpty())
            {
                capture=this.findPitWithMaxCapture();
                amountM=this.board[12-Board.getNextPit(capture,this.board[capture])]+1;
                for (int x:this.info[0]) {
                    amountM+=x>capture?1:0;
                }
                if(amountM>amountO)
                {
                    if (!this.info[0].isEmpty())
                    {
                        if(this.info[0].getFirst()>capture)
                        {

                            optimalMove=this.info[0].getFirst();
                        }
                        else
                        {
                            optimalMove=capture;
                        }
                    }
                    else
                    {
                        optimalMove=capture;
                    }
                }
                else
                {
                    optimalMove=defenceStrategy(danger);

                }
            }
            else{
                optimalMove=defenceStrategy(danger);
            }
        }
        return optimalMove;
    }

    /**
     * Returns the pit to be used for defense, given the pit that is being threatened.
     * If the player has no defensive pits, returns the threatened pit. If the player has
     * defensive pits but none of them are being threatened, returns the first defensive pit.
     *
     * @param threatenedPit the pit that is being threatened
     * @return the pit to be used for defense
     *
     * Time Complexity: O(1)
     */
    private int defenceStrategy(int threatenedPit)
    {

        int defensivePit;
        if (!this.info[0].isEmpty())
        {
            defensivePit = this.info[0].getFirst();
        }
        else
        {
            if(this.info[0].contains(threatenedPit))
            {
                defensivePit=this.info[0].getFirst();
            }
            else
            {
                defensivePit=threatenedPit;
            }

        }
        return defensivePit;
    }

    /**
     * Finds the pit with the maximum number of captures that can be made.
     * @return the index of the pit with the maximum captures
     *
     * Time Complexity: O(n), where n is the length of the array this.info[2].
     * The for loop iterates through each element of the array, and each iteration
     * performs constant time operations.
     */
    private int findPitWithMaxCapture() {
        int maxStonesCaptured = 0;
        int pitWithMaxStonesCaptured = 0;
        int stonesCaptured = 0;

        for (int pit : this.info[2]) {
            int oppositePit = 12 - Board.getNextPit(pit, this.board[pit]);
            stonesCaptured = this.board[oppositePit] + 1;

            if (stonesCaptured > maxStonesCaptured) {
                maxStonesCaptured = stonesCaptured;
                pitWithMaxStonesCaptured = pit;
            }
        }

        return pitWithMaxStonesCaptured;
    }



}

