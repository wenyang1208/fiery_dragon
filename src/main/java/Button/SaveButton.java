package Button;

import Game.Game;
import Game.SaveSlotsMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SaveButton extends JButton implements ActionListener {

  private Game game;
  private JFrame frame;
  public SaveButton(JFrame frame, Game game) {
    setVisible(true);
    setBackground(new Color(255, 153, 0));
    setText("SAVE");
    setForeground(Color.WHITE);
    this.game = game;
    this.frame = frame;
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    SaveSlotsMenu slotsMenu = new SaveSlotsMenu(game);
    slotsMenu.setVisible(true);
  }
}

