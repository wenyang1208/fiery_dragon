import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel implements Runnable {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    private ImageIcon backgroundImage;

    public MainMenuPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("/background/Fiery.png"));
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }

    @Override
    public void run() {

    }
}
