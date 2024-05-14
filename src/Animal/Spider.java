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
}

