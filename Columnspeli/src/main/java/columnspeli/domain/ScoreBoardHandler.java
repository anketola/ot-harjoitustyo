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
    
    public boolean isEglibleForScoreList(int score) throws Exception {
        ArrayList<ScoreEntry> currentScores = giveTopTenPlayers();
        if (score > currentScores.get(currentScores.size()).getScore()) {
            return true;
        }
        return false;
    }
    
    public int findNewPlaceOnScoreList(int score) throws Exception {
        ArrayList<ScoreEntry> topTenPlayers = scoreDao.findAll();
        int i = 0;
        while (score > topTenPlayers.get(i).getScore()) {
            System.out.println("Place on list:" + i);
        }
        
        return i;
    }
    
    public void saveScoreEntry(String newName, int newScore) throws Exception {
        ScoreEntry scoreEntry = new ScoreEntry(newName, newScore);
        scoreDao.save(scoreEntry);
    }
      
}
