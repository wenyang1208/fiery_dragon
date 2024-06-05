package Animal;

/**
 * The Salamander class represents a salamander, implementing the Animal interface.
 */
public class Salamander implements Animal {
  // Constants for the name and maximum value
  private final static String name = "salamander";
  private final static int maxVal = 3;

  /**
   * Retrieves the name of the salamander.
   *
   * @return The name of the salamander.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the salamander.
   *
   * @return The maximum value of the salamander.
   */
  public int getMaxVal(){
    return maxVal;
  }

  /**
   * Indicates whether the salamander has special characteristics.
   * For a bat, this method always returns {@code false}.
   *
   * @return {@code false} as salamander are not considered special
   */
  @Override
  public boolean isSpeical() {
    return false;
  }

  /**
   * Returns a string representation of the salamander.
   *
   * @return a string "Salamander" representing the salamander
   */
  @Override
  public String toString(){
    return "Salamander";
  }
}

