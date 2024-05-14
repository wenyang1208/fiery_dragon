package Game;

import javax.swing.JFrame;

public class Application {
    public static final int frameWidth = 1050;
    public static final int frameHeight = 750;

    public static void main(String[] args) throws Exception{
        JFrame window = new JFrame("Fiery Dragon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(frameWidth,frameHeight);
        Home mmp = new Home(window);
        window.add(mmp);
//    window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
//    Home h =
//    new Home();
//    Game g = new Game();
    }

}


