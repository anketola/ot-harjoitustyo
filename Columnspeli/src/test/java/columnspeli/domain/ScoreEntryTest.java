package columnspeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreEntryTest {
   
    @Test
    public void constuctorNameIsReturnedCorrectly() {
        ScoreEntry testScoreEntry = new ScoreEntry("Billy", 1234);
        assertEquals("Billy", testScoreEntry.getName());
    }
    
    @Test
    public void constuctorScoreIsReturnedCorrectly() {
        ScoreEntry testScoreEntry = new ScoreEntry("Billy", 1234);
        assertEquals(1234, testScoreEntry.getScore());
    }
    
    
}
