package columnspeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import columnspeli.domain.Block;

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
    public void rightCollisionNoMove() {
        GameController testController = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testController.getGameBlockArea().setBlock(6, 0, testBlock);
        testController.getPlayerBlock().setGridX(5);
        testController.movePlayer(Directions.RIGHT);
        assertEquals(5, testController.getPlayerBlock().getGridX());
    }
    
    @Test
    public void gameOverWhenNoSpawn() {
        GameController testController = new GameController(3, 10);
        Block testBlock1 = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.BLUE);
        Block testBlock3 = new Block(Color.BLUE);
        testController.getGameBlockArea().setBlock(0, 0, testBlock1);
        testController.getGameBlockArea().setBlock(1, 0, testBlock2);
        testController.getGameBlockArea().setBlock(2, 0, testBlock3);
        assertTrue(testController.gameOver());
    }
    
    @Test
    public void gameOverWhenNoSpawnVariation() {
        GameController testController = new GameController(3, 10);
        Block testBlock1 = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.BLUE);
        Block testBlock3 = new Block(Color.BLUE);
        testController.getGameBlockArea().setBlock(0, 0, testBlock1);
        testController.getGameBlockArea().setBlock(1, 2, testBlock2);
        testController.getGameBlockArea().setBlock(2, 1, testBlock3);
        assertTrue(testController.gameOver());
    }
    
    @Test
    public void gameNotOverWhenSpawnSpace() {
        GameController testController = new GameController(3, 10);
        Block testBlock1 = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.BLUE);
        testController.getGameBlockArea().setBlock(0, 0, testBlock1);
        testController.getGameBlockArea().setBlock(1, 2, testBlock2);
        assertTrue(!testController.gameOver());
    }
    
    @Test
    public void pauseNotOnByDefault() {
        GameController testController = new GameController(10, 10);
        assertTrue(!testController.paused());
    }

    @Test
    public void pausePressedSwitchesState() {
        GameController testController = new GameController(10, 10);
        testController.pausePressed();
        testController.pausePressed();
        testController.pausePressed();
        assertTrue(testController.paused());
    }
    
    @Test
    public void gameActiveNotOnByDefault() {
        GameController testController = new GameController(10, 10);
        assertTrue(!testController.gameActive());
    }
    
    @Test
    public void gameActiveFalseWhenClosed() {
        GameController testController = new GameController(10, 10);
        testController.activateGame();
        testController.closeGame();
        assertTrue(!testController.gameActive());
    }
    
    @Test
    public void resetStateClearsScore() {
        GameController testController = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testController.getGameBlockArea().setBlock(4, 0, testBlock1);
        testController.getGameBlockArea().setBlock(5, 0, testBlock2);
        testController.getGameBlockArea().setBlock(6, 0, testBlock3);
        testController.seekAndDestroy();
        testController.resetState();
        assertEquals(0, testController.getStatistics().getScore());
    }
    
    @Test
    public void movePlayerLeftNoCollision() {
        GameController testController = new GameController(10, 10);
        testController.getPlayerBlock().setGridX(5);
        testController.movePlayer(Directions.LEFT);
        assertEquals(4, testController.getPlayerBlock().getGridX());
    }
    
    @Test
    public void movePlayerRightNoCollision() {
        GameController testController = new GameController(10, 10);
        testController.getPlayerBlock().setGridX(5);
        testController.movePlayer(Directions.RIGHT);
        assertEquals(6, testController.getPlayerBlock().getGridX());
    }
    
    @Test
    public void movePlayerDownNoCollision() {
        GameController testController = new GameController(10, 10);
        testController.getPlayerBlock().setGridY(5);
        testController.movePlayer(Directions.DOWN);
        assertEquals(6, testController.getPlayerBlock().getGridY());
    }
    
    @Test
    public void shrinkAreaCreatesGray() {
        GameController testController = new GameController(10, 10);
        testController.shrinkArea();
        assertEquals(Color.GRAY, testController.getGameBlockArea().getBlock(5, 9).getColor());
    }
    
    @Test
    public void movePlayerDownCollisionBlocksReleased() {
        GameController testController = new GameController(10, 10);
        testController.getPlayerBlock().setGridY(7);
        testController.getPlayerBlock().setGridX(5);
        Block testBlock = testController.getPlayerBlock().getBottomBlock();
        testController.movePlayer(Directions.DOWN);
        assertSame(testBlock, testController.getGameBlockArea().getBlock(5, 9));
    }
    
  
}