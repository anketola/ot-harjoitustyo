package columnspeli.domain;

public class GameArea {
    
    private Block[][] areaBlocks;
    private PlayerBlock playerBlock;
    
    public GameArea(int x, int y) {
        this.areaBlocks = new Block[x][y];
        this.playerBlock = new PlayerBlock(5, 0); // temp
        int y2 = 0;
        while (y2 < areaBlocks.length) {
            int x2 = 0;
            while (x2 < areaBlocks[y2].length) {
                areaBlocks[y2][x2] = new Block("empty");
                x2++;
            }
            y2++;    
        }
    }
    
    public void setBlock(int x, int y, Block block) {
        areaBlocks[x][y] = block;
    }
    
    public void movePlayer(String direction) {
        if (direction.equals("left") && (!isCollisionLeft())) {
            playerBlock.moveX(-20);
        }
        if (direction.equals("right") && (!isCollisionRight())) {
            playerBlock.moveX(20);
        }
        if (direction.equals("down") && (!isCollisionDown())) {
            playerBlock.moveDown();
        } else if (direction.equals("down") && (isCollisionDown())) {
            releaseAndReset();
        }
                
    }
    
    public boolean isCollisionLeft() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (blockX == 0) {
            return true;
        }
        if ((hasBlock(blockX - 1, topBlockY)) || (hasBlock(blockX - 1, topBlockY + 1)) || (hasBlock(blockX - 1, topBlockY + 2))) {
            return true;
        }
        return false;
    }
    
    public boolean isCollisionRight() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (blockX == getAreaEdgeX() - 1) {
            return true;
        }
        if ((hasBlock(blockX + 1, topBlockY)) || (hasBlock(blockX + 1, topBlockY + 1)) || (hasBlock(blockX + 1, topBlockY + 2))) {
            return true;
        }
        return false;
    }
    
    public boolean isCollisionDown() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (hasBlock(blockX, topBlockY + 3)) {
            return true;
        }
        return false;
    }
    
    public void releaseAndReset() {
        setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
        playerBlock.newBlocks();
        playerBlock.setY(0);
        playerBlock.setGridY(0);
        
        
    }
    
    public boolean hasBlock(int x, int y) {
        if (y < getAreaEdgeY()) {
            if (this.areaBlocks[x][y].getColor().equals("empty")) {
                return false;
            }
        }
        return true;        
    }
    
    public Block getBlock(int x, int y) {
        return this.areaBlocks[x][y];
    }
    
    public int getAreaEdgeX() {
        return this.areaBlocks.length;
    }
    
    public int getAreaEdgeY() {
        return this.areaBlocks[0].length;
    }
 
    public PlayerBlock getPlayerBlock() {
        return playerBlock;
    }
    
}
