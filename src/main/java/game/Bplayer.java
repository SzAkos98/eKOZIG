package game;

import java.util.ArrayList;

public class Bplayer extends Players {

    private ArrayList<Integer> BplayerScore = new ArrayList<>();

    public int BLogic ( Row row) {
        if (row.getRow().size() > 1) {
            int first, second, third;

            Row test = new Row(row.getRow());
            chooseNumber(test, Choice.FIRST);
            first = testGame(test);

            test = new Row(row.getRow());
            chooseNumber(test, Choice.LAST);
            second = testGame(test);



            if (first < second) {

                return chooseNumber(test, Choice.FIRST);
            } else {
                return chooseNumber(row, Choice.LAST);
            }
        } else {
            return chooseNumber(row, Choice.LAST);
        }
    }

    private int testGame (Row row) {
        return getNumber(row,Choice.FIRST) + getNumber(row, Choice.LAST);
        /*
        int firstF, firstL, second, third;
        Row test1 = new Row(row.getRow());
        Row test2 = new Row(row.getRow());
       firstF = chooseNumber(test1, Choice.FIRST);
       firstL = chooseNumber(test2, Choice.LAST);
       if (getNumber(test1, Choice.FIRST) + getNumber(test1, Choice.LAST) > getNumber(test2, Choice.FIRST) + getNumber(test2, Choice.LAST)) {

       }

        first = chooseNumber(test, Choice.BIGGER);
        if (getNumber(test, Choice.BIGGER) > first) {

        }


            first = getNumber(test, Choice.BIGGER);
            chooseNumber(test, Choice.LAST);
            second = getNumber(test, Choice.BIGGER);
            chooseNumber(test, Choice.BIGGER);
            third = getNumber(test, Choice.BIGGER);

        }*/

    }

    public ArrayList<Integer> getBplayerScore() {
        return BplayerScore;
    }
}
