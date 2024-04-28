import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolcanoCard {
    ArrayList<Object> volcanoCardList = new ArrayList<>();
    public VolcanoCard(){
        // index 1 = numOfVolcanoCard, index 2 = x, index 3 = y, index 4 = animalType
        volcanoCardList.add(new ArrayList<>(List.of(1, 12, 3, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(2, 10, 3, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(3, 8, 4, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(4, 6, 5, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(5, 4, 6, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(6, 3, 8, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(7, 3, 10, AnimalType.BAT)));
        volcanoCardList.add(new ArrayList<>(List.of(8, 3, 12, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(9, 4, 14, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(10, 6, 15, AnimalType.BAT)));
        volcanoCardList.add(new ArrayList<>(List.of(11, 8, 16, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(12, 10, 17, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(13, 12, 17, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(14, 14, 17, AnimalType.BAT)));
        volcanoCardList.add(new ArrayList<>(List.of(15, 16, 16, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(16, 18, 15, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(17, 20, 14, AnimalType.BAT)));
        volcanoCardList.add(new ArrayList<>(List.of(18, 21, 12, AnimalType.SPIDER)));
        volcanoCardList.add(new ArrayList<>(List.of(19, 21, 10, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(20, 21, 8, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(21, 20, 6, AnimalType.BAT)));
        volcanoCardList.add(new ArrayList<>(List.of(22, 18, 5, AnimalType.BABYDRAGON)));
        volcanoCardList.add(new ArrayList<>(List.of(23, 16, 4, AnimalType.SALAMANDER)));
        volcanoCardList.add(new ArrayList<>(List.of(24, 14, 3, AnimalType.BAT)));


    }
    public void draw(Graphics2D g2){

    }
}
