package game;



import java.util.ArrayList;

/**
 * A B játékos osztály. Megvalósítja a logikát amely az A legyőzéséhez szükséges.
 */
public class Bplayer extends Players {

    private ArrayList<Integer> BplayerScore = new ArrayList<>();
    public static  int turn;
    private static Choice pick;

    /**
     * A B játékos lépésének kiszámítása amennyiben ő kezdett.
     *
     * A logika alapja, hogy figyelembe vesszük a szélső 4-4 elemet és kiválasztjuk a legmegfelelőbb utat.
     * Figyelembe véve azt, hogy az A játékos mindig a legnagyobbat veszi el a sorból. Ez alapján a lehető
     * legjobb lépés kalkulálása és a lehető legkisebb értékkel rendelkező az A játékos számára a legnagyobb
     * szám meghagyása. Figyelembe véve a számunkra következő és az yzután az ellenfél számára következő elemet.
     *
     * @param row az alap készlet/a számsor melyből a játékos kiválaszt egyet.
     * @return a játékos álltal kiválasztott elem
     */
    public int BLogicFirst ( Row row) {
        if (row.getRow().size() > 3) {
                int first, second, first1,first2, second1, second2;
                int firstbig, secondbig,firstbig1,firstbig2,secondbig1,secondbig2;

                Row test1 = new Row(row.getRow());
                first = getNumber(test1, Choice.FIRST);
                chooseNumber(test1, Choice.FIRST);
                firstbig = getNumber(test1, Choice.BIGGER);
                chooseNumber(test1, Choice.BIGGER);

                Row test2 = new Row(row.getRow());
                second = getNumber(test2, Choice.LAST);
                chooseNumber(test2, Choice.LAST);
                secondbig = getNumber(test2, Choice.BIGGER);
                chooseNumber(test2, Choice.BIGGER);


                Row test1b = new Row(test1.getRow());
                first1 = getNumber(test1, Choice.FIRST);
                chooseNumber(test1b, Choice.FIRST);
                firstbig1 = getNumber(test1b, Choice.BIGGER);

                test1b = new Row(test1.getRow());
                first2 = getNumber(test1, Choice.LAST);
                chooseNumber(test1b, Choice.LAST);
                firstbig2 = getNumber(test1b, Choice.BIGGER);

                Row test2b = new Row(test2.getRow());
                second1 = getNumber(test2, Choice.FIRST);
                chooseNumber(test2b, Choice.FIRST);
                secondbig1 = getNumber(test2b, Choice.BIGGER);

                test2b = new Row(test2.getRow());
                second2 = getNumber(test2, Choice.LAST);
                chooseNumber(test2b, Choice.LAST);
                secondbig2 = getNumber(test2b, Choice.BIGGER);

            if (turn % 2 == 1) {
                if (firstbig + firstbig1 - first - first1 <= secondbig + secondbig1 - second - second1 &&
                        firstbig + firstbig1 - first - first1 <= firstbig + firstbig2 - first - first2 &&
                        firstbig + firstbig1 - first - first1 <= secondbig + secondbig2 - second - second2) {
                    turn++;
                    pick = Choice.FIRST;
                    return chooseNumber(row, Choice.FIRST);
                } else if (firstbig + firstbig2 - first - first2 <= secondbig + secondbig1 - second - second1 &&
                        firstbig + firstbig2 - first - first2 <= firstbig + firstbig1 - first - first1 &&
                        firstbig + firstbig2 - first - first2 <= secondbig + secondbig2 - second - second2) {
                    turn++;
                    pick = Choice.LAST;
                    return chooseNumber(row, Choice.FIRST);
                } else if (secondbig + secondbig1 - second - second1 <= firstbig + firstbig1 - first - first1 &&
                        secondbig + secondbig1 - second - second1 <= firstbig + firstbig2 - first - first2 &&
                        secondbig + secondbig1 - second - second1 <= secondbig + secondbig2 - second - second2) {
                    turn++;
                    pick = Choice.FIRST;
                    return chooseNumber(row, Choice.LAST);
                } else {
                    pick = Choice.LAST;
                    turn++;
                    return chooseNumber(row, Choice.LAST);
                }
            } else {
                int a,b;
                Row test = new Row(row.getRow());
                boolean firsta = getNumber(test,Choice.FIRST) == getNumber(test, pick);
                chooseNumber(test, Choice.FIRST);
                a = getNumber(test,Choice.FIRST) + getNumber(test, Choice.LAST);

                test = new Row(row.getRow());
                boolean seconda = getNumber(test,Choice.LAST) == getNumber(test, pick);
                chooseNumber(test,Choice.LAST);
                b = getNumber(test, Choice.FIRST) + getNumber(test, Choice.LAST);

                if (firsta && !seconda){
                    turn++;
                    return chooseNumber(row, Choice.FIRST);
                } else {
                    turn++;
                    return chooseNumber(row, Choice.LAST);
                }
            }
        } else if (row.getRow().size() < 4 && row.getRow().size() > 1 ) {
            int a,b;
            Row test = new Row(row.getRow());
            chooseNumber(test, Choice.FIRST);
            a = getNumber(test,Choice.FIRST) + getNumber(test, Choice.LAST);

            test = new Row(row.getRow());
            chooseNumber(test,Choice.LAST);
            b = getNumber(test, Choice.FIRST) + getNumber(test, Choice.LAST);

            if ( a < b ){
                turn = 1;
                return chooseNumber(row, Choice.FIRST);
            } else {
                turn = 1;
                return chooseNumber(row, Choice.LAST);
            }

        }else {
            turn = 1;
                return chooseNumber(row, Choice.LAST);
            }
    }


    /**
     * Hasonlóan az elsőhöz az alap logika ugyan az, csak az A player nem 0 pontról indul mint a B
     * így a különbség figyelembe vételével és törekedve annak lehető legkisebbre való lecsökkentésével
     * választunk elemet B-nek.
     */
    private static int remaining;
    public int BLogicSecond ( Row row) {
        Aplayer aplayer = new Aplayer();

        if (row.getRow().size() >998) {
            int firstNumberOfA = aplayer.getAplayerScore().stream().mapToInt(Integer::intValue).sum();
            int first, second, first1,first2, second1, second2;
            int firstbig, secondbig,firstbig1,firstbig2,secondbig1,secondbig2;

            Row test1 = new Row(row.getRow());
            first = getNumber(test1, Choice.FIRST);
            chooseNumber(test1, Choice.FIRST);
            firstbig = getNumber(test1, Choice.BIGGER);
            chooseNumber(test1, Choice.BIGGER);

            Row test2 = new Row(row.getRow());
            second = getNumber(test2, Choice.LAST);
            chooseNumber(test2, Choice.LAST);
            secondbig = getNumber(test2, Choice.BIGGER);
            chooseNumber(test2, Choice.BIGGER);


            Row test1b = new Row(test1.getRow());
            first1 = getNumber(test1, Choice.FIRST);
            chooseNumber(test1b, Choice.FIRST);
            firstbig1 = getNumber(test1b, Choice.BIGGER);

            test1b = new Row(test1.getRow());
            first2 = getNumber(test1, Choice.LAST);
            chooseNumber(test1b, Choice.LAST);
            firstbig2 = getNumber(test1b, Choice.BIGGER);

            Row test2b = new Row(test2.getRow());
            second1 = getNumber(test2, Choice.FIRST);
            chooseNumber(test2b, Choice.FIRST);
            secondbig1 = getNumber(test2b, Choice.BIGGER);

            test2b = new Row(test2.getRow());
            second2 = getNumber(test2, Choice.LAST);
            chooseNumber(test2b, Choice.LAST);
            secondbig2 = getNumber(test2b, Choice.BIGGER);

                if (firstbig + firstbig1 - first - first1 + firstNumberOfA  <= secondbig + secondbig1 - second - second1 + firstNumberOfA &&
                        firstbig + firstbig1 - first - first1 + firstNumberOfA <= firstbig + firstbig2 - first - first2 + firstNumberOfA &&
                        firstbig + firstbig1 - first - first1 + firstNumberOfA <= secondbig + secondbig2 - second - second2 + firstNumberOfA) {
                    turn++;
                    pick = Choice.FIRST;
                    remaining = firstNumberOfA -first+firstbig;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.FIRST);
                } else if (firstbig + firstbig2 - first - first2 + firstNumberOfA <= secondbig + secondbig1 - second - second1 + firstNumberOfA &&
                        firstbig + firstbig2 - first - first2 + firstNumberOfA <= firstbig + firstbig1 - first - first1 + firstNumberOfA &&
                        firstbig + firstbig2 - first - first2 + firstNumberOfA <= secondbig + secondbig2 - second - second2 + firstNumberOfA) {
                    turn++;
                    pick = Choice.LAST;
                    remaining = firstNumberOfA - first+firstbig;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.FIRST);
                } else if (secondbig + secondbig1 - second - second1 + firstNumberOfA <= firstbig + firstbig1 - first - first1 + firstNumberOfA &&
                        secondbig + secondbig1 - second - second1 + firstNumberOfA <= firstbig + firstbig2 - first - first2 + firstNumberOfA &&
                        secondbig + secondbig1 - second - second1 + firstNumberOfA <= secondbig + secondbig2 - second - second2 + firstNumberOfA) {
                    turn++;
                    pick = Choice.FIRST;
                    remaining = firstNumberOfA - second+secondbig;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.LAST);
                } else {
                    pick = Choice.LAST;
                    turn++;
                    remaining = firstNumberOfA -second+secondbig;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.LAST);
                }

        } else if (remaining >0 && row.getRow().size() >3) {
            int first, second, first1,first2, second1, second2;
            int firstbig, secondbig,firstbig1,firstbig2,secondbig1,secondbig2;

            Row test1 = new Row(row.getRow());
            first = getNumber(test1, Choice.FIRST);
            chooseNumber(test1, Choice.FIRST);
            firstbig = getNumber(test1, Choice.BIGGER);
            chooseNumber(test1, Choice.BIGGER);

            Row test2 = new Row(row.getRow());
            second = getNumber(test2, Choice.LAST);
            chooseNumber(test2, Choice.LAST);
            secondbig = getNumber(test2, Choice.BIGGER);
            chooseNumber(test2, Choice.BIGGER);


            Row test1b = new Row(test1.getRow());
            first1 = getNumber(test1, Choice.FIRST);
            chooseNumber(test1b, Choice.FIRST);
            firstbig1 = getNumber(test1b, Choice.BIGGER);

            test1b = new Row(test1.getRow());
            first2 = getNumber(test1, Choice.LAST);
            chooseNumber(test1b, Choice.LAST);
            firstbig2 = getNumber(test1b, Choice.BIGGER);

            Row test2b = new Row(test2.getRow());
            second1 = getNumber(test2, Choice.FIRST);
            chooseNumber(test2b, Choice.FIRST);
            secondbig1 = getNumber(test2b, Choice.BIGGER);

            test2b = new Row(test2.getRow());
            second2 = getNumber(test2, Choice.LAST);
            chooseNumber(test2b, Choice.LAST);
            secondbig2 = getNumber(test2b, Choice.BIGGER);

            if (turn % 2 == 1) {
            if (firstbig + firstbig1 - first - first1 + remaining  <= secondbig + secondbig1 - second - second1 + remaining &&
                    firstbig + firstbig1 - first - first1 + remaining <= firstbig + firstbig2 - first - first2 + remaining &&
                    firstbig + firstbig1 - first - first1 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                turn++;
                pick = Choice.FIRST;
                remaining = remaining -first+firstbig;
                if (remaining<=0)
                    turn=1;
                return chooseNumber(row, Choice.FIRST);
            } else if (firstbig + firstbig2 - first - first2 + remaining <= secondbig + secondbig1 - second - second1 + remaining &&
                    firstbig + firstbig2 - first - first2 + remaining <= firstbig + firstbig1 - first - first1 + remaining &&
                    firstbig + firstbig2 - first - first2 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                turn++;
                pick = Choice.LAST;
                remaining = remaining - first+firstbig;
                if (remaining<=0)
                    turn=1;
                return chooseNumber(row, Choice.FIRST);
            } else if (secondbig + secondbig1 - second - second1 + remaining <= firstbig + firstbig1 - first - first1 + remaining &&
                    secondbig + secondbig1 - second - second1 + remaining <= firstbig + firstbig2 - first - first2 + remaining &&
                    secondbig + secondbig1 - second - second1 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                turn++;
                pick = Choice.FIRST;
                remaining = remaining - second+secondbig;
                if (remaining<=0)
                    turn=1;
                return chooseNumber(row, Choice.LAST);
            } else {
                pick = Choice.LAST;
                turn++;
                remaining = remaining -second+secondbig;
                if (remaining<=0)
                    turn=1;
                return chooseNumber(row, Choice.LAST);
            }
            } else {
                int a,b;
                Row test = new Row(row.getRow());
                boolean firsta = getNumber(test,Choice.FIRST) == getNumber(test, pick);
                chooseNumber(test, Choice.FIRST);
                a = getNumber(test,Choice.BIGGER);

                test = new Row(row.getRow());
                boolean seconda = getNumber(test,Choice.LAST) == getNumber(test, pick);
                chooseNumber(test,Choice.LAST);
                b = getNumber(test, Choice.BIGGER);

                if (firsta && !seconda){
                    turn++;
                    remaining = remaining - getNumber(row, Choice.FIRST) +a;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.FIRST);
                } else {
                    turn++;
                    remaining = remaining - getNumber(row, Choice.LAST)+b;
                    if (remaining<=0)
                        turn=1;
                    return chooseNumber(row, Choice.LAST);
                }
            }

        } else  if (row.getRow().size() > 3 && remaining <= 0 && row.getRow().size() < 998) {
            int first, second, first1,first2, second1, second2;
            int firstbig, secondbig,firstbig1,firstbig2,secondbig1,secondbig2;

            Row test1 = new Row(row.getRow());
            first = getNumber(test1, Choice.FIRST);
            chooseNumber(test1, Choice.FIRST);
            firstbig = getNumber(test1, Choice.BIGGER);
            chooseNumber(test1, Choice.BIGGER);

            Row test2 = new Row(row.getRow());
            second = getNumber(test2, Choice.LAST);
            chooseNumber(test2, Choice.LAST);
            secondbig = getNumber(test2, Choice.BIGGER);
            chooseNumber(test2, Choice.BIGGER);


            Row test1b = new Row(test1.getRow());
            first1 = getNumber(test1, Choice.FIRST);
            chooseNumber(test1b, Choice.FIRST);
            firstbig1 = getNumber(test1b, Choice.BIGGER);

            test1b = new Row(test1.getRow());
            first2 = getNumber(test1, Choice.LAST);
            chooseNumber(test1b, Choice.LAST);
            firstbig2 = getNumber(test1b, Choice.BIGGER);

            Row test2b = new Row(test2.getRow());
            second1 = getNumber(test2, Choice.FIRST);
            chooseNumber(test2b, Choice.FIRST);
            secondbig1 = getNumber(test2b, Choice.BIGGER);

            test2b = new Row(test2.getRow());
            second2 = getNumber(test2, Choice.LAST);
            chooseNumber(test2b, Choice.LAST);
            secondbig2 = getNumber(test2b, Choice.BIGGER);


            if (turn % 2 == 1) {
                if (firstbig + firstbig1 - first - first1 + remaining  <= secondbig + secondbig1 - second - second1 + remaining &&
                        firstbig + firstbig1 - first - first1 + remaining <= firstbig + firstbig2 - first - first2 + remaining &&
                        firstbig + firstbig1 - first - first1 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                    turn++;
                    pick = Choice.FIRST;
                    remaining = remaining -first+firstbig;
                    return chooseNumber(row, Choice.FIRST);
                } else if (firstbig + firstbig2 - first - first2 + remaining <= secondbig + secondbig1 - second - second1 + remaining &&
                        firstbig + firstbig2 - first - first2 + remaining <= firstbig + firstbig1 - first - first1 + remaining &&
                        firstbig + firstbig2 - first - first2 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                    turn++;
                    pick = Choice.LAST;
                    remaining = remaining - first+firstbig;
                    return chooseNumber(row, Choice.FIRST);
                } else if (secondbig + secondbig1 - second - second1 + remaining <= firstbig + firstbig1 - first - first1 + remaining &&
                        secondbig + secondbig1 - second - second1 + remaining <= firstbig + firstbig2 - first - first2 + remaining &&
                        secondbig + secondbig1 - second - second1 + remaining <= secondbig + secondbig2 - second - second2 + remaining) {
                    turn++;
                    pick = Choice.FIRST;
                    remaining = remaining - second+secondbig;
                    return chooseNumber(row, Choice.LAST);
                } else {
                    pick = Choice.LAST;
                    turn++;
                    remaining = remaining -second+secondbig;
                    return chooseNumber(row, Choice.LAST);
                }
            } else {
                int a,b;
                Row test = new Row(row.getRow());
                boolean firsta = getNumber(test,Choice.FIRST) == getNumber(test, pick);
                chooseNumber(test, Choice.FIRST);
                a = getNumber(test,Choice.BIGGER);

                test = new Row(row.getRow());
                boolean seconda = getNumber(test,Choice.LAST) == getNumber(test, pick);
                chooseNumber(test,Choice.LAST);
                b = getNumber(test, Choice.BIGGER);

                if (firsta && !seconda){
                    turn++;
                    remaining = remaining - getNumber(row, Choice.FIRST) +a;
                    return chooseNumber(row, Choice.FIRST);
                } else {
                    turn++;
                    remaining = remaining - getNumber(row, Choice.LAST)+b;
                    return chooseNumber(row, Choice.LAST);
                }
            }
        } else if (row.getRow().size() < 4 && row.getRow().size() > 1 && remaining <=0) {
            int a,b,c,d;
            Row test = new Row(row.getRow());
            chooseNumber(test, Choice.FIRST);
            a = getNumber(test,Choice.FIRST) + getNumber(test, Choice.LAST);
            c = getNumber(test, Choice.BIGGER);

            test = new Row(row.getRow());
            chooseNumber(test,Choice.LAST);
            b = getNumber(test, Choice.FIRST) + getNumber(test, Choice.LAST);
            d = getNumber(test, Choice.BIGGER);

            if ( a < b ){
                turn = 1;
                remaining = remaining - getNumber(row, Choice.LAST)+c;
                return chooseNumber(row, Choice.FIRST);
            } else {
                turn = 1;
                remaining = remaining - getNumber(row, Choice.LAST)+d;
                return chooseNumber(row, Choice.LAST);
            }
        } else if (row.getRow().size() < 4 && row.getRow().size() > 1 && remaining >0) {
            int a,b,c,d;
            Row test = new Row(row.getRow());
            chooseNumber(test, Choice.FIRST);
            a = getNumber(test,Choice.FIRST) + getNumber(test, Choice.LAST);
            c = getNumber(test, Choice.BIGGER);

            test = new Row(row.getRow());
            chooseNumber(test,Choice.LAST);
            b = getNumber(test, Choice.FIRST) + getNumber(test, Choice.LAST);
            d = getNumber(test, Choice.BIGGER);

            if ( a < b ){
                turn = 1;
                remaining = remaining - getNumber(row, Choice.LAST)+c;
                return chooseNumber(row, Choice.FIRST);
            } else {
                turn = 1;
                remaining = remaining - getNumber(row, Choice.LAST)+d;
                return chooseNumber(row, Choice.LAST);
            }
        } else {
            turn = 1;
            return chooseNumber(row, Choice.LAST);
        }
    }

    /**
     * A B játékos álltal kiválasztott számok listáját határozza meg.
     * @return a B játékos számainak listája
     */
    public ArrayList<Integer> getBplayerScore() {
        return BplayerScore;
    }
}