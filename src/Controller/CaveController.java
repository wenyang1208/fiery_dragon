package Controller;

import Animal.Animal;
import Animal.AnimalFactory;
import Game.GamePanel;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JLabel;
import GameBoardComponent.Cave;
import java.util.ArrayList;
import GameBoardComponent.ChitCard;
import GameBoardComponent.Token;
import GameBoardComponent.VolcanoCard;

/**
 * The CaveController class manages the configuration and initialization of caves on the game board.
 */
public class CaveController {

  private ArrayList<Cave> caves;
  private ArrayList<String> players;
  private GamePanel gamePanel;
  private HashMap<Cave,Integer> cavesHashMap;
  /**
   * Constructs a new CaveController with the specified GameSetup object.
   *
   * @param gamePanel The GameSetup object representing the game setup.
   */
  public CaveController(GamePanel gamePanel, ArrayList<VolcanoCard> volcanoCards,ArrayList<String> players) {
    this.gamePanel = gamePanel;
    this.players = players;
    this.caves = new ArrayList<>();
    this.cavesHashMap =new HashMap<>();
    initialiseCaveSetup(volcanoCards);
  }
  /**
   * Retrieves the list of caves.
   *
   * @return The list of caves.
   */
  public ArrayList<Cave> getCaves() {
    return caves;
  }
  /**
   * Retrieves the GameSetup object associated with this CaveController.
   *
   * @return The GameSetup object associated with this CaveController.
   */
  public GamePanel getGamePanel() {
    return this.gamePanel;
  }


  /**
   * Initializes the setup of caves on the game board.
   */
  public void initialiseCaveSetup(ArrayList<VolcanoCard> volcanoCards) {
    int caveSize = VolcanoCardController.cardSize;
    for(int i=0; i < AnimalFactory.createCaveAnimal().size(); i++){
      Cave cave = new Cave(AnimalFactory.createCaveAnimal().get(i),i,caveSize);
      this.cavesHashMap.put(cave,i);
    }
    for(String player:players){
      for (int i=0; i < AnimalFactory.createCaveAnimal().size(); i++) {
        Cave cave = new Cave(AnimalFactory.createCaveAnimal().get(i),i,caveSize);
        if (player.equals(AnimalFactory.createCaveAnimal().get(i).getClass().getSimpleName())) {
          this.caves.add(cave);
          int[] offsetsX = {-caveSize, -caveSize, caveSize, caveSize};
          int[] offsetsY = {caveSize, -caveSize, -caveSize, caveSize};
          int xOffset = offsetsX[i % 4];
          int yOffset = offsetsY[i % 4];
          cave.setBounds(volcanoCards.get(i).getX()+xOffset,volcanoCards.get(i).getY()+yOffset,caveSize,caveSize);
          getGamePanel().add(cave);
          break;
        }
      }
    }
    for(Entry<Cave, Integer> entry : cavesHashMap.entrySet()){
      int[] offsetsX = {-caveSize, -caveSize, caveSize, caveSize};
      int[] offsetsY = {caveSize, -caveSize, -caveSize, caveSize};
      int xOffset = offsetsX[entry.getValue() % 4];
      int yOffset = offsetsY[entry.getValue() % 4];
      entry.getKey().setBounds(volcanoCards.get(entry.getValue()).getX()+xOffset,volcanoCards.get(entry.getValue()).getY()+yOffset,caveSize,caveSize);
      getGamePanel().add(entry.getKey());
    }
  }
}
