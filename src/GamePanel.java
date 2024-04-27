import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1250;
    final int MAX_COL = 21;
    final int MAX_ROW = 25;
    private ImageIcon backgroundImage;
    private int[][] opacity;
    Board board = new Board();

    public GamePanel(JFrame frame) {
        backgroundImage = new ImageIcon(getClass().getResource("/background/Background.jpg"));
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background image
        g.drawImage(backgroundImage.getImage(), 0, 0, this);

        Graphics2D g2 = (Graphics2D)g;
        // Draw white squares for caves, dragon cards and volcanoes cards
        board.draw(g2);
    }

    private void drawWhiteSquares(Graphics g) {
        // Draw white squares with opacity based on the opacity array
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                int squareOpacity = opacity[row][col]; // Get opacity value for the current square
                g.setColor(new Color(255, 255, 255, squareOpacity)); // Set color with opacity
                g.fillRect(col * Board.SQUARE_SIZE, row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
            }
        }
    }

    public int[][] designBoard(){
        opacity = new int[MAX_ROW][MAX_COL];
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                opacity[row][col] = 0; // Set all squares to no opacity initially
            }
        }

        /*
        opacity[1][10] = 100;
        opacity[3][10] = 100;
        opacity[3][12] = 100;
        opacity[4][14] = 100;
        opacity[6][15] = 100;
        opacity[8][16] = 100;
        opacity[10][17] = 100;
        opacity[12][17] = 100;
        opacity[12][19] = 100;*/

        opacity[0][21] = 100;

        return opacity;
    }
}
