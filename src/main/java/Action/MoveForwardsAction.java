package Action;

import Game.Game;
import GameBoardComponent.Path;
import GameBoardComponent.Token;

/**
 * The MoveForwardsAction class implements the Move interface and represents an action
 * where a token moves forwards based on the chit card value.
 */
public class MoveForwardsAction implements Move{

    /**
     * Executes the action which moves the token forwards by the specified chit card value.
     *
     * @param chitCardValue The value of the chit card that determines how many positions the token
     *                      moves forwards.
     * @param game
     * @return A string indicating the result of the action. Returns "go" if the token moved
     * successfully, "win" if the token reached the end of the path, or null if the move could not be
     * performed.
     */
    public String execute(int chitCardValue, Game game){
        Token token = game.getCurrentPlayer();
        if(token.getTokenPosition()+chitCardValue < token.getPaths().size() && !token.getPaths().get(token.getTokenPosition()+chitCardValue).isOccupied()){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            Path path = token.getPaths().get(token.getTokenPosition()+chitCardValue);
            token.setCurrentSqaure(path);
            token.setTokenPosition(token.getTokenPosition()+chitCardValue);
            path.addToken(token);
            if(token.getTokenPosition() == token.getPaths().size()-1){
                return "win";
            }
            return "go";
        }
        return null;
    }
}

