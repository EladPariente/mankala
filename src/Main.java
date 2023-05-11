/**
 * The Main class contains the entry point for the Mancala game.
 * It creates an instance of the GUI class to display the game.
 */
import UI.GUI;
import logic.Board;
import logic.ComputerPlayer;
import logic.GameStatus;

import java.util.Scanner;
public class Main {

    /**
     * The main method creates an instance of the GUI class to start the game.
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {

        GUI gui =new GUI();
        //-----------------
        //statistic();


    }

    public static void statistic()
    {
        int h=0;
        int c=0;
        int a=0;
        //System.out.println(game());
        while (a!=1000000)
        {
            int g=game();
            if(g==1)
                c++;
            else if(g==2)
                h++;
            a++;
        }
        double d;
        System.out.println(c);
        System.out.println("statistic");
        d=(double) c/(double) a;
        System.out.println("computer win: "+Math.round(d*100)+"%");
        d=(double) h/(double) a;
        System.out.println("player win: "+Math.round(d*100)+"%");
        d=((double) a-(c+h))/(double) a;
        System.out.println("draw: "+Math.round(d*100)+"%");
    }
    public static int game()
    {
        Board board= new Board();
        GameStatus status=new GameStatus(Board.e_GStatus.NOT_FINISHED,true);
        ComputerPlayer computerPlayer = new ComputerPlayer(false);

        while (status.getStatus()== Board.e_GStatus.NOT_FINISHED)
        {
            while (status.isTurn()==true)
            {
                if (status.getStatus()!= Board.e_GStatus.NOT_FINISHED)
                    break;
                int rand = (int)(Math.random() * 6) + 0;
                status=board.move(rand);

            }
            while (status.isTurn()==false)
            {
                if (status.getStatus()!= Board.e_GStatus.NOT_FINISHED)
                    break;
//                int rand = (int)(Math.random() * 6) + 7;
//                status=board.move(rand);
                status=board.move(computerPlayer.optimalMove(board.getBoard()));
            }

        }
        if(status.getStatus()== Board.e_GStatus.PLAYER_A_WON)
            return 1;
        else if(status.getStatus()== Board.e_GStatus.PLAYER_B_WON)
            return 2;
        else
            return 3;
    }


}