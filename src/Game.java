import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Game extends JFrame {
  private static final int boardWidth = 750;
  private static final int boardHeight = 750;
  JLabel background;


  public Game(){
    initialisingBackground();
    setTitle("Fiery Dragon");
    setVisible(true);
    setSize(boardWidth,boardHeight);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void initialisingBackground(){
    ImageIcon img = new ImageIcon(this.getClass().getResource("/Project_cartoon/Background.jpg"));
    background = new JLabel("", img, JLabel.CENTER);
    background.setSize(boardWidth,boardHeight);
    GameSetup gameSetup = new GameSetup();
    add(gameSetup);
    add(background);
  }

}
