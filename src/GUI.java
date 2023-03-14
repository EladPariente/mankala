import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel1;
    private JButton button;
    private JLabel label;
    public GUI()
    {
        Color myColor = new Color(173, 216, 230);
        frame=new JFrame();
        panel1 =new JPanel();
        button=new JButton("play 1v1 (2 human players)");
        label=new JLabel("mankala game");

        label.setBounds(750,300,375,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 50));


        button.addActionListener(this);
        button.setBounds(800,500,275,75);
        button.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        button.setBackground(Color.lightGray);


        //panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel1.setLayout(null);
        panel1.add(button);
        panel1.add(label);
        panel1.setBackground(myColor);


        frame.add(panel1,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mankala Game");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.panel1.setVisible(false);
    }
}
