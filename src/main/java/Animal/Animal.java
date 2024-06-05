package Animal;

/**
 * The Animal interface represents a generic animal.
 */
public interface Animal {

  /**
   * Retrieves the name of the animal.
   *
   * @return The name of the animal.
   */
  String getName();

  /**
   * Retrieves the maximum value associated with the animal.
   *
   * @return The maximum value of the animal.
   */
  int getMaxVal();

  /**
   * Checks if the animal has special characteristics.
   * This method indicates whether the animal has unique properties or behaviors
   * that distinguish it from regular animals.
   *
   * @return {@code true} if the animal is special, {@code false} otherwise
   */
  boolean isSpeical();

}
