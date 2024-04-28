import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int MAX_COL = 30;
    public static final int MAX_ROW = 25;
    public static final int SQUARE_SIZE = 50;
    private boolean opacity[][];
    VolcanoCard vCard = new VolcanoCard();
    DragonCard dCard = new DragonCard();
    Cave cave = new Cave();
    ArrayList<Player> playerList = new ArrayList<>();

    public Board() {
        playerList.add(new Player(1, 1, 12, AnimalType.BAT));
        playerList.add(new Player(2, 10, 1, AnimalType.SPIDER));
        playerList.add(new Player(3, 19, 12, AnimalType.SALAMANDER));
        playerList.add(new Player(4, 10, 23, AnimalType.BABYDRAGON));


        for(Player p: playerList){
            p.playerStartRound();
        }

    }

    public boolean[][] designBoard(){
        opacity = new boolean[MAX_ROW][MAX_COL];
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                opacity[row][col] = false; // Set all squares to no opacity initially
            }
        }

        //caves
        opacity[1][10] = true;
        opacity[12][1] = true;
        opacity[12][19] = true;
        opacity[23][10] = true;

        //dragon cards
        opacity[9][7] = true;
        opacity[9][9] = true;
        opacity[9][11] = true;
        opacity[9][13] = true;
        opacity[11][7] = true;
        opacity[11][9] = true;
        opacity[11][11] = true;
        opacity[11][13] = true;
        opacity[13][7] = true;
        opacity[13][9] = true;
        opacity[13][11] = true;
        opacity[13][13] = true;
        opacity[15][7] = true;
        opacity[15][9] = true;
        opacity[15][11] = true;
        opacity[15][13] = true;

        //buttons
        List<Integer> rowButton = new ArrayList<>();

        // Add elements to the list
        rowButton.add(5);
        rowButton.add(6);
        rowButton.add(12);
        rowButton.add(13);
        rowButton.add(19);
        rowButton.add(20);

        for (int row: rowButton) {
            for (int col = 23; col < 28; col++) {
                opacity[row][col] = true;
            }
        }

        //volcano cards
        opacity[12][3] = true;
        opacity[10][3] = true;
        opacity[8][4] = true;
        opacity[6][5] = true;
        opacity[4][6] = true;
        opacity[3][8] = true;
        opacity[3][10] = true;
        opacity[3][12] = true;
        opacity[4][14] = true;
        opacity[6][15] = true;
        opacity[8][16] = true;
        opacity[10][17] = true;
        opacity[12][17] = true;
        opacity[14][17] = true;
        opacity[16][16] = true;
        opacity[18][15] = true;
        opacity[20][14] = true;
        opacity[21][12] = true;
        opacity[21][10] = true;
        opacity[21][8] = true;
        opacity[20][6] = true;
        opacity[18][5] = true;
        opacity[16][4] = true;
        opacity[14][3] = true;

        return opacity;
    }

    public void draw(Graphics2D g2){
        opacity = designBoard();

        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if (opacity[row][col]) {
                    Color translucentWhite = new Color(255, 255, 255, 128); // 128 is the alpha value for 50% opacity
                    g2.setColor(translucentWhite);
                    int squareX = col * SQUARE_SIZE;
                    int squareY = row * SQUARE_SIZE;
                    g2.fillRect(squareX, squareY, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        vCard.draw(g2);
        dCard.draw(g2);
        cave.draw(g2);

        for(Player p: playerList){
            p.draw(g2);
        }

        /*
        ImageIcon babyDragonToken = new ImageIcon(getClass().getResource("/token/baby_dragon_token.png"));
        int squareCenterX = 10 * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2; // X-coordinate of the center of the square
        int squareCenterY = 23 * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2; // Y-coordinate of the center of the square
        int imageX = squareCenterX - (Board.SQUARE_SIZE / 4); // Adjusted X-coordinate for centering
        int imageY = squareCenterY - (Board.SQUARE_SIZE / 4); // Adjusted Y-coordinate for centering
        g2.drawImage(babyDragonToken.getImage(), imageX, imageY, Board.SQUARE_SIZE / 2, Board.SQUARE_SIZE / 2, null);
        */

    }
}
