package Action;

import Game.Game;
import GameBoardComponent.Cave;
import GameBoardComponent.Path;
import GameBoardComponent.Token;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import javax.swing.JPanel;

public class MoveNearestCaveAction implements Move{
  private ArrayList<Path> paths;
  private int index;
  private boolean caveIndexIsFound = false;
  @Override
  public String execute(int chitCardValue, Game game) {
    Token currentPlayer = game.getCurrentPlayer();
    currentPlayer.setFlipNewDragon(true);
    if(!game.isCreatedPaths() && !currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
      game.setCompletedPaths(setPathIncludingCave(game));
    }
    if(!currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")) {
      currentPlayer.getPaths().get(currentPlayer.getTokenPosition()).removeToken();
      // Find the index of the nearest cave in the completedPaths from range 0-27 paths (including volcano card and cave)
      for(int i=0; i<currentPlayer.getPaths().size(); i++){
        System.out.println("pathsd " + currentPlayer.getPaths().get(i).getPosition());
        System.out.println("pathasd " + currentPlayer.getPaths().get(i).getAnimal().getName());
      }
      for(int i=currentPlayer.getCurrentSquare().getPosition(); i>=0; i--){
        if(game.getCompletedPaths().get(i).getClass().getSimpleName().equals("Cave")) {
          index = i;
          caveIndexIsFound = true;
          break;
        }
      }
      if(!caveIndexIsFound){
        for(int i=game.getCompletedPaths().size(); i>=currentPlayer.getCurrentSquare().getPosition(); i--){
          if(game.getCompletedPaths().get(i).getClass().getSimpleName().equals("Cave")) {
            index = i;
            caveIndexIsFound = true;
            break;
          }
        }
      }
      // collect new paths
      ArrayList<Path> in = new ArrayList<>();
      for(int i=index; i<=currentPlayer.getCurrentSquare().getPosition(); i++){
        in.add(0,game.getCompletedPaths().get(i));
      }
      int insertIndex = 0;
      // find place to insert
      for(int i=0; i<currentPlayer.getPaths().size(); i++){
        if(currentPlayer.getPaths().get(i).getPosition() == currentPlayer.getCurrentSquare().getPosition()){
          insertIndex = i;
          break;
        }
      }
      for(int i=0; i<in.size(); i++){
        currentPlayer.getPaths().add(insertIndex+1,in.get(i));
      }
      for(int i=0; i<currentPlayer.getPaths().size(); i++){
        System.out.println("sdc " + currentPlayer.getPaths().get(i).getPosition());
        System.out.println("path " + currentPlayer.getPaths().get(i).getAnimal().getName());
      }
      Path path = currentPlayer.getPaths().get(currentPlayer.getTokenPosition()+1);
      currentPlayer.setCurrentSqaure(path);
      currentPlayer.setTokenPosition(currentPlayer.getTokenPosition()+1);
      path.addToken(currentPlayer);
    }
    return "hu";
  }

  public ArrayList<Path> setPathIncludingCave(Game game){
      paths = new ArrayList<>();
      int[] caveIndices = new int[4];
      int counter = 0;
      int counter2 = 0;
      // Add volcano cards first
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCards().size(); i++){
        paths.add(game.getVolcanoCardController().getVolcanoCards().get(i));
      }
      // Find the position of the volcano card with cave
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCardsNearToCave().size(); i++){
        caveIndices[i] = game.getVolcanoCardController().getVolcanoCardsNearToCave().get(i).getPosition();
        if(game.getCurrentPlayer().getPaths().get(0).getPosition() == i){
          game.getCurrentPlayer().getPaths().get(0).setPosition(caveIndices[i]+counter2);
          counter2 += 1;
        }
      }
      // Insert cave into the path lists at the correct index
      for (Entry<Cave, Integer> entry : game.getCaveController().getCavesHashMap().entrySet()) {
        paths.add(caveIndices[entry.getValue()]+counter,entry.getKey());
        counter += 1;
      }
      // Make the path positions become correct
      for (int i=0; i < paths.size(); i++) {
        paths.get(i).setPosition(i);
      }
      game.setCreatedPaths(true);
      return paths;
  }
}
