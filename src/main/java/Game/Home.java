package Game;

import Button.ExitButton;
import Button.GameRuleButton;
import Button.LoadButton;
import Button.StartButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * The Home class represents the main home page of the entire game.
 * */
public class Home extends JPanel {
    JLabel background;
    //  public static final int boardWidth = 1050;
//  public static final int boardHeight = 750;
    private JLabel dragon_icon;
    private JLabel title;
    private JButton gameRuleButton;
    private JButton startButton;
    private JButton exitButton;
    private JButton loadButton;
    private JFrame frame;

    /**
     * Constructs a new Home object.
     * */
    public Home(final JFrame frame){
        this.frame = frame;
        initialisingBackground();
        setLayout(null);
        setVisible(true);
        setSize(frame.getWidth(),frame.getHeight());
    }

    /**
     * Initialises the background of the home page along with the other key components of the home page.
     * */
    public void initialisingBackground(){
        Image img = new ImageIcon(getClass().getClassLoader().getResource(
            "Project_cartoon/Background.jpg")).getImage();
        Image temp=img.getScaledInstance(frame.getWidth(),frame.getHeight(), Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(temp));
        background.setLayout(null);
        background.setBounds(0,0,frame.getWidth(),frame.getHeight());

        addButton();
        addDragonIcon();
        addTitle();
        add(background);
    }

    /**
     * Adds the main dragon mascot to the home page
     * */
    public void addDragonIcon(){
        Image dragon_img = new ImageIcon(getClass().getClassLoader().getResource(
            "Project_cartoon/dragon.png")).getImage();
        Image dragon_temp=dragon_img.getScaledInstance((int) (frame.getWidth()/2.5),(int) (frame.getHeight()*0.7), Image.SCALE_SMOOTH);
        dragon_icon = new JLabel(new ImageIcon(dragon_temp));
        dragon_icon.setBounds(10,frame.getHeight()/5, (int) (frame.getWidth()/2.5),(int) (frame.getHeight()*0.7));
        add(dragon_icon);
    }

    /**
     * Displays the title of the game 'Fiery Dragon' in a graphical style to the home page
     * */
    public void addTitle(){
        Image title_img = new ImageIcon(getClass().getClassLoader().getResource(
            "Project_cartoon/Fiery Dragon.png")).getImage();
        Image title_temp=title_img.getScaledInstance(frame.getWidth()*3/4,frame.getHeight()*2/10, Image.SCALE_SMOOTH);
        title = new JLabel((new ImageIcon(title_temp)));
        title.setBounds(frame.getWidth()/7,frame.getHeight()/10,frame.getWidth()*3/4,  frame.getHeight()*2/10);
        add(title);
    }

    /**
     * Adds the key buttons (StartButton, GameRuleButton, and ExitButton) components to the Home page
     * */
    public void addButton(){
        int button_height = frame.getHeight()/10;
        int button_width = frame.getWidth()/4;


        gameRuleButton = new GameRuleButton();
        gameRuleButton.setFont(new Font("Calibri", Font.BOLD, button_width/7));
        gameRuleButton.setBounds((int) (frame.getWidth()/2.5),frame.getHeight()/3+(button_height+button_height/2), button_width, button_height);
        gameRuleButton.setBorder(new LineBorder(Color.WHITE,button_height/10));
        add(gameRuleButton);

        startButton = new StartButton(frame);
        startButton.setFont(new Font("Calibri", Font.BOLD, button_width/6));
        startButton.setBounds((int) (frame.getWidth()/2.5),frame.getHeight()/3, button_width, button_height);
        startButton.setBorder(new LineBorder(Color.WHITE,button_height/10));
        add(startButton);

        exitButton = new ExitButton();
        exitButton.setFont(new Font("Calibri", Font.BOLD, button_width/6));
        exitButton.setBounds((int) (frame.getWidth()/2.5),frame.getHeight()/3+(button_height+button_height/2)*2, button_width, button_height);
        exitButton.setBorder(new LineBorder(Color.WHITE,button_height/10));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the program
            }
        });

        add(exitButton);
    }
}
