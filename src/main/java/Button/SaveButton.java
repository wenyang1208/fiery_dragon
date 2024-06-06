package Button;

import Game.Game;
import data.SaveLoad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SaveButton extends JButton implements ActionListener {

  private Game game;
  private JFrame frame;
  private SaveLoad saveLoad;

  public SaveButton(SaveLoad saveLoad) {
    setVisible(true);
    setBackground(new Color(255, 153, 0));
    setText("SAVE");
    setForeground(Color.WHITE);
    addActionListener(this);

    this.saveLoad = saveLoad;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Game has been saved!");
    saveLoad.save();
  }
}


