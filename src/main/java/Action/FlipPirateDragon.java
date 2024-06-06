package Action;

import Game.Game;

/**
 * The FlipPirateDragon class implements the Flip interface to handle the action of flipping a Pirate Dragon card.
 */
public class FlipPirateDragon implements Flip{

  /**
   * Executes the flip action for the Pirate Dragon card. This sets the current player's move to {@code MoveBackwardsAction}
   * and then executes the move based on the value of the flipped card.
   *
   * @param game The game instance.
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public void flip(Game game) {
    game.getCurrentPlayer().setMove(new MoveBackwardsAction());
    game.getCurrentPlayer().executeMove(game.getFlippedCard().getValue(), game);
  }
}
