package Animal;

import java.util.ArrayList;

/**
 * The AnimalFactory class provides static methods for creating different types of animal collections.
 */
public class AnimalFactory {

  /**
   * Creates a collection of animals for the Chit Card.
   *
   * @return An ArrayList containing animals for the Chit Card.
   */
  public static ArrayList<Animal> createChitCardAnimal() {
    ArrayList<Animal> animalFactories = new ArrayList<>();
    animalFactories.add(new Spider());
    animalFactories.add(new Bat());
    animalFactories.add(new Salamander());
    animalFactories.add(new BabyDragon());
    animalFactories.add(new PirateDragon());
    animalFactories.add(new PirateDragon());
    return animalFactories;
  }

  /**
   * Creates a collection of animals for the Volcano Card.
   *
   * @param numberOfCards          The number of volcano cards.
   * @param numberOfSquaresInACard The number of squares in each volcano card.
   * @return An ArrayList containing animals for the Volcano Card.
   */
  public static ArrayList<Animal> createVolcanoCardAnimal(int numberOfCards, int numberOfSquaresInACard){
    ArrayList<Animal> animalFactories = new ArrayList<>();
    int totalNumberOfSquares = numberOfCards * numberOfSquaresInACard;
    for (int i = 1; i <= Math.round(totalNumberOfSquares/4); i++){
      animalFactories.add(new Spider());
      animalFactories.add(new Bat());
      animalFactories.add(new Salamander());
      animalFactories.add(new BabyDragon());
    }
    return animalFactories;
  }

  /**
   * Creates a collection of animals for the Cave.
   *
   * @return An ArrayList containing animals for the Cave.
   */
  public static ArrayList<Animal> createCaveAnimal(){
    ArrayList<Animal> animalFactories = new ArrayList<>();
    animalFactories.add(new Spider());
    animalFactories.add(new Bat());
    animalFactories.add(new Salamander());
    animalFactories.add(new BabyDragon());
    return animalFactories;
  }

  /**
   * Creates a collection of animals for a token.
   *
   * @return An ArrayList containing animals for a token.
   */
  public static ArrayList<Animal> createTokenAnimal(){
    ArrayList<Animal> animalFactories = new ArrayList<>();
    animalFactories.add(new Spider());
    animalFactories.add(new Bat());
    animalFactories.add(new Salamander());
    animalFactories.add(new BabyDragon());
    return animalFactories;
  }
}