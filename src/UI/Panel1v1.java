package UI;

import UI.GUI;
import logic.Board;
import logic.Game1v1;
import logic.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Panel1v1  implements ActionListener {

    private JButton[]buttons;//pit buttons
    private JPanel panel;//panel
    private JLabel label13;//scorring pit
    private JLabel label6;//scorring pit
    Map<JButton, Runnable> actions;//lambda actions pits
    private JLabel label;//title
    private GUI mygui;//the UI.GUI of the game
    private Game1v1 game;//1v1 middle man (manage the game)
    private JButton returnB;//button to return to the start
    private JButton newGB;//button to restart game

    public void BClicked(int buttonIndex) {

        GameStatus status= this.game.stepGame(buttonIndex);
        int b[]=this.game.getBoard();
        updateButtons(b,status.isTurn());

        if(status.getStatus()!= Board.e_GStatus.NOT_FINISHED)
        {
            this.GameOver1v1(status.getStatus());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnB)
            this.mygui.startPanel();
        else if (e.getSource()==newGB)
            this.mygui.game1v1();
    }

    private void updateButtons(int[] b,boolean turn){
        for(int i=0;i<6;i++)
        {
            this.buttons[i].setText(""+b[i]);
            this.buttons[i].setEnabled(turn);
        }
        for(int i=11;i>=6;i--)
        {
            this.buttons[i].setText(""+b[i+1]);
            this.buttons[i].setEnabled(!turn);
        }
        label13.setText(""+b[13]);
        label6.setText(""+b[6]);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public Panel1v1(GUI g) {

        this.mygui=g;
        this.game=new Game1v1();



        //set up panel
        Color myColor = new Color(173, 216, 230);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(myColor);

        //set up scorring pits
        label13 =new JLabel("0");
        label13.setBounds(200,300,150,450);
        label13.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        label13.setBackground(Color.orange);
        label13.setOpaque(true);
        panel.add(label13);

        label6 =new JLabel("0");
        label6.setBounds(1600,300,150,450);
        label6.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setBackground(Color.orange);
        label6.setOpaque(true);
        panel.add(label6);



        //set up pit buttons
        actions = new HashMap<>();

        this.buttons=new JButton[12];
        for(int i=0;i<6;i++)
        {
            buttons[i]=new JButton("4");
            final int x=i;
            buttons[i].setBounds(400+(x)*200,300,150,150);
            buttons[i].setFont(new Font("Bebas Neue", Font.PLAIN, 70));
            buttons[i].setBackground(Color.orange);
            actions.put(buttons[i], () -> BClicked(x));
            this.panel.add(buttons[i]);
        }

        for(int i=6;i<12;i++)
        {
            buttons[i]=new JButton("4");
            final int x=i+1;
            buttons[i].setBounds(400+(11-i)*200,600,150,150);
            buttons[i].setFont(new Font("Bebas Neue", Font.PLAIN, 70));
            buttons[i].setBackground(Color.orange);
            actions.put(buttons[i], () -> BClicked(x));
            this.panel.add(buttons[i]);
        }

        for (JButton button : actions.keySet()) {
            button.addActionListener(e -> {
                Runnable action = actions.get(e.getSource());
                if (action != null) {
                    action.run();
                }
            });
        }

        updateButtons(game.getBoard(),true);



        //set up return button
        this.returnB=new JButton("return to menu");
        returnB.setBounds(150,850,275,75);
        returnB.setFont(new Font("Bebas Neue", Font.PLAIN, 35));
        returnB.setBackground(Color.gray);
        this.panel.add(returnB);
        this.returnB.addActionListener(this);


        //set up new game button
        this.newGB=new JButton("new game");
        newGB.setBounds(1550,850,275,75);
        newGB.setFont(new Font("Bebas Neue", Font.PLAIN, 35));
        newGB.setBackground(Color.gray);
        this.panel.add(newGB);
        this.newGB.addActionListener(this);


        //set up title
        label=new JLabel("mankala game");
        label.setBounds(750,100,375,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        panel.add(label);



    }
    public void GameOver1v1(Board.e_GStatus Gstat){
        if(Gstat== Board.e_GStatus.DRAW)
        {
            this.label.setText("Draw");
        } else if (Gstat== Board.e_GStatus.PLAYER_A_WON) {
            this.label.setText("Player A Won");
        } else if (Gstat== Board.e_GStatus.PLAYER_B_WON) {
            this.label.setText("Player B Won");
        }
        if (Gstat!= Board.e_GStatus.NOT_FINISHED)
        {
            for (JButton button: buttons) {
                button.setEnabled(false);
            }
        }

    }






}
