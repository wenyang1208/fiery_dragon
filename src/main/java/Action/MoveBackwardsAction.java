package Action;

import static java.lang.Math.abs;

import Game.Game;
import GameBoardComponent.Path;
import GameBoardComponent.Token;
import java.util.ArrayList;

/**
 * The MoveBackwardsAction class implements the Move interface and represents an action
 * where a token moves backwards based on the chit card value.
 */
public class MoveBackwardsAction implements Move{

    /**
     * Executes the action which moves the token backwards by the specified chit card value.
     *
     * @param chitCardValue The value of the chit card that determines how many positions the token
     *                      moves backwards.
     * @param game
     * @return A string indicating the result of the action, which is "back".
     */
    public String execute(int chitCardValue, Game game){
        Token token = game.getCurrentPlayer();
        ArrayList<Path>  processPathList = new ArrayList<>();
        int positionAfterMovingBack = token.getTokenPosition()-chitCardValue;
        if(positionAfterMovingBack <= 0 && !token.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            token.getPaths().remove(0);
            // If the player has been flipped the new dragon card
            if(game.getCompletedPaths() != null){
                int currentIndex = token.getCurrentSquare().getPosition();
                do {
                    currentIndex = (currentIndex - 1 + game.getCompletedPaths().size()) % game.getCompletedPaths().size();
                    if(!game.getCompletedPaths().get(currentIndex).getClass().getSimpleName().equals("Cave")){
                        processPathList.add(game.getCompletedPaths().get(currentIndex));
                    }
                } while (currentIndex != token.getCurrentSquare().getPosition());
            }else{
                for(int i=token.getPaths().size()-3;i>=0;i--){
                    processPathList.add(token.getPaths().get(i));
                }
            }
            token.setAdditionalVolcanoCardPath(processPathList);
            for(int i=0; i<token.getAdditionalVolcanoCardPath().size(); i++){
                token.getPaths().add(0,token.getAdditionalVolcanoCardPath().get(i));
            }
            if(!token.getPaths().get((token.getAdditionalVolcanoCardPath().size()-1) - abs(positionAfterMovingBack)).isOccupied()){
                token.getPaths().get((token.getAdditionalVolcanoCardPath().size()-1) - abs(positionAfterMovingBack)).addToken(token);
                token.setTokenPosition((token.getAdditionalVolcanoCardPath().size()-1) - abs(positionAfterMovingBack));
            }
        }
        else if(positionAfterMovingBack > 0 && !token.getPaths().get(positionAfterMovingBack).isOccupied()){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            Path path = token.getPaths().get(token.getTokenPosition()-chitCardValue);
            token.setTokenPosition(token.getTokenPosition()-chitCardValue);
            token.setCurrentSqaure(path);
            path.addToken(token);
        }
        return "back";
    }


}
