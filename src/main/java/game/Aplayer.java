package game;

import java.util.ArrayList;

public class Aplayer extends Players {
    private ArrayList<Integer> AplayerScore = new ArrayList<>();


    public int ALogic (Row row) {
        return chooseNumber(row, Choice.BIGGER);
    }

    public ArrayList<Integer> getAplayerScore() {
        return AplayerScore;
    }
}
