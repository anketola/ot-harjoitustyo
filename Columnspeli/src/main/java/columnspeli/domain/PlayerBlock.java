package columnspeli.domain;

public class PlayerBlock {
    
    private int x;
    private int y;
    private Block topBlock;
    private Block middleBlock;
    private Block bottomBlock;
    
    public PlayerBlock(int x, int y, Block topBlock, Block middleBlock, Block bottomBlock) {
        this.x = x;
        this.y = y;
        this.topBlock = topBlock;
        this.middleBlock = middleBlock;
        this.bottomBlock = bottomBlock;
    }
    
    public void shuffleBlocks () {
        Block shuffleHelper = topBlock;
        topBlock = middleBlock;
        middleBlock = bottomBlock;
        bottomBlock = shuffleHelper;
    }
    
    public void moveDown () {
        this.y = y + 1;
    }
    
    public void moveX(int changeX) {
        this.x = x + changeX;
    }
    
    public void speedPush() {
        this.y = y + 5;
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
    
    public Block getTopBlock() {
        return topBlock;
    }
    
    public Block getMiddleBlock() {
        return middleBlock;
    }
    
    public Block getBottomBlock() {
        return bottomBlock;
    }
    
    
    
}
