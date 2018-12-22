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
    public void leftCollisionDetectedTopBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 0, testBlock); 
        assertTrue(collisionTest.isCollisionLeft());
    }
    
    @Test
    public void leftCollisionDetectedMiddleBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 1, testBlock); 
        assertTrue(collisionTest.isCollisionLeft());
    }
    
    @Test
    public void leftCollisionDetectedBottomBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 2, testBlock); 
        assertTrue(collisionTest.isCollisionLeft());
    }
    
    @Test
    public void leftCollisionDetectedEdge() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(0, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(collisionTest.isCollisionLeft());
    }
    
    @Test
    public void leftCollisionNoDetectCorrectly() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(!collisionTest.isCollisionLeft());
    }
    
    @Test
    public void rightCollisionDetectedTopBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 0, testBlock); 
        assertTrue(collisionTest.isCollisionRight());
    }
    
    @Test
    public void rightCollisionDetectedMiddleBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 1, testBlock); 
        assertTrue(collisionTest.isCollisionRight());
    }
    
    @Test
    public void rightCollisionDetectedottomBlock() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 2, testBlock); 
        assertTrue(collisionTest.isCollisionRight());
    }
 
    @Test
    public void rightCollisionDetectedEdge() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(9, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(collisionTest.isCollisionRight());
    }
    
    @Test
    public void rightCollisionNoDetectCorrectly() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(!collisionTest.isCollisionRight());
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
    public void downCollisionDetectedEdge() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 7);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(collisionTest.isCollisionDown());
    }
    
    @Test
    public void downCollisionNoDetectCorrectly() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        PlayerBlock playerBlock = new PlayerBlock(5, 0);
        PlayerCollisionLogic collisionTest = new PlayerCollisionLogic(playerBlock, testArea);
        assertTrue(!collisionTest.isCollisionDown());
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
