import java.awt.Image;
import javax.swing.ImageIcon;

public class ChitCard {

  private Animal animalFactory;
  private int value;

  public ChitCard(Animal animalFactory, int value){
    setAnimal(animalFactory);
    setValue(value);
  }

  public Animal getAnimal(){
    return animalFactory;
  }

  public int getValue(){
    return value;
  }

  public void setAnimal(Animal animalFactory){
    this.animalFactory = animalFactory;
  }

  public void setValue(int value){
    this.value = value;
  }

  public ImageIcon getBackSideCard(){
    Image backSideChitCardImage = new ImageIcon(getClass().getResource("./ChitCard/Back.png")).getImage();
    Image resizedImage = backSideChitCardImage.getScaledInstance(75,75, java.awt.Image.SCALE_SMOOTH);
    ImageIcon backSideChitCardImageIcon = new ImageIcon(resizedImage);
    return backSideChitCardImageIcon;
  }

  public void getFrontSideCard(){
    System.out.println(this.getAnimal().getName());
//    Image backSideChitCardImage = new ImageIcon(getClass().getResource("./ChitCard/Back.png")).getImage();
//    Image resizedImage = backSideChitCardImage.getScaledInstance(75,75, java.awt.Image.SCALE_SMOOTH);
//    ImageIcon frontSideChitCardImageIcon = new ImageIcon(resizedImage);
//    return frontSideChitCardImageIcon;
  }

}
