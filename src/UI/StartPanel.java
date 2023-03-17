package UI;

import UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel implements ActionListener {

    private JPanel startPanel;//panel
    private JButton button1v1;//button for 1v1 game
    private JLabel label;//title
    private GUI mygui;//the UI.GUI of the game

    public StartPanel(GUI g) {
        this.mygui=g;

        Color myColor = new Color(173, 216, 230);
        startPanel =new JPanel();
        button1v1 =new JButton("play 1v1 (2 human players)");
        label=new JLabel("mankala game");

        label.setBounds(750,300,375,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 50));


        button1v1.addActionListener(this);
        button1v1.setBounds(800,500,275,75);
        button1v1.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        button1v1.setBackground(Color.lightGray);



        startPanel.setLayout(null);
        startPanel.add(button1v1);
        startPanel.add(label);
        startPanel.setBackground(myColor);


    }
    public JPanel getPanel()
    {
        return this.startPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1v1)
            this.mygui.game1v1();

    }
}
