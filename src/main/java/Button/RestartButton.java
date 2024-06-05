package Button;

import Game.Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The RestartButton class represents the button component used to restart the entire game
 * */
public class RestartButton extends JButton implements ActionListener {
    private JFrame frame;
    private Game game;

    /**
     * Constructs the RestartButton object, specifying its visibility,
     * foreground and background color, and text inside the button.
     * Along with actions performed when button is clicked on.
     * */
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
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Game(frame,game.getPlayers(), game.getTimeLimit()));
        frame.revalidate();
        frame.repaint();
        String gameRulesMessage = "Game has been restarted";

        // Show the message dialog
        JOptionPane.showMessageDialog(frame, gameRulesMessage, "", JOptionPane.INFORMATION_MESSAGE);
    }
}

