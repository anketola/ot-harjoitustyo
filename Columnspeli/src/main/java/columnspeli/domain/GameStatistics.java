package columnspeli.domain;

public class GameStatistics {

    private int score;
    private int speed;
    
    public GameStatistics() {
        this.score = 0;
        this.speed = 0;
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
    
    public int getElapsedTime() {
        // TO DO
        return 0;
    }
    
}
