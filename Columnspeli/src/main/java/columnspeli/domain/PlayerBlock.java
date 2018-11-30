package columnspeli.domain;

import columnspeli.ui.ColumnsUi;
import java.util.ArrayList;
import java.util.Random;

public class PlayerBlock {
    
    private int x;
    private int y;
    private int gridX;
    private int gridY;
    private Block topBlock;
    private Block middleBlock;
    private Block bottomBlock;
    
    public PlayerBlock(int x, int y) {
        this.x = x * ColumnsUi.BLOCK_SIZE;
        this.y = y * ColumnsUi.BLOCK_SIZE;
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
        this.x = i * ColumnsUi.BLOCK_SIZE;
        newBlocks();
        setY(0);
        setGridY(0);
    }
    
    
    public void moveDown() {
        this.y = y + ColumnsUi.BLOCK_SIZE;
        this.gridY++;
    }
    
    public void moveX(int changeX) {
        if ((this.x + changeX) < 0 || ((this.x + changeX) >= ColumnsUi.GAME_FIELD_WIDTH)) {
            return;
        }
        this.x = x + changeX;
        if (changeX > 0) {        
            gridX++;
        } else {
            this.gridX--; 
        }
    }
    
    public void speedPush() {
        this.y = y + ColumnsUi.BLOCK_SIZE;
        this.gridY++;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
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
