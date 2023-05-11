package UI;

import logic.Board;
import logic.GamePvC;
import logic.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * The PanelPvC class creates a panel that contains a game between a computer player and a human player
 */
public class PanelPvC implements ActionListener {

    private JButton[] pitsBtns;//pit buttons
    private JPanel pvcPanel;//panel
    private JLabel BaseB;//scoring pit
    private JLabel BaseA;//scoring pit
    Map<JButton, Runnable> actions;//lambda actions pits
    private JLabel title;//title
    private GUI gui;// the GUI of the game
    private GamePvC gameManager;//pvc middle man (manage the game)
    private JButton returnBtn;//button to return to the start panel
    private JButton newGameBtn;//button to restart game

    /**
     * Method called when a pit button is clicked.
     * @param buttonIndex An integer representing the index of the clicked button.
     */
    public void BClicked(int buttonIndex) {

        GameStatus status= this.gameManager.stepGame(buttonIndex);
        int board[]=this.gameManager.getBoard();
        updateButtons(board,status.isTurn());

        if(status.getStatus()!= Board.e_GStatus.NOT_FINISHED)
        {
            this.GameOver1v1(status.getStatus());
        }

    }

    /**
     * Invoked when an action occurs.
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== returnBtn)
            this.gui.startPanel();
        else if (e.getSource()== newGameBtn)
            this.gui.gamePvC();
    }

    /**
     * Updates the state of the pits/buttons in a Mancala board based on the current game state.
     * @param board An integer array representing the current state of the game board.
     * @param turn A boolean representing whose turn it is in the game.
     */
    private void updateButtons(int[] board,boolean turn){
        for(int i=0;i<6;i++)
        {
            this.pitsBtns[i].setText(""+board[i]);
            this.pitsBtns[i].setEnabled(turn);
        }
        for(int i=11;i>=6;i--)
        {
            this.pitsBtns[i].setText(""+board[i+1]);
            this.pitsBtns[i].setEnabled(!turn);
        }
        BaseB.setText(""+board[13]);
        BaseA.setText(""+board[6]);
    }

    public JPanel getPvcPanel() {
        return this.pvcPanel;
    }

    /**
     * Constructs a new PanelPvC with the specified GUI and GamePvC objects.
     * Sets up the UI components and initializes the game manager.
     * @param g The GUI object.
     */
    public PanelPvC(GUI g) {

        this.gui =g;
        this.gameManager =new GamePvC();



        //set up panel
        Color myColor = new Color(173, 216, 230);
        pvcPanel = new JPanel();
        pvcPanel.setLayout(null);
        pvcPanel.setBackground(myColor);

        //set up scoring pits
        BaseB =new JLabel("0");
        BaseB.setBounds(200,300,150,450);
        BaseB.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        BaseB.setHorizontalAlignment(SwingConstants.CENTER);
        BaseB.setBackground(Color.orange);
        BaseB.setOpaque(true);
        pvcPanel.add(BaseB);

        BaseA =new JLabel("0");
        BaseA.setBounds(1600,300,150,450);
        BaseA.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        BaseA.setHorizontalAlignment(SwingConstants.CENTER);
        BaseA.setBackground(Color.orange);
        BaseA.setOpaque(true);
        pvcPanel.add(BaseA);



        //set up pit buttons
        actions = new HashMap<>();

        this.pitsBtns =new JButton[12];
        for(int i=0;i<6;i++)
        {
            pitsBtns[i]=new JButton("4");
            final int x=i;
            pitsBtns[i].setBounds(400+(x)*200,600,150,150);
            pitsBtns[i].setFont(new Font("Bebas Neue", Font.PLAIN, 70));
            pitsBtns[i].setBackground(Color.orange);
            actions.put(pitsBtns[i], () -> BClicked(x));
            this.pvcPanel.add(pitsBtns[i]);
        }

        for(int i=6;i<12;i++)
        {
            pitsBtns[i]=new JButton("4");
            final int x=i+1;
            pitsBtns[i].setBounds(400+(11-i)*200,300,150,150);
            pitsBtns[i].setFont(new Font("Bebas Neue", Font.PLAIN, 70));
            pitsBtns[i].setBackground(Color.orange);
            actions.put(pitsBtns[i], () -> BClicked(x));
            this.pvcPanel.add(pitsBtns[i]);
        }

        for (JButton button : actions.keySet()) {
            button.addActionListener(e -> {
                Runnable action = actions.get(e.getSource());
                if (action != null) {
                    action.run();
                }
            });
        }

        updateButtons(gameManager.getBoard(),true);



        //set up return button
        this.returnBtn =new JButton("return to menu");
        returnBtn.setBounds(150,850,275,75);
        returnBtn.setFont(new Font("Bebas Neue", Font.PLAIN, 35));
        returnBtn.setBackground(Color.gray);
        this.pvcPanel.add(returnBtn);
        this.returnBtn.addActionListener(this);


        //set up new game button
        this.newGameBtn =new JButton("new game");
        newGameBtn.setBounds(1550,850,275,75);
        newGameBtn.setFont(new Font("Bebas Neue", Font.PLAIN, 35));
        newGameBtn.setBackground(Color.gray);
        this.pvcPanel.add(newGameBtn);
        this.newGameBtn.addActionListener(this);


        //set up title
        title =new JLabel("mankala game PvC");
        title.setBounds(650,100,475,100);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        pvcPanel.add(title);



    }

    /**
     * Updates the GUI according to the game status.
     * If the game is a draw, displays "Draw".
     * If player A has won, displays "Player A Won".
     * If player B has won, displays "Player B Won".
     * Disables all buttons if the game has ended.
     * @param Gstat the game status to display
     */
    public void GameOver1v1(Board.e_GStatus Gstat){
        if(Gstat== Board.e_GStatus.DRAW)
        {
            this.title.setText("Draw");
        } else if (Gstat== Board.e_GStatus.PLAYER_A_WON) {
            this.title.setText("computer player Won");
        } else if (Gstat== Board.e_GStatus.PLAYER_B_WON) {
            this.title.setText("human player Won");
        }
        if (Gstat!= Board.e_GStatus.NOT_FINISHED)
        {
            for (JButton button: pitsBtns) {
                button.setEnabled(false);
            }
        }

    }

}
