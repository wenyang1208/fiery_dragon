package Action;

import Game.Game;

/**
 * The Flip interface defines the contract for actions that can be taken when a chit card is flipped.
 */
public interface Flip {

  /**
   * Executes the action associated with flipping a chit card.
   *
   * @param game The game instance in which the action takes place.
   */
  void flip(Game game);
}
