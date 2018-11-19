package columnspeli.ui;


import columnspeli.domain.PlayerBlock;
import columnspeli.domain.Block;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class ColumnsUi extends Application {
    
    public static int GAME_FIELD_WIDTH = 200;
    public static int GAME_FIELD_HEIGHT = 300;
    public static int BLOCK_SIZE = 10; 
  
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Columns-peli");
        primaryStage.setResizable(false);
        PlayerBlock playerObject = new PlayerBlock(50, 50, new Block("yellow"), new Block("red"), new Block("blue"));
        
        BorderPane testBorderPane = new BorderPane(); 
        
        Canvas testCanvas = new Canvas(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        GraphicsContext drawer = testCanvas.getGraphicsContext2D();
      
        testBorderPane.setCenter(testCanvas);
        
        Scene testScene = new Scene(testBorderPane);
        
        testScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                System.out.println("Keyboard feed: left");
                playerObject.moveX(-BLOCK_SIZE);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                System.out.println("Keyboard feed: right");
                playerObject.moveX(BLOCK_SIZE);
            }
            
            if (event.getCode() == KeyCode.DOWN) {
                System.out.println("Keyboard feed: down");
                playerObject.speedPush();
            }
            if (event.getCode() == KeyCode.UP) {
                System.out.println("Keyboard feed: up");
                playerObject.shuffleBlocks();
            }
        });
        
        new AnimationTimer() {
            long past = 0;
            @Override
            public void handle(long now) {
                if (now - past < 100000000) {
                    return;
                }
                int x = playerObject.getX(); // for debugging
                int y = playerObject.getY(); // for debugging
                
                // Temporary: Scrolling the field
                if (y > GAME_FIELD_HEIGHT) { 
                    playerObject.setY(0);
                }
                System.out.println("X: " + x + " Y: " + y); // for debugging
                System.out.println(playerObject.getBottomBlock().getColor());
                
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
                
                
                // Drawing player object
                
                String vari = playerObject.getTopBlock().getColor();
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x, y, 10, 10);
                
                vari = playerObject.getMiddleBlock().getColor();
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x, y + BLOCK_SIZE, 10, 10);
                
                vari = playerObject.getBottomBlock().getColor();
                if (vari.equals("yellow")) {
                    drawer.setFill(Color.YELLOW);
                } else if (vari.equals("red")) {
                    drawer.setFill(Color.RED);
                } else if (vari.equals("blue")) {
                    drawer.setFill(Color.BLUE);
                }
                
                drawer.fillRect(x, y + 2 * BLOCK_SIZE, 10, 10);
                
                
                playerObject.moveDown();
                
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
