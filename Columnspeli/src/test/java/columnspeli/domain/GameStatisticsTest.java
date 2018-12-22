package columnspeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;

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
    
    @Test
    public void setSpeedReturnsCorrectWithGetSpeed() {
        GameStatistics testStatistics = new GameStatistics();
        testStatistics.setSpeed(5);
        assertEquals(5, testStatistics.getSpeed());
    }
    
    @Test
    public void setScoreReturnsCorrectWithGetSpeed() {
        GameStatistics testStatistics = new GameStatistics();
        testStatistics.setScore(5000);
        assertEquals(5000, testStatistics.getScore());
    }
    
    @Test
    public void setShrinkSpeedReturnsCorrectWithGetShrinkSpeed() {
        GameStatistics testStatistics = new GameStatistics();
        testStatistics.setShrinkSpeed(50);
        assertEquals(50, testStatistics.getShrinkSpeed());
    }
    
    @Test
    public void timePassesAfterStartTimer() {
        GameStatistics testStatistics = new GameStatistics();
        testStatistics.startTimer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        }
        assertTrue(testStatistics.getElapsedTime() > 0);
        
    }
    
    
}
