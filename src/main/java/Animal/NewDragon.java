package Animal;

/**
 * The New Dragon class represents a New Dragon, implementing the Animal interface.
 * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
 */
public class NewDragon implements Animal{
  private final static String name = "new_dragon";
  private final static int maxVal = 1;

  /**
   * Retrieves the name of the new dragon.
   *
   * @return The name of the new dragon.
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the new dragon.
   *
   * @return The maximum value of the new dragon.
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public int getMaxVal() {
    return maxVal;
  }

  /**
   * Indicates whether the new dragon has special characteristics.
   * For a new dragon, this method always returns {@code true}.
   *
   * @return {@code true} as new dragon are considered special
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public boolean isSpeical() {
    return true;
  }

  /**
   * Returns a string representation of the new dragon.
   *
   * @return a string "New Dragon" representing the new dragon
   * Teammate responsible: Chua Wen Yang (new dragon card 1 feature)
   */
  @Override
  public String toString(){
    return "New Dragon";
  }
}
