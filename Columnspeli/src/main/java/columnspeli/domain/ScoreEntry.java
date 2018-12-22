package columnspeli.domain;

/**
 * Nimen ja pisteen yhdistelmän sisältävä luokka pistetuloksia varten.
 * 
 */

public class ScoreEntry {
    
    private String name;
    private int score;
    
    /**
     * Konstruktori, joka asettaa nimen ja pisteet.
     * @param name Käyttäjän nimi.
     * @param score Käyttäjän pisteet.
     */
    
    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getScore() {
        return this.score;
    }
    
}
