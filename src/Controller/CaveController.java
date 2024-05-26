package Controller;

import Animal.AnimalFactory;
import Game.GamePanel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import GameBoardComponent.Cave;
import java.util.ArrayList;
import GameBoardComponent.VolcanoCard;
import java.util.Set;
import javax.swing.SpringLayout;

/**
 * The CaveController class manages the configuration and initialization of caves on the game board.
 */
public class CaveController {

  private ArrayList<Cave> caves;
  private ArrayList<String> players;
  private GamePanel gamePanel;
  private HashMap<Cave, Integer> cavesHashMap;
  private Set<String> addedCaves;

  /**
   * Constructs a new CaveController with the specified GamePanel object, list of volcano cards, and
   * list of players.
   *
   * @param gamePanel    The GamePanel object representing the game setup.
   * @param volcanoCards The list of volcano cards.
   * @param players      The list of players.
   */
  public CaveController(GamePanel gamePanel, ArrayList<VolcanoCard> volcanoCards,
      ArrayList<String> players) {
    this.gamePanel = gamePanel;
    this.players = players;
    this.caves = new ArrayList<>();
    this.cavesHashMap = new HashMap<>();
    this.addedCaves = new HashSet<>();
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
   * Gets the game panel associated with this controller.
   *
   * @return The GamePanel object.
   */
  public GamePanel getGamePanel() {
    return this.gamePanel;
  }

  /**
   * Initializes the setup of caves on the game board.
   */
  public void initialiseCaveSetup(ArrayList<VolcanoCard> volcanoCards) {
    int caveSize = VolcanoCardController.cardSize;
    for (int i = 0; i < AnimalFactory.createCaveAnimal().size(); i++) {
      Cave cave = new Cave(AnimalFactory.createCaveAnimal().get(i), i, caveSize);
      this.cavesHashMap.put(cave, i);
    }
    List<Integer> positionSeq = generateSequence(VolcanoCardController.numberOfCards * VolcanoCardController.numberOfSquaresInACard);
    for (String player : players) {
      for (int i = 0; i < AnimalFactory.createCaveAnimal().size(); i++) {
        Cave cave = new Cave(AnimalFactory.createCaveAnimal().get(i), i, caveSize);
        if (player.equals(AnimalFactory.createCaveAnimal().get(i).getClass().getSimpleName())) {
          this.caves.add(cave);
          setCavesPositions(cave, volcanoCards,i,caveSize,positionSeq);
          break;
        }
      }
    }

    for (Entry<Cave, Integer> entry : cavesHashMap.entrySet()) {
      setCavesPositions(entry.getKey(), volcanoCards, entry.getValue(), caveSize,positionSeq);
    }
  }

  private void addCaveToPanel(Cave cave) {
    if (!addedCaves.contains(cave.getAnimal().getName())) {
      getGamePanel().add(cave);
      addedCaves.add(cave.getAnimal().getName());
    }
  }

  private static List<Integer> generateSequence(int n) {
    List<Integer> sequence = new ArrayList<>();
    int current = 1; // Starting point of the sequence

    while (current <= n) {
      sequence.add(current);
      current += 3; // Increment by 3
    }
    return sequence;
  }

  public void setCavesPositions(Cave cave, ArrayList<VolcanoCard> volcanoCards, int i, int caveSize, List<Integer> positionSeq) {
    int positionIndex = 0;
    for (int j = 0; j < positionSeq.size(); j++) {
      int element = positionSeq.get(j);
      if (volcanoCards.get(i).getPosition() == element) {
        positionIndex =  j;
        break;
      }
    }
    int[] offsetX = {0, -1, -1, -1, 0, 1, 1, 1};
    int[] offsetY = {1, 1, 0, -1, -1, -1, 0, 1};
    int x = volcanoCards.get(i).getX() + offsetX[positionIndex] * volcanoCards.get(i).getWidth();
    int y = volcanoCards.get(i).getY() + offsetY[positionIndex] * volcanoCards.get(i).getHeight();

    cave.setBounds(x, y, caveSize, caveSize);
    addCaveToPanel(cave);
    }
}

