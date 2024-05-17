package Button;

import java.awt.Color;
import javax.swing.JButton;

/**
 * The ExitButton class represents the component used to exit from the game
 * */
public class ExitButton extends JButton{

    /**
     * Constructs the ExitButton object, specifying its visibility,
     * foreground and background color, and text inside the button
     * */
    public ExitButton(){
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("EXIT");
        setForeground(Color.WHITE);
    }

}

