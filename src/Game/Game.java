package Game;
import Action.DoNothingAction;
import Action.MoveBackwardsAction;
import Action.MoveForwardsAction;
import Button.ExitButton;
import Button.GameRuleButton;
import Button.RestartButton;
import Controller.TokenController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread;
import Controller.CaveController;
import Controller.ChitCardController;
import Controller.VolcanoCardController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import GameBoardComponent.ChitCard;
import GameBoardComponent.Token;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Game class represents the main game window and manages the initialization of game components.
 */
public class Game extends JPanel{
  public static final int boardWidth = Application.frameWidth;
  public static final int boardHeight = Application.frameHeight;
  private ChitCardController chitCardController;
  private TokenController tokenController;
  JLabel background;
  private Token currentPlayer;
  private int currentElem;
  HashMap<JLabel,ChitCard> labels = new HashMap<>();
  private boolean isFlippingTheFlippedCard = true;
  private JButton gameRuleButton;
  private JButton restartButton;
  private JButton exitButton;
  private JLabel currentPlayerTurnLabel;
  private JFrame frame;
  private ArrayList<String> players;

  /**
   * Constructs a new Game object.
   *
   * @throws IOException If an I/O error occurs.
   */
  public Game(JFrame frame, ArrayList<String> players){
    this.frame = frame;
    this.players = players;
    initialisingBackground(players);
    run();
    setLayout(null);
    setVisible(true);
    setSize(frame.getWidth(),boardHeight);
  }

  public ArrayList<String> getPlayers(){
    return this.players;
  }
  /**
   * Initializes the background of the game window.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void initialisingBackground(ArrayList<String> players){
    Image img = new ImageIcon(getClass().getClassLoader().getResource("Project_cartoon/Background.jpg")).getImage();
    Image temp=img.getScaledInstance(boardWidth,boardHeight, Image.SCALE_SMOOTH);
    background = new JLabel(new ImageIcon(temp));
    background.setLayout(null);
    background.setBounds(0,0,boardWidth,boardHeight);

    GamePanel gamePanel = new GamePanel();
    chitCardController = new ChitCardController(gamePanel);
    chitCardController.initialiseChitCardSetup();
    // Cannot create new Game.GameSetup, so I pass the previous Game.GameSetup from the ChitCardContoller as the parameter to the controller.
    VolcanoCardController volcanoCardController = new VolcanoCardController(chitCardController.getGameSetup());

    CaveController caveController = new CaveController(volcanoCardController.getGameSetup(),volcanoCardController.getVolcanoCardsNearToCave(),players);

    tokenController = new TokenController(players, volcanoCardController.getVolcanoCards(),caveController.getCaves(),volcanoCardController.getVolcanoCardsNearToCave());
    for(int i = 0; i< tokenController.getTokens().size(); i++){
      Token token = tokenController.getTokens().get(i);
      token.setCurrentSqaure(caveController.getCaves().get(i));
      caveController.getCaves().get(i).addToken(token);
    }

    currentPlayerTurnLabel = new JLabel();
    currentPlayerTurnLabel.setFont(new Font("Calibri", Font.BOLD, 20));
    currentPlayerTurnLabel.setBackground(new Color(255, 153, 0));
    currentPlayerTurnLabel.setOpaque(true);
    currentPlayerTurnLabel.setForeground(Color.WHITE);
    currentPlayerTurnLabel.setBounds(0, 0, Game.boardWidth*1/4, 30);
    currentPlayerTurnLabel.setBorder(new LineBorder(Color.WHITE,currentPlayerTurnLabel.getWidth()/100));
    add(currentPlayerTurnLabel);

    addButton();
    add(caveController.getGameSetup());
    add(chitCardController.getGameSetup());
    add(volcanoCardController.getGameSetup());
    add(background);
  }

  public void addButton(){
    int button_height = (Game.boardWidth - GamePanel.WIDTH)/4;
    int button_width = (Game.boardWidth - GamePanel.WIDTH)/2;

    gameRuleButton = new GameRuleButton();
    gameRuleButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    gameRuleButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3, button_width, button_height);
    gameRuleButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    restartButton = new RestartButton(frame,this);
    restartButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    restartButton.setBounds(gameRuleButton.getX(),Game.boardHeight/3+(button_height+button_height/2),button_width,button_height);
    restartButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    exitButton = new ExitButton();
    exitButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    exitButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3+(button_height+button_height/2)*2, button_width, button_height);
    exitButton.setBorder(new LineBorder(Color.WHITE,button_height/10));
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        setVisible(false);
//        Home h =
//        new Home();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Home(frame));
        frame.revalidate();
        frame.repaint();
      }
    });

    add(gameRuleButton);
    add(restartButton);
    add(exitButton);
  }

  public void run(){
    if (!tokenController.getTokens().isEmpty()) {
      currentPlayer = tokenController.getTokens().get(currentElem);
//      processTokenTurn();
    }
  }




