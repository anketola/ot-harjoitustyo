package columnspeli.domain;

/**
 * Sovelluksen pisteistä ja nopeustiloista kirjaa pitävä luokka.
 * 
 */

public class GameStatistics {

    private int score;
    private int speed;
    private int shrinkSpeed;
    long startTime;
    
    public GameStatistics() {
        this.score = 0;
        this.speed = 0;
        this.shrinkSpeed = 30;
    }
    
    public void setShrinkSpeed(int newShrinkSpeed) {
        this.shrinkSpeed = newShrinkSpeed;
    }
    
    public int getShrinkSpeed() {
        return this.shrinkSpeed;
    }
  
    public void addScore(int increase) {
        this.score = score + increase;
    }
    
    public void setScore(int newScore) {
        this.score = newScore;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    public long getElapsedTime() {
        long timeElapsed = System.currentTimeMillis() - this.startTime;
        return timeElapsed / 1000;
    }
    
}
