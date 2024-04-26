public class Spider implements Animal{
  private final static String name = "SPIDER";
  private final static int maxVal = 3;

  @Override
  public String getName() {
    return name;
  }

  public int getMaxVal(){
    return maxVal;
  }
}
