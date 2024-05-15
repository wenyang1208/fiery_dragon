package Action;

import GameBoardComponent.Token;

public class DoNothingAction implements Move{
    public String execute(int chitCardValue, Token token){
        return "stay";
    }
}
