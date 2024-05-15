package Action;

import GameBoardComponent.Path;
import GameBoardComponent.Token;

public class MoveBackwardsAction implements Move{
    public String execute(int chitCardValue, Token token){
        if(token.getTokenPosition()-chitCardValue < 0){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            token.setTokenPosition(0);
            token.getPaths().get(0).addToken(token);
        }
        else if(token.getTokenPosition()!=0 && !token.getPaths().get(token.getTokenPosition()-chitCardValue).isOccupied()){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            Path path = token.getPaths().get(token.getTokenPosition()-chitCardValue);
            token.setTokenPosition(token.getTokenPosition()-chitCardValue);
            token.setCurrentSqaure(path);
            path.addToken(token);
        }
        return "back";
    }


}
