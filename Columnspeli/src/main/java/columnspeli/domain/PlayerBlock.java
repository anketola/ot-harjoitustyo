package columnspeli.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

public class PlayerBlock {
    
    private int gridX;
    private int gridY;
    private Block topBlock;
    private Block middleBlock;
    private Block bottomBlock;
    
    public PlayerBlock(int x, int y) {
        this.gridY = y;
        this.gridX = x;
        this.topBlock = new Block();
        this.topBlock.randomizeBlock();
        this.middleBlock = new Block();
        this.middleBlock.randomizeBlock();
        this.bottomBlock = new Block();
        this.bottomBlock.randomizeBlock();
    }
    
    public void shuffleBlocks() {
        Block shuffleHelper = topBlock;
        topBlock = middleBlock;
        middleBlock = bottomBlock;
        bottomBlock = shuffleHelper;
    }
    
    public void respawn(ArrayList<Integer> possibleRespawns) {
        Random rand = new Random();
        int i = possibleRespawns.get(rand.nextInt(possibleRespawns.size()));
        //System.out.println("Respawns open: " + possibleRespawns);
        //System.out.println("Respawning to: " + i);
        this.gridX = i;
        newBlocks();
        setGridY(0);
    }
    
    
    public void moveDown() {
        this.gridY++;
    }
    
    public void moveX(String direction) {
        if (direction.equals("right")) {        
            gridX++;
        } else if (direction.equals("left")) {
            this.gridX--; 
        }
    }
    
    public void speedPush() {
        this.gridY++;
    }
    
    public int getGridX() {
        return this.gridX;
    }
    
    public int getGridY() {
        return this.gridY;
    }
    
    public void setGridY(int newGridY) {
        this.gridY = newGridY;
    }
    
    public void setGridX(int newGridX) {
        this.gridX = newGridX;
    }
    
    public Block getTopBlock() {
        return topBlock;
    }
    
    public Block getMiddleBlock() {
        return middleBlock;
    }
    
    public Block getBottomBlock() {
        return bottomBlock;
    }
    
    public void newBlocks() {
        topBlock = new Block();
        topBlock.randomizeBlock();
        middleBlock = new Block();
        middleBlock.randomizeBlock();
        bottomBlock = new Block();
        bottomBlock.randomizeBlock();
    }
    
}
