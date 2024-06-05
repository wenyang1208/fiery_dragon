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

  /**
   * Indicates whether the baby dragon has special characteristics.
   * For a baby dragon, this method always returns {@code false}.
   *
   * @return {@code false} as baby dragons are not considered special
   */
  @Override
  public boolean isSpeical() {
    return false;
  }

  /**
   * Returns a string representation of the baby dragon.
   *
   * @return a string "Baby Dragon" representing the baby dragon
   */
  @Override
  public String toString(){
    return "Baby Dragon";
  }
}
