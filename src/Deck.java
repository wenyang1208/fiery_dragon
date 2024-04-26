import java.util.ArrayList;
import java.util.Collections;

public class Deck {

  private ArrayList<ChitCard> chitCards;

  public Deck(){
    this.chitCards = new ArrayList<ChitCard>();
  }

  public ArrayList<ChitCard> getChitCards(){
    return this.chitCards;
  }
  public void createFullDeck(){
    for (Animal animal : AnimalFactory.createChitCardAnimal()){
      for (int j = 1; j < animal.getMaxVal()+1; j++){
        this.chitCards.add(new ChitCard(animal, j));
      }
    }
  }

  public void shuffleDeck(){
    Collections.shuffle(chitCards);
  }

  public void startGame(){
    createFullDeck();
    shuffleDeck();
  }
}
