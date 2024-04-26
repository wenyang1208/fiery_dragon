public class Bat implements Animal{
  private final static String name = "BAT";
  private final static int maxVal = 3;

  @Override
  public String getName() {
    return name;
  }

  public int getMaxVal(){
    return maxVal;
  }
}
