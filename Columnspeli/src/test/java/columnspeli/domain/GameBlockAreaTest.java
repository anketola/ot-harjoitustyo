package columnspeli.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameBlockAreaTest {
    
    
    @Test
    public void cratedGameAreaIsEmpty() {
        GameBlockArea testArea = new GameBlockArea(10, 10);
        assertEquals(false, testArea.hasBlock(5, 5));
    }
    
    @Test
    public void getAreaEdgeXReturnsCorrectValue() {
        GameBlockArea testArea = new GameBlockArea(20, 30);
        assertEquals(20, testArea.getAreaEdgeX());
    }
    
    @Test
    public void getAreaEdgeYReturnsCorrectValue() {
        GameBlockArea testArea = new GameBlockArea(20, 30);
        assertEquals(30, testArea.getAreaEdgeY());
    }
    
}
