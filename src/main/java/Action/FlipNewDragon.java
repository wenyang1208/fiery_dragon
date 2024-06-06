package Action;

import Game.Game;

/**
 * The FlipNewDragon class implements the Flip interface to handle the action of flipping a New Dragon card.
 */
public class FlipNewDragon implements Flip{

  /**
   * Executes the flip action for the New Dragon card. This sets the current player's move to {@code MoveNearestCaveAction}
   * and then executes the move based on the value of the flipped card.
   *
   * @param game The game instance.
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public void flip(Game game) {
    game.getCurrentPlayer().setMove(new MoveNearestCaveAction());
    game.getCurrentPlayer().executeMove(game.getFlippedCard().getValue(), game);
  }
}
