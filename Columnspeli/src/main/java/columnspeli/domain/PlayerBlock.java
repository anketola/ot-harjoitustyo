package columnspeli.domain;

import columnspeli.ui.ColumnsUi;

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
