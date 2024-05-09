import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cave {
    ArrayList<CaveInfo> caveList = new ArrayList<>();

    public Cave(){
        caveList.add(new CaveInfo(1, 10, AnimalType.SPIDER));
        caveList.add(new CaveInfo(12, 1, AnimalType.BAT));
        caveList.add(new CaveInfo(23, 10, AnimalType.BABYDRAGON));
        caveList.add(new CaveInfo(12, 19, AnimalType.SALAMANDER));

    }
    public void draw(Graphics2D g2){
        for (CaveInfo cave : caveList) {
            //Note: X and Y are inverted somehow idk why
            int pixelY = cave.getX() * Board.SQUARE_SIZE;
            int pixelX = cave.getY() * Board.SQUARE_SIZE;

            ImageIcon imageIcon;
            switch (cave.getAnimalType()) {
                case BABYDRAGON:
                    imageIcon = new ImageIcon(getClass().getResource("/cave/baby_dragon_cave.png"));
                    break;
                case BAT:
                    imageIcon = new ImageIcon(getClass().getResource("/cave/bat_cave.png"));
                    break;
                case SALAMANDER:
                    imageIcon = new ImageIcon(getClass().getResource("/cave/salamander_cave.png"));
                    break;
                case SPIDER:
                    imageIcon = new ImageIcon(getClass().getResource("/cave/spider_cave.png"));
                    break;
                default:
                    continue;
            }
            g2.drawImage(imageIcon.getImage(), pixelX, pixelY, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
        }
    }

    private static class CaveInfo {
        private int x;
        private int y;
        private AnimalType animalType;

        public CaveInfo(int x, int y, AnimalType animalType) {
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
