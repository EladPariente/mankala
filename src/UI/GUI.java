package UI;

import javax.swing.*;
import java.awt.*;


public class GUI  {
    private JFrame frame;//frame
    private JPanel panel;//the panel that presented
    private StartPanel sPanel;//manage the start panel
    private Panel1v1 panel1v1;//manage the 1v1 panel

    public GUI() {

        frame=new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mankala Game");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.startPanel();

        frame.setVisible(true);
    }

    public void startPanel() {
        if(panel!=null)
            frame.remove(panel);

        sPanel=new StartPanel(this);
        panel=sPanel.getPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void game1v1() {
        frame.remove(panel);

        panel1v1=new Panel1v1(this);
        panel=panel1v1.getPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


    }



}
