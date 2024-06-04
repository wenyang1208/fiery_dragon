package Controller;

import Animal.Animal;
import Animal.AnimalFactory;
import Game.GamePanel;
import GameBoardComponent.VolcanoCard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The VolcanoCardController class manages the configuration and initialization of volcano cards on the game board.
 */
public class VolcanoCardController {
  private ArrayList<VolcanoCard> volcanoCards;
  private ArrayList<VolcanoCard> volcanoCardsNearToCave;
  private GamePanel gamePanel;
  public final static int numberOfCards = 8;
  public final static int numberOfSquaresInACard = 3;
  public final static int cardSize = (GamePanel.WIDTH+ GamePanel.HEIGHT)/(numberOfSquaresInACard*numberOfCards)-5;

  /**
   * Constructs a new VolcanoCardController with the specified GameSetup object.
   *
   * @param gamePanel The GameSetup object representing the game setup.
   */
  public VolcanoCardController(GamePanel gamePanel) {
    this.volcanoCards = new ArrayList<VolcanoCard>();
    this.volcanoCardsNearToCave = new ArrayList<VolcanoCard>();
    this.gamePanel = gamePanel;
    initialiseVolcanoCards();
  }

  /**
   * Initializes the setup of volcano cards on the game board.
   */
  public void initialiseVolcanoCards() {
    int centerX = GamePanel.WIDTH / 2;
    int centerY = GamePanel.HEIGHT / 2;
    int radius = (GamePanel.WIDTH+ GamePanel.HEIGHT)/6;
    ArrayList<Animal> animals = AnimalFactory.createVolcanoCardAnimal();
    int cardNumber = animals.size();
    Collections.reverse(animals);
    ArrayList<List<Integer>> positions = new ArrayList<>();
    for (int i = 0; i < cardNumber-4; i++) {
      double angle = 2 * Math.PI * i / (cardNumber-4);
      int x = centerX + (int) (radius * Math.sin(angle)) - cardSize / 2;
      int y = centerY + (int) (radius * Math.cos(angle)) - cardSize / 2;
      List<Integer> position = new ArrayList<>();
      position.add(x);
      position.add(y);
      positions.add(position);
    }
    for (int i = 0; i < numberOfSquaresInACard-1; i++){
      List<Integer> position = positions.remove(0);
      positions.add(position);
    }
    System.out.println(positions);
    for (int i = 0; i < cardNumber; i++) {
      if(!animals.get(i).getName().equals("pirate_dragon")){
        VolcanoCard volcanoCard;
        if(i > 1 && animals.get(i-2).getName().equals("pirate_dragon")){
          volcanoCard = new VolcanoCard(animals.get(i), 0,cardSize,cardSize,true);
        }else{
          volcanoCard = new VolcanoCard(animals.get(i), 0,cardSize,cardSize,false);
        }
        this.volcanoCards.add(volcanoCard);
      }
    }
    for(int i = 0; i < this.volcanoCards.size(); i++){
      VolcanoCard volcanoCard = this.volcanoCards.get(i);
      volcanoCard.setPosition((this.volcanoCards.size()-1)-i);
      if(volcanoCard.isCaveContained()){
        this.volcanoCardsNearToCave.add(volcanoCard);
      }
      volcanoCard.setBounds(positions.get(i).get(0),positions.get(i).get(1),cardSize,cardSize);
      getGamePanel().add(volcanoCard);
    }
    Collections.reverse(this.volcanoCards);
    Collections.reverse(this.volcanoCardsNearToCave);
  }

  /**
   * Gets the list of volcano cards that are near to the cave.
   *
   * @return An ArrayList of VolcanoCard objects near to the cave.
   */
  public ArrayList<VolcanoCard> getVolcanoCardsNearToCave(){
    return this.volcanoCardsNearToCave;
  }

  /**
   * Gets the list of all volcano cards.
   *
   * @return An ArrayList of all VolcanoCard objects.
   */
  public ArrayList<VolcanoCard> getVolcanoCards(){return this.volcanoCards;}

  /**
   * Gets the game panel associated with this controller.
   *
   * @return The GamePanel object.
   */
  public GamePanel getGamePanel() {
    return this.gamePanel;
  }
}


