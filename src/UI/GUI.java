package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * The GUI class manages the user interface for the Mancala game.
 */
public class GUI {

    private JFrame gameFrame;           // The main window frame
    private JPanel currentPanel;           // The current panel being displayed
    private StartPanel startPanel;  // The panel for the game's start screen
    private PanelPvP pvpPanel;      // The panel for the player vs. player game mode
    private PanelPvC pvcPanel;      // The panel for the player vs. computer game mode

    /**
     * Constructs a new instance of the GUI class and initializes the main window.
     */
    public GUI() {
        gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setTitle("Mancala Game");
        gameFrame.pack();
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startPanel();
        gameFrame.setVisible(true);
    }

    /**
     * Displays the start panel in the main window.
     */
    public void startPanel() {
        if (currentPanel != null)
            gameFrame.remove(currentPanel);

        startPanel = new StartPanel(this);
        currentPanel = startPanel.getPanelStart();

        gameFrame.add(currentPanel, BorderLayout.CENTER);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    /**
     * Displays the player vs. player game panel in the main window.
     */
    public void gamePvP() {
        gameFrame.remove(currentPanel);

        pvpPanel = new PanelPvP(this);
        currentPanel = pvpPanel.getPvpPanel();

        gameFrame.add(currentPanel, BorderLayout.CENTER);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    /**
     * Displays the player vs. computer game panel in the main window.
     */
    public void gamePvC() {
        gameFrame.remove(currentPanel);

        pvcPanel = new PanelPvC(this);
        currentPanel = pvcPanel.getPvcPanel();

        gameFrame.add(currentPanel, BorderLayout.CENTER);
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}
