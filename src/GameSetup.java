import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSetup extends JPanel {
  private Deck deck;
  private JPanel chitCardPanel;
  public GameSetup(){
    setBackground(Color.BLACK);
    setBounds(30,30,550,650);
    setLayout(null);

    chitCardPanel = new JPanel();
    chitCardPanel.setBackground(Color.BLACK);
    chitCardPanel.setBounds(120, 100, 300, 400);
    add(chitCardPanel);

    chitCardSetup();
  }

  public void chitCardSetup() {
    deck = new Deck();
    deck.createFullDeck();
    this.chitCardPanel.setLayout(new GridLayout(4,4));
    for (int i = 0; i < deck.getChitCards().size(); i++) {
      JLabel chitCard = new JLabel(new ChitCard(new Bat(), 0).getBackSideCard());
      this.chitCardPanel.add(chitCard);
    }
    new ChitCard(new Bat(),1).getFrontSideCard();

  }
}
