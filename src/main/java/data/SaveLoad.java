package data;

import Game.Game;

import java.io.*;

/**
 * The SaveLoad class is responsible for saving and loading the state of the game.
 * It uses Java's serialization mechanism to write and read the game state to and from a file.
 * This class helps in persisting the game data between sessions.
 */
public class SaveLoad implements Serializable{
    private Game game;

    /**
     * Constructs a SaveLoad instance with the specified game.
     *
     * @param game the game instance whose state needs to be saved or loaded.
     */
    public SaveLoad(Game game){
        this.game = game;
    }

    /**
     * Saves the current state of the game to a binary file.
     * It serializes the game state into a DataStorage object and writes it to "game_save.dat".
     */
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

    /**
     * Loads the game state from a binary file.
     * It reads a DataStorage object from "game_save.dat" and restores the game state.
     */
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

