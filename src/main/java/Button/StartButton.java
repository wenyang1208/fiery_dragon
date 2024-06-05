package Button;

import Game.PlayerInformation;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * The StartButton class represents the button component used to start the game.
 * */
public class StartButton extends JButton implements ActionListener {
    private JFrame frame;

    /**
     * Constructs the StartButton object, specifying its visibility,
     * foreground and background color, and text inside the button.
     * Along with actions performed when button is clicked on.
     * */
    public StartButton(JFrame frame){
        this.frame = frame;
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("NEW GAME");
        setForeground(Color.WHITE);
        addActionListener(this);
    }

    /**
     * Redirects player from the Home page to the Game page
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        new PlayerInformation(frame);
    }
}
