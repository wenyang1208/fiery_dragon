public class PirateDragon implements Animal{
  private final static String name = "PIRATE_DRAGON";
  private final static int maxVal = 2;

  @Override
  public String getName() {
    return name;
  }

  public int getMaxVal(){
    return maxVal;
  }

}
