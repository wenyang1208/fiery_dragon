package Action;

import Game.Game;
import GameBoardComponent.Cave;
import GameBoardComponent.Path;
import GameBoardComponent.Token;
import java.util.ArrayList;
import java.util.Map.Entry;

public class MoveNearestCaveAction implements Move{

  private boolean isPathWithCaveCreated = false;
  @Override
  public String execute(int chitCardValue, Game game) {
    if(!isPathWithCaveCreated && !game.getCurrentPlayer().getCurrentSquare().getClass().getSimpleName().equals("Cave")){
      int[] caveIndices = new int[4];
      int counter = 0;
      ArrayList<Path> paths = new ArrayList<>();
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCards().size(); i++){
        paths.add(game.getVolcanoCardController().getVolcanoCards().get(i));
      }
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCardsNearToCave().size(); i++){
        System.out.println(game.getVolcanoCardController().getVolcanoCardsNearToCave().get(i).getPosition());
        caveIndices[i] = game.getVolcanoCardController().getVolcanoCardsNearToCave().get(i).getPosition();
      }
      for (Entry<Cave, Integer> entry : game.getCaveController().getCavesHashMap().entrySet()) {
        paths.add(caveIndices[entry.getValue()]+counter,entry.getKey());
        counter += 1;
      }
      isPathWithCaveCreated = true;
    }
    return "hu";
  }
}
