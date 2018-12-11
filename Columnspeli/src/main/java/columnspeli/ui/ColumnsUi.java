package columnspeli.ui;


import columnspeli.domain.Block;
import columnspeli.domain.GameArea;
import columnspeli.domain.ScoreBoardHandler;
import columnspeli.domain.ScoreEntry;
import columnspeli.dao.ScoreEntryDao;
import columnspeli.dao.Database;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.io.File;

public class ColumnsUi extends Application {
    
    public static int GAME_FIELD_WIDTH = 240;
    public static int GAME_FIELD_HEIGHT = 480;
    public static int BLOCK_SIZE = 20; 
    public static int DEFAULT_SPEED = 5;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Columns-peli");
        primaryStage.setResizable(false);
        
        File databaseFile = new File("db", "scores.db");
        Database database = new Database("jdbc:sqlite:" + databaseFile.getAbsolutePath());
        ScoreEntryDao scoreEntryDao = new ScoreEntryDao(database);
        
        GameArea gameArea = new GameArea(5, 5);
        ScoreBoardHandler scoreBoardHandler = new ScoreBoardHandler(scoreEntryDao);
        gameArea.getStatistics().setSpeed(DEFAULT_SPEED);
        
        Slider speedSlider = new Slider();
        speedSlider.setMin(1);
        speedSlider.setMax(10);
        speedSlider.setValue(DEFAULT_SPEED);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(0);
        speedSlider.setBlockIncrement(1);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                gameArea.getStatistics().setSpeed(new_val.intValue());
            }
        });
        
        
        BorderPane gameBorderPane = new BorderPane(); 
        GridPane rightGridPane = new GridPane();
        GridPane scoreGridPane = new GridPane();
        BorderPane gameOverBorderPane = new BorderPane();
        BorderPane menuBorderPane = new BorderPane();
        BorderPane scoreBorderPane = new BorderPane();
        
        
        VBox gameOverVBox = new VBox();
        VBox menuVBox = new VBox();
        Button buttonLaunch = new Button("Käynnistä peli");
        Button buttonScoreScreen = new Button("Ennätyspisteet");
        Button buttonQuit = new Button("Lopeta peli");
        Button buttonSendHighScore = new Button("Tallenna");
        
        Button buttonPause = new Button("Jäädytä peli");
        Button buttonReturnToMenu = new Button("Palaa valikkoon");
        Button buttonReturnFromScoreView = new Button("Palaa päävalikkoon");
        
        
        buttonLaunch.setMaxWidth(Double.MAX_VALUE);
        buttonScoreScreen.setMaxWidth(Double.MAX_VALUE);
        buttonQuit.setMaxWidth(Double.MAX_VALUE);
        buttonReturnFromScoreView.setMaxWidth(Double.MAX_VALUE);
        
        Canvas gameCanvas = new Canvas(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        Canvas nextBlockCanvas = new Canvas(BLOCK_SIZE * 2, BLOCK_SIZE * 4);
        
        GraphicsContext drawer = gameCanvas.getGraphicsContext2D();
        
        Label speedTitle = new Label("Pelin nopeus");
        Label scoreTitle = new Label("Pisteet: ");
        Label scoreText = new Label(Integer.toString(gameArea.getStatistics().getScore()));
        Label timeTitle = new Label("Aikaa kulunut: ");
        Label timeText = new Label("0");
        Label nextBlockText = new Label("Seuraava palikka:");
        Label gameOverText = new Label("Peli päättyi!"); 
        Label gameOverTextScore = new Label();
        Label scoreMainText = new Label("Ennätyspisteet");
       
        Label newScoreText = new Label("Anna nimesi");
        TextField highScoreName = new TextField();
        
        rightGridPane.add(scoreTitle, 0, 0);
        rightGridPane.add(scoreText, 0, 1);
        rightGridPane.add(timeTitle, 0, 2);
        rightGridPane.add(timeText, 0, 3);
        rightGridPane.add(buttonPause, 0, 4);
        rightGridPane.add(nextBlockText, 0, 5);
        
        
        scoreGridPane.add(new Label("Sijoitus"), 0, 0);
        scoreGridPane.add(new Label("Nimi"), 1, 0);
        scoreGridPane.add(new Label("Pisteet"), 2, 0);
        
        ArrayList<ScoreEntry> scoreDisplay = scoreBoardHandler.giveTopTenPlayers();
        for (int i = 0; i < 10; i++) {
            scoreGridPane.add(new Label(Integer.toString(i + 1)), 0, 1 + i);
            scoreGridPane.add(new Label(scoreDisplay.get(i).getName()), 1, 1 + i);
            scoreGridPane.add(new Label(Integer.toString(scoreDisplay.get(i).getScore())), 2, 1 + i);
        }
        

        scoreGridPane.setHgap(20);
        scoreGridPane.setVgap(10);
        scoreGridPane.setPadding(new Insets(20, 20, 20, 20));
        
        rightGridPane.setHgap(20);
        rightGridPane.setVgap(20);
        rightGridPane.setPrefWidth(200);
        gameBorderPane.setCenter(gameCanvas);
        gameBorderPane.setRight(rightGridPane);
        
        menuVBox.setPadding(new Insets(50, 50, 50, 50));
        menuVBox.getChildren().addAll(buttonLaunch, buttonScoreScreen, buttonQuit, speedTitle, speedSlider);
        
        gameOverVBox.getChildren().addAll(gameOverText, gameOverTextScore, newScoreText, highScoreName, buttonSendHighScore);
        
        menuBorderPane.setPrefWidth(600);
        menuBorderPane.setPrefHeight(400);
        menuBorderPane.setCenter(menuVBox);
        
        scoreBorderPane.setPrefWidth(600);
        scoreBorderPane.setPrefHeight(400);
        scoreBorderPane.setCenter(scoreGridPane);
        scoreBorderPane.setTop(scoreMainText);
        scoreBorderPane.setBottom(buttonReturnFromScoreView);
        
        gameOverBorderPane.setPrefWidth(600);
        gameOverBorderPane.setPrefHeight(400);
        gameOverBorderPane.setCenter(gameOverVBox);
        gameOverBorderPane.setBottom(buttonReturnToMenu);
        
        
       
        Scene gameScene = new Scene(gameBorderPane);
        Scene menuScene = new Scene(menuBorderPane);
        Scene gameScoreScene = new Scene(scoreBorderPane);
        Scene gameOverScene = new Scene(gameOverBorderPane); 
        
        buttonLaunch.setOnAction((event) -> {
            primaryStage.setScene(gameScene);
            gameArea.activateGame();
            gameArea.resetState();
        });
        
        buttonScoreScreen.setOnAction((event) -> {
            primaryStage.setScene(gameScoreScene);
        });
        
        buttonReturnFromScoreView.setOnAction((event) -> {
            primaryStage.setScene(menuScene);
        });
        
        buttonReturnToMenu.setOnAction((event) -> {
            primaryStage.setScene(menuScene);
            
        });
        
        buttonSendHighScore.setOnAction((event) -> {
            scoreBoardHandler.saveScoreEntry("Timo", 9000);
        });
        
        buttonQuit.setOnAction((event) -> {
            System.exit(0);
        });
        
        buttonPause.setOnAction((event) -> {
            gameArea.pausePressed();
            if (gameArea.paused()) {
                buttonPause.setText("Jatka peliä");
            } else {
                buttonPause.setText("Jäädytä peli");
            }
        });
        
        gameScene.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.LEFT) && (!gameArea.paused())) {
                gameArea.movePlayer("left");
            }

            if ((event.getCode() == KeyCode.RIGHT) && (!gameArea.paused())){
                gameArea.movePlayer("right");
            }
            
            if ((event.getCode() == KeyCode.DOWN) && (!gameArea.paused())) {
                gameArea.movePlayer("down");
            }
            if ((event.getCode() == KeyCode.UP) && (!gameArea.paused())) {
                gameArea.getPlayerBlock().shuffleBlocks();
            }
        });
        
        
        
        new AnimationTimer() {
            long past = 0;
            int dropSpeed = 0;
            int shrinkCounter = 0;
            @Override
            public void handle(long now) {
                if (now - past < 100000000) {
                    return;
                }
                
                if ((gameArea.gameActive()) && (!gameArea.paused())) {
                int x = gameArea.getPlayerBlock().getGridX();
                int y = gameArea.getPlayerBlock().getGridY();
                scoreText.setText(Integer.toString(gameArea.getStatistics().getScore()));
                timeText.setText(Long.toString(gameArea.getStatistics().getElapsedTime()));
               
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
                
                // This draws a white rectangle to give a border for the blocks
                // The actual colored ones are few pixels smaller 
                
                drawer.setFill(Color.WHITE);
                drawer.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE * 3);
                Color color;
                for (int i = 0; i < 3; i ++) {
                    if (i == 0) {
                        color = gameArea.getPlayerBlock().getTopBlock().getColor();
                    } else if (i == 1) {
                        color = gameArea.getPlayerBlock().getMiddleBlock().getColor();
                    } else {
                        color = gameArea.getPlayerBlock().getBottomBlock().getColor();
                    }
                        drawer.setFill(color);
                        drawer.fillRect(x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1 + i * BLOCK_SIZE, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                    }
                
                // draw other blocks
                
                y = 0;
                while (y < gameArea.getAreaEdgeY()) {
                    x = 0;
                    while (x < gameArea.getAreaEdgeX()) {
                        if (gameArea.hasBlock(x, y)) {
                            drawer.setFill(Color.WHITE);
                            drawer.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                            drawer.setFill(gameArea.getBlock(x, y).getColor());
                            color = gameArea.getBlock(x, y).getColor();
                            drawer.fillRect(1 + x * BLOCK_SIZE, 1 + y * BLOCK_SIZE, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                        }
                        x++;
                    }
                    y++;
                }
                dropSpeed++;
                if (dropSpeed == (11 - gameArea.getStatistics().getSpeed())) {
                    dropSpeed = 0;
                    shrinkCounter++;
                    gameArea.movePlayer("down");
                    if (shrinkCounter == gameArea.getStatistics().getShrinkSpeed()) {
                        gameArea.shrinkArea();
                        shrinkCounter = 0;
                    }
                }
                if (gameArea.gameOver()) {
                    int activeScore = gameArea.getStatistics().getScore();
                    gameOverText.setText("Onneksi olkoon, pääsit ennätyslistalle!");
                    gameOverTextScore.setText("Pisteesi olivat " + Integer.toString(gameArea.getStatistics().getScore()));
                    primaryStage.setScene(gameOverScene);
                    gameArea.closeGame();
                }
                }
            this.past = now;
            }
        }.start();
        
        primaryStage.setScene(menuScene);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
