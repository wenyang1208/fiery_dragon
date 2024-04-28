import javax.swing.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragonCard {
    ArrayList<DragonCardInfo> dragonCardList = new ArrayList<>();
    public DragonCard() {
        List<List<Integer>> pairList = new ArrayList<>();

        for (int i = 9; i < 16; i += 2){
            for (int j = 7; j < 14; j += 2){
                pairList.add(List.of(i, j));
            }
        }

        randomisedPosOfDC(pairList);


        dragonCardList.add(new DragonCardInfo(1, pairList.get(0).get(0), pairList.get(0).get(1), AnimalType.PIRATE, 1));
        dragonCardList.add(new DragonCardInfo(2, pairList.get(1).get(0), pairList.get(1).get(1), AnimalType.PIRATE, 1));
        dragonCardList.add(new DragonCardInfo(3, pairList.get(2).get(0), pairList.get(2).get(1), AnimalType.PIRATE, 2));
        dragonCardList.add(new DragonCardInfo(4, pairList.get(3).get(0), pairList.get(3).get(1), AnimalType.PIRATE, 2));
        dragonCardList.add(new DragonCardInfo(5, pairList.get(4).get(0), pairList.get(4).get(1), AnimalType.BABYDRAGON, 1));
        dragonCardList.add(new DragonCardInfo(6, pairList.get(5).get(0), pairList.get(5).get(1), AnimalType.BABYDRAGON, 2));
        dragonCardList.add(new DragonCardInfo(7, pairList.get(6).get(0), pairList.get(6).get(1), AnimalType.BABYDRAGON, 3));
        dragonCardList.add(new DragonCardInfo(8, pairList.get(7).get(0), pairList.get(7).get(1), AnimalType.BAT, 1));
        dragonCardList.add(new DragonCardInfo(9, pairList.get(8).get(0), pairList.get(8).get(1), AnimalType.BAT, 2));
        dragonCardList.add(new DragonCardInfo(10, pairList.get(9).get(0), pairList.get(9).get(1), AnimalType.BAT, 3));
        dragonCardList.add(new DragonCardInfo(11, pairList.get(10).get(0), pairList.get(10).get(1), AnimalType.SALAMANDER, 1));
        dragonCardList.add(new DragonCardInfo(12, pairList.get(11).get(0), pairList.get(11).get(1), AnimalType.SALAMANDER, 2));
        dragonCardList.add(new DragonCardInfo(13, pairList.get(12).get(0), pairList.get(12).get(1), AnimalType.SALAMANDER, 3));
        dragonCardList.add(new DragonCardInfo(14, pairList.get(13).get(0), pairList.get(13).get(1), AnimalType.SPIDER, 1));
        dragonCardList.add(new DragonCardInfo(15, pairList.get(14).get(0), pairList.get(14).get(1), AnimalType.SPIDER, 2));
        dragonCardList.add(new DragonCardInfo(16, pairList.get(15).get(0), pairList.get(15).get(1), AnimalType.SPIDER, 3));

    }

    public List<List<Integer>> randomisedPosOfDC(List<List<Integer>> pairList){
        Collections.shuffle(pairList);
        return pairList;
    }

    public void stepsToMove(){

    }

    public void draw(Graphics2D g2){
        for (DragonCardInfo dragonCard: dragonCardList){
            int pixelY = dragonCard.getX() * Board.SQUARE_SIZE;
            int pixelX = dragonCard.getY() * Board.SQUARE_SIZE;

            ImageIcon imageIcon = null;

            if (dragonCard.flipped){
                imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Back.png"));
            }
            else {
                if (dragonCard.getAnimalType() == AnimalType.PIRATE && dragonCard.getNumOfSteps() == 1) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/PirateDragon_1.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.PIRATE && dragonCard.getNumOfSteps() == 2) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/PirateDragon_2.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BABYDRAGON && dragonCard.getNumOfSteps() == 1) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/BabyDragon_1.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BABYDRAGON && dragonCard.getNumOfSteps() == 2) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/BabyDragon_2.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BABYDRAGON && dragonCard.getNumOfSteps() == 3) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/BabyDragon_3.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BAT && dragonCard.getNumOfSteps() == 1) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Bat_1.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BAT && dragonCard.getNumOfSteps() == 2) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Bat_2.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.BAT && dragonCard.getNumOfSteps() == 3) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Bat_3.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SALAMANDER && dragonCard.getNumOfSteps() == 1) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Salamander_1.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SALAMANDER && dragonCard.getNumOfSteps() == 2) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Salamander_2.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SALAMANDER && dragonCard.getNumOfSteps() == 3) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Salamander_3.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SPIDER && dragonCard.getNumOfSteps() == 1) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Spider_1.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SPIDER && dragonCard.getNumOfSteps() == 2) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Spider_2.png"));
                } else if (dragonCard.getAnimalType() == AnimalType.SPIDER && dragonCard.getNumOfSteps() == 3) {
                    imageIcon = new ImageIcon(getClass().getResource("/dragoncard/Spider_3.png"));
                }
            }

            g2.drawImage(imageIcon.getImage(), pixelX, pixelY, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
        }
    }


    public static class DragonCardInfo {
        private int DragonCardNum;
        private int x;
        private int y;
        private AnimalType animalType;
        private int numOfSteps;
        boolean flipped = true;

        public DragonCardInfo(int numOfVolcanoCard, int x, int y, AnimalType animalType, int numOfSteps) {
            this.DragonCardNum = numOfVolcanoCard;
            this.x = x;
            this.y = y;
            this.animalType = animalType;
            this.numOfSteps = numOfSteps;
        }

        public int getDragonCardNum() {
            return DragonCardNum;
        }

        public int getNumOfSteps() {
            return numOfSteps;
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

        public void flip() {
            flipped = !flipped;
        }
    }
}
