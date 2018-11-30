package columnspeli.domain;

public class GameStatistics {

    private int score;
   
    public GameStatistics() {
        this.score = 0;
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
    
    public int getElapsedTime() {
        // TO DO
        return 0;
    }
    
}
