package data;

import Controller.ChitCardController;
import Controller.VolcanoCardController;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    ArrayList<String> playerList;
    int timeLimit;
    VolcanoCardController volcanoController;
}

