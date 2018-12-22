package columnspeli.logic;

import columnspeli.domain.Block;
import columnspeli.domain.Directions;
import columnspeli.domain.GameBlockArea;
import javafx.scene.paint.Color;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ScanLogicTest {
    
    @Test
    public void nextBlockSimiliarWorksRight() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(0, 9, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(scanLogic.nextBlockSimiliar(0, 9, Directions.RIGHT));
    }
 
    @Test
    public void nextBlockSimiliarDifferentBlockWorksRight() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.BLUE);
        testArea.setBlock(0, 9, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(!scanLogic.nextBlockSimiliar(0, 9, Directions.RIGHT));
    }
    
    @Test
    public void nextBlockSimiliarWorksUp() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(1, 8, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(scanLogic.nextBlockSimiliar(1, 9, Directions.UP));
    }
 
    @Test
    public void nextBlockSimiliarDifferentBlockWorksUp() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.BLUE);
        testArea.setBlock(1, 8, testBlock);
        testArea.setBlock(1, 9, testBlock2);
        assertTrue(!scanLogic.nextBlockSimiliar(1, 9, Directions.UP));
    }
    
    @Test
    public void nextBlockSimiliarWorksDownRight() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(6, 6, testBlock2);
        assertTrue(scanLogic.nextBlockSimiliar(5, 5, Directions.DOWNRIGHT));
    }
 
    @Test
    public void nextBlockSimiliarDifferentBlockWorksDownRight() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(6, 6, testBlock2);
        assertTrue(!scanLogic.nextBlockSimiliar(5, 5, Directions.DOWNRIGHT));
    }
    
    @Test
    public void nextBlockSimiliarWorksDownLeft() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(4, 6, testBlock2);
        assertTrue(scanLogic.nextBlockSimiliar(5, 5, Directions.DOWNLEFT));
    }
 
    @Test
    public void nextBlockSimiliarDifferentBlockWorksDownLeft() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock = new Block(Color.BLUE);
        Block testBlock2 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock);
        testArea.setBlock(4, 6, testBlock2);
        assertTrue(!scanLogic.nextBlockSimiliar(5, 5, Directions.DOWNLEFT));
    }
    
    @Test
    public void horizontalScanStreakWorksThree() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 8, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(5, 8, testBlock3);
        scanLogic.horizontalScan();
        int amount = scanLogic.getCollected().size();
        assertEquals(3, amount);
    }
    
    @Test
    public void horizontalScanStreakWorksFour() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 8, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(5, 8, testBlock3);
        testArea.setBlock(6, 8, testBlock4);
        scanLogic.horizontalScan();
        int amount = scanLogic.getCollected().size();
        assertEquals(4, amount);
    }
    
    @Test
    public void verticalScanStreakWorksThree() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 7, testBlock1);
        testArea.setBlock(3, 8, testBlock2);
        testArea.setBlock(3, 9, testBlock3);
        scanLogic.verticalScan();
        int amount = scanLogic.getCollected().size();
        assertEquals(3, amount);
    }
    
    @Test
    public void verticallScanStreakWorksFour() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 6, testBlock1);
        testArea.setBlock(3, 7, testBlock2);
        testArea.setBlock(3, 8, testBlock3);
        testArea.setBlock(3, 9, testBlock4);
        scanLogic.verticalScan();
        int amount = scanLogic.getCollected().size();
        assertEquals(4, amount);
    }
    
    @Test
    public void diagonalScanDownRightStreakWorksThree() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(3, 3, testBlock1);
        testArea.setBlock(4, 4, testBlock2);
        testArea.setBlock(5, 5, testBlock3);
        scanLogic.diagonalScanDownRight(0, 0);
        int amount = scanLogic.getCollected().size();
        assertEquals(3, amount);
    }
    
    @Test
    public void diagonalScanDownRightStreakWorksFour() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(3, 3, testBlock1);
        testArea.setBlock(4, 4, testBlock2);
        testArea.setBlock(5, 5, testBlock3);
        testArea.setBlock(6, 6, testBlock4);
        scanLogic.diagonalScanDownRight(0, 0);
        int amount = scanLogic.getCollected().size();
        assertEquals(4, amount);
    }    
    
    @Test
    public void diagonalScanDownLeftStreakWorksThree() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock1);
        testArea.setBlock(4, 6, testBlock2);
        testArea.setBlock(3, 7, testBlock3);
        scanLogic.diagonalScanDownLeft(testArea.getAreaEdgeX() - 1, 0);
        int amount = scanLogic.getCollected().size();
        assertEquals(3, amount);
    }
    
    @Test
    public void diagonalScanDownLeftStreakWorksFour() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        testArea.setBlock(5, 5, testBlock1);
        testArea.setBlock(4, 6, testBlock2);
        testArea.setBlock(3, 7, testBlock3);
        testArea.setBlock(2, 8, testBlock4);
        scanLogic.diagonalScanDownLeft(testArea.getAreaEdgeX() - 1 , 0);
        int amount = scanLogic.getCollected().size();
        assertEquals(4, amount);
    }
    
    @Test
    public void combinationScanCorrectVariation1() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        Block testBlock5 = new Block(Color.RED);
        Block testBlock6 = new Block(Color.RED);
        testArea.setBlock(0, 9, testBlock1);
        testArea.setBlock(1, 9, testBlock2);
        testArea.setBlock(2, 9, testBlock3);
        testArea.setBlock(2, 8, testBlock4);
        testArea.setBlock(2, 7, testBlock5);
        testArea.setBlock(2, 6, testBlock6);
        scanLogic.scanStreaks();
        int amount = scanLogic.getCollected().size();
        assertEquals(7, amount);
    }
    
        @Test
        public void combinationScanCorrectVariation2() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        ScanLogic scanLogic = new ScanLogic(testArea);
        Block testBlock1 = new Block(Color.RED);
        Block testBlock2 = new Block(Color.RED);
        Block testBlock3 = new Block(Color.RED);
        Block testBlock4 = new Block(Color.RED);
        Block testBlock5 = new Block(Color.RED);
        testArea.setBlock(3, 7, testBlock1);
        testArea.setBlock(4, 8, testBlock2);
        testArea.setBlock(5, 9, testBlock3);
        testArea.setBlock(6, 8, testBlock4);
        testArea.setBlock(7, 7, testBlock5);
        scanLogic.scanStreaks();
        int amount = scanLogic.getCollected().size();
        assertEquals(6, amount);
    }
    
    
}
