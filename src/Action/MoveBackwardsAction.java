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
        if(positionAfterMovingBack <= 0 && !token.getCurrentSquare().getClass().getSimpleName().equals("Cave") && !token.getPaths().get(positionAfterMovingBack).isOccupied()){
            token.getPaths().get(token.getTokenPosition()).removeToken();
            System.out.println(positionAfterMovingBack);

            if(token.getNumberOfBelowMinSquare() == 0){
                token.getPaths().remove(0);
                for(int i=token.getPaths().size()-3;i>=0;i--){
                    processPathList.add(token.getPaths().get(i));
                }
                token.setAdditionalVolcanoCardPath(processPathList);
            }
            token.increaseNumberOfBelowMinSquare();
            System.out.println("ac");
            for(int i=0; i<token.getAdditionalVolcanoCardPath().size(); i++){
                System.out.println("i: " + i);
                token.getPaths().add(0,token.getAdditionalVolcanoCardPath().get(i));
            }
            token.getPaths().get((token.getAdditionalVolcanoCardPath().size()-1) - abs(positionAfterMovingBack)).addToken(token);
            token.setTokenPosition((token.getAdditionalVolcanoCardPath().size()-1) - abs(positionAfterMovingBack));
        }
        else if(positionAfterMovingBack > 0 && !token.getPaths().get(positionAfterMovingBack).isOccupied()){
            System.out.println("b");
            token.getPaths().get(token.getTokenPosition()).removeToken();
            Path path = token.getPaths().get(token.getTokenPosition()-chitCardValue);
            token.setTokenPosition(token.getTokenPosition()-chitCardValue);
            token.setCurrentSqaure(path);
            path.addToken(token);
        }
        System.out.println("token pos: " + token.getTokenPosition());
        System.out.println(positionAfterMovingBack);
        for(int i =0;i<token.getPaths().size();i++){
            System.out.println("i: " + i);
            System.out.println("position of token: " + token.getPaths().get(i).getPosition());
        }
        return "back";
    }


}
