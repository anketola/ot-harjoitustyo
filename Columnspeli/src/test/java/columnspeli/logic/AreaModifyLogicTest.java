package columnspeli.logic;

import columnspeli.domain.GameBlockArea;
import columnspeli.domain.PlayerBlock;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import javafx.scene.paint.Color;
import columnspeli.domain.Block;
import org.junit.Test;

public class AreaModifyLogicTest {
    
    @Test
    public void blocksAreReleased() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(5, 5);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       modifyTest.releaseBlocks();
       assertTrue(testArea.hasBlock(5, 5));
    }
    
    @Test
    public void fillIndesturctibleIsGray() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(5, 5);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       modifyTest.fillIndestructible(1);
       assertEquals(Color.GRAY, testArea.getBlock(5, 9).getColor());
    }
    
    @Test
    public void fillIndesturctibleCorrectHeighGrayt() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(0, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       modifyTest.fillIndestructible(5);
       assertEquals(Color.GRAY, testArea.getBlock(5, 4).getColor());
    }
    
    @Test
    public void fillIndesturctibleCorrectHeightEmpty() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(0, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       modifyTest.fillIndestructible(5);
       assertEquals(Color.BLACK, testArea.getBlock(5, 3).getColor());
    }
    
    @Test
    public void dropAboveMovesBlocksDown() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(5, 5);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       Block testBlock1 = new Block(Color.RED);
       Block testBlock2 = new Block(Color.BLUE);
       testArea.setBlock(3, 8, testBlock1);
       testArea.setBlock(3, 7, testBlock2);
       modifyTest.dropAbove(3, 10);
       assertEquals(Color.RED, testArea.getBlock(3, 9).getColor());
    }
    
    @Test
    public void dropAboveAtTopEmptyBlock() {
       GameBlockArea testArea = new GameBlockArea(10, 4);
       PlayerBlock playerBlock = new PlayerBlock(4, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       Block testBlock1 = new Block(Color.RED);
       Block testBlock2 = new Block(Color.BLUE);
       Block testBlock3 = new Block(Color.YELLOW);
       testArea.setBlock(0, 1, testBlock1);
       testArea.setBlock(0, 2, testBlock2);
       testArea.setBlock(0, 3, testBlock2);
       modifyTest.dropAbove(0, 0);
       assertEquals(Color.BLACK, testArea.getBlock(0, 0).getColor());
    }
    
    @Test
    public void highestBlocksCorrectValue() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(0, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       Block testBlock1 = new Block(Color.RED);
       Block testBlock2 = new Block(Color.BLUE);
       Block testBlock3 = new Block(Color.YELLOW);
       testArea.setBlock(3, 9, testBlock1);
       testArea.setBlock(3, 8, testBlock2);
       testArea.setBlock(3, 7, testBlock3);
       int highest = modifyTest.findHighestBlock().get(3);
       assertEquals(7, highest);
    }
    
    @Test
    public void scanAndDropCorrectValuesColor() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(0, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       Block testBlock1 = new Block(Color.RED);
       Block testBlock2 = new Block(Color.BLUE);
       Block testBlock3 = new Block(Color.YELLOW);
       testArea.setBlock(5, 9, testBlock1);
       testArea.setBlock(5, 7, testBlock2);
       testArea.setBlock(5, 6, testBlock3);
       modifyTest.scanAndDrop();
       assertEquals(Color.BLUE, testArea.getBlock(5, 8).getColor());
    }
    
    @Test
    public void scanAndDropCorrectValuesEmpty() {
       GameBlockArea testArea = new GameBlockArea(10, 10);
       PlayerBlock playerBlock = new PlayerBlock(0, 0);
       AreaModifyLogic modifyTest = new AreaModifyLogic(testArea, playerBlock);
       Block testBlock1 = new Block(Color.RED);
       Block testBlock2 = new Block(Color.BLUE);
       Block testBlock3 = new Block(Color.YELLOW);
       testArea.setBlock(5, 9, testBlock1);
       testArea.setBlock(5, 7, testBlock2);
       testArea.setBlock(5, 6, testBlock3);
       modifyTest.scanAndDrop();
       assertEquals(Color.BLACK, testArea.getBlock(5, 6).getColor());
    }
    
}
