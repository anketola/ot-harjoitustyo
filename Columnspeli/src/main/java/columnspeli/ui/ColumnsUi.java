package columnspeli.ui;


import columnspeli.domain.PlayerBlock;
import columnspeli.domain.Block;
import columnspeli.domain.GameArea;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

// Note, work in progress
// Controls: left, right, up and down arrows
// No collision detection etc. yet

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
        
        BorderPane testBorderPane = new BorderPane(); 
        
        Canvas testCanvas = new Canvas(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        GraphicsContext drawer = testCanvas.getGraphicsContext2D();
      
        testBorderPane.setCenter(testCanvas);
        
        Scene testScene = new Scene(testBorderPane);
        
        testScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                System.out.println("Keyboard feed: left");
                gameArea.movePlayer("left");
            }

            if (event.getCode() == KeyCode.RIGHT) {
                System.out.println("Keyboard feed: right");
                gameArea.movePlayer("right");
            }
            
            if (event.getCode() == KeyCode.DOWN) {
                System.out.println("Keyboard feed: down");
                gameArea.movePlayer("down");
            }
            if (event.getCode() == KeyCode.UP) {
                System.out.println("Keyboard feed: up");
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
                int x = gameArea.getPlayerBlock().getX();
                int y = gameArea.getPlayerBlock().getY();
                int xGrid = gameArea.getPlayerBlock().getGridX();
                int yGrid = gameArea.getPlayerBlock().getGridY();
                boolean cLeft = gameArea.isCollisionLeft();
                boolean cRight = gameArea.isCollisionRight();
                boolean cDown = gameArea.isCollisionDown();
                
                
                
                System.out.println("GridX: " + xGrid + " GridY: " + yGrid); // for debugging
                System.out.println("Collision left:" + cLeft);
                System.out.println("Collision right:" + cRight);
                System.out.println("Collision down:" + cDown);
                System.out.println(gameArea.eglibleRespawn());
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
            this.past = now;
            }
        }.start();
        
        primaryStage.setScene(testScene);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
