package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * A számsorozat generálása. 1000 darab elemből 1 és 9999 közötti értékekkel véletrenszerűen.
 */
public class GenerateRow {

    static Row generateNewRow ( int limit) {
        Row row = new Row();
        row.setRow(new ArrayList<>());

        Random random = new Random();
        int number;

        for (int i = 0; i < 1000; i++){
            number = random.nextInt(limit - 2) + 1;
            row.getRow().add(number);
        }
        return row;
    }
}
