package GameBoardComponent;

import Animal.Animal;
import Animal.AnimalFactory;
import Controller.VolcanoCardController;
import Game.GamePanel;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class represents a deck of chit cards used in the game.
 */
public class Deck {
  private ArrayList<ChitCard> chitCards;

  /**
   * Constructs a new Deck object.
   */
  public Deck(){
    this.chitCards = new ArrayList<ChitCard>();
  }

  /**
   * Retrieves the list of chit cards in the deck.
   *
   * @return The list of chit cards.
   */
  public ArrayList<ChitCard> getChitCards(){
    return this.chitCards;
  }

  /**
   * Creates a full deck of chit cards.
   */
  public void createFullDeck(){
    for (Animal animal : AnimalFactory.createChitCardAnimal()){
      for (int j = 1; j < animal.getMaxVal()+1; j++){
        this.chitCards.add(new ChitCard(animal, j, VolcanoCardController.cardSize));
      }
    }
  }

  /**
   * Shuffles the deck of chit cards.
   */
  public void shuffleDeck(){
    Collections.shuffle(chitCards);
  }
}
