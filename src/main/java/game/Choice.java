package game;

/**
 * A lehetséges választások típusának és eredményének deffiniálása.
 */
public enum Choice {
    FIRST,
    LAST,
    BIGGER,
    LESS;

    public int getNumbert ( Row row, Choice choice) {
        if ( choice == FIRST ){
            return 0;
        } else if ( choice == LAST ) {
            return row.getRow().size() -1;
        } else if ( choice == BIGGER)  {
            return (row.getRow().get(0) > row.getRow().get(row.getRow().size() - 1)) ? 0 : row.getRow().size() -1;
        } else {
            return (row.getRow().get(0) < row.getRow().get(row.getRow().size() - 1)) ? 0 : row.getRow().size() -1;
        }
    }
}
