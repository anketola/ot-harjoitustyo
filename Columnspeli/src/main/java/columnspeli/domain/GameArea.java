package columnspeli.domain;

public class GameArea {
    
    private Block[][] areaBlocks;
    private PlayerBlock playerBlock;
    
    public GameArea(int x, int y) {
        this.areaBlocks = new Block[x][y];
        this.playerBlock = new PlayerBlock(60, 0, new Block("yellow"), new Block("red"), new Block("blue")); // temp
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
    
    public boolean hasBlock(int x, int y) {
        if (this.areaBlocks[x][y].getColor().equals("empty")) {
            return false;
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
