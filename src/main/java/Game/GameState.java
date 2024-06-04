package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
  private ArrayList<String> players;

  public void setPlayers(ArrayList<String> players) {
    this.players = players;
  }
  public ArrayList<String> getPlayers() {
    return players;
  }
}
