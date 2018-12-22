package columnspeli.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;

public class GameControllerTest {
    
    @Test
    public void cratedGameAreaIsEmpty() {
        GameController testArea = new GameController(10, 10);
        assertEquals(false, testArea.hasBlock(5, 5));
    }
    
    @Test
    public void getAreaEdgeXReturnsCorrectValue() {
        GameController testArea = new GameController(20, 30);
        assertEquals(20, testArea.getAreaEdgeX());
    }
    
    @Test
    public void getAreaEdgeYReturnsCorrectValue() {
        GameController testArea = new GameController(20, 30);
        assertEquals(30, testArea.getAreaEdgeY());
    }
    
    @Test
    public void nextBlockSimiliarWorksRight() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(0, 9, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(testArea.nextBlockSimiliar(0, 9, "right"));
    }
    
    @Test
    public void nextBlockSimiliarDifferentBlockWorksRight() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.BLUE);
        testArea.setBlock(0, 9, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(!testArea.nextBlockSimiliar(0, 9, "right"));
    }
    
    @Test
    public void nextBlockSimiliarWorksUp() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(1, 8, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(testArea.nextBlockSimiliar(1, 9, "up"));
    }
    
    @Test
    public void nextBlockSimiliarDifferentBlockWorksUp() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.BLUE);
        testArea.setBlock(1, 8, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(!testArea.nextBlockSimiliar(1, 9, "up"));
    }
    
    @Test
    public void nextBlockSimiliarWorksDownRight() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(6, 6, testBlock2);
        assertTrue(testArea.nextBlockSimiliar(5, 5, "downright"));
    }
    
    @Test
    public void nextBlockSimiliarDifferentBlockWorksDownRight() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(6, 6, testBlock2);
        assertTrue(!testArea.nextBlockSimiliar(5, 5, "downright"));
    }
    
    @Test
    public void nextBlockSimiliarWorksDownLeft() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(4, 6, testBlock2);
        assertTrue(testArea.nextBlockSimiliar(5, 5, "downleft"));
    }
    
    @Test
    public void nextBlockSimiliarDifferentBlockWorksDownLeft() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(4, 6, testBlock2);
        assertTrue(!testArea.nextBlockSimiliar(5, 5, "downleft"));
    }
    
    @Test
    public void eglibleRespawnCorrectWhenEmpty() {
        GameController testArea = new GameController(10, 10);
        assertEquals(10, testArea.eglibleRespawn().size());
    }

    @Test
    public void eglibleRespawnCorrectWhenTwoBlocking() {
        GameController testArea = new GameController(5, 10);
        Block testBlock = new Block(Color.GREEN);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(0, 0, testBlock);
        testArea.setBlock(1, 0, testBlock2);
        assertEquals(3, testArea.eglibleRespawn().size());
    }
    
    @Test
    public void leftCollisionDetected() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 0, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionLeft());
    }
    
    @Test
    public void rightCollisionDetected() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 0, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionRight());
    }
    
    @Test
    public void downCollisionDetected() {
        GameController testArea = new GameController(10, 10);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(5, 3, testBlock); 
        // 5 is the default first PlayerBlock spawn for now
        assertTrue(testArea.isCollisionDown());
    }
    
    @Test
    public void horizontalScanStreakWorksThree() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 8, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(5, 8, testBlock3);
        testArea.horizontalScan();
        assertEquals(3, testArea.clearCollected());
    }
    
    @Test
    public void horizontalScanStreakWorksFour() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 8, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(5, 8, testBlock3);
        testArea.setBlock(6, 8, testBlock4);
        testArea.horizontalScan();
        assertEquals(4, testArea.clearCollected());
    }
    
    @Test
    public void verticalScanStreakWorksThree() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 7, testBlock1);
        testArea.setBlock(3, 8, testBlock2);
        testArea.setBlock(3, 9, testBlock3);
        testArea.verticalScan();
        assertEquals(3, testArea.clearCollected());
    }
    
    @Test
    public void verticallScanStreakWorksFour() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 6, testBlock1);
        testArea.setBlock(3, 7, testBlock2);
        testArea.setBlock(3, 8, testBlock3);
        testArea.setBlock(3, 9, testBlock4);
        testArea.verticalScan();
        assertEquals(4, testArea.clearCollected());
    }
    
    @Test
    public void diagonalScanDownRightStreakWorksThree() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 3, testBlock1);
        testArea.setBlock(4, 4, testBlock2);
        testArea.setBlock(5, 5, testBlock3);
        testArea.diagonalScanDownRight();
        assertEquals(3, testArea.clearCollected());
    }
    
    @Test
    public void diagonalScanDownRightStreakWorksFour() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 3, testBlock1);
        testArea.setBlock(4, 4, testBlock2);
        testArea.setBlock(5, 5, testBlock3);
        testArea.setBlock(6, 6, testBlock4);
        testArea.diagonalScanDownRight();
        assertEquals(4, testArea.clearCollected());
    }    
    
    @Test
    public void diagonalScanDownLeftStreakWorksThree() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock1);
        testArea.setBlock(4, 6, testBlock2);
        testArea.setBlock(3, 7, testBlock3);
        testArea.diagonalScanDownLeft();
        assertEquals(3, testArea.clearCollected());
    }
    
    @Test
    public void diagonalScanDownLeftStreakWorksFour() {
        GameController testArea = new GameController(10, 10);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock1);
        testArea.setBlock(4, 6, testBlock2);
        testArea.setBlock(3, 7, testBlock3);
        testArea.setBlock(2, 8, testBlock4);
        testArea.diagonalScanDownLeft();
        assertEquals(4, testArea.clearCollected());
    }
    
    @Test
    public void blocksAreReleased() {
       GameController testArea = new GameController(10, 5);
       testArea.movePlayer("down");
       testArea.movePlayer("down");
       testArea.movePlayer("down");
       assertTrue(testArea.hasBlock(10, 5));
    }
    
    @Test
    public void leftCollisionNoMove() {
        GameController testArea = new GameController(10, 5);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(4, 0, testBlock);
        testArea.movePlayer("left");
        assertEquals(5, testArea.getPlayerBlock().getGridX());
    }
    
    @Test
    public void rightCollisionNoMove() {
        GameController testArea = new GameController(10, 5);
        Block testBlock = new Block(Color.RED);
        testArea.setBlock(6, 0, testBlock);
        testArea.movePlayer("right");
        assertEquals(5, testArea.getPlayerBlock().getGridX());
    }
    
}