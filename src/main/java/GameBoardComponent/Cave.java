package GameBoardComponent;

import Animal.Animal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Cave class represents a cave on the game board.
 * It extends JPanel and implements the Path interface.
 */
public class Cave extends JPanel implements Path {

  private int caveSize;

  private Image caveImage;
  private Animal animal;
  private int position;

  /**
   * Constructs a new Cave object with the specified animal, position, and size.
   *
   * @param animal The animal associated with the cave.
   * @param position The position of the cave on the game board.
   * @param caveSize The size of the cave.
   */
  public Cave(Animal animal, int position, int caveSize){
    setAnimal(animal);
    setPosition(position);
    setBackground(Color.BLACK);
    setCaveSize(caveSize);
  }

  /**
   * Sets the size of the chit card.
   *
   * @param caveSize The size of the cave.
   */
  public void setCaveSize(int caveSize){
    this.caveSize = caveSize;
  }

  /**
   * Gets the size of the cave.
   */
  public int getCaveSize(){
    return this.caveSize;
  }

  /**
   * Sets the animal associated with the cave.
   *
   * @param animal The animal to set.
   */
  public void setAnimal(Animal animal){
    this.animal = animal;
  }

  /**
   * Retrieves the animal associated with the cave.
   *
   * @return The animal associated with the cave.
   */
  public Animal getAnimal(){
    return this.animal;
  }

  /**
   * Sets the position of the cave on the game board.
   *
   * @param position The position to set.
   */
  public void setPosition(int position) {
    this.position = position;
  }

  /**
   * Retrieves the position of the cave on the game board.
   *
   * @return The position of the cave on the game board.
   */
  public int getPosition(){
    return this.position;
  }

  /**
   * Overrides the paintComponent method to draw the cave image.
   *
   * @param g The Graphics object to draw on.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    caveImage = new ImageIcon(getClass().getClassLoader().getResource(
        "CaveImage/" + getAnimal().getName() + "_cave.png")).getImage();
    g.drawImage(caveImage, 0, 0,getCaveSize(),getCaveSize(), null);
  }

  /**
   * Checks if the cave is occupied by a token.
   * */
  public boolean isOccupied() {
    if(this.getComponents().length != 0){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Adds a token to the cave.
   *
   * @param token The token to add.
   */
  public void addToken(Token token){
    token.setCurrentSqaure(this);
    JLabel tokenLabel = new JLabel("", token.getImage(), JLabel.CENTER);
    this.add(tokenLabel);
    revalidate();
    repaint();
  }

  /**
   * Removes a token from the cave.
   */
  public void removeToken(){
    removeAll();
    revalidate();
    repaint();
  }

}
