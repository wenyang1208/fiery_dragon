package Game;
import Action.DoNothingAction;
import Action.Flip;
import Action.FlipNewDragon;
import Action.FlipPirateDragon;
import Action.MoveForwardsAction;
import Button.ExitButton;
import Button.GameRuleButton;
import Button.LoadButton;
import Button.RestartButton;
import Button.SaveButton;
import Controller.TokenController;
import GameBoardComponent.Path;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Serializable;
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
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.border.LineBorder;
import GameBoardComponent.ChitCard;
import GameBoardComponent.Token;
import netscape.javascript.JSObject;

/**
 * The Game class represents the main game window and manages the initialization of game components.
 */
public class Game extends JPanel implements Serializable {
  public static final int boardWidth = Application.frameWidth;
  public static final int boardHeight = Application.frameHeight;
  private ChitCardController chitCardController;
  private TokenController tokenController;
  private VolcanoCardController volcanoCardController;

  private CaveController caveController;
  JLabel background;
  private Token currentPlayer;
  private int currentElem;
  HashMap<JLabel,ChitCard> labels = new HashMap<>();
  private boolean isFlippingTheFlippedCard = true;
  private JButton gameRuleButton;
  private JButton restartButton;
  private JButton saveButton;
  private JButton loadButton;
  private JButton exitButton;
  private JLabel currentPlayerTurnLabel;
  private JFrame frame;
  private ArrayList<String> players;
  private MouseAdapter mouseAdapter;
  private ChitCard flippedCard;
  private Map<String, Flip> flipMap;
  private Timer timer; // Java Swing timer, not Java util Timer
  private JLabel timeLeftLabel;
  private int timeLeft;
  public HashMap<JLabel, ChitCard> getLabels() {
    return labels;
  }
  public ArrayList<Path> getCompletedPaths() {
    return completedPaths;
  }

  public void setCompletedPaths(ArrayList<Path> completedPaths) {
    this.completedPaths = completedPaths;
  }

  private ArrayList<Path> completedPaths;
  private boolean isCreatedPaths;

  public boolean isCreatedPaths() {
    return isCreatedPaths;
  }

  public void setCreatedPaths(boolean createdPaths) {
    isCreatedPaths = createdPaths;
  }

  /**
   * Constructs a new Game object.
   *
   * @throws IOException If an I/O error occurs.
   */
  public Game(JFrame frame, ArrayList<String> players){
    this.frame = frame;
    this.players = players;
    this.flipMap = new HashMap<String, Flip>();
    initialisingBackground(players);
    run();
    setLayout(null);
    setVisible(true);
    setSize(frame.getWidth(),boardHeight);
    screenTimeLimit(3600000);
  }

  /**
   * Sets the amount of screen time allowed in this game.
   *
   * @param timeLimit the amount of time (milliseconds) allowed for the game to be played.
   * */
  private void screenTimeLimit(int timeLimit){
    timeLeft = timeLimit;
  timer = new Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      updateTimeLabel();
      timeLeft -= 1000;
      if (timeLeft <= -2000){ // 2 second delay

        timer.stop();
        double closestPlayer = Double.POSITIVE_INFINITY;
        int winnerIndex = 0;
        for (int i=0; i < tokenController.getTokens().size(); i++){
          currentPlayer = tokenController.getTokens().get(i);

//          System.out.println("\n"+(i+1)+": "+currentPlayer.getTokenPosition());
//          System.out.println(currentPlayer.getAnimal().getName());
//          System.out.println("steps to finish for player "+(i+1) + ": " + (currentPlayer.getPaths().size() - currentPlayer.getTokenPosition()));
//          System.out.println("closest "+closestPlayer);
          if (closestPlayer > currentPlayer.getPaths().size() - currentPlayer.getTokenPosition()){
            System.out.println("True");
            closestPlayer = currentPlayer.getPaths().size() - currentPlayer.getTokenPosition();
            winnerIndex += 1;
          }
        }
        timeLeftLabel.setText("Time is up!");

        currentPlayer = tokenController.getTokens().get(winnerIndex-1); // index 0...n-1 but winnerIndex up to n

//        System.out.println("winner is supposed to be: "+currentPlayer.getAnimal().getName());
        finish();
      }
    }
  });

//    timer = new Timer(timeLimit, new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        System.out.println("Time is up");
//        timeLeftLabel.setText("Time is up!");
//      }
//    });

//    timer.setRepeats(false);
    timer.start();
  }

  private void updateTimeLabel(){
    long hours = timeLeft / (1000 * 60 * 60);
    long minutes = (timeLeft / (1000 * 60)) % 60;
    long seconds = (timeLeft / 1000) % 60;
    timeLeftLabel.setText(String.format("Time left: %02d:%02d:%02d", hours, minutes, seconds));
  }

  /**
   * Returns the list of players who are playing the current game.
   *
   * @return An ArrayList of players playing the current game.
   * */
  public ArrayList<String> getPlayers(){
    return this.players;
  }

  public Token getCurrentPlayer(){return this.currentPlayer;}
  public ChitCard getFlippedCard(){return this.flippedCard;}
  public VolcanoCardController getVolcanoCardController() {
    return volcanoCardController;
  }
  public CaveController getCaveController() {
    return caveController;
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
    volcanoCardController = new VolcanoCardController(chitCardController.getGamePanel());

    caveController = new CaveController(volcanoCardController.getGamePanel(),volcanoCardController.getVolcanoCardsNearToCave(),players);

    tokenController = new TokenController(players, volcanoCardController.getVolcanoCards(),caveController.getCaves(),volcanoCardController.getVolcanoCardsNearToCave());
    for(int i = 0; i< tokenController.getTokens().size(); i++){
      Token token = tokenController.getTokens().get(i);
      token.setCurrentSqaure(caveController.getCaves().get(i));
      caveController.getCaves().get(i).addToken(token);
    }


    //testing for video demo
//    Token testingToken1 = tokenController.getTokens().get(0);
//    caveController.getCaves().get(0).removeToken();
//    testingToken1.setTokenPosition(testingToken1.getPaths().size()-2);
//    testingToken1.setCurrentSqaure(testingToken1.getPaths().get(testingToken1.getPaths().size()-2));
//    testingToken1.getPaths().get(testingToken1.getPaths().size()-2).addToken(testingToken1);
//
//    Token testingToken2 = tokenController.getTokens().get(1);
//    caveController.getCaves().get(1).removeToken();
//    testingToken2.setTokenPosition(18)
//    testingToken2.setCurrentSqaure(testingToken1.getPaths().get(18));
//    testingToken2.getPaths().get(18).addToken(testingToken2);

    currentPlayerTurnLabel = new JLabel();
    currentPlayerTurnLabel.setFont(new Font("Calibri", Font.BOLD, 20));
    currentPlayerTurnLabel.setBackground(new Color(255, 153, 0));
    currentPlayerTurnLabel.setOpaque(true);
    currentPlayerTurnLabel.setForeground(Color.WHITE);
    currentPlayerTurnLabel.setBounds(0, 0, Game.boardWidth*1/3, 30);
    currentPlayerTurnLabel.setBorder(new LineBorder(Color.WHITE,currentPlayerTurnLabel.getWidth()/100));
    add(currentPlayerTurnLabel);

    // Label to display amount of allowed screen time left
    timeLeftLabel = new JLabel("Time left: 00:00:00");
    timeLeftLabel.setFont(new Font("Calibri", Font.BOLD, 20));
    timeLeftLabel.setBackground(Color.green);
    timeLeftLabel.setOpaque(true);
    timeLeftLabel.setForeground(Color.RED);
    timeLeftLabel.setBounds(Game.boardWidth*3/4, 0, Game.boardWidth*1/4, 30);
    timeLeftLabel.setBorder(new LineBorder(Color.WHITE,currentPlayerTurnLabel.getWidth()/100));
    timeLeftLabel.setHorizontalAlignment(JLabel.CENTER);
    add(timeLeftLabel);



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

    saveButton = new SaveButton(frame, this);
    saveButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    saveButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3-(button_height+button_height/2), button_width, button_height);
    saveButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    loadButton = new LoadButton();
    loadButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    loadButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3, button_width, button_height);
    loadButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    gameRuleButton = new GameRuleButton();
    gameRuleButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    gameRuleButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3+(button_height+button_height/2), button_width, button_height);
    gameRuleButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    restartButton = new RestartButton(frame,this);
    restartButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    restartButton.setBounds(gameRuleButton.getX(),Game.boardHeight/3+(button_height+button_height/2)*2,button_width,button_height);
    restartButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    exitButton = new ExitButton();
    exitButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    exitButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3+(button_height+button_height/2)*3, button_width, button_height);
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

    add(saveButton);
    add(loadButton);
    add(gameRuleButton);
    add(restartButton);
    add(exitButton);
  }

  /**
   * Runs the entire game in the Game page.
   * */
  public void run(){
    if (!tokenController.getTokens().isEmpty()) {
      flipMap.put("pirate_dragon", new FlipPirateDragon());
      flipMap.put("new_dragon", new FlipNewDragon());
      currentPlayer = tokenController.getTokens().get(currentElem);
      processTokenTurn(this);
    }
  }

  /**
   *
   * */
  public void processTokenTurn(Game game) {
    chitCardController.getDeck().shuffleDeck();

    for (int i = 0; i < chitCardController.getDeck().getChitCards().size(); i++) {
      System.out.println(i + 1 + ": " + chitCardController.getDeck().getChitCards().get(i).getAnimal().getName() + " " + chitCardController.getDeck().getChitCards().get(i).getValue());
    }
    currentPlayerTurnLabel.setText("Current Player: " + processTokenAnimalName(currentPlayer.getAnimal().getName()));

    mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        JLabel clickedLabel = (JLabel) e.getSource();
        int index = chitCardController.getLabelIndexMap().get(clickedLabel);
        flippedCard = chitCardController.getDeck().getChitCards().get(index);

        checkIfFlippingTheFlippedCard(flippedCard);

        labels.put(clickedLabel, flippedCard);
        flippedCard.setIsFlipped(true);
        clickedLabel.setIcon(flippedCard.getImage());

        disableChitCardMouseListeners();

        new Thread(() -> {
          try {
            Thread.sleep(1000); // Sleep period

            if (isFlippingTheFlippedCard) {
              if (!currentPlayer.getCurrentSquare().getAnimal().getName()
                  .equals(flippedCard.getAnimal().getName())) {
                flippedCard.setIsFlipped(true);
                clickedLabel.setIcon(flippedCard.getImage());
                if (flippedCard.getAnimal().isSpeical()) {
                  if (!currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
                    Flip flip = flipMap.get(flippedCard.getAnimal().getName());
                    flip.flip(game);
                    askIfContinueTheTurn(labels, processTokenAnimalName(flippedCard.getAnimal().getName()));
                  }else{
                    passNextToken(labels);
                  }
                } else {
                  currentPlayer.setMove(new DoNothingAction());
                  currentPlayer.executeMove(flippedCard.getValue(), game);
                  passNextToken(labels);
                }
              } else {
                System.out.println("Match found!");
                currentPlayer.setMove(new MoveForwardsAction());
                String str = currentPlayer.executeMove(flippedCard.getValue(), game);
                if (str == null) {
                  flippedCard.setIsFlipped(true);
                  clickedLabel.setIcon(flippedCard.getImage());
                  passNextToken(labels);
                } else if (str.equals("win")) {
                  finish();
                  disableChitCardMouseListeners();
                  labels.clear();
                } else {
                  askIfContinueTheTurn(labels, processTokenAnimalName(flippedCard.getAnimal().getName()));
                }
              }
            }
          } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
          } finally {
            // Re-enable all chit cards by adding mouse listeners back
            enableChitCardMouseListeners(mouseAdapter);
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
   * Prompts the player if they would like to continue their turn
   * Changes to next player's turn if 'No' or no option was selected.
   * */
  public void askIfContinueTheTurn(HashMap<JLabel,ChitCard> labels, String flippedCardAnimalName){
    String changeTurnMessage = "You flipped the " + flippedCardAnimalName + " card.\n Do you want to continue your turn?";
    int choice = JOptionPane.showConfirmDialog(null, changeTurnMessage,
        "Question", JOptionPane.YES_NO_OPTION);
    if(choice != JOptionPane.YES_OPTION){
      passNextToken(labels);
    }
  }

  /**
   * Checks if the chit card is already flipped.
   * */
  public void checkIfFlippingTheFlippedCard(ChitCard flippedCard){
    if(!flippedCard.isFlipped()){
      isFlippingTheFlippedCard = false;
    }
    else{
      isFlippingTheFlippedCard = true;
    }
  }

  /**
   * Ends the game with a winner pop-up screen, indicating the winner of the game.
   * Gives the option to start a new game with the same player information as the previous round.
   * Returns to Home page if option was not selected.
   * */
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

  /**
   * Returns the animal name in a Start Case style.
   * */
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

  /**
   * Disables mouse listeners for all chit cards.
   */
  private void disableChitCardMouseListeners() {
    for (JLabel label : chitCardController.getLabelIndexMap().keySet()) {
      for (MouseListener listener : label.getMouseListeners()) {
        label.removeMouseListener(listener);
      }
    }
  }

  /**
   * Enables mouse listeners for all chit cards.
   */
  private void enableChitCardMouseListeners(MouseAdapter mouseAdapter) {
    for (JLabel label : chitCardController.getLabelIndexMap().keySet()) {
      label.addMouseListener(mouseAdapter);
    }
  }
}


