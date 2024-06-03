package Button;

import Game.Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LoadButton extends JButton implements ActionListener {
  private Game game;
  private JFrame frame;
  public LoadButton(JFrame frame, Game game) {
    setVisible(true);
    setBackground(new Color(255, 153, 0));
    setText("LOAD");
    setForeground(Color.WHITE);
    this.game = game;
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
//      JFileChooser fileChooser = new JFileChooser();
//      int option = fileChooser.showOpenDialog(frame);
//      if (option == JFileChooser.APPROVE_OPTION) {
//        Game loadedGame = loadGame(fileChooser.getSelectedFile().getPath(), frame);
//        if (loadedGame != null) {
//          frame.getContentPane().removeAll();
//          frame.getContentPane().add(loadedGame);
//          frame.revalidate();
//          frame.repaint();
//        }
//      }
    }
}

