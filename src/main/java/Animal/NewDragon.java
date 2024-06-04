package Animal;

public class NewDragon implements Animal{
  private final static String name = "new_dragon";
  private final static int maxVal = 1;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getMaxVal() {
    return maxVal;
  }

  @Override
  public boolean isSpeical() {
    return true;
  }
}
