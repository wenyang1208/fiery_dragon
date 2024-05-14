package Animal;

public class PirateDragon implements Animal {
  private final static String name = "pirate_dragon";
  private final static int maxVal = 2;

  @Override
  public String getName() {
    return name;
  }
  @Override
  public int getMaxVal(){
    return maxVal;
  }

}

