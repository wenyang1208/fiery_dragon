package Controller;

import Animal.AnimalFactory;
import Game.GamePanel;
import GameBoardComponent.VolcanoCard;
import java.util.ArrayList;

/**
 * The VolcanoCardController class manages the configuration and initialization of volcano cards on the game board.
 */
public class VolcanoCardController {
  private ArrayList<VolcanoCard> volcanoCards;
  private ArrayList<VolcanoCard> volcanoCardsNearToCave;
  private GamePanel gamePanel;
  public final static int numberOfCards = 8;
  public final static int numberOfSquaresInACard = 3;
  public final static int cardSize = (GamePanel.WIDTH+ GamePanel.HEIGHT)/(numberOfSquaresInACard*numberOfCards) + 10;

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
//    int cardWidth = (GameSetup.WIDTH+GameSetup.HEIGHT)/(numberOfSquaresInACard*numberOfCards) + 10;
//    int cardHeight = (GameSetup.WIDTH+GameSetup.HEIGHT)/(numberOfSquaresInACard*numberOfCards) + 10;
    int centerX = GamePanel.WIDTH / 2;
    int centerY = GamePanel.HEIGHT / 2;
    int radius = (GamePanel.WIDTH+ GamePanel.HEIGHT)/5;
    int cardNumber = AnimalFactory.createVolcanoCardAnimal(numberOfCards,
        numberOfSquaresInACard).size();
    for (int i = 0; i < cardNumber; i++) {
      double angle = 2 * Math.PI * i / cardNumber;
      int x = centerX + (int) (radius * Math.sin(angle)) - cardSize / 2;
      int y = centerY + (int) (radius * Math.cos(angle)) - cardSize / 2;
      VolcanoCard volcanoCard = new VolcanoCard(AnimalFactory.createVolcanoCardAnimal(numberOfCards, numberOfSquaresInACard).get(i), i,cardSize,cardSize);
      this.volcanoCards.add(volcanoCard);
      if(i % (int)(numberOfCards*numberOfSquaresInACard/4) == numberOfSquaresInACard){
        this.volcanoCardsNearToCave.add(volcanoCard);
      }
      volcanoCard.setBounds(x,y,cardSize,cardSize);
      getGamePanel().add(volcanoCard);
    }
  }

  public ArrayList<VolcanoCard> getVolcanoCardsNearToCave(){
    return this.volcanoCardsNearToCave;
  }
  public ArrayList<VolcanoCard> getVolcanoCards(){return this.volcanoCards;}

  public GamePanel getGamePanel() {
    return this.gamePanel;
  }
}


