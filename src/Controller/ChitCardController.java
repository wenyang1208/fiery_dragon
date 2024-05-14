package Controller;

import Game.GameSetup;
import GameBoardComponent.ChitCard;
import GameBoardComponent.Deck;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
/**
 * The ChitCardController class manages the functionality related to chit cards in the game.
 * It implements the MouseListener interface to handle mouse events on chit card images.
 */
public class ChitCardController implements MouseListener {
  private Deck deck;
  private GameSetup chitCardView;
  private Map<JLabel, Integer> labelIndexMap;
  private JLabel chitCardImage;
  private ArrayList<ChitCard> flippedChitCards;
  /**
   * Constructs a new ChitCardController with default settings.
   */
  public ChitCardController(){
    this.chitCardView = new GameSetup();
    this.labelIndexMap = new HashMap<>();
    this.flippedChitCards = new ArrayList<>();
  }
  /**
   * Initializes the setup of chit cards.
   */
  public void initialiseChitCardSetup(){
    deck = new Deck();
    deck.startGame();
    for (int i = 0; i < deck.getChitCards().size(); i++) {
      chitCardImage = new JLabel();
      chitCardImage.addMouseListener(this);
      deck.getChitCards().get(i).setIsFlipped(false);
      chitCardImage.setIcon(deck.getChitCards().get(i).getImage());
      chitCardView.getChitCardPanel().add(chitCardImage);
      labelIndexMap.put(chitCardImage, i);
    }
  }
  /**
   * Retrieves the GameSetup object associated with this ChitCardController.
   *
   * @return The GameSetup object associated with this ChitCardController.
   */
  public GameSetup getGameSetup(){
    return chitCardView;
  }
  /**
   * Retrieves the list of flipped chit cards.
   *
   * @return The list of flipped chit cards.
   */
  public ArrayList<ChitCard> getFlippedChitCards(){
    return this.flippedChitCards;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    JLabel clickedLabel = (JLabel) e.getSource();
    int index = labelIndexMap.get(clickedLabel);
    clickedLabel.setIcon(deck.getChitCards().get(index).getImage());
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
