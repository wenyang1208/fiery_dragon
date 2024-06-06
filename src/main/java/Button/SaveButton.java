package Button;

import Game.Game;
import data.SaveLoad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * The SaveButton class represents the component used to save the current game
 * */
public class SaveButton extends JButton implements ActionListener {

  private Game game;
  private JFrame frame;
  private SaveLoad saveLoad;


  /**
   * Constructs the SaveButton object, specifying its visibility,
   * foreground and background color, and text inside the button
   * */
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


