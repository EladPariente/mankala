import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Panel1v1  {

    private JPanel panel;
    private JLabel label6;
    private JLabel label13;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;

    Map<JButton, Runnable> actions;

    private JLabel label;
    private GUI mygui;
    private Game1v1 game;

    public void BClicked(int buttonIndex)
    {

        boolean isGameOver=this.game.stepGame(buttonIndex);
        int b[]=this.game.getBoard();
        button0.setText(""+b[0]);
        button1.setText(""+b[1]);
        button2.setText(""+b[2]);
        button3.setText(""+b[3]);
        button4.setText(""+b[4]);
        button5.setText(""+b[5]);
        button7.setText(""+b[7]);
        button8.setText(""+b[8]);
        button9.setText(""+b[9]);
        button10.setText(""+b[10]);
        button11.setText(""+b[11]);
        button12.setText(""+b[12]);
        label6.setText(""+b[13]);
        label13.setText(""+b[6]);



//        int i=0;
//        for (JButton button : actions.keySet()) {
//            button.setText(""+b[i]);
//            i++;
//            if(i==6){
//                i++;
//            }
//        }
        if(isGameOver)
            this.label.setText("GG");
    }

    public JPanel getPanel()
    {
        return this.panel;
    }

    public Panel1v1(GUI g)
    {

        this.mygui=g;
        this.game=new Game1v1();

        actions = new HashMap<>();

        label=new JLabel("mankala game");
        label.setBounds(750,100,375,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 50));

        label6=new JLabel("0");
        label13=new JLabel("0");

        label6.setBounds(200,300,150,450);
        label6.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setBackground(Color.orange);
        label6.setOpaque(true);

        label13.setBounds(1600,300,150,450);
        label13.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        label13.setBackground(Color.orange);
        label13.setOpaque(true);




        button0=new JButton("4");
        button1=new JButton("4");
        button2=new JButton("4");
        button3=new JButton("4");
        button4=new JButton("4");
        button5=new JButton("4");
        button7=new JButton("4");
        button8=new JButton("4");
        button9=new JButton("4");
        button10=new JButton("4");
        button11=new JButton("4");
        button12=new JButton("4");

        //button0.addActionListener(this);
        button0.setBounds(400,300,150,150);
        button0.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button0.setBackground(Color.orange);

        //button1.addActionListener(this);
        button1.setBounds(600,300,150,150);
        button1.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button1.setBackground(Color.orange);

        //button2.addActionListener(this);
        button2.setBounds(800,300,150,150);
        button2.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button2.setBackground(Color.orange);

        //button3.addActionListener(this);
        button3.setBounds(1000,300,150,150);
        button3.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button3.setBackground(Color.orange);

        //button4.addActionListener(this);
        button4.setBounds(1200,300,150,150);
        button4.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button4.setBackground(Color.orange);

        //button5.addActionListener(this);
        button5.setBounds(1400,300,150,150);
        button5.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button5.setBackground(Color.orange);

        //button12.addActionListener(this);
        button12.setBounds(400,600,150,150);
        button12.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button12.setBackground(Color.orange);

        //button11.addActionListener(this);
        button11.setBounds(600,600,150,150);
        button11.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button11.setBackground(Color.orange);

        //button10.addActionListener(this);
        button10.setBounds(800,600,150,150);
        button10.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button10.setBackground(Color.orange);

        //button9.addActionListener(this);
        button9.setBounds(1000,600,150,150);
        button9.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button9.setBackground(Color.orange);

        //button8.addActionListener(this);
        button8.setBounds(1200,600,150,150);
        button8.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button8.setBackground(Color.orange);

        //button7.addActionListener(this);
        button7.setBounds(1400,600,150,150);
        button7.setFont(new Font("Bebas Neue", Font.PLAIN, 70));
        button7.setBackground(Color.orange);


        actions.put(button0, () -> BClicked(0));
        actions.put(button1, () -> BClicked(1));
        actions.put(button2, () -> BClicked(2));
        actions.put(button3, () -> BClicked(3));
        actions.put(button4, () -> BClicked(4));
        actions.put(button5, () -> BClicked(5));
        actions.put(button7, () -> BClicked(7));
        actions.put(button8, () -> BClicked(8));
        actions.put(button9, () -> BClicked(9));
        actions.put(button10, () -> BClicked(10));
        actions.put(button11, () -> BClicked(11));
        actions.put(button12, () -> BClicked(12));


        for (JButton button : actions.keySet()) {
            button.addActionListener(e -> {
                Runnable action = actions.get(e.getSource());
                if (action != null) {
                    action.run();
                }
            });
        }
            panel = new JPanel();
            panel.setLayout(null);
            panel.add(button0);
            panel.add(button1);
            panel.add(button2);
            panel.add(button3);
            panel.add(button4);
            panel.add(button5);
            panel.add(button7);
            panel.add(button8);
            panel.add(button9);
            panel.add(button10);
            panel.add(button11);
            panel.add(button12);
            panel.add(label6);
            panel.add(label13);
            panel.add(label);




    }





//    public void actionPerformed(ActionEvent e) {
//        JButton b = (JButton) e.getSource();
//    }

}
