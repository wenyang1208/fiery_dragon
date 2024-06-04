package Action;

import Game.Game;

public class FlipPirateDragon implements Flip{

  @Override
  public void flip(Game game) {
    game.getCurrentPlayer().setMove(new MoveBackwardsAction());
    game.getCurrentPlayer().executeMove(game.getFlippedCard().getValue(), game);
  }
}
