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
        
        BorderPane gameBorderPane = new BorderPane(); 
        GridPane rightGridPane = new GridPane();
        BorderPane gameOverBorderPane = new BorderPane();
        BorderPane menuBorderPane = new BorderPane();
        VBox menuVBox = new VBox();
        Button buttonLaunch = new Button("Käynnistä peli");
        Button buttonQuit = new Button("Lopeta peli");
        Button buttonPause = new Button("Jäädytä peli");
        Button returnToMenu = new Button("Palaa valikkoon");
        
        buttonLaunch.setMaxWidth(Double.MAX_VALUE);
        buttonQuit.setMaxWidth(Double.MAX_VALUE);
        menuVBox.setPadding(new Insets(50, 50, 50, 50));
        menuVBox.getChildren().addAll(buttonLaunch, buttonQuit);
        
       
        Canvas gameCanvas = new Canvas(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        Canvas nextBlockCanvas = new Canvas(BLOCK_SIZE * 2, BLOCK_SIZE * 4);
        
        GraphicsContext drawer = gameCanvas.getGraphicsContext2D();
        GraphicsContext drawerNextBlock = nextBlockCanvas.getGraphicsContext2D();
        
        
        Label scoreTitle = new Label("Pisteet: ");
        Label scoreText = new Label(Integer.toString(gameArea.getStatistics().getScore()));
        Label timeTitle = new Label("Aikaa kulunut: ");
        Label timeText = new Label("TO DO");
        Label nextBlockText = new Label("Seuraava palikka:");
        Label gameOverText = new Label("Peli päättyi!"); 
        
        rightGridPane.add(scoreTitle, 0, 0);
        rightGridPane.add(scoreText, 0, 1);
        rightGridPane.add(timeTitle, 0, 2);
        rightGridPane.add(timeText, 0, 3);
        rightGridPane.add(buttonPause, 0, 4);
        rightGridPane.add(nextBlockText, 0, 5);
        //rightGridPane.add(nextBlockCanvas, 0, 6);
        
        rightGridPane.setHgap(20);
        rightGridPane.setVgap(20);
        rightGridPane.setPrefWidth(200);
        gameBorderPane.setCenter(gameCanvas);
        gameBorderPane.setRight(rightGridPane);
        
        menuBorderPane.setPrefWidth(600);
        menuBorderPane.setPrefHeight(400);
        menuBorderPane.setCenter(menuVBox);
        
        gameOverBorderPane.setPrefWidth(600);
        gameOverBorderPane.setPrefHeight(400);
        gameOverBorderPane.setCenter(gameOverText);
        gameOverBorderPane.setBottom(returnToMenu);
        
        Scene gameScene = new Scene(gameBorderPane);
        Scene menuScene = new Scene(menuBorderPane);
        Scene gameOverScene = new Scene(gameOverBorderPane); 
        
        buttonLaunch.setOnAction((event) -> {
            primaryStage.setScene(gameScene);
            gameArea.activateGame();
            gameArea.resetState();
        });
        
        returnToMenu.setOnAction((event) -> {
            primaryStage.setScene(menuScene);
            
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
            @Override
            public void handle(long now) {
                if (now - past < 100000000) {
                    return;
                }
                
                if ((gameArea.gameActive()) && (!gameArea.paused())) {
                int x = gameArea.getPlayerBlock().getX();
                int y = gameArea.getPlayerBlock().getY();
                scoreText.setText(Integer.toString(gameArea.getStatistics().getScore()));
                
               
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
                
                drawerNextBlock.setFill(Color.WHITE);
                drawerNextBlock.fillRect(0, 0, BLOCK_SIZE * 2 , BLOCK_SIZE * 4);
                drawerNextBlock.setFill(Color.BLACK);
                drawerNextBlock.fillRect(2, 2, BLOCK_SIZE * 2 - 2, BLOCK_SIZE * 4 - 2);
                
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
                if (gameArea.gameOver()) {
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
