package columnspeli.domain;

import columnspeli.dao.ScoreEntryDao;
import java.util.ArrayList;

public class ScoreBoardHandler {
    
    /**
     * Piste-ennätysten hallintaan UI:n ja DAO:n välissä tarkoitettu luokka
     */
    
    private ScoreEntryDao scoreDao;
    
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
        if (score > currentScores.get(currentScores.size()).getScore()) {
            return true;
        }
        return false;
    }
    
    /**
     * Käyttöliittymän pisteennäyttöä avustava luokka, joka tunnistaa oikean kohdan uudelle piste-ennätykselle
     * @param score käyttäjän pisteet
     * @return sijoitus listalla
     */
    
    public int findNewPlaceOnScoreList(int score) throws Exception {
        ArrayList<ScoreEntry> topTenPlayers = scoreDao.findAll();
        int i = 0;
        while (score > topTenPlayers.get(i).getScore()) {
            System.out.println("Place on list:" + i);
        }
        
        return i;
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
