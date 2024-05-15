package Action;

import GameBoardComponent.Path;
import GameBoardComponent.Token;

public class MoveForwardsAction implements Move{
    public String execute(int chitCardValue, Token token){
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

