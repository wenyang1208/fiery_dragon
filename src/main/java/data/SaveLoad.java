package data;

import Game.Game;

import java.io.*;

public class SaveLoad implements Serializable{
    private Game game;

    public SaveLoad(Game game){
        this.game = game;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("game_save.dat")));

            DataStorage ds = new DataStorage();
            ds.playerList = game.getPlayers();
            ds.timeLimit = game.getTimeLimit();

            oos.writeObject(ds);
            oos.close();
        } catch (Exception e) {
            System.out.println("Save Exception!");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("game_save.dat")));

            DataStorage ds = (DataStorage) ois.readObject();
            game.setPlayers(ds.playerList);
            game.setTimeLimit(ds.timeLimit);

            // Refresh the game's components based on the loaded data
            game.updateGameFromLoadedState();

            ois.close();
        } catch (Exception e) {
            System.out.println("Load Exception!");
            e.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }
}

