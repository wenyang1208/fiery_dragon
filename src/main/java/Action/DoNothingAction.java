package Action;

import Game.Game;
import GameBoardComponent.Token;

/**
 * The DoNothingAction class implements the Move interface and represents an action where a token does nothing.
 */
public class DoNothingAction implements Move{

    /**
     * Executes the action which, in this case, results in the token doing nothing.
     *
     * @param chitCardValue The value of the chit card that might influence the action.
     * @param game
     * @return A string indicating the result of the action, which is always "stay".
     */
    public String execute(int chitCardValue, Game game){
        return "stay";
    }
}
