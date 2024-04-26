import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame("Fiery Dragon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        MainMenuPanel mmp = new MainMenuPanel();
        window.add(mmp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
