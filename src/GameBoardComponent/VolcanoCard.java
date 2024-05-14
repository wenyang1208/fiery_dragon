package GameBoardComponent;

import Animal.Animal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * The VolcanoCard class represents a volcano card on the game board.
 */
public class VolcanoCard extends JPanel implements Path {

  private boolean isOccupied;

  private Animal animal;
  private int position;
  private Image volcanoCardImage;
  /**
   * Constructs a new VolcanoCard object with the specified animal and position.
   *
   * @param animal The animal associated with the volcano card.
   * @param position The position of the volcano card.
   */
  public VolcanoCard(Animal animal, int position){
    setAnimal(animal);
    setPosition(position);
    setBackground(Color.BLACK);
  }
  /**
   * Sets the animal associated with the volcano card.
   *
   * @param animal The animal to set.
   */
  public void setAnimal(Animal animal){
    this.animal = animal;
  }
  /**
   * Retrieves the animal associated with the volcano card.
   *
   * @return The animal associated with the volcano card.
   */
  public Animal getAnimal(){
    return this.animal;
  }
  /**
   * Sets the position of the volcano card.
   *
   * @param position The position to set.
   */
  public void setPosition(int position) {
    this.position = position;
  }
  /**
   * Retrieves the position of the volcano card.
   *
   * @return The position of the volcano card.
   */
  public int getPosition(){
    return this.position;
  }

  /**
   * Overrides the paintComponent method to draw the volcano card image.
   *
   * @param g The graphics context.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    //getClass().getClassLoader().getResource("Project_cartoon/Background.jpg")
    volcanoCardImage = new ImageIcon(getClass().getClassLoader().getResource(
        "VolcanoCardImage/" + getAnimal().getName() + "_volcano_card.png")).getImage();
    g.drawImage(volcanoCardImage, 0, 0,70,70, null);
  }
  /**
   * Adds a token to the volcano card.
   *
   * @param token The token to add.
   */
  public void addToken(Token token){
  }
}
