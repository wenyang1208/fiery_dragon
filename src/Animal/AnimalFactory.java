package Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AnimalFactory class provides static methods for creating different types of animal collections.
 */
public class AnimalFactory {

  // Map to hold the mapping between animal names and their respective objects
  private static final Map<String, Animal> animalMap = new HashMap<>();

  static {
    // Initialize the map with animal name-object pairs
    animalMap.put("Spider", new Spider());
    animalMap.put("Bat", new Bat());
    animalMap.put("Salamander", new Salamander());
    animalMap.put("BabyDragon", new BabyDragon());
    animalMap.put("true", new PirateDragon());
  }

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
    animalFactories.add(new NewDragon());
    animalFactories.add(new NewDragon());
    animalFactories.add(new NewDragon());
    animalFactories.add(new NewDragon());
    return animalFactories;
  }

  /**
   * Creates a collection of animals for the Volcano Card.
   *
   * @return An ArrayList containing animals for the Volcano Card.
   */
  public static ArrayList<Animal> createVolcanoCardAnimal() {
    ArrayList<Animal> animalFactories = new ArrayList<>();

    // Pre-defined arrangement of animals for each card
    String[][] arrangements = {
        {"BabyDragon", "Salamander", "Bat"},
//        {"Bat", "BabyDragon", "Salamander", "true"},//cave
        {"BabyDragon", "Bat", "Spider"},
        {"Bat", "BabyDragon", "Salamander", "true"},//cave
//        {"Bat", "Spider", "BabyDragon", "true"}, //cave
        {"Spider", "Salamander", "BabyDragon"},
        {"Bat", "Spider", "BabyDragon", "true"}, //cave
        {"Salamander", "Spider", "Bat", "true"}, //cave
        {"Salamander", "BabyDragon", "Spider"},
        {"Spider", "Bat", "Salamander","true"} //cave
    };

    // Convert the array to a list and shuffle it
    List<String[]> arrangementsList = Arrays.asList(arrangements);
    Collections.shuffle(arrangementsList);

    // Populate the animalFactories list based on the shuffled arrangements
    for (String[] arrangement : arrangementsList) {
      for (String animalName : arrangement) {
        Animal animal = animalMap.get(animalName);
        if (animal != null) {
          animalFactories.add(animal);
        } else {
          throw new IllegalArgumentException("Unknown animal name: " + animalName);
        }
      }
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
