package game;


import java.util.ArrayList;
import java.util.Random;

/**
 * A program alap logikája a szabályok alapján.
 */
public class GameLogic {

    private static Row generatedRow;
    private static Aplayer aplayer;
    private static Bplayer bplayer;


    /**
     * Játékosok és a számsor létrehozása.
     */
    private static void generate() {
        generatedRow = GenerateRow.generateNewRow(10000);
        aplayer = new Aplayer();
        bplayer = new Bplayer();
    }

    /**
     * A játék szimulálását megkezdő függvény.
     * @return  a végeredmény statisztika
     */
    public static EndState simulate() {
        generate();

        playGame();

        return calculatingResults();
    }

    /**
     * Egy játékmenet leszimulálása véletlen kezdő játékossal.
     */
    private static void playGame() {
            if (new Random().nextBoolean()) {
                Bplayer.turn=1;
                for (int i = 0; i < 500; i++) {
                    aplayer.getAplayerScore().add(aplayer.ALogic(generatedRow));
                    bplayer.getBplayerScore().add(bplayer.BLogicSecond(generatedRow));
                }

            } else {
                Bplayer.turn=1;
                for (int i = 0; i < 500; i++) {
                    bplayer.getBplayerScore().add(bplayer.BLogicFirst(generatedRow));
                    aplayer.getAplayerScore().add(aplayer.ALogic(generatedRow));
                }
            }
    }

    /**
     * A végeredmény statisztika kiszámítása egy játékra nézve.
     * @return  a végeredmény típusa a B játékosra nézve
     */
    private static EndState calculatingResults() {
        int sumOfA = accumulate(aplayer.getAplayerScore());
        int sumOfB = accumulate(bplayer.getBplayerScore());
        if (sumOfA < sumOfB) {
            return EndState.WIN;
        } else if (sumOfA == sumOfB) {
            return EndState.TIE;
        } else {
            return EndState.LOSE;
        }
    }

    /**
     * Az összesített pontszám kiszámolása a játékos álltal kiválasztott számokból
     * @param target a játékos által kiválasztott számok készlete
     * @return a készlet összege
     */
    public static int accumulate(ArrayList<Integer> target) {
        return target.stream().mapToInt(Integer::intValue).sum();
    }
}
