package game;


import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private static Row generatedRow;
    private static Aplayer aplayer;
    private static Bplayer bplayer;


    /**
     * Generating the row of numbers and players.
     */
    private static void generate() {
        generatedRow = GenerateRow.generateNewRow(10000);
        aplayer = new Aplayer();
        bplayer = new Bplayer();
    }

    public static EndState simulate() {
        generate();

        playGame();

        return evaluateEndState();
    }

    private static void playGame() {
        try {
            if (new Random().nextBoolean()) {
                for (int i = 0; i < 500; i++) {
                    aplayer.getAplayerScore().add(aplayer.ALogic(generatedRow));
                    bplayer.getBplayerScore().add(bplayer.BLogic(generatedRow));
                }

            } else {
                for (int i = 0; i < 500; i++) {
                    bplayer.getBplayerScore().add(bplayer.BLogic(generatedRow));
                    aplayer.getAplayerScore().add(aplayer.ALogic(generatedRow));
                }
            }
        } catch (Exception e) {
            System.out.println(generatedRow.getRow().toString());
            e.printStackTrace();
        }
    }

    private static EndState evaluateEndState() {
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

    private static int accumulate(ArrayList<Integer> target) {
        return target.stream().mapToInt(Integer::intValue).sum();
    }
}
