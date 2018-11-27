package columnspeli.ui;


import columnspeli.domain.Block;
import columnspeli.domain.GameArea;

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
import javafx.geometry.Pos;
import javafx.geometry.Insets;

// Messy code at times, will be fixed. Enum will be implemented later etc
// The game is now "playable" to some degree, will be fixing stuff later
// the falling speed is controlled by "dropspeed" variable with 0 - 10 as values
// no practical implementation for it yet

public class ColumnsUi extends Application {
    
    public static int GAME_FIELD_WIDTH = 300;
    public static int GAME_FIELD_HEIGHT = 600;
    public static int BLOCK_SIZE = 20; 
  
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Columns-peli");
        primaryStage.setResizable(false);

        GameArea gameArea = new GameArea(15, 30);
        
        // Random blocks for testing purposes
        
        gameArea.setBlock(0, 29, new Block("red"));
        gameArea.setBlock(0, 28, new Block("yellow"));
        gameArea.setBlock(5, 20, new Block("yellow"));
        gameArea.setBlock(14, 29, new Block("blue"));
        
        BorderPane gameBorderPane = new BorderPane(); 
        GridPane rightGridPane = new GridPane();
        BorderPane menuBorderPane = new BorderPane();
        VBox menuVBox = new VBox();
        Button buttonLaunch = new Button("Käynnistä peli");
        Button buttonQuit = new Button("Lopeta peli");
        Button buttonPause = new Button("Jäädytä peli / jatka peliä");
        buttonLaunch.setMaxWidth(Double.MAX_VALUE);
        buttonQuit.setMaxWidth(Double.MAX_VALUE);
        menuVBox.setPadding(new Insets(50, 50, 50, 50));
        menuVBox.getChildren().addAll(buttonLaunch, buttonQuit);
        
       
        Canvas testCanvas = new Canvas(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        GraphicsContext drawer = testCanvas.getGraphicsContext2D();
        Label scoreTitle = new Label("Pisteet: ");
        Label scoreText = new Label(Integer.toString(gameArea.getStatistics().getScore()));
        Label timeTitle = new Label("Aikaa kulunut: ");
        Label timeText = new Label("TO DO");
        
        
        rightGridPane.add(scoreTitle, 0, 0);
        rightGridPane.add(scoreText, 0, 1);
        rightGridPane.add(timeTitle, 0, 2);
        rightGridPane.add(timeText, 0, 3);
        rightGridPane.add(buttonPause, 0, 4);
        
        rightGridPane.setHgap(20);
        rightGridPane.setVgap(20);
        rightGridPane.setPrefWidth(200);
        gameBorderPane.setCenter(testCanvas);
        gameBorderPane.setRight(rightGridPane);
        
        menuBorderPane.setPrefWidth(600);
        menuBorderPane.setPrefHeight(400);
        menuBorderPane.setCenter(menuVBox);
        
        Scene gameScene = new Scene(gameBorderPane);
        Scene menuScene = new Scene(menuBorderPane);
        
        buttonLaunch.setOnAction((event) -> {
            primaryStage.setScene(gameScene);
            gameArea.activateGame();
        });
        
        buttonQuit.setOnAction((event) -> {
            System.exit(0);
        });
        
        buttonPause.setOnAction((event) -> {
            gameArea.pausePressed();
        });
        
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                gameArea.movePlayer("left");
            }

            if (event.getCode() == KeyCode.RIGHT) {
                gameArea.movePlayer("right");
            }
            
            if (event.getCode() == KeyCode.DOWN) {
                gameArea.movePlayer("down");
            }
            if (event.getCode() == KeyCode.UP) {
                gameArea.getPlayerBlock().shuffleBlocks();
            }
        });
        
        new AnimationTimer() {
            long past = 0;
            int dropSpeed = 0;
            @Override
            public void handle(long now) {
                if (now - past < 100000000) {
                    return;
                }
                
                if ((gameArea.gameActive()) && (!gameArea.paused())) {
                int x = gameArea.getPlayerBlock().getX();
                int y = gameArea.getPlayerBlock().getY();
                scoreText.setText(Integer.toString(gameArea.getStatistics().getScore()));
                
                System.out.println("Spawn open: " + gameArea.eglibleRespawn());
                System.out.println("Game over: " + gameArea.gameOver());
                
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
                
                
                // Drawing player object
                
                
                String vari = gameArea.getPlayerBlock().getTopBlock().getColor();
                
                drawer.setFill(Color.WHITE);
                drawer.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x + 1, y + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                
                
                vari = gameArea.getPlayerBlock().getMiddleBlock().getColor();
                
                drawer.setFill(Color.WHITE);
                drawer.fillRect(x, y + BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x + 1, y + 1 + BLOCK_SIZE, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                
                
                vari = gameArea.getPlayerBlock().getBottomBlock().getColor();
                
                drawer.setFill(Color.WHITE);
                drawer.fillRect(x, y + 2 * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x + 1, y + 1 + 2 * BLOCK_SIZE, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                
                // draw other blocks
                
                y = 0;
                while (y < gameArea.getAreaEdgeY()) {
                    x = 0;
                    while (x < gameArea.getAreaEdgeX()) {
                        if (gameArea.hasBlock(x, y)) {
                            drawer.setFill(Color.WHITE);
                            drawer.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                            vari = gameArea.getBlock(x, y).getColor();
                            if (vari.equals("yellow")) {
                                drawer.setFill(Color.YELLOW);
                            } else if (vari.equals("red")) {
                                drawer.setFill(Color.RED);
                            } else if (vari.equals("blue")) {
                                drawer.setFill(Color.BLUE);
                            }
                            
                        drawer.fillRect(1 + x * BLOCK_SIZE, 1 + y * BLOCK_SIZE, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
                        }
                        x++;
                    }
                    y++;
                }
                dropSpeed++;
                if (dropSpeed == 10) {
                    dropSpeed = 0;
                    gameArea.movePlayer("down");
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
