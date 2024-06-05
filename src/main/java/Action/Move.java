package Action;

import Game.Game;
import GameBoardComponent.Token;

/**
 * The Move interface defines a contract for executing an action on a token based on a chit card value.
 */
public interface Move {

    /**
     * Executes an action on the specified token based on the provided chit card value.
     *
     * @param chitCardValue The value of the chit card that might influence the action.
     * @param game
     * @return A string indicating the result of the action.
     */
    String execute(int chitCardValue, Game game);

}

