package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * The GameRuleButton represents the button component used to display the rules of the game
 * */
public class GameRuleButton extends JButton implements ActionListener {

    /**
     * Constructs the GameRuleButton object, specifying its visibility,
     * foreground and background color, and text inside the button
     * Along with actions performed when button is clicked on.
     * */
    public GameRuleButton(){
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("GAME RULES");
        setForeground(Color.WHITE);
        addActionListener(this);
    }

    /**
     * Displays a pop-up window which displays the rules of the game.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        String gameRulesMessage = "1. Start by going clockwise. The youngest player begins.\n" +
                "2. Uncover a dragon card. If it matches the animal on your square, move your dragon based on the number of animals on card.\n" +
                "3. If the card does not match the animal on your square, your turn ends, and your dragon stays put.\n" +
                "4. Beware of dragon pirates! Move your dragon back the number of spaces shown on the card if you uncover it.\n" +
                "5. Beware of the new dragons! Move your dragon back until it reaches an empty cave (any player) and move into that cave.\n" +
                "6. Your dragon can't move if it's in its cave or if the square it would move to is already occupied by other player.\n" +
                "7. You must land on your cave with exact steps. If you pass it, your turn ends and your dragon stays put.\n" +
                "8. The game ends when a dragon reaches its cave after going around the volcano clockwise. He or she wins!\n";

        // Show the message dialog
        JOptionPane.showMessageDialog(null, gameRulesMessage, "Game Rules", JOptionPane.INFORMATION_MESSAGE);
    }
}

