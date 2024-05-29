package Animal;

public class NewDragonCard implements Animal{
  private final static String name = "new_dragon_card";
  private final static int maxVal = 1;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getMaxVal() {
    return maxVal;
  }
}
