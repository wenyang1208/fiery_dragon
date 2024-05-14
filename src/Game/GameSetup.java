package Game;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * The GameSetup class represents the setup of the game board, including chit card panels, volcano card panels, and cave panels.
 */
public class GameSetup extends JPanel {

  private JPanel chitCardPanel;
  private JPanel volcanoCardPanelLeft;
  private JPanel volcanoCardPanelRight;
  private JPanel volcanoCardPanelUp;
  private JPanel volcanoCardPanelDown;
  private JPanel cave1Panel;
  private JPanel cave2Panel;
  private JPanel cave3Panel;
  private JPanel cave4Panel;
  /**
   * Constructs a new GameSetup object.
   */
  public GameSetup() {
    setBackground(Color.BLACK);
    setBounds(30, 30, 550, 650);
    setLayout(null);
    setupChitCardPanel();
    setupVolcanoCardPanel();
    setupCave();
  }
  /**
   * Retrieves the chit card panel.
   *
   * @return The chit card panel.
   */
  public JPanel getChitCardPanel() {
    return chitCardPanel;
  }
  /**
   * Retrieves the left volcano card panel.
   *
   * @return The left volcano card panel.
   */
  public JPanel getVolcanoCardPanelLeft() {
    return volcanoCardPanelLeft;
  }
  /**
   * Retrieves the right volcano card panel.
   *
   * @return The right volcano card panel.
   */
  public JPanel getVolcanoCardPanelRight() {
    return volcanoCardPanelRight;
  }
  /**
   * Retrieves the upper volcano card panel.
   *
   * @return The upper volcano card panel.
   */
  public JPanel getVolcanoCardPanelUp() {
    return volcanoCardPanelUp;
  }
  /**
   * Retrieves the lower volcano card panel.
   *
   * @return The lower volcano card panel.
   */
  public JPanel getVolcanoCardPanelDown() {
    return volcanoCardPanelDown;
  }
  /**
   * Retrieves the first cave panel.
   *
   * @return The first cave panel.
   */
  public JPanel getCave1Panel() {
    return cave1Panel;
  }
  /**
   * Retrieves the second cave panel.
   *
   * @return The second cave panel.
   */
  public JPanel getCave2Panel() {
    return cave2Panel;
  }
  /**
   * Retrieves the third cave panel.
   *
   * @return The third cave panel.
   */
  public JPanel getCave3Panel() {
    return cave3Panel;
  }
  /**
   * Retrieves the fourth cave panel.
   *
   * @return The fourth cave panel.
   */
  public JPanel getCave4Panel() {
    return cave4Panel;
  }
  /**
   * Sets up the chit card panel.
   */
  public void setupChitCardPanel() {
    chitCardPanel = new JPanel();
    chitCardPanel.setLayout(new GridLayout(4, 4));
    chitCardPanel.setBackground(Color.BLACK);
    chitCardPanel.setBounds(130, 150, 300, 330);
    add(chitCardPanel);
  }
  /**
   * Sets up the volcano card panels.
   */
  public void setupVolcanoCardPanel() {

    volcanoCardPanelLeft = new JPanel();
    volcanoCardPanelLeft.setBackground(Color.BLACK);
    volcanoCardPanelLeft.setBounds(25, 80, 70, 490);
    add(volcanoCardPanelLeft);

    volcanoCardPanelRight = new JPanel();
    volcanoCardPanelRight.setLayout(new GridLayout(7, 1));
    volcanoCardPanelRight.setBackground(Color.BLACK);
    volcanoCardPanelRight.setBounds(455, 80, 70, 490);
    add(volcanoCardPanelRight);

    volcanoCardPanelUp = new JPanel();
    volcanoCardPanelUp.setBackground(Color.BLACK);
    volcanoCardPanelUp.setLayout(new GridLayout(1, 5));
    volcanoCardPanelUp.setBounds(100, 80, 350, 70);
    add(volcanoCardPanelUp);

    volcanoCardPanelDown = new JPanel();
    volcanoCardPanelLeft.setLayout(new GridLayout(1, 5));
    volcanoCardPanelDown.setBackground(Color.BLACK);
    volcanoCardPanelDown.setBounds(100, 500, 350, 70);
    add(volcanoCardPanelDown);
  }
  /**
   * Sets up the cave panels.
   */
  public void setupCave() {
    cave1Panel = new JPanel();
    cave1Panel.setBackground(Color.BLACK);
    cave1Panel.setBounds(25, 5, 70, 70);
    add(cave1Panel);
    cave2Panel = new JPanel();
    cave2Panel.setBackground(Color.BLACK);
    cave2Panel.setBounds(455, 5, 70, 70);
    add(cave2Panel);
    cave3Panel = new JPanel();
    cave3Panel.setBackground(Color.BLACK);
    cave3Panel.setBounds(25, 575, 70, 70);
    add(cave3Panel);
    cave4Panel = new JPanel();
    cave4Panel.setBackground(Color.BLACK);
    cave4Panel.setBounds(455, 575, 70, 70);
    add(cave4Panel);
  }
}
