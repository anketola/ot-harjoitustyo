package columnspeli.logic;

import columnspeli.domain.GameBlockArea;
import columnspeli.domain.PlayerBlock;
import static org.junit.Assert.assertTrue;
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
    
}
