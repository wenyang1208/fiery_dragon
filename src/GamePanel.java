import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private ImageIcon backgroundImage;

    public GamePanel(JFrame frame) {
        backgroundImage = new ImageIcon(getClass().getResource("/background/Background.jpg"));
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }
}