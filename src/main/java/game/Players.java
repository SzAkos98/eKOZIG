package game;

import java.util.ArrayList;

public class Players {

    int getNumber(Row row, Choice choice) {
        return row.getRow().get(choice.getNumbert(row, choice));
    }

    int chooseNumber ( Row row, Choice choice) {
        return  row.getRow().remove(choice.getNumbert(row, choice));
    }

}
