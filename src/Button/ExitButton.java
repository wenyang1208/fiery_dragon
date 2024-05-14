package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The ExitButton class represents the component used to exit from the game
 * */
public class ExitButton extends JButton{
    public ExitButton(){
        setVisible(true);
        setBackground(new Color(255, 153, 0));
        setText("EXIT");
        setForeground(Color.WHITE);
    }

}

