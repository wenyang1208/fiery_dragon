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
   * Constructs a new ChitCardController with the specified GamePanel.
   *
   * @param gamePanel The GamePanel object representing the game setup.
   */
  public ChitCardController(GamePanel gamePanel){
    this.gamePanel = gamePanel;
    this.labelIndexMap = new HashMap<>();
  }

  /**
   * Gets the map of JLabel components to their corresponding indices.
   *
   * @return A map where the keys are JLabel components and the values are their corresponding indices.
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
    deck.shuffleDeck();
  }

  /**
   * Gets the map of JLabel components to their corresponding indices.
   *
   * @return A map where the keys are JLabel components and the values are their corresponding indices.
   */
  public Map<JLabel, Integer> getLabelIndexMap() {
    return labelIndexMap;
  }

  /**
   * Retrieves the GamePanel object associated with this ChitCardController.
   *
   * @return The GamePanel object associated with this ChitCardController.
   */
  public GamePanel getGamePanel(){
    return gamePanel;
  }

  /**
   * Retrieves the deck of chit cards.
   *
   * @return The Deck object containing the chit cards.
   */
  public Deck getDeck(){
    return this.deck;
  }
}
