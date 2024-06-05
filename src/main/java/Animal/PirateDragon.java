package Animal;

/**
 * The PirateDragon class represents a pirate dragon, implementing the Animal interface.
 */
public class PirateDragon implements Animal {
  private final static String name = "pirate_dragon";
  private final static int maxVal = 2;

  /**
   * Retrieves the name of the pirate dragon.
   *
   * @return The name of the pirate dragon.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the pirate dragon.
   *
   * @return The maximum value of the pirate dragon.
   */
  @Override
  public int getMaxVal(){
    return maxVal;
  }

  /**
   * Indicates whether the pirate dragon has special characteristics.
   * For a pirate dragon, this method always returns {@code true}.
   *
   * @return {@code true} as pirate dragon are considered special
   */
  @Override
  public boolean isSpeical() {
    return true;
  }

  /**
   * Returns a string representation of the pirate dragon.
   *
   * @return a string "Pirate Dragon" representing the pirate dragon
   */
  @Override
  public String toString(){
    return "Pirate Dragon";
  }

}

