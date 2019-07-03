package game;

import java.util.ArrayList;

/**
 * Az A játékos osztály és annak logikája.
 */
public class Aplayer extends Players {
    private ArrayList<Integer> AplayerScore = new ArrayList<>();

    /**
     * Az A játékos logikája miszerint a legnagyobb elemet veszi el a sor végéről vagy elejéről.
     * @param row a sor amelyből a játékos választhat
     * @return  a kiválasztott elem
     */
    public int ALogic (Row row) {
        return chooseNumber(row, Choice.BIGGER);
    }

    /**
     * Az A játékos álltal kiválasztott elemek listáját meghatározó függvény.
     * @return az A játékos álltal kiválasztott elemek listája
     */
    public ArrayList<Integer> getAplayerScore() {
        return AplayerScore;
    }
}
