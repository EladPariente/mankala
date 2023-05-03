package logic;

import java.util.LinkedList;

public class ComputerPlayer {

    private int[] board=new int[Board.size*2+2];
    private boolean turn;
    private LinkedList<Integer>[] info = new LinkedList[Board.size*2+2];

    public ComputerPlayer(boolean turn)
    {
        this.turn=turn;

    }

    public int optimalMove(int[] board)
    {
        for(int i=0;i<this.info.length;i++)
        {
            this.info[i]=new LinkedList<>();
        }
        for(int i=0;i<14;i++)
        {
            this.board[i]=board[i];
        }
        System.out.println("****"+this.board[8]);
        //System.arraycopy(board,0,this.board,0,14);
        scanBoard();
        return rulls();
    }
    private void scanBoard() {
        int startM=this.turn?0:7;
        int startO=this.turn?7:0;
        int endM=this.turn?5:12;
        int endO=this.turn?12:5;
        int maxD=0;
        int maxDPit=0;
        int dest;
        for(int i=startM;i<=endM;i++)
        {
            System.out.println("**********"+i);
            //int I = Math.abs((i > 6 ? 12 : 0) - i);
            if(this.board[i]+ i%7 <=6 &&this.board[i]+ i%7 >=4)
            {
                this.info[6-(this.board[i]+ i%7) ].add(i);
            }

            if(maxD<this.board[i]+ i)
            {
                maxD=this.board[i]+ i;
                maxDPit=i;
            }

            if(this.board[i]==0)
            {
                this.info[4].add(i);
            }
            else
            {
                this.info[5].add(i);
            }
            dest=Board.dest(i,this.board[i]);
            if(this.board[i]!=0 && dest>=startM && dest<=endM && this.board[12-dest]!=0 && this.board[dest]==0)
            {
                this.info[6].add(i);
                //
            }

        }
        System.out.println("****"+this.board[8]);
        this.info[3].add(maxDPit);
        maxD=0;
        maxDPit=0;
        int maxT=0;
        int maxTP=0;
        int maxT1=0;
        int maxTP1=0;
        for(int i=startO;i<=endO;i++)
        {

            dest=Board.dest(i,this.board[i]);
//            if(i==0)
//                System.out.println("&&&&&&&&"+this.board[i]);
            if(this.board[i]!=0 && dest>=startO && dest<=endO && this.board[12-dest]!=0 && this.board[12-dest]+1>maxT &&this.board[dest]==0)
            {
                maxT=this.board[12-dest]+1;
                maxTP=i;

            }
            dest=Board.dest(i,this.board[i]+1);
            if(this.board[i]!=0 && dest>=startO && dest<=endO && this.board[12-dest]!=0 && this.board[12-dest]+1>maxT1)
            {
                maxT1=this.board[12-dest]+1;
                maxTP1=i;
            }
            if(maxD<this.board[i]+ i)
            {
                maxD=this.board[i]+ i;
                maxDPit=i;
            }

        }
        if(maxT!=0)
            this.info[7].add(maxTP);

        this.info[8].add(maxTP1);
        this.info[9].add(maxDPit);


    }
    private int rulls() {
        int optimalMove;
        int capture;
        int hits;
        int amountO;
        int amountM;
        System.out.println(this.info[4].size());
        optimalMove=(this.info[5].getLast()==5||this.info[5].getLast()==12)?this.info[5].getLast():this.info[5].getFirst();
        //optimalMove=this.info[5].getLast();
//        if(!this.info[9].isEmpty())
//        {
//            int dest=Board.Pit(this.info[9].getFirst(),this.board[this.info[9].getFirst()]);
//            if(dest>Board.startPit(this.turn))
//            {
//
//            }
//
//        }
        if(!this.info[0].isEmpty())
        {
            optimalMove=this.info[0].getFirst();
        }
        System.out.println("))))))))))"+optimalMove);
        if(!this.info[6].isEmpty())
        {
            capture=this.maxCapture();
            hits=0;
            amountM=this.board[12-Board.dest(capture,this.board[capture])]+1;
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
        System.out.println("))))))))))"+optimalMove);
        if(!this.info[7].isEmpty())
        {
            //System.out.println("*********"+this.info[7].getFirst());
            int danger=12-Board.dest(this.info[7].getFirst(),this.board[this.info[7].getFirst()]);

            amountO=this.board[danger]+1;
            //int sum=Board.dest(this.info[6].getFirst(),this.board[this.info[6].getFirst()]);

            if(!this.info[6].isEmpty())
            {
                int n=this.maxCapture();
                amountM=this.board[12-Board.dest(n,this.board[n])]+1;
                for (int x:this.info[0]) {
                    amountM+=x>n?1:0;
                }
                if(amountM>amountO)
                {
                    if (!this.info[0].isEmpty())
                    {
                        if(this.info[0].getFirst()>n)
                        {

                            optimalMove=this.info[0].getFirst();
                        }
                        else
                        {
                            optimalMove=n;
                        }
                    }
                    else
                    {
                        optimalMove=n;
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
    private int defenceStrategy(int danger)
    {

        int op;
        if (!this.info[0].isEmpty())
        {
            op = this.info[0].getFirst();
        }
        else
        {
            if(this.info[0].contains(danger))
            {
                op=this.info[0].getFirst();
            }
            else
            {
//                if(this.info[1].contains(danger)&& Board.Pit(this.info[3].getFirst(),this.board[this.info[3].getFirst()])>this.info[7].getFirst())
//                {
//                    op=this.info[3].getFirst();
//                }
//                else
//                {
//                    op=danger;
//                }
                op=danger;
            }

        }
        return op;
    }
    private int maxCapture()
    {

        int max=0;
        int numMax=0;
        int amount=0;
        for (int x:this.info[6])
        {
            //System.out.println(this.info[6].size());
            amount=this.board[12-Board.dest(x,this.board[x])]+1;
            if(amount>max)
            {
                max=amount;
                numMax=x;
            }
        }
        return numMax;
    }


}
