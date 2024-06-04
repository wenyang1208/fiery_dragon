package Action;

import Game.Game;

public class FlipNewDragon implements Flip{
  @Override
  public void flip(Game game) {
    game.getCurrentPlayer().setMove(new MoveNearestCaveAction());
    game.getCurrentPlayer().executeMove(game.getFlippedCard().getValue(), game);
  }
}
