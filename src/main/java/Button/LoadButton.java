package Button;

import Game.Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The LoadButton class represents the component used to load the previously exited game
 * */
public class LoadButton extends JButton implements ActionListener {

  /**
   * Constructs the LoadButton object, specifying its visibility,
   * foreground and background color, and text inside the button
   * */
  public LoadButton() {
    setVisible(true);
    setBackground(new Color(255, 153, 0));
    setText("LOAD");
    setForeground(Color.WHITE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    }
}

