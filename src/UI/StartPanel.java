package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The StartPanel class creates a panel that contains buttons for the user to choose between playing against another player or playing against the computer, as well as a title for the game.
 */
public class StartPanel implements ActionListener {

    private JPanel panelStart; // the panel that contains the buttons and title
    private JButton btnPvP; // button for PvP game
    private JButton btnPvC; // button for PvC game
    private JLabel titleLabel; // title of the game
    private GUI gui; // the GUI of the game

    /**
     * Constructs the StartPanel object with buttons and a title
     * @param g the GUI of the game
     */
    public StartPanel(GUI g) {

        this.gui = g;

        // Setting the background color of the panel
        Color backgroundColor = new Color(173, 216, 230);

        // Creating the panel
        panelStart = new JPanel();

        // Creating the title
        titleLabel = new JLabel("Mankala Game");
        titleLabel.setBounds(750, 300, 375, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Bebas Neue", Font.BOLD, 50));

        // Creating the PvP button
        btnPvP = new JButton("Play 1v1 (2 human players)");
        btnPvP.addActionListener(this);
        btnPvP.setBounds(800, 500, 275, 75);
        btnPvP.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        btnPvP.setBackground(Color.lightGray);

        // Creating the PvC button
        btnPvC = new JButton("Play 1vC (1 human player vs. computer)");
        btnPvC.addActionListener(this);
        btnPvC.setBounds(750, 600, 400, 75);
        btnPvC.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
        btnPvC.setBackground(Color.lightGray);

        // Setting the layout and background color of the panel and adding the components to it
        panelStart.setLayout(null);
        panelStart.add(btnPvP);
        panelStart.add(btnPvC);
        panelStart.add(titleLabel);
        panelStart.setBackground(backgroundColor);

    }

    /**
     * Returns the StartPanel panel
     * @return the StartPanel panel
     */
    public JPanel getPanelStart() {
        return this.panelStart;
    }

    /**
     * Responds to the user clicking one of the buttons and switches the panel to the corresponding game mode
     * @param e the action event of clicking a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnPvP)
            this.gui.gamePvP();
        if(e.getSource()== btnPvC)
            this.gui.gamePvC();

    }
}
