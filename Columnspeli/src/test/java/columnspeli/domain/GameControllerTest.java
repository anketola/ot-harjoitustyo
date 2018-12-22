package columnspeli.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;

public class GameControllerTest {
    
    @Test
    public void leftCollisionNoMove() {
        GameController testController = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testController.getGameBlockArea().setBlock(4, 0, testBlock);
        testController.getPlayerBlock().setGridX(5);
        testController.movePlayer(Directions.LEFT);
        assertEquals(5, testController.getPlayerBlock().getGridX());
    }
    
    @Test
    public void RightCollisionNoMove() {
        GameController testController = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testController.getGameBlockArea().setBlock(6, 0, testBlock);
        testController.getPlayerBlock().setGridX(5);
        testController.movePlayer(Directions.RIGHT);
        assertEquals(5, testController.getPlayerBlock().getGridX());
    }
    

}