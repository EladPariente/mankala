import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI  {
    private JFrame frame;
    private JPanel panel;
    private StartPanel sPanel;
    private Panel1v1 panel1v1;
    public GUI()
    {

        frame=new JFrame();


        sPanel=new StartPanel(this);
        panel=sPanel.getPanel();


        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mankala Game");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        frame.setVisible(true);
    }

    public void game1v1()
    {
        frame.remove(panel);

        panel1v1=new Panel1v1(this);
        panel=panel1v1.getPanel();

        frame.add(panel,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }



}
