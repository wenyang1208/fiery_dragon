package Controller;

import Animal.AnimalFactory;
import Game.GameSetup;
import GameBoardComponent.VolcanoCard;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * The VolcanoCardController class manages the configuration and initialization of volcano cards on the game board.
 */
public class VolcanoCardController {

  private ArrayList<VolcanoCard> volcanoCards;
  private GameSetup volcanoCardView;
  private final static int numberOfCards = 8;
  private final static int numberOfSquaresInACard = 3;
  private final static int numberOfDirection = 4;
  private final static int volcanoCardSize = numberOfCards * numberOfSquaresInACard / numberOfDirection;
  private JPanel leftPanel;
  private JPanel rightPanel;
  private JPanel UpPanel;
  private JPanel DownPanel;
  /**
   * Constructs a new VolcanoCardController with the specified GameSetup object.
   *
   * @param gameSetup The GameSetup object representing the game setup.
   */
  public VolcanoCardController(GameSetup gameSetup) {
    this.volcanoCards = new ArrayList<VolcanoCard>();
    this.volcanoCardView = gameSetup;
  }
  /**
   * Configures the panels associated with the volcano cards.
   */
  public void configurePanel() {
    leftPanel = this.volcanoCardView.getVolcanoCardPanelLeft();
    rightPanel = this.volcanoCardView.getVolcanoCardPanelRight();
    UpPanel = this.volcanoCardView.getVolcanoCardPanelUp();
    DownPanel = this.volcanoCardView.getVolcanoCardPanelDown();

    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    UpPanel.setLayout(new BoxLayout(UpPanel, BoxLayout.X_AXIS));
    DownPanel.setLayout(new BoxLayout(DownPanel, BoxLayout.X_AXIS));
  }
  /**
   * Initializes the setup of volcano cards on the game board.
   */
  public void initialiseVolcanoCards() {
    for (int i = 0; i < AnimalFactory.createVolcanoCardAnimal(numberOfCards,
        numberOfSquaresInACard).size(); i++) {
      this.volcanoCards.add(new VolcanoCard(AnimalFactory.createVolcanoCardAnimal(numberOfCards,
          numberOfSquaresInACard).get(i), i+4));
    }
    configurePanel();
    displaySquaresInVertical(leftPanel);
    displaySquaresInVertical(rightPanel);
    displaySquaresInHorizontal(UpPanel);
    displaySquaresInHorizontal(DownPanel);
  }
  /**
   * Displays squares of volcano cards vertically on the specified panel.
   *
   * @param panel The panel to display the squares.
   */
  public void displaySquaresInVertical(JPanel panel) {
    int i = 0;
    while (i < (volcanoCardSize + 1)) {
//      JLabel volcanoCardsImage = new JPanel(this.volcanoCards.get(0).getImage());
      panel.add(this.volcanoCards.get(0));
      this.volcanoCards.remove(0);
      i++;
    }
  }
  /**
   * Displays squares of volcano cards horizontally on the specified panel.
   *
   * @param panel The panel to display the squares.
   */
  public void displaySquaresInHorizontal(JPanel panel) {
    int i = 0;
    while (i < (volcanoCardSize - 1)) {
//      JLabel volcanoCardsImage = new JLabel(this.volcanoCards.get(0).getImage());
      panel.add(this.volcanoCards.get(0));
      this.volcanoCards.remove(0);
      i++;
    }
  }
  /**
   * Retrieves the GameSetup object associated with this VolcanoCardController.
   *
   * @return The GameSetup object associated with this VolcanoCardController.
   */
  public GameSetup getGameSetup() {
    return this.volcanoCardView;
  }
}


