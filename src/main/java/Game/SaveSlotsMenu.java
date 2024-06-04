package Game;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class SaveSlotsMenu extends JFrame {
  public SaveSlotsMenu(Game game) {
    setTitle("Save");
    setSize(300, 400);  // Set appropriate size for the window
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame on the screen

    // Set layout to GridLayout with 0 rows (will expand) and 1 column
    setLayout(new GridLayout(0, 1));

    // Create and add GameSlot buttons
    for (int i = 1; i <= 3; i++) {
      GameSlot slot = new GameSlot(i, game);
      add(slot);
    }
  }
}
