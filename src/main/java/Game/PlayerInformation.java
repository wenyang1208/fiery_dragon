package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GameBoardComponent.ChitCard;

/**
 * The PlayerInformation class represents the component used to
 * gather information of how many players are going to play the game,
 * along with the ages of each player.
 * */
public class PlayerInformation extends JDialog implements ActionListener {
    private JLabel l;
    private JCheckBox cb1,cb2,cb3,cb4;
    private JButton b;
    private JFrame frame;
    private TreeMap<String, Integer> playerInfo;
    private ArrayList<String> playerSequence;

    /**
     * Constructor used to prompt players to select the number of players playing the game.
     * */
    public PlayerInformation(JFrame frame){
        this.frame = frame;
        l = new JLabel("Choose 2 to 4 token animals you want to play with!");
        l.setBounds(50, 50, 300, 20);
        cb1 = new JCheckBox("Spider");
        cb1.setBounds(100, 100, 150, 20);
        cb2 = new JCheckBox("Bat");
        cb2.setBounds(100, 150, 150, 20);
        cb3 = new JCheckBox("Salamander");
        cb3.setBounds(100, 200, 150, 20);
        cb4 = new JCheckBox("Baby Dragon");
        cb4.setBounds(100, 250, 150, 20);
        b = new JButton("Start Game");
        b.setBounds(100, 300, 150, 30);
        b.addActionListener(this);
        add(l);
        add(cb1);
        add(cb2);
        add(cb3);
        add(cb4);
        add(b);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(frame);
        playerInfo = new TreeMap<>(); // Initialize the HashMap\
        playerSequence = new ArrayList<>();
    }

    /**
     * Gets the number of players selected to play along with the ages of each player.
     * There must be between 2 to 4 players to start the game.
     * Prompts players to select more players if  number of players selected is not within the valid range.
     * */
    public void actionPerformed(ActionEvent e) {
        int selectedCount = 0;
        if (cb1.isSelected()) {
            selectedCount++;
            int age = promptForAge("Spider");
            if (age == -1)
                return; // If user cancels the input, return without proceeding
            playerInfo.put("Spider", age);
        }
        if (cb2.isSelected()) {
            selectedCount++;
            int age = promptForAge("Bat");
            if (age == -1)
                return;
            playerInfo.put("Bat", age);
        }
        if (cb3.isSelected()) {
            selectedCount++;
            int age = promptForAge("Salamander");
            if (age == -1)
                return;
            playerInfo.put("Salamander", age);
        }
        if (cb4.isSelected()) {
            selectedCount++;
            int age = promptForAge("Baby Dragon");
            if (age == -1)
                return;
            playerInfo.put("BabyDragon",age);
        }

        // if the required 2 to 4 players are chosen in the game.
        if (selectedCount >= 2 && selectedCount <= 4) {
            // Find the animal with the smallest age
            String startingAnimal = Collections.min(playerInfo.entrySet(), Map.Entry.comparingByValue()).getKey();

            // Create the playerSequence list based on the clockwise direction
            ArrayList<String> clockwiseAnimals = new ArrayList<>(List.of("Spider", "Bat", "Salamander", "BabyDragon"));
            int startingIndex = clockwiseAnimals.indexOf(startingAnimal);
            ArrayList<String> orderedAnimals = new ArrayList<>();
            for (int i = startingIndex; i < startingIndex + 4; i++) {
                orderedAnimals.add(clockwiseAnimals.get(i % 4));
            }

            for(Entry<String, Integer> player: playerInfo.entrySet()){
                playerSequence.add(player.getKey());
            }

            Collections.sort(playerSequence, Comparator.comparingInt(orderedAnimals::indexOf));

            int time = promptForTime();

            setVisible(false);
            dispose();
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Game(frame,playerSequence, time));
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Please choose between 2 to 4 players.");
        }
    }

    /**
     * Prompts player for their preferred time limit for each round.
     * Time inputed must be valid to continue (between 1 to 60 minutes).
     * Prompts user for input again if previous input does not meet the valid age range
     * */
    private int promptForTime(){
        int timeSet;
        while (true){
            String input = JOptionPane.showInputDialog(this, "Please set your preferred time limit " +
                                                                                    "\nfor this round (1-60 minutes):");
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a time (minutes).");
                continue;
            }
            try {
                timeSet = Integer.parseInt(input);
                if (timeSet > 60) {
                    JOptionPane.showMessageDialog(this, "Time set is too long! Maximum is 60 minutes");
                    continue;
                }
                if (timeSet < 1) {
                    JOptionPane.showMessageDialog(this, "Time set is too short! Minimum is 1 minute");
                    continue;
                }
                break; // If input is valid, break the loop
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid time.");
            }
        }
        return timeSet * 60 * 1000; // convert from minutes to milliseconds
    }

    /**
     * Prompts player for their age.
     * Age inputed must be valid to continue (between 5 to 99).
     * Prompts user for input again if previous input does not meet the valid age range
     * */
    private int promptForAge(String player) {
        int age;
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Enter age for " + player + ":");
            if (input == null) return -1; // If user cancels the input, return -1
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an age.");
                continue;
            }
            try {
                age = Integer.parseInt(input);
                if (age < 5 || age > 99) {
                    JOptionPane.showMessageDialog(this, "Please enter an age between 5 and 99.");
                    continue;
                }
                break; // If input is valid, break the loop
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
            }
        }
        return age;
    }
}
