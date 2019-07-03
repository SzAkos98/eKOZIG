package game;

/**
 * A játékosok típusát deffiniáló ősosztály.
 */
public class Players {

    int getNumber(Row row, Choice choice) {
        return row.getRow().get(choice.getNumbert(row, choice));
    }

    int chooseNumber ( Row row, Choice choice) {
        return  row.getRow().remove(choice.getNumbert(row, choice));
    }

}
