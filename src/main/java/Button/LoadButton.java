package Button;

import Game.Game;
import data.SaveLoad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LoadButton extends JButton implements ActionListener {
  private SaveLoad saveLoad;
  private Game game;
  private JFrame frame;

  public LoadButton(SaveLoad saveLoad, Game game, JFrame frame) {
    setVisible(true);
    setBackground(new Color(255, 153, 0));
    setText("LOAD");
    setForeground(Color.WHITE);
    addActionListener(this);

    this.saveLoad = saveLoad;
    this.game = game;
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Game has been loaded!");
    saveLoad.load();

    // Refresh the game panel
    game.refreshGame();
    frame.revalidate();
    frame.repaint();
  }
}
