import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements Runnable {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private ImageIcon backgroundImage;

    public MainMenuPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("/background/Fiery.png"));
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setLayout(null);

        JButton startGameButton = new JButton("");
        JButton gameRuleButton = new JButton("");
        JButton exitButton = new JButton("");


        startGameButton.setBounds(520, 300, 220, 100);
        gameRuleButton.setBounds(480, 440, 300, 100);
        exitButton.setBounds(515, 580, 205, 100);

        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setBorderPainted(false);

        gameRuleButton.setOpaque(false);
        gameRuleButton.setContentAreaFilled(false);
        gameRuleButton.setBorderPainted(false);

        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game button clicked");
            }
        });

        gameRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game rule button clicked");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit button clicked");
            }
        });

        add(startGameButton);
        add(gameRuleButton);
        add(exitButton);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }

    @Override
    public void run() {

    }
}
