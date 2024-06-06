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

/**
 * The MoveNearestCaveAction class implements the Move interface to move a token to the nearest unoccupied cave.
 */
public class MoveNearestCaveAction implements Move{
  private ArrayList<Path> paths;
  private int[] caveIndices;

  /**
   * Executes the move to the nearest unoccupied cave.
   *
   * @param chitCardValue The number of spaces to move (not used in this implementation).
   * @param game The game instance.
   * @return A string indicating the result of the move (always returns "none").
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public String execute(int chitCardValue, Game game) {
    Token currentPlayer = game.getCurrentPlayer();
    currentPlayer.setFlipNewDragon(true);
    if(!currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
      if(!game.isCreatedPaths()){
        game.setCompletedPaths(setPathIncludingCave(game));
      }
      currentPlayer.getCurrentSquare().removeToken();
      // Remove the previous path of the token!
      for(int i=0; i<currentPlayer.getTokenPosition(); i++){
        currentPlayer.getPaths().remove(0);
      }
      // Find the nearest unoccupied cave, correct path
      int currentPositionIndex = currentPlayer.getCurrentSquare().getPosition();
      while(!game.getCompletedPaths().get(currentPositionIndex).getClass().getSimpleName().equals("Cave") || game.getCompletedPaths().get(currentPositionIndex).isOccupied()){
        currentPositionIndex = (currentPositionIndex - 1 + game.getCompletedPaths().size()) % game.getCompletedPaths().size();
      }
      // Modify the paths of the current player
      ArrayList<Path> auxPath = new ArrayList<>();
      while(game.getCompletedPaths().get(currentPositionIndex).getPosition() != currentPlayer.getCurrentSquare().getPosition()){
        auxPath.add(game.getCompletedPaths().get(currentPositionIndex));
        currentPositionIndex= (currentPositionIndex + 1) % game.getCompletedPaths().size();
      }
      for(int i = 0; i<currentPlayer.getPaths().size(); i++){
        auxPath.add(currentPlayer.getPaths().get(i));
      }
      currentPlayer.getPaths().clear();
      for(int i = 0; i<auxPath.size(); i++){
        currentPlayer.getPaths().add(auxPath.get(i));
      }
      Path path = currentPlayer.getPaths().get(0);
      currentPlayer.setCurrentSqaure(path);
      currentPlayer.setTokenPosition(0);
      path.addToken(currentPlayer);
    }
    return "none";
  }

  /**
   * Sets the path including caves and returns the updated path.
   *
   * @param game The game instance.
   * @return The list of paths including caves.
   */
  public ArrayList<Path> setPathIncludingCave(Game game){
      paths = new ArrayList<>();
      caveIndices = new int[4];
      int counter = 0;
      // Add volcano cards first
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCards().size(); i++){
        paths.add(game.getVolcanoCardController().getVolcanoCards().get(i));
      }
      // Find the position of the volcano card with cave
      for(int i=0; i < game.getVolcanoCardController().getVolcanoCardsNearToCave().size(); i++){
        caveIndices[i] = game.getVolcanoCardController().getVolcanoCardsNearToCave().get(i).getPosition();
      }
      // Insert cave into the path lists at the correct index
      ArrayList<String> usedCaves = new ArrayList<>();
      for(int i=0; i<game.getCaveController().getCaves().size(); i++){
        paths.add(game.getCaveController().getCaves().get(i).getPosition(),game.getCaveController().getCaves().get(i));
        usedCaves.add(game.getCaveController().getCaves().get(i).getAnimal().getName());
      }

      for (Entry<Cave, Integer> entry : game.getCaveController().getCavesHashMap().entrySet()) {
        if(!usedCaves.contains(entry.getKey().getAnimal().getName())){
          paths.add(caveIndices[entry.getValue()]+counter,entry.getKey());
        }
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
