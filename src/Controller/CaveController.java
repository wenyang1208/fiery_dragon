package Controller;

import Animal.AnimalFactory;
import Game.GameSetup;
import GameBoardComponent.Cave;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 * The CaveController class manages the configuration and initialization of caves on the game board.
 */
public class CaveController {

  private ArrayList<Cave> caves;
  private GameSetup gameSetup;
  private JPanel cave1;
  private JPanel cave2;
  private JPanel cave3;
  private JPanel cave4;
  /**
   * Constructs a new CaveController with the specified GameSetup object.
   *
   * @param gameSetup The GameSetup object representing the game setup.
   */
  public CaveController(GameSetup gameSetup) {
    this.gameSetup = gameSetup;
    this.caves = new ArrayList<>();
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
  public GameSetup getGameSetup() {
    return this.gameSetup;
  }
  /**
   * Configures the panels associated with the caves.
   */
  public void configurePanel() {
    cave1 = this.gameSetup.getCave1Panel();
    cave2 = this.gameSetup.getCave2Panel();
    cave3 = this.gameSetup.getCave3Panel();
    cave4 = this.gameSetup.getCave4Panel();

  }
  /**
   * Initializes the setup of caves on the game board.
   */
  public void initialiseCaveSetup() {
    for (int i=0; i < AnimalFactory.createCaveAnimal().size(); i++) {
      this.caves.add(new Cave(AnimalFactory.createCaveAnimal().get(i),i));
    }
    configurePanel();
    displayCaves(cave1,0);
    displayCaves(cave2,1);
    displayCaves(cave3,2);
    displayCaves(cave4,3);
  }
  /**
   * Displays a cave on the specified cave panel.
   *
   * @param cavePanel The panel associated with the cave.
   * @param caveID    The ID of the cave.
   */
  public void displayCaves(JPanel cavePanel, int caveID) {
    cavePanel.setLayout(new BorderLayout());
    cavePanel.add(this.caves.get(caveID), BorderLayout.CENTER);
  }
}
