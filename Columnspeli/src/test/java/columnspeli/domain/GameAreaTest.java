package columnspeli.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameAreaTest {
    
    @Test
    public void cratedGameAreaIsEmpty () {
        GameArea testArea = new GameArea(10, 10);
        assertEquals(false, testArea.hasBlock(5, 5));
    }
}