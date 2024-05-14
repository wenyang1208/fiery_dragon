package Button;

import Game.Game;
import Game.Home;
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
    public StartButton(JFrame frame){
        this.frame = frame;
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("START");
        setForeground(Color.WHITE);
        addActionListener(this);
    }

    /**
     * Redirects player from the Home page to the Game page
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
//    this.home.setVisible(false);
//    this.home.dispose();
//    Game game =
//    frame.getContentPane().removeAll();
//    frame.getContentPane().add(new PlayerInformation(frame));
//    frame.revalidate();
//    frame.repaint();
        new PlayerInformation(frame);
//    new Game();
    }
}
