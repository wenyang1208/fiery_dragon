import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VolcanoCard {
    ArrayList<VolcanoCardInfo> volcanoCardList = new ArrayList<>();

    public VolcanoCard() {
        volcanoCardList.add(new VolcanoCardInfo(1, 12, 3, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(2, 10, 3, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(3, 8, 4, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(4, 6, 5, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(5, 4, 6, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(6, 3, 8, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(7, 3, 10, AnimalType.BAT));
        volcanoCardList.add(new VolcanoCardInfo(8, 3, 12, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(9, 4, 14, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(10, 6, 15, AnimalType.BAT));
        volcanoCardList.add(new VolcanoCardInfo(11, 8, 16, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(12, 10, 17, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(13, 12, 17, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(14, 14, 17, AnimalType.BAT));
        volcanoCardList.add(new VolcanoCardInfo(15, 16, 16, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(16, 18, 15, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(17, 20, 14, AnimalType.BAT));
        volcanoCardList.add(new VolcanoCardInfo(18, 21, 12, AnimalType.SPIDER));
        volcanoCardList.add(new VolcanoCardInfo(19, 21, 10, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(20, 21, 8, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(21, 20, 6, AnimalType.BAT));
        volcanoCardList.add(new VolcanoCardInfo(22, 18, 5, AnimalType.BABYDRAGON));
        volcanoCardList.add(new VolcanoCardInfo(23, 16, 4, AnimalType.SALAMANDER));
        volcanoCardList.add(new VolcanoCardInfo(24, 14, 3, AnimalType.BAT));
    }

    public void draw(Graphics2D g2) {
        for (VolcanoCardInfo volcanoCard : volcanoCardList) {
            //Note: X and Y are inverted somehow idk why
            int pixelY = volcanoCard.getX() * Board.SQUARE_SIZE;
            int pixelX = volcanoCard.getY() * Board.SQUARE_SIZE;

            ImageIcon imageIcon;
            switch (volcanoCard.getAnimalType()) {
                case BABYDRAGON:
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/BabyDragon_1.png"));
                    break;
                case BAT:
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Bat_1.png"));
                    break;
                case SALAMANDER:
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Salamander_1.png"));
                    break;
                case SPIDER:
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Spider_1.png"));
                    break;
                default:
                    continue;
            }
            g2.drawImage(imageIcon.getImage(), pixelX, pixelY, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
        }
    }


    private static class VolcanoCardInfo {
        private int numOfVolcanoCard;
        private int x;
        private int y;
        private AnimalType animalType;

        public VolcanoCardInfo(int numOfVolcanoCard, int x, int y, AnimalType animalType) {
            this.numOfVolcanoCard = numOfVolcanoCard;
            this.x = x;
            this.y = y;
            this.animalType = animalType;
        }

        public AnimalType getAnimalType() {
            return animalType;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
