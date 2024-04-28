import javax.swing.*;
import java.awt.*;

public class Token {
    private int tokenNo;
    private int x;
    private int y;
    private AnimalType animalType;
    private int stepsTaken;
    public Token(int tokenNo, int x, int y, AnimalType animalType){
        this.tokenNo = tokenNo;
        this.x = x;
        this.y = y;
        this.animalType = animalType;
        this.stepsTaken = 0;
    }

    public void draw(Graphics2D g2){
        ImageIcon imageIcon = null;
        switch (this.getAnimalType()) {
            case BABYDRAGON:
                imageIcon = new ImageIcon(getClass().getResource("/token/baby_dragon_token.png"));
                break;
            case BAT:
                imageIcon = new ImageIcon(getClass().getResource("/token/bat_token.png"));
                break;
            case SALAMANDER:
                imageIcon = new ImageIcon(getClass().getResource("/token/salamander_token.png"));
                break;
            case SPIDER:
                imageIcon = new ImageIcon(getClass().getResource("/token/spider_token.png"));
                break;
        }
        int squareCenterX = this.getX() * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2;
        int squareCenterY = this.getY() * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2;
        int imageX = squareCenterX - (Board.SQUARE_SIZE / 4);
        int imageY = squareCenterY - (Board.SQUARE_SIZE / 4);
        g2.drawImage(imageIcon.getImage(), imageX, imageY, Board.SQUARE_SIZE / 2, Board.SQUARE_SIZE / 2, null);
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
