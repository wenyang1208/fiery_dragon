import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        setLayout(null);


        JButton gameRuleButton = new JButton("Game Rule");
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");

        gameRuleButton.setBounds(1150, 250, 260, 100);
        restartButton.setBounds(1150, 600, 260, 100);
        exitButton.setBounds(1150, 950, 260, 100);

        gameRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameRulesMessage = "Your game rules message here.";

                // Show the message dialog
                JOptionPane.showMessageDialog(frame, gameRulesMessage, "Game Rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new GamePanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MainMenuPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        add(restartButton);
        add(gameRuleButton);
        add(exitButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Get mouse coordinates
                int mouseX = e.getX();
                int mouseY = e.getY();


                // Check if the click is on a DragonCard
                for (DragonCard.DragonCardInfo dragonCard : board.dCard.dragonCardList) {
                    int cardY = dragonCard.getX() * Board.SQUARE_SIZE;
                    int cardX = dragonCard.getY() * Board.SQUARE_SIZE;
                    if (mouseX >= cardX && mouseX < cardX + Board.SQUARE_SIZE &&
                            mouseY >= cardY && mouseY < cardY + Board.SQUARE_SIZE) {
                        // Toggle the state of the clicked DragonCard
                        dragonCard.flip();
                        // Repaint the panel to show the flipped DragonCard
                        repaint();
                        break; // No need to check other cards
                    }
                }
            }
        });
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
