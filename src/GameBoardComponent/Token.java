package GameBoardComponent;

import Animal.Animal;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 * The Token class represents a token on the game board.
 */
public class Token extends GameComponent {
  private int tokenPosition;
  private Path path;
  private Image tokenImage;
  /**
   * Constructs a new Token object with the specified animal and token position.
   *
   * @param animal The animal associated with the token.
   * @param tokenPosition The position of the token.
   */
  public Token(Animal animal, int tokenPosition){
    setAnimal(animal);
    setTokenPosition(tokenPosition);
  }
  /**
   * Sets the position of the token.
   *
   * @param tokenPosition The position to set.
   */
  public void setTokenPosition(int tokenPosition){
    this.tokenPosition = tokenPosition;
  }
  /**
   * Sets the current square of the token.
   *
   * @param path The path representing the current square.
   */
  public void setCurrentSqaure(Path path){
    this.path = path;
  }
  /**
   * Retrieves the current square of the token.
   *
   * @return The path representing the current square.
   */
  public Path getCurrentSqaure(){
    return this.path;
  }
  /**
   * Retrieves the image icon representing the token.
   *
   * @return The image icon of the token.
   */
  public ImageIcon getImage(){
    tokenImage = new ImageIcon(getClass().getClassLoader().getResource("TokenImage/" + getAnimal().getName() + "_token.png")).getImage();
    Image resizedImage = tokenImage.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH);
    ImageIcon tokenImageIcon = new ImageIcon(resizedImage);
    return tokenImageIcon;
  }


}

