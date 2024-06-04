package Game;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class GameSlot extends JButton implements ActionListener {
  private Game game;
  private int id;

  public GameSlot(int id, Game game) {
    this.id = id;
    this.game = game;
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    GameState gameState = new GameState();
    gameState.setPlayers(game.getPlayers());
    try{
      SaveLoad.save(gameState,"Game");
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
