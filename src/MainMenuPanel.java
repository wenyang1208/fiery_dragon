import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements Runnable {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1250;
    private ImageIcon backgroundImage;
    private JFrame frame;

    public MainMenuPanel(final JFrame frame) {
        this.frame = frame;
        backgroundImage = new ImageIcon(getClass().getResource("/background/Fiery.png"));
        backgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setLayout(null);

        JButton startGameButton = new JButton("");
        JButton gameRuleButton = new JButton("");
        JButton exitButton = new JButton("");


        startGameButton.setBounds(650, 375, 260, 120);
        gameRuleButton.setBounds(610, 550, 350, 120);
        exitButton.setBounds(650, 730, 260, 120);

        /*
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setBorderPainted(false);*/

        gameRuleButton.setOpaque(false);
        gameRuleButton.setContentAreaFilled(false);
        gameRuleButton.setBorderPainted(false);

        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new GamePanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        gameRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
