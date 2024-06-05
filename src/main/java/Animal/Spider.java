package Animal;

/**
 * The Spider class represents a spider, implementing the Animal interface.
 */
public class Spider implements Animal {
  // Constants for the name and maximum value
  private final static String name = "spider";
  private final static int maxVal = 3;

  /**
   * Retrieves the name of the spider.
   *
   * @return The name of the spider.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the maximum value associated with the spider.
   *
   * @return The maximum value of the spider.
   */
  public int getMaxVal(){
    return maxVal;
  }

  /**
   * Indicates whether the spider has special characteristics.
   * For a bat, this method always returns {@code false}.
   *
   * @return {@code false} as spider are not considered special
   */
  @Override
  public boolean isSpeical() {
    return false;
  }

  /**
   * Returns a string representation of the spider.
   *
   * @return a string "Spider" representing the spider
   */
  @Override
  public String toString(){
    return "Spider";
  }
}

