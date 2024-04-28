import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1250;
    private ImageIcon backgroundImage;
    Board board = new Board();
    Thread gameThread;
    final int FPS = 60;

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

    public void update(){

    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
}
