package game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class View extends Application {

    private static Stage window;
    private static Scene scene1,scene2;
    private Logger logger = LoggerFactory.getLogger(View.class);

    /**
     * A {@link Main} osztály hívja meg, ez a program valódi {@code main} függvénye.
     *
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("ProjectSWE");
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

    public void mainView(Pane menuLayout) {

        //background and font size fixed
        Rectangle bg = new Rectangle(600, 600);
        bg.setStroke(Color.DARKCYAN);
        bg.setFill(Color.ANTIQUEWHITE);
        Font font = Font.font(72);

        //start button
        Button btnStart = new Button("Start");
        btnStart.setFont(font);
        btnStart.setOnAction(actionEvent -> {
            simulateGames(1000000);
            logger.info("Clicked Start button. Going to the Start Game scene.");
        });

        //exit button
        Button btnExit = new Button("Exit");
        btnExit.setFont(font);
        btnExit.setOnAction(actionEvent -> {
            logger.info("Clicked Exit button. Exiting program.");
            System.exit(0);
        });

        //display
        VBox btns = new VBox(50, btnStart, btnExit);
        btns.setAlignment(Pos.CENTER);


        menuLayout.getChildren().add(btns);
        scene1 = new Scene(menuLayout, 600, 600);
        AnchorPane asd = new AnchorPane();
        asd.getChildren().add(bg);

    }



    private long startTime;
    private int wins;
    private int ties;
    private int loses;


    private static VBox list = new VBox();

    private static Button startButton;

    private void simulateGames(int limit) {

        //background and font size fixed
        Rectangle bg = new Rectangle(600, 600);
        bg.setStroke(Color.DARKCYAN);
        bg.setFill(Color.ANTIQUEWHITE);
        Font font = Font.font(72);
        Pane pane = new Pane();

        wins = 0;
        loses = 0;
        ties = 0;
        int timer = 10;
        startTime = System.nanoTime();
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
                int fxWins = wins;
                int fxLoses = loses;
                int fxTies = ties;
                final int counter = i;
                Platform.runLater(() -> {
                    Label header = new Label("Simulation ran " + (counter + 1) + " times");
                    Label time = new Label("Elapsed time: " + (System.nanoTime() - startTime) / 1000000 + " ms");
                    Label stats = new Label("Stats: " + "wins: " + fxWins + " loses: " + fxLoses + " ties: " + fxTies);
                    list.getChildren().addAll(header, time, stats);
                    list.setAlignment(Pos.CENTER);
                    pane.getChildren().add(list);
                    scene2 = new Scene(pane, 600, 600);
                    window.setScene(scene2);
                });
                timer *= 10;
            }
        }
        Platform.runLater(() -> {
            startButton.disableProperty().setValue(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Simulation Ended!");
            alert.setContentText("The simulation was " + (wins / 1000000.0) * 100 + "% successful!");

            alert.showAndWait();
        });

    }

}
