package GameBoardComponent;

import Animal.Animal;
import javax.swing.ImageIcon;

/**
 * The GameComponent class represents a component used in the game board.
 */
public abstract class GameComponent {
  private Animal animal;

  /**
   * Sets the animal associated with the game component.
   *
   * @param animal The animal to set.
   */
  public void setAnimal(Animal animal){
    this.animal = animal;
  }

  /**
   * Retrieves the animal associated with the game component.
   *
   * @return The animal associated with the game component.
   */
  public Animal getAnimal(){
    return this.animal;
  };

  /**
   * Abstract method to retrieve the image icon representing the game component.
   *
   * @return The image icon representing the game component.
   */
  public abstract ImageIcon getImage();

}
