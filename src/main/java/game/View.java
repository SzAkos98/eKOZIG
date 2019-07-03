package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {

    private static Stage window;
    private static Scene scene1,scene2;

    /**
     * A {@link Main} osztály hívja meg, ez a program valódi {@code main} függvénye.
     *
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * A program grafikus felületének generálásáért felelős függvény.
     * @param primaryStage az alap Stage.
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Project eKOZIG");
        Rectangle bg = new Rectangle(1280, 720);
        bg.setStroke(Color.DARKCYAN);
        bg.setFill(Color.ANTIQUEWHITE);

        StackPane layout1 = new StackPane();
        layout1.getChildren().add(bg);
        mainView(layout1);
        window.setResizable(false);
        window.setScene(scene1);
        window.show();
    }

    /**
     * A menü grafikus felületének létrehozása.
     * @param menuLayout
     */
    public void mainView(Pane menuLayout) {

        //background and font size fixed
        Rectangle bg = new Rectangle(600, 600);
        bg.setStroke(Color.DARKCYAN);
        bg.setFill(Color.ANTIQUEWHITE);
        Font font = Font.font(72);
        AnchorPane asd = new AnchorPane();
        asd.getChildren().add(bg);

        //start button
        Button btnStart = new Button("Start");
        btnStart.setFont(font);
        btnStart.setOnAction(actionEvent -> {
            simulateGames(1000000, asd);
        });

        //exit button
        Button btnExit = new Button("Exit");
        btnExit.setFont(font);
        btnExit.setOnAction(actionEvent -> {
            System.exit(0);
        });

        //display
        VBox btns = new VBox(50, btnStart, btnExit);
        btns.setAlignment(Pos.CENTER);


        menuLayout.getChildren().add(btns);
        scene1 = new Scene(menuLayout, 600, 600);


    }



    private long startTime;
    private int wins;
    private int ties;
    private int loses;


    private static VBox list = new VBox();


    /**
     * A teljes végeredmény grafikus megjelenítése.
     * @param limit a szimulációk száma/hányszor fusson le a program
     * @param pane
     */
    public void simulateGames(int limit, Pane pane) {

        //background and font size fixed
        Rectangle bg = new Rectangle(600, 600);
        bg.setStroke(Color.DARKCYAN);
        bg.setFill(Color.ANTIQUEWHITE);
        wins = 0;
        loses = 0;
        ties = 0;
        int timer = 10;
        startTime = System.nanoTime();
        scene2 = new Scene(pane, 600, 600);
        window.setScene(scene2);

        for (int i = 0; i < limit; i++) {

            EndState endState = GameLogic.simulate();
            if (endState == EndState.WIN) {
                wins++;
            } else if (endState == EndState.TIE) {
                ties++;
            } else {
                loses++;
            }

            if (i == timer - 1) {
                final int counter = i;
                Label header = new Label("Simulation ran " + (counter + 1) + " times");
                System.out.println("Simulation ran " + (counter + 1) + " times");
                Label time = new Label("Elapsed time: " + (System.nanoTime() - startTime) / 1000000 + " ms");
                System.out.println("Elapsed time: " + (System.nanoTime() - startTime) / 1000000 + " ms");
                Label stats = new Label("Stats: " + "wins: " + wins + " loses: " + loses + " ties: " + ties);
                System.out.println("Stats: " + "wins: " + wins + " loses: " + loses + " ties: " + ties);
                list.getChildren().addAll(header, time, stats);
                if (counter +1 == 1000000) {
                    Label end = new Label("The simulation was " + (wins / 1000000.0) * 100 + "% successful!");
                    list.getChildren().add(end);
                }
                list.setAlignment(Pos.CENTER);
                timer *= 10;
            }
        }
        pane.getChildren().add(list);
    }

}
