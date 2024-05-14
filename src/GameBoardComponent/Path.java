package GameBoardComponent;

import Animal.Animal;
/**
 * The Path interface represents a path on the game board.
 */
public interface Path {
  /**
   * Sets the position of the path.
   *
   * @param position The position to set.
   */
  void setPosition(int position);
  /**
   * Retrieves the animal associated with the path.
   *
   * @return The animal associated with the path.
   */
  Animal getAnimal();
  /**
   * Adds a token to the path.
   *
   * @param token The token to add.
   */
  void addToken(Token token);

}