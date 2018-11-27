package columnspeli.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameAreaTest {
    
    @Test
    public void cratedGameAreaIsEmpty() {
        GameArea testArea = new GameArea(10, 10);
        assertEquals(false, testArea.hasBlock(5, 5));
    }
    
    @Test
    public void getAreaEdgeXReturnsCorrectValue() {
        GameArea testArea = new GameArea(20, 30);
        assertEquals(20, testArea.getAreaEdgeX());
    }
    
    @Test
    public void getAreaEdgeYReturnsCorrectValue() {
        GameArea testArea = new GameArea(20, 30);
        assertEquals(30, testArea.getAreaEdgeY());
    }
    
    @Test
    public void leftCollisionDetected() {
        GameArea testArea = new GameArea(10, 10);
        Block testBlock = new Block("red");
        testArea.setBlock(4, 0, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionLeft());
    }
    
    @Test
    public void rightCollisionDetected() {
        GameArea testArea = new GameArea(10, 10);
        Block testBlock = new Block("red");
        testArea.setBlock(6, 0, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionRight());
    }
    
    @Test
    public void downCollisionDetected() {
        GameArea testArea = new GameArea(10, 10);
        Block testBlock = new Block("red");
        testArea.setBlock(5, 3, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionDown());
    }
    
    // The following tests are redundant as the method will be replaced fully
    // Temporary
    
    @Test
    public void scanAndEraseFullLeft() { // temp, method will be replaced
        GameArea testArea = new GameArea(10, 10);
        Block testBlock1 = new Block("red");
        Block testBlock2 = new Block("red");
        Block testBlock3 = new Block("red");
        testArea.setBlock(5, 8, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(3, 8, testBlock3);
        testArea.scanAndErase(5, 8);
        assertTrue(!testArea.hasBlock(5, 8));
    }
    
    @Test
    public void scanAndEraseVerticallTop() { // temp, method will be replaced
        GameArea testArea = new GameArea(10, 10);
        Block testBlock1 = new Block("red");
        Block testBlock2 = new Block("red");
        Block testBlock3 = new Block("red");
        testArea.setBlock(0, 5, testBlock1);
        testArea.setBlock(0, 6, testBlock2);
        testArea.setBlock(0, 7, testBlock3);
        testArea.scanAndErase(0, 5);
        assertTrue(!testArea.hasBlock(0, 5));
    }
    
    @Test
    public void scanAndEraseVerticallMiddle() { // temp, method will be replaced
        GameArea testArea = new GameArea(10, 10);
        Block testBlock1 = new Block("red");
        Block testBlock2 = new Block("red");
        Block testBlock3 = new Block("red");
        testArea.setBlock(0, 5, testBlock1);
        testArea.setBlock(0, 6, testBlock2);
        testArea.setBlock(0, 7, testBlock3);
        testArea.scanAndErase(0, 5);
        assertTrue(!testArea.hasBlock(0, 5));
    }
    
    @Test
    public void blocksAreReleased() {
       GameArea testArea = new GameArea(10, 5);
       testArea.movePlayer("down");
       testArea.movePlayer("down");
       testArea.movePlayer("down");
       assertTrue(testArea.hasBlock(10, 5));
    }
    
    @Test
    public void leftCollisionNoMove() {
        GameArea testArea = new GameArea(10, 5);
        Block testBlock = new Block("red");
        testArea.setBlock(4, 0, testBlock);
        testArea.movePlayer("left");
        assertEquals(5, testArea.getPlayerBlock().getGridX());
    }
    
    @Test
    public void rightCollisionNoMove() {
        GameArea testArea = new GameArea(10, 5);
        Block testBlock = new Block("red");
        testArea.setBlock(6, 0, testBlock);
        testArea.movePlayer("right");
        assertEquals(5, testArea.getPlayerBlock().getGridX());
    }
    
}