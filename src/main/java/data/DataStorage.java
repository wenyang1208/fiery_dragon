package data;

import Controller.ChitCardController;
import Controller.VolcanoCardController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The DataStorage class is a container for storing the game state.
 * It is used to save and load game data in a serialized form.
 * This class holds the necessary data required to restore the game state.
 */
public class DataStorage implements Serializable {
    ArrayList<String> playerList;
    int timeLimit;
    VolcanoCardController volcanoController;
}

