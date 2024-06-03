package Action;

import Game.Game;
import Game.Home;
import GameBoardComponent.Token;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EndGameAction {
    private Game game;

    /**
     * Ends the game with a winner pop-up screen, indicating the winner of the game.
     * Gives the option to start a new game with the same player information as the previous round.
     * Returns to Home page if option was not selected.
     * */
    public void finish(JFrame frame, Token currentPlayer, ArrayList<String> players){
        String result = game.processTokenAnimalName(currentPlayer.getAnimal().getName());
        String winningMessage = "Congratulations! The winner is " + result + "!\n Do you want to start a new game?";
        int choice = JOptionPane.showConfirmDialog(null, winningMessage,
                "Question", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            System.out.println(players);
            ArrayList<String> clockwiseAnimals = new ArrayList<>(
                    List.of("Spider", "Bat", "Salamander", "BabyDragon"));
            int startingIndex = clockwiseAnimals.indexOf(result.replaceAll("\\s",""));
            ArrayList<String> orderedAnimals = new ArrayList<>();
            for (int i = startingIndex; i < startingIndex + 4; i++) {
                orderedAnimals.add(clockwiseAnimals.get(i % 4));
            }
            Collections.sort(players, Comparator.comparingInt(orderedAnimals::indexOf));
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Game(frame,players));
            frame.revalidate();
            frame.repaint();
        }else{
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Home(frame));
            frame.revalidate();
            frame.repaint();
        }
    }
}
