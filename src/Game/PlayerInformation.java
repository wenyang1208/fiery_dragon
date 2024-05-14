package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GameBoardComponent.ChitCard;

public class PlayerInformation extends JDialog implements ActionListener {
    private JLabel l;
    private JCheckBox cb1,cb2,cb3,cb4;
    private JButton b;
    private JFrame frame;
    private TreeMap< Integer,String> playerInfo;
    private ArrayList<String> playerSequence;
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
    public void actionPerformed(ActionEvent e) {
        int selectedCount = 0;
        if (cb1.isSelected()) {
            selectedCount++;
            int age = promptForAge("Spider");
            if (age == -1)
                return; // If user cancels the input, return without proceeding
            playerInfo.put(age,"Spider");
        }
        if (cb2.isSelected()) {
            selectedCount++;
            int age = promptForAge("Bat");
            if (age == -1)
                return;
            playerInfo.put(age,"Bat");
        }
        if (cb3.isSelected()) {
            selectedCount++;
            int age = promptForAge("Salamander");
            if (age == -1)
                return;
            playerInfo.put(age,"Salamander");
        }
        if (cb4.isSelected()) {
            selectedCount++;
            int age = promptForAge("Baby Dragon");
            if (age == -1)
                return;
            playerInfo.put(age,"BabyDragon");
        }

        if (selectedCount >= 2 && selectedCount <= 4) {
            for(Entry<Integer, String> entry : playerInfo.entrySet()){
                playerSequence.add(entry.getValue());
            }
            setVisible(false);
            dispose();
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Game(frame,playerSequence));
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Please choose between 2 to 4 players.");
        }
    }
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
