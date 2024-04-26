public class BabyDragon implements Animal{
  private final static String name = "BABY_DRAGON";
  private final static int maxVal = 3;

  @Override
  public String getName() {
    return name;
  }

  public int getMaxVal(){
    return maxVal;
  }

}
