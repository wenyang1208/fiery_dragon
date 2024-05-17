package Controller;

import Animal.AnimalFactory;
import java.util.ArrayList;
import GameBoardComponent.Cave;
import GameBoardComponent.Token;
import GameBoardComponent.VolcanoCard;

/**
 * The TokenController class manages the initialization and path computation for tokens in the game.
 */
public class TokenController {

    private ArrayList<Token> tokens;

    /**
     * Constructs a new TokenController with the specified players, volcano cards, caves, and volcano cards near to caves.
     *
     * @param players                A list of player names.
     * @param volcanoCards           A list of VolcanoCard objects.
     * @param caves                  A list of Cave objects.
     * @param volcanoCardsNearToCaves A list of VolcanoCard objects near to the caves.
     */
    public TokenController(ArrayList<String> players, ArrayList<VolcanoCard> volcanoCards, ArrayList<Cave> caves, ArrayList<VolcanoCard> volcanoCardsNearToCaves){
        this.tokens = new ArrayList<>();
        initialiseToken(players);
        computePaths(volcanoCards,caves,volcanoCardsNearToCaves);
    }

    /**
     * Computes the paths for each token based on the volcano cards and caves.
     *
     * @param volcanoCards           A list of VolcanoCard objects.
     * @param caves                  A list of Cave objects.
     * @param volcanoCardsNearToCaves A list of VolcanoCard objects near to the caves.
     */
    private void computePaths(ArrayList<VolcanoCard> volcanoCards, ArrayList<Cave> caves,ArrayList<VolcanoCard> volcanoCardsNearToCaves) {
        for(int i=0; i<tokens.size();i++){
            tokens.get(i).getPaths().add(caves.get(i));
            int pos = volcanoCardsNearToCaves.get(tokens.get(i).getTokenNumber()).getPosition();
            for(int j=pos; j < volcanoCards.size(); j++){
                tokens.get(i).getPaths().add(volcanoCards.get(j));
            }
            for(int k = 0; k <= pos;k++){
                tokens.get(i).getPaths().add(volcanoCards.get(k));
            }
            tokens.get(i).getPaths().add(caves.get(i));
        }
    }

    /**
     * Initializes the tokens for each player.
     *
     * @param players A list of player names.
     */
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
    }

    /**
     * Gets the list of tokens.
     *
     * @return An ArrayList of Token objects.
     */
    public ArrayList<Token> getTokens(){
        return this.tokens;
    }
}
