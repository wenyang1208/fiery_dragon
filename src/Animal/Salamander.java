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

}

