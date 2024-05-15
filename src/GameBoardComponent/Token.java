package GameBoardComponent;

import Action.Move;
import Animal.Animal;
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
  /**
   * Constructs a new Token object with the specified animal and token position.
   *
   * @param animal The animal associated with the token.
   */
  public Token(Animal animal, int tokenSize, int tokenNumber){
    setAnimal(animal);
    setTokenSize(tokenSize);
    setTokenNumber(tokenNumber);
    this.paths = new ArrayList<>();
    this.additionalPath = new ArrayList<>();
  }

  public ArrayList<Path> getAdditionalVolcanoCardPath(){
    return this.additionalPath;
  }

  public void setAdditionalVolcanoCardPath(ArrayList<Path> processPathList){
    this.additionalPath = processPathList;
  }
  public void increaseNumberOfBelowMinSquare(){
    this.numberOfBelowMinSquare += 1;
  }
  public int getNumberOfBelowMinSquare(){
    return this.numberOfBelowMinSquare;
  }
  public int getTokenPosition(){
    return this.tokenPosition;
  }

  /**
   * Sets the position of the token.
   *
   * @param tokenPosition The position to set.
   */
  public void setTokenPosition(int tokenPosition){
    this.tokenPosition = tokenPosition;
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

  public void setTokenSize(int tokenSize){
    this.tokenSize = tokenSize-15;
  }

  public void setTokenNumber(int tokenNumber){this.tokenNumber = tokenNumber;}
  public Path getCurrentSquare(){
    return this.path;
  }
  /**
   * Retrieves the image icon representing the token.
   *
   * @return The image icon of the token.
   */
  public ImageIcon getImage(){
    tokenImage = new ImageIcon(getClass().getClassLoader().getResource("TokenImage/" + getAnimal().getName() + "_token.png")).getImage();
    Image resizedImage = tokenImage.getScaledInstance(getTokenSize(),getTokenSize(), java.awt.Image.SCALE_SMOOTH);
    ImageIcon tokenImageIcon = new ImageIcon(resizedImage);
    return tokenImageIcon;
  }

  public int getTokenSize(){
    return this.tokenSize;
  }
  public int getTokenNumber(){return this.tokenNumber;}

  public void setMove(Move move){
    this.move = move;
  }
  public String executeMove(int chitCardValue, Token token){
    return move.execute(chitCardValue, this);
  }
  public ArrayList<Path> getPaths(){
    return this.paths;
  }
}
