package Game;

import Animal.AnimalFactory;
import Controller.CaveController;
import Controller.ChitCardController;
import Controller.VolcanoCardController;
import java.io.IOException;
import GameBoardComponent.Token;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Game class represents the main game window and manages the initialization of game components.
 */
public class Game extends JFrame{
  private static final int boardWidth = 750;
  private static final int boardHeight = 750;
  private ArrayList<Token> tokens = new ArrayList<>();
  private ChitCardController chitCardController;
  JLabel background;

  /**
   * Constructs a new Game object.
   *
   * @throws IOException If an I/O error occurs.
   */
  public Game() throws IOException {
    initialisingBackground();
    setTitle("Fiery Dragon");
    setVisible(true);
    setSize(boardWidth,boardHeight);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  /**
   * Initializes the background of the game window.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void initialisingBackground() throws IOException {
    ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("Project_cartoon/Background.jpg"));
    background = new JLabel("", img, JLabel.CENTER);
    background.setSize(boardWidth,boardHeight);

    chitCardController = new ChitCardController();
    chitCardController.initialiseChitCardSetup();
    // Cannot create new Game.GameSetup, so I pass the previous Game.GameSetup from the ChitCardContoller as the parameter to the controller.
    VolcanoCardController volcanoCardController = new VolcanoCardController(chitCardController.getGameSetup());
    volcanoCardController.initialiseVolcanoCards();

    CaveController caveController = new CaveController(volcanoCardController.getGameSetup());
    caveController.initialiseCaveSetup();

    for(int i = 0; i< AnimalFactory.createTokenAnimal().size(); i++){
      Token token = new Token(AnimalFactory.createTokenAnimal().get(i),i);
      tokens.add(token);
      token.setCurrentSqaure(caveController.getCaves().get(i));
      caveController.getCaves().get(i).addToken(token);
    }

    add(caveController.getGameSetup());
    add(chitCardController.getGameSetup());
    add(volcanoCardController.getGameSetup());
    add(background);
  }
}

