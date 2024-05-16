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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

  /**
   * Returns the list of players who are playing the current game.
   *
   * @return An ArrayList of players playing the current game.
   * */
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
    VolcanoCardController volcanoCardController = new VolcanoCardController(chitCardController.getGamePanel());

    CaveController caveController = new CaveController(volcanoCardController.getGamePanel(),volcanoCardController.getVolcanoCardsNearToCave(),players);

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
    add(caveController.getGamePanel());
    add(chitCardController.getGamePanel());
    add(volcanoCardController.getGamePanel());
    add(background);
  }

/**
 * Adds the key buttons (GameRuleButton, RestartButton, and ExitButton) components to the Game page
 * */
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

  /**
   * Runs the entire game in the Game page.
   * */
  public void run(){
    if (!tokenController.getTokens().isEmpty()) {
      currentPlayer = tokenController.getTokens().get(currentElem);
      processTokenTurn();
    }
  }

  /**
   *
   * */
  public void processTokenTurn(){

    chitCardController.getDeck().shuffleDeck();

    for(int i=0; i<chitCardController.getDeck().getChitCards().size();i++){
      System.out.println(i+1 + ": " + chitCardController.getDeck().getChitCards().get(i).getAnimal().getName() + " " + chitCardController.getDeck().getChitCards().get(i).getValue());
    }
    currentPlayerTurnLabel.setText("Current Player: " + processTokenAnimalName(currentPlayer.getAnimal().getName()));

    MouseAdapter mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        JLabel clickedLabel = (JLabel) e.getSource();
        int index = chitCardController.getLabelIndexMap().get(clickedLabel);
        ChitCard flippedCard = chitCardController.getDeck().getChitCards().get(index);

        checkIfFlippingTheFlippedCard(flippedCard);

        labels.put(clickedLabel,flippedCard);
        flippedCard.setIsFlipped(true);
        clickedLabel.setIcon(flippedCard.getImage());

        new Thread(() -> {
          if(isFlippingTheFlippedCard) {
            if (!currentPlayer.getCurrentSquare().getAnimal().getName()
                    .equals(flippedCard.getAnimal().getName())) {
              try {
                Thread.sleep(1000);
                flippedCard.setIsFlipped(true);
                clickedLabel.setIcon(flippedCard.getImage());
                if (flippedCard.getAnimal().getName().equals("pirate_dragon")) {
                  currentPlayer.setMove(new MoveBackwardsAction());
                  currentPlayer.executeMove(flippedCard.getValue(), currentPlayer);
                  if(currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
                    passNextToken(labels);
                  }
                  else{
                    askIfContinueTheTurn(labels, processTokenAnimalName(flippedCard.getAnimal().getName()));
                  }
                } else {
                  currentPlayer.setMove(new DoNothingAction());
                  currentPlayer.executeMove(flippedCard.getValue(), currentPlayer);
                  // if the player flips the pirate dragon, still can uncover another card
                  // but if the player flip different animal from its current position, only pass the next turn.
                  passNextToken(labels);
                }
              } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
              }
            } else {
              System.out.println("Match found!");
              currentPlayer.setMove(new MoveForwardsAction());
              String str = currentPlayer.executeMove(flippedCard.getValue(), currentPlayer);
              if (str == null) {
                try {
                  Thread.sleep(1000);
                  flippedCard.setIsFlipped(true);
                  clickedLabel.setIcon(flippedCard.getImage());
                } catch (InterruptedException ex) {
                  throw new RuntimeException(ex);
                }
                passNextToken(labels);
              } else if (str.equals("win")) {
                finish();
                for (JLabel j : chitCardController.getLabelIndexMap().keySet()) {
                  j.removeMouseListener(this);
                }
                labels.clear();
              }
              else{
                askIfContinueTheTurn(labels, processTokenAnimalName(flippedCard.getAnimal().getName()));
              }
            }
          }
        }).start();
      }
    };
    for (JLabel j : chitCardController.getLabelIndexMap().keySet()) {
      j.addMouseListener(mouseAdapter);
    }
  }

/**
 * Changes turn to the next player.
 */
  public void passNextToken(HashMap<JLabel,ChitCard> labels) {
    for (Entry<JLabel, ChitCard> entry : labels.entrySet()) {
      entry.getValue().setIsFlipped(false);
      entry.getKey().setIcon(entry.getValue().getImage());
    }
    labels.clear();
    // Move to the next player
    currentElem = (currentElem + 1) % tokenController.getTokens().size();
    // Update the current and next players
    currentPlayer = tokenController.getTokens().get(currentElem);
    // Print a message indicating the next player's turn
    currentPlayerTurnLabel.setText("Current Player: " + processTokenAnimalName(currentPlayer.getAnimal().getName()));
  }

  /**
   *
   * */
  public void askIfContinueTheTurn(HashMap<JLabel,ChitCard> labels, String flippedCardAnimalName){
    String changeTurnMessage = "You flipped the " + flippedCardAnimalName + " card.\n Do you want to continue your turn?";
    int choice = JOptionPane.showConfirmDialog(null, changeTurnMessage,
        "Question", JOptionPane.YES_NO_OPTION);
    if(choice == JOptionPane.NO_OPTION){
      passNextToken(labels);
    }
  }

  public void checkIfFlippingTheFlippedCard(ChitCard flippedCard){
    if(!flippedCard.isFlipped()){
      isFlippingTheFlippedCard = false;
    }
    else{
      isFlippingTheFlippedCard = true;
    }
  }

  public void finish(){
    String result = processTokenAnimalName(currentPlayer.getAnimal().getName());
    String winningMessage = "Congratulations! The winner is " + result + "!\n Do you want to start a new game?";
    int choice = JOptionPane.showConfirmDialog(null, winningMessage,
            "Question", JOptionPane.YES_NO_OPTION);
    if(choice == JOptionPane.YES_OPTION){
      System.out.println(players);
      ArrayList<String> clockwiseAnimals = new ArrayList<>(
          List.of("Spider", "Bat", "Salamander", "BabyDragon"));
      int startingIndex = clockwiseAnimals.indexOf(result.replaceAll("\\s",""));
      ArrayList<String> orderedAnimals = new ArrayList<>();
      for (int i = startingIndex; i < startingIndex + 4; i++) {
        orderedAnimals.add(clockwiseAnimals.get(i % 4));
      }
      Collections.sort(players, Comparator.comparingInt(orderedAnimals::indexOf));
      frame.getContentPane().removeAll();
      frame.getContentPane().add(new Game(frame,players));
      frame.revalidate();
      frame.repaint();
    }else{
      frame.getContentPane().removeAll();
      frame.getContentPane().add(new Home(frame));
      frame.revalidate();
      frame.repaint();
    }
  }

  public String processTokenAnimalName(String AnimalName){
    String[] words = AnimalName.split("_");
    StringBuilder result = new StringBuilder();

    // Capitalize the first letter of each word and append to result
    for (String word : words) {
      if (word.length() > 0) {
        result.append(Character.toUpperCase(word.charAt(0)));
        result.append(word.substring(1).toLowerCase()); // Convert remaining characters to lowercase
        result.append(" "); // Add a space between words
      }
    }
    // Remove the trailing space if any
    if (result.length() > 0) {
      result.setLength(result.length() - 1);
    }
    return result.toString();
  }
}


