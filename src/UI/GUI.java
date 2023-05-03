package UI;

import javax.swing.*;
import java.awt.*;


public class GUI  {
    private JFrame frame;//frame
    private JPanel panel;//the panel that presented
    private StartPanel sPanel;//manage the start panel
    private PanelPvP panelPvP;//manage the 1v1 panel
    private PanelPvC panelPvC;//manage the 1v1 panel

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

    public void gamePvP() {
        frame.remove(panel);

        panelPvP =new PanelPvP(this);
        panel= panelPvP.getPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


    }
    public void gamePvC() {
        frame.remove(panel);

        panelPvC =new PanelPvC(this);
        panel= panelPvC.getPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


    }



}
