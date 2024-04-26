import java.util.ArrayList;

public class AnimalFactory {
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
}

