package columnspeli.domain;

import columnspeli.dao.ScoreEntryDao;
import java.util.ArrayList;

public class ScoreBoardHandler {
    
    /**
     * Piste-ennätysten hallintaan UI:n ja DAO:n välissä tarkoitettu luokka
     */
    
    private ScoreEntryDao scoreDao;
    
    /**
     * Konstruktori pistekäsittelijälle.
     * @param scoreDao ottaa parametriksi DAO abstraktion toteuttavan ScoreEntryDao:n tietokantayhteyttä varten.
     */
    
    public ScoreBoardHandler(ScoreEntryDao scoreDao) {
        this.scoreDao = scoreDao;
    }
    
    /**
     * Metodi etsii tietokannasta tuloksia. Jos tuloksia puuttuu, ne täytetään tyhjillä.
     * @return palauttaa kymmenen parasta tulosta,
     */
    
    public ArrayList<ScoreEntry> giveTopTenPlayers() throws Exception {
        ArrayList<ScoreEntry> topTenPlayers = scoreDao.findAll();
        if (topTenPlayers.size() < 10) {
            while (topTenPlayers.size() < 10) {
                topTenPlayers.add(new ScoreEntry("Empty", 0));
            }
        }
        return topTenPlayers;
    }
    
    /**
     * Käyttölittymää avustava ja muokkaava luokka, joka tunnistaa, onko käyttäjä oikeutettu nimen syöttämiseen
     * @param score Käyttäjän saavuttama pistemäärä.
     * @return palauttaa True, jos käyttäjälle avataan mahdollisuus nimen syöttämiseen.
     *  
     */
    
    public boolean isEglibleForScoreList(int score) throws Exception {
        ArrayList<ScoreEntry> currentScores = giveTopTenPlayers();
        for (int i = 0; i < currentScores.size(); i++) {
            if (score > currentScores.get(i).getScore()) {
                return true;
            }
        }
        return false;
    }
    

    /**
     * Metodi tallentaa ennätyksen tietokantaan
     * @param newName käyttäjän nimi tai nimimerkki
     * @param newScore käyttäjän pistemäärä
     * 
     */
    
    public void saveScoreEntry(String newName, int newScore) throws Exception {
        ScoreEntry scoreEntry = new ScoreEntry(newName, newScore);
        scoreDao.save(scoreEntry);
    }
      
}
