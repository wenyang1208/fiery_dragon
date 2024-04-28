import java.awt.*;

public class Player {
    private Token tokenAssigned;
    public Player(int playerNo){
        this.tokenAssigned = new Token(playerNo);
    }

    public void playerStartRound(){

    }

    public void draw(Graphics2D g2){
        tokenAssigned.draw(g2);
    }
}
