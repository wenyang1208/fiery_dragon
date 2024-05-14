package Button;

import Game.Game;
import Game.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 * The RestartButton class represents the button component used to restart the entire game
 * */
public class RestartButton extends JButton implements ActionListener {
    private JFrame frame;
    private Game game;

    public RestartButton(JFrame frame,Game game){
        this.frame = frame;
        this.game = game;
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("RESTART");
        setForeground(Color.WHITE);
        addActionListener(this);
    }

    /**
     * Removes current game from view and displays a new game to be played.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
//      game.setVisible(false);
//      game = new Game();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Game(frame,game.getPlayers()));
        frame.revalidate();
        frame.repaint();
        String gameRulesMessage = "Game has been restarted";

        // Show the message dialog
        JOptionPane.showMessageDialog(frame, gameRulesMessage, "", JOptionPane.INFORMATION_MESSAGE);
    }
}
