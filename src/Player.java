import java.awt.*;

public class Player {
    private Token tokenAssigned;

    public Player(int playerNo, int x, int y, AnimalType animalType){
        this.tokenAssigned = new Token(playerNo, x, y, animalType);
    }

    public void playerStartRound(){

    }

    public void draw(Graphics2D g2){
        tokenAssigned.draw(g2);
    }

}
