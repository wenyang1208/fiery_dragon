package Action;

import static java.lang.Math.abs;

import Controller.VolcanoCardController;
import GameBoardComponent.Path;
import GameBoardComponent.Token;
import java.util.ArrayList;

public class MoveBackwardsAction implements Move{
    public String execute(int chitCardValue, Token token){
        ArrayList<Path>  processPathList = new ArrayList<>();
        int positionAfterMovingBack = token.getTokenPosition()-chitCardValue;
        if(positionAfterMovingBack <= 0 && !token.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            if(token.getNumberOfBelowMinSquare() == 0){
                token.getPaths().remove(0);
                for(int i=token.getPaths().size()-3;i>=0;i--){
                    processPathList.add(token.getPaths().get(i));
                }
                token.setAdditionalVolcanoCardPath(processPathList);
            }
            token.increaseNumberOfBelowMinSquare();
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