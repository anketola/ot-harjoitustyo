package columnspeli.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hotaru
 */
public class GameStatisticsTest {
    
    @Test
    public void constructorSetsToZeroScore() {
        GameStatistics testStatistics = new GameStatistics();
        assertEquals(0, testStatistics.getScore());
    }
    
    @Test
    public void scoreIsAddedProperly() {
        GameStatistics testStatistics = new GameStatistics();
        testStatistics.addScore(100);
        assertEquals(100, testStatistics.getScore());
    }
    
}
