package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel implements ActionListener {

    private JPanel startPanel;//panel
    private JButton buttonPvP;//button for 1v1 game
    private JButton buttonPvC;
    private JLabel label;//title
    private GUI mygui;//the UI.GUI of the game

    public StartPanel(GUI g) {
        this.mygui=g;

        Color myColor = new Color(173, 216, 230);
        startPanel =new JPanel();
        buttonPvP =new JButton("play 1v1 (2 human players)");
        buttonPvC =new JButton("play 1vC (2 human players)");
        label=new JLabel("mankala game");

        label.setBounds(750,300,375,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 50));


        buttonPvP.addActionListener(this);
        buttonPvP.setBounds(800,500,275,75);
        buttonPvP.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        buttonPvP.setBackground(Color.lightGray);

        buttonPvC.addActionListener(this);
        buttonPvC.setBounds(800,600,275,75);
        buttonPvC.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        buttonPvC.setBackground(Color.lightGray);



        startPanel.setLayout(null);
        startPanel.add(buttonPvP);
        startPanel.add(buttonPvC);
        startPanel.add(label);
        startPanel.setBackground(myColor);


    }
    public JPanel getPanel()
    {
        return this.startPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonPvP)
            this.mygui.gamePvP();
        if(e.getSource()== buttonPvC)
            this.mygui.gamePvC();

    }
}
