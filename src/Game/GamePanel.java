package Game;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * The GameSetup class represents the setup of the game board,
 * including chit card panels, volcano card panels, and cave panels.
 */
public class GamePanel extends JPanel {
    public final static int WIDTH = Game.boardWidth-300;
    public final static int HEIGHT = Game.boardHeight;
    private JPanel chitCardPanel;

    /**
     * Constructs a new GameSetup object.
     */
    public GamePanel() {
        setBackground(Color.BLACK);
        setBounds(30, 0, WIDTH, HEIGHT);
        setLayout(null);
        setupChitCardPanel();
    }
    /**
     * Retrieves the chit card panel.
     *
     * @return The chit card panel.
     */
    public JPanel getChitCardPanel() {
        return chitCardPanel;
    }

    /**
     * Sets up the panel to display the chit cards
     * */
    public void setupChitCardPanel() {
        chitCardPanel = new JPanel();
        chitCardPanel.setLayout(new GridLayout(5, 4));
        chitCardPanel.setBackground(Color.BLACK);
        chitCardPanel.setBounds(WIDTH/3, HEIGHT/3, (GamePanel.WIDTH+ GamePanel.HEIGHT)/6, (GamePanel.WIDTH+ GamePanel.HEIGHT)/6);
        add(chitCardPanel);
    }

}
