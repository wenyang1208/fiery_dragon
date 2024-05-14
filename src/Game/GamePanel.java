package Game;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * The GameSetup class represents the setup of the game board, including chit card panels, volcano card panels, and cave panels.
 */
public class GamePanel extends JPanel {
    public final static int WIDTH = Game.boardWidth-200;
    public final static int HEIGHT = Game.boardHeight-100;
    private JPanel chitCardPanel;

    /**
     * Constructs a new GameSetup object.
     */
    public GamePanel() {
        setBackground(Color.BLACK);
        setBounds(30, 30, WIDTH, HEIGHT);
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

    public void setupChitCardPanel() {
        chitCardPanel = new JPanel();
        chitCardPanel.setLayout(new GridLayout(4, 4));
        chitCardPanel.setBackground(Color.BLACK);
        chitCardPanel.setBounds(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/2);
        add(chitCardPanel);
    }

}
