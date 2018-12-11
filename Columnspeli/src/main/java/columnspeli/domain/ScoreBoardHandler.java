package columnspeli.domain;

import columnspeli.dao.ScoreEntryDao;
import java.util.ArrayList;

public class ScoreBoardHandler {
    
    private ScoreEntryDao scoreDao;
    
    public ScoreBoardHandler(ScoreEntryDao scoreDao) {
    this.scoreDao = scoreDao;
    }
    
    public ArrayList<ScoreEntry> giveTopTenPlayers() throws Exception {
        ArrayList<ScoreEntry> topTenPlayers = scoreDao.findAll();
        if (topTenPlayers.size() < 10) {
            while (topTenPlayers.size() < 10)
                topTenPlayers.add(new ScoreEntry("Empty", 0));
        }
        return topTenPlayers;
    }
    
    public boolean isEglibleForScoreList(int score) {
        return true;
    }
      
}
