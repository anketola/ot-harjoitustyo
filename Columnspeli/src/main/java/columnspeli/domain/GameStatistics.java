package columnspeli.domain;

public class GameStatistics {

    private int score;

    public GameStatistics() {
        this.score = 0;
    }
  
    public void addScore(int increase) {
        this.score = score + increase;
    }
    
    public int getScore() {
        return this.score;
    }
}
