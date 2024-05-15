package Controller;

import Game.GamePanel;
import GameBoardComponent.Deck;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 * The ChitCardController class manages the functionality related to chit cards in the game.
 * It implements the MouseListener interface to handle mouse events on chit card images.
 */
public class ChitCardController{
  private Deck deck;
  private GamePanel gamePanel;
  private Map<JLabel, Integer> labelIndexMap;
  private JLabel chitCardImage;
  /**
   * Constructs a new ChitCardController with default settings.
   */
  public ChitCardController(GamePanel gamePanel){
    this.gamePanel = gamePanel;
    this.labelIndexMap = new HashMap<>();
  }
  /**
   * Initializes the setup of chit cards.
   */
  public void initialiseChitCardSetup(){
    deck = new Deck();
    deck.createFullDeck();
    for (int i = 0; i < deck.getChitCards().size(); i++) {
      chitCardImage = new JLabel();
      deck.getChitCards().get(i).setIsFlipped(false);
      chitCardImage.setIcon(deck.getChitCards().get(i).getImage());
      gamePanel.getChitCardPanel().add(chitCardImage);
      labelIndexMap.put(chitCardImage, i);
    }
  }

  public Map<JLabel, Integer> getLabelIndexMap() {
    return labelIndexMap;
  }

  /**
   * Retrieves the GameSetup object associated with this ChitCardController.
   *
   * @return The GameSetup object associated with this ChitCardController.
   */
  public GamePanel getGamePanel(){
    return gamePanel;
  }

  /**
   * Retrieves the list of flipped chit cards.
   *
   * @return The list of flipped chit cards.
   */
  public Deck getDeck(){
    return this.deck;
  }
}
