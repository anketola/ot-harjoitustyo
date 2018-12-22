package columnspeli.logic;

import columnspeli.domain.Block;
import columnspeli.domain.GameBlockArea;
import columnspeli.domain.GameController;
import columnspeli.domain.PlayerBlock;
import javafx.scene.paint.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PlayerCollisionLogicTest {
    
    @Test
    public void leftCollisionDetected() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 0, testBlock); 
        assertTrue(collisionTest.isCollisionLeft());
    }
    
    @Test
    public void rightCollisionDetected() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 0, testBlock); 
        assertTrue(collisionTest.isCollisionRight());
    }
    
    @Test
    public void downCollisionDetected() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(5, 3, testBlock); 
        assertTrue(collisionTest.isCollisionDown());
    }
    
    @Test
    public void eglibleRespawnCorrectWhenEmpty() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertEquals(10, collisionTest.eglibleRespawn().size());
    }

    @Test
    public void eglibleRespawnCorrectWhenTwoBlocking() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.GREEN);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(0, 0, testBlock);
        testArea.setBlock(1, 0, testBlock2);
        assertEquals(8, collisionTest.eglibleRespawn().size());
    }
    
    
}
