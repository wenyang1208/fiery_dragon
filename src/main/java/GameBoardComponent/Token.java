package GameBoardComponent;

import Action.Move;
import Animal.Animal;
import Game.Game;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * The Token class represents a token on the game board.
 */
public class Token extends GameComponent {
  private ArrayList<Path> paths;
  private Move move;
  private int tokenPosition = 0;
  private int tokenSize;
  private int tokenNumber;
  private Path path;
  private Image tokenImage;
  private int numberOfBelowMinSquare = 0;
  private ArrayList<Path> additionalPath;
  private boolean isFlipNewDragon = false;

  /**
   * Constructs a new Token object with the specified animal, token size, and token number.
   *
   * @param animal The animal associated with the token.
   * @param tokenSize The size of the token.
   * @param tokenNumber The number assigned to the token.
   */
  public Token(Animal animal, int tokenSize, int tokenNumber){
    setAnimal(animal);
    setTokenSize(tokenSize);
    setTokenNumber(tokenNumber);
    this.paths = new ArrayList<>();
    this.additionalPath = new ArrayList<>();
  }

  public boolean isFlipNewDragon() {
    return isFlipNewDragon;
  }

  public void setFlipNewDragon(boolean flipNewDragon) {
    isFlipNewDragon = flipNewDragon;
  }

  /**
   * Sets the additional paths for volcano cards.
   *
   * @param processPathList The list of additional paths to set.
   */
  public void setAdditionalVolcanoCardPath(ArrayList<Path> processPathList){
    this.additionalPath = processPathList;
  }

  /**
   * Gets the additional paths for volcano cards.
   *
   * @return A list of additional paths.
   */
  public ArrayList<Path> getAdditionalVolcanoCardPath(){
    return this.additionalPath;
  }

  /**
   * Sets the current square of the token.
   *
   * @param path The path representing the current square.
   */
  public void setCurrentSqaure(Path path){
    this.path = path;
  }


  /**
   * Retrieves the current square of the token.
   *
   * @return The path representing the current square.
   */
  public Path getCurrentSquare(){
    return this.path;
  }

  /**
   * Sets the number assigned to the token.
   *
   * @param tokenNumber The number to set.
   */
  public void setTokenNumber(int tokenNumber){this.tokenNumber = tokenNumber;}

  /**
   * Gets the token number assigned to each token in the game.
   * */
  public int getTokenNumber(){return this.tokenNumber;}

  /**
   * Sets the position of the token.
   *
   * @param tokenPosition The position to set.
   */
  public void setTokenPosition(int tokenPosition){
    this.tokenPosition = tokenPosition;
  }

  /**
   * Gets the current position of the token.
   *
   * @return The current position of the token.
   */
  public int getTokenPosition(){
    return this.tokenPosition;
  }

  /**
   * Sets the size of the token.
   *
   * @param tokenSize The size to set.
   */
  public void setTokenSize(int tokenSize){
    this.tokenSize = tokenSize-15;
  }

  /**
   * Gets the size of the token
   * */
  public int getTokenSize(){
    return this.tokenSize;
  }

  /**
   * Retrieves the image icon representing the token.
   *
   * @return The image icon of the token.
   */
  public ImageIcon getImage(){
    tokenImage = new ImageIcon(getClass().getClassLoader().getResource(
        "TokenImage/" + getAnimal().getName() + "_token.png")).getImage();
    Image resizedImage = tokenImage.getScaledInstance(getTokenSize(),getTokenSize(), java.awt.Image.SCALE_SMOOTH);
    ImageIcon tokenImageIcon = new ImageIcon(resizedImage);
    return tokenImageIcon;
  }

  /**
   * Gets the path of the token
   * */
  public ArrayList<Path> getPaths(){
    return this.paths;
  }


  /**
   * Sets the type of move the token has to move on the board.
   * @param move Type of move the token has to move on the board
   * */
  public void setMove(Move move){
    this.move = move;
  }

  /**
   * Executes the move for the token on the board.
   *
   * @param chitCardValue The number of spaces for the token to move on the board.
   * @param game The game instance.
   * @return The result of the move execution.
   */
  public String executeMove(int chitCardValue, Game game){
    return move.execute(chitCardValue, game);
  }

}
