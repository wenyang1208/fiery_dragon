package Animal;

/**
 * The Bat class represents a bat, implementing the Animal interface.
 */
public class Bat implements Animal {
  // Constants for the name and maximum value
  private final static String name = "bat";
  private final static int maxVal = 3;

  /**
   * Retrieves the name of the bat.
   *
   * @return The name of the bat.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the bat.
   *
   * @return The maximum value of the bat.
   */
  @Override
  public int getMaxVal(){
    return maxVal;
  }

  /**
   * Indicates whether the bat has special characteristics.
   * For a bat, this method always returns {@code false}.
   *
   * @return {@code false} as bat are not considered special
   */
  @Override
  public boolean isSpeical() {
    return false;
  }

  /**
   * Returns a string representation of the bat.
   *
   * @return a string "Bat" representing the bat
   */
  @Override
  public String toString(){
    return "Bat";
  }
}
