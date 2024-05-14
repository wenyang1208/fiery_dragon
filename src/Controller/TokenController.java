package Controller;

import Animal.AnimalFactory;
import java.util.ArrayList;
import GameBoardComponent.Cave;
import GameBoardComponent.Path;
import GameBoardComponent.Token;
import GameBoardComponent.VolcanoCard;

public class TokenController {

  private ArrayList<Token> tokens;

  public TokenController(ArrayList<String> players, ArrayList<VolcanoCard> volcanoCards, ArrayList<Cave> caves, ArrayList<VolcanoCard> volcanoCardsNearToCaves){
    this.tokens = new ArrayList<>();
    initialiseToken(players);
    computePaths(volcanoCards,caves,volcanoCardsNearToCaves);
  }

  private void computePaths(ArrayList<VolcanoCard> volcanoCards, ArrayList<Cave> caves,ArrayList<VolcanoCard> volcanoCardsNearToCaves) {
    for(int i=0; i<tokens.size();i++){
      tokens.get(i).getPaths().add(caves.get(i));
      int pos = volcanoCardsNearToCaves.get(tokens.get(i).getTokenNumber()).getPosition();
      for(int j=pos; j>=0; j--){
        tokens.get(i).getPaths().add(volcanoCards.get(j));
      }
      for(int k = volcanoCards.size()-1;k>=pos;k--){
        tokens.get(i).getPaths().add(volcanoCards.get(k));
      }
      tokens.get(i).getPaths().add(caves.get(i));
    }
  }

  public void initialiseToken(ArrayList<String> players){
    for(String player:players){
      for (int i=0; i < AnimalFactory.createTokenAnimal().size(); i++) {
        Token token = new Token(AnimalFactory.createTokenAnimal().get(i),VolcanoCardController.cardSize,i);
        if (player.equals(AnimalFactory.createTokenAnimal().get(i).getClass().getSimpleName())) {
          this.tokens.add(token);
          break;
        }
      }
    }
//    for(Token token:tokens){
//      System.out.println(token.getAnimal().getName());
//    }
  }

  public ArrayList<Token> getTokens(){
    return this.tokens;
  }



}

