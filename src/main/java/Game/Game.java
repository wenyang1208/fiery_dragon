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
import data.SaveLoad;

/**
 * The Game class represents the main game window and manages the initialization of game components.
 */
public class Game extends JPanel implements Serializable {
  private SaveLoad saveLoad;
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
  private int timeLimit;
  private ArrayList<Path> completedPaths;
  private boolean isCreatedPaths;

  /**
   * Constructs a new Game object.
   *
   * @param frame the frame
   * @param players the players
   * @param timeLimit the time limit
   */
  public Game(JFrame frame, ArrayList<String> players, int timeLimit){
    this.frame = frame;
    this.players = players;
    this.timeLimit = timeLimit;
    this.flipMap = new HashMap<String, Flip>();
    this.saveLoad = new SaveLoad(this);

    initialisingBackground(players);
    run();
    setLayout(null);
    setVisible(true);
    setSize(frame.getWidth(),boardHeight);
    screenTimeLimit(timeLimit);

  }


  /**
   * Gets the list of completed paths.
   *
   * @return An ArrayList of completed Path objects.
   */
  public ArrayList<Path> getCompletedPaths() {
    return completedPaths;
  }

  /**
   * Sets the list of completed paths.
   *
   * @param completedPaths An ArrayList of completed Path objects.
   */
  public void setCompletedPaths(ArrayList<Path> completedPaths) {
    this.completedPaths = completedPaths;
  }

  /**
   * Checks if paths are created.
   *
   * @return true if paths are created, false otherwise.
   */
  public boolean isCreatedPaths() {
    return isCreatedPaths;
  }

  /**
   * Sets the status of path creation.
   *
   * @param createdPaths A boolean indicating if paths are created.
   */
  public void setCreatedPaths(boolean createdPaths) {
    isCreatedPaths = createdPaths;
  }

  /**
   * Returns the list of players who are playing the current game.
   *
   * @return An ArrayList of players playing the current game.
   * */
  public ArrayList<String> getPlayers(){
    return this.players;
  }

  public void setPlayers(ArrayList<String> players) {
    this.players = players;
  }

  /**
   * Returns the time limit for the game.
   *
   * @return The time limit in milliseconds.
   */
  public int getTimeLimit(){
    return this.timeLimit;
  }

  public void setTimeLimit(int timeLimit){
    this.timeLimit = timeLimit;
  }

  /**
   * Returns the current player.
   *
   * @return The current Token object representing the player.
   */
  public Token getCurrentPlayer(){return this.currentPlayer;}

  /**
   * Returns the currently flipped card.
   *
   * @return The currently flipped ChitCard object.
   */
  public ChitCard getFlippedCard(){return this.flippedCard;}

  /**
   * Returns the VolcanoCardController for this game.
   *
   * @return The VolcanoCardController object.
   */
  public VolcanoCardController getVolcanoCardController() {
    return volcanoCardController;
  }

  /**
   * Returns the CaveController for this game.
   *
   * @return The CaveController object.
   */
  public CaveController getCaveController() {
    return caveController;
  }

  public void setVolcanoCardController(VolcanoCardController volcanoCardController) {
    this.volcanoCardController = volcanoCardController;
  }

  public void refreshGame() {
    // Clear existing components
    removeAll();

    // Re-initialize background and game components with the loaded state
    initialisingBackground(players);

    // Set other states if needed
    screenTimeLimit(timeLimit);
  }

  /**
   * Update the game state based on the loaded data.
   */
  public void updateGameFromLoadedState() {
    // Update the game's state with the loaded data
    this.players = saveLoad.getGame().getPlayers();
    this.timeLimit = saveLoad.getGame().getTimeLimit();

  }

  /**
   * Sets the amount of screen time allowed in this game.
   * If the game were to exceed the time limit, the token closest to finishing the path will be the winner.
   * If two or more tokens are equally near their own caves, the youngest player wins.
   *
   * @param timeLimit the amount of time (milliseconds) allowed for the game to be played.
   *
   * Teammate responsible: Chong Jet Shen (time limit feature)
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
              closestPlayer = currentPlayer.getPaths().size() - currentPlayer.getTokenPosition();
              winnerIndex += 1;
            }
          }
          timeLeftLabel.setText("Time is up!");

          currentPlayer = tokenController.getTokens().get(winnerIndex-1); // index 0...n-1 but winnerIndex up to n

          finish();
        }
      }
    });
    timer.start();
  }

  /**
   * Updates the time label.
   */
  private void updateTimeLabel(){
    long hours = timeLeft / (1000 * 60 * 60);
    long minutes = (timeLeft / (1000 * 60)) % 60;
    long seconds = (timeLeft / 1000) % 60;
    timeLeftLabel.setText(String.format("Time left: %02d:%02d:%02d", hours, minutes, seconds));
  }

  /**
   * Initializes the background of the game window.
   * Teammate responsible: Chong Jet Shen (time limit feature)
   */
  public void initialisingBackground(ArrayList<String> players){
    Image img = new ImageIcon(getClass().getClassLoader().getResource(
            "Project_cartoon/Background.jpg")).getImage();
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
   * Adds the key buttons (SaveButton, LoadButton, GameRuleButton, RestartButton, and ExitButton) components to the Game page
   * */
  public void addButton(){
    int button_height = (Game.boardWidth - GamePanel.WIDTH)/4;
    int button_width = (Game.boardWidth - GamePanel.WIDTH)/2;


    saveButton = new SaveButton(this.saveLoad);
    saveButton.setFont(new Font("Calibri", Font.BOLD, button_width/5-5));
    saveButton.setBounds(((Game.boardWidth + GamePanel.WIDTH)/2 - button_height),Game.boardHeight/3-(button_height+button_height/2), button_width, button_height);
    saveButton.setBorder(new LineBorder(Color.WHITE,button_height/10));

    loadButton = new LoadButton(this.saveLoad, this, frame);
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
   * Processes a player's token turn in the game. This method performs the following steps:
   * 1. Prints out the animal type and value of each chit card.
   * 2. Displays the current player.
   * 3. Sets up a mouse adapter to handle chit card flipping and game logic.
   *
   * The mouse adapter performs the following actions when a chit card is clicked:
   * 1. Identifies the clicked chit card and retrieves its index.
   * 2. Checks if the chosen chit card is already flipped to prevent redundant clicks.
   * 3. Updates the status of the chit card to flipped and updates its icon.
   * 4. Disables mouse listeners for other chit cards.
   * 5. Handles game logic based on the flipped chit card:
   *    - If the chit card's animal type matches the current player's position animal type, the player moves forward.
   *    - If the chit card's animal type is different, appropriate actions are taken based on the chit card type.
   *    - Special handling is done for specific animal types like Pirate Dragon or New Dragon.
   *    - The method supports auto unflipping the chit card after a delay.
   *
   * @param game the current game instance
   */
  public void processTokenTurn(Game game) {

    // Print out the animal type and the value of each chit card
    for (int i = 0; i < chitCardController.getDeck().getChitCards().size(); i++) {
      System.out.println(i + 1 + ": " + chitCardController.getDeck().getChitCards().get(i).getAnimal().getName() + " " + chitCardController.getDeck().getChitCards().get(i).getValue());
    }

    // Display the current player
    currentPlayerTurnLabel.setText("Current Player: " + currentPlayer.getAnimal().toString());

    // Use mouse adapter to flip the chit card
    mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        // The clicked label (chit card)
        JLabel clickedLabel = (JLabel) e.getSource();

        // Get the index from the HashMap (key: JLabel (chitCard), value: index)
        int index = chitCardController.getLabelIndexMap().get(clickedLabel);

        // Get the chit card from the deck list based on the index
        flippedCard = chitCardController.getDeck().getChitCards().get(index);

        // Check if the chosen chit card is flipped, we want to prevent the player click on the
        // flipped card and move.
        checkIfFlippingTheFlippedCard(flippedCard);

        // Put the clicked/flipped/chosen chit card in HashMap (key: JLabel, Value: ChitCard)
        labels.put(clickedLabel, flippedCard);

        // Set the status of the chit card become flipped
        flippedCard.setIsFlipped(true);

        // Get the image based on the status of the chit card
        clickedLabel.setIcon(flippedCard.getImage());

        // Disable the other chit card to be flipped
        disableChitCardMouseListeners();

        // Animation of the auto unflip of the chit card
        new Thread(() -> {
          // Let the flipped chit card display its front picture within 1 second
          try {
            Thread.sleep(1000);

            // If the chosen chit card is flipped
            if (isFlippingTheFlippedCard) {

              // If the animal type of the current player's position is different from the animal on the flipped chit card
              if (!currentPlayer.getCurrentSquare().getAnimal().getName().equals(flippedCard.getAnimal().getName())) {
                flippedCard.setIsFlipped(true);
                clickedLabel.setIcon(flippedCard.getImage());

                // If the flipped card is Pirate Dragon or New Dragon 1
                if (flippedCard.getAnimal().isSpeical()) {
                  if (!currentPlayer.getCurrentSquare().getClass().getSimpleName().equals("Cave")){
                    Flip flip = flipMap.get(flippedCard.getAnimal().getName());
                    flip.flip(game);
                    askIfContinueTheTurn(labels, flippedCard.getAnimal().toString());
                  }else{
                    passNextToken(labels);
                  }
                } else {
                  // The flipped card is the normal animal card, but different from the animal of the current player's position
                  currentPlayer.setMove(new DoNothingAction());
                  currentPlayer.executeMove(flippedCard.getValue(), game);
                  passNextToken(labels);
                }
              }

              // If the animal type of the current player's position is same as the animal on the flipped chit card
              else {
                System.out.println("Match found!");
                currentPlayer.setMove(new MoveForwardsAction());
                String str = currentPlayer.executeMove(flippedCard.getValue(), game);

                // Moving the token forward
                // If the token is allowed to move forward (i.e. not overstepping the cave/other token is at that position)
                if (str.equals("go")) {
                  askIfContinueTheTurn(labels, flippedCard.getAnimal().toString());
                  // If the token is winning
                } else if (str.equals("win")) {
                  finish();
                  disableChitCardMouseListeners();
                  labels.clear();
                } else {
                  flippedCard.setIsFlipped(true);
                  clickedLabel.setIcon(flippedCard.getImage());
                  passNextToken(labels);
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
    // Add the mouseListener to each chit card label
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
    currentPlayerTurnLabel.setText("Current Player: " + currentPlayer.getAnimal().toString());
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
    isFlippingTheFlippedCard = flippedCard.isFlipped();
  }

  /**
   * Ends the game with a winner pop-up screen, indicating the winner of the game.
   * Gives the option to start a new game with the same player information as the previous round.
   * Returns to Home page if option was not selected.
   *
   * Teammate responsible: Chong Jet Shen (time limit feature)
   * */
  public void finish(){
    JOptionPane.getRootFrame().dispose(); // removes all other frames
    timer.stop();

    String result = currentPlayer.getAnimal().toString();
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
      frame.getContentPane().add(new Game(frame, players, timeLimit));
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
