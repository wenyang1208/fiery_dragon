package Animal;

/**
 * The BabyDragon class represents a baby dragon, implementing the Animal interface.
 */
public class BabyDragon implements Animal {
  // Constants for the name and maximum value
  private final static String name = "baby_dragon";
  private final static int maxVal = 3;

  /**
   * Retrieves the name of the baby dragon.
   *
   * @return The name of the baby dragon.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the baby dragon.
   *
   * @return The maximum value of the baby dragon.
   */
  @Override
  public int getMaxVal(){
    return maxVal;
  }

  @Override
  public boolean isSpeical() {
    return false;
  }

  public String toString(){
    return "Baby Dragon";
  }
}
