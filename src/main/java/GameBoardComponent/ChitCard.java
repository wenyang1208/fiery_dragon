package GameBoardComponent;

import Animal.Animal;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The ChitCard class represents the chit card component on the game board.
 */
public class ChitCard extends GameComponent {
  private int chitCardSize;
  private int value;
  private boolean isFlipped;
  private Image chitCardImage;

  /**
   * Constructs a new ChitCard object with the specified animal, value, and size.
   *
   * @param animal The animal associated with the chit card.
   * @param value The value of the chit card.
   * @param chitCardSize The size of the chit card.
   */
  public ChitCard(Animal animal, int value, int chitCardSize){
    setAnimal(animal);
    setValue(value);
    setChitCardSize(chitCardSize);
  }

  /**
   * Retrieves the value of the chit card.
   *
   * @return The value of the chit card.
   */
  public int getValue(){
    return value;
  }

  /**
   * Checks if the chit card is flipped.
   *
   * @return True if the chit card is flipped, false otherwise.
   */
  public boolean isFlipped(){
    return this.isFlipped;
  }

  /**
   * Sets the value of the chit card.
   *
   * @param value The value to set.
   */
  public void setValue(int value){
    this.value = value;
  }

  /**
   * Sets whether the chit card is flipped or not.
   *
   * @param isFlipped True to set the chit card as flipped, false otherwise.
   */
  public void setIsFlipped(boolean isFlipped){
    this.isFlipped = isFlipped;
  }

  /**
   * Sets the size of the chit card.
   *
   * @param chitCardSize The size of the chit card.
   */
  public void setChitCardSize(int chitCardSize){
    this.chitCardSize = chitCardSize;
  }

  /**
   * Gets the size of the chit card.
   */
  public int getChitCardSize(){
    return this.chitCardSize;
  }

  /**
   * Retrieves the image icon representing the chit card.
   *
   * @return The image icon of the chit card.
   */
  public ImageIcon getImage(){
    if(isFlipped){
      chitCardImage = new ImageIcon(getClass().getClassLoader().getResource(
          "ChitCardImage/" + getAnimal().getName() + "_" + getValue() + ".png")).getImage();
      setIsFlipped(false);
    }else{
      chitCardImage = new ImageIcon(getClass().getClassLoader().getResource(
          "ChitCardImage/Back.png")).getImage();
      setIsFlipped(true);
    }
    Image resizedImage = chitCardImage.getScaledInstance(getChitCardSize(),getChitCardSize(), java.awt.Image.SCALE_SMOOTH);
    ImageIcon ChitCardImageIcon = new ImageIcon(resizedImage);
    return ChitCardImageIcon;
  }
}
