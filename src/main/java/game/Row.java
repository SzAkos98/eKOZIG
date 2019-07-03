package game;

import java.util.ArrayList;

/**
 * A számsoroazt típusának deklarálása.
 */
public class Row {
    private ArrayList<Integer> row;

    public ArrayList<Integer> getRow() {
        return row;
    }

    public void setRow(ArrayList<Integer> row) {
        this.row = row;
    }

    Row() {
        this.row = new ArrayList<>();
    }

    public Row(ArrayList<Integer> row) {
        this.row = new ArrayList<>(row);
    }
}
