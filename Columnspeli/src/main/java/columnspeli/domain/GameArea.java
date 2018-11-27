package columnspeli.domain;

import java.util.ArrayList;

public class GameArea {
    
    private Block[][] areaBlocks;
    private PlayerBlock playerBlock;
    private GameStatistics gameStatistics;
    
    public GameArea(int x, int y) {
        this.areaBlocks = new Block[x][y];
        this.gameStatistics = new GameStatistics();
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
            int tempX = playerBlock.getGridX(); // temp, fix
            int tempY = playerBlock.getGridY();
            
            releaseBlocks();
            scanAndErase(tempX, tempY);
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
    
    public void scanAndErase(int releaseX, int releaseY) {
        // TO DO later: scan for acceptable color streaks + erase them
        // now a temporary (and bad copy-paste) checker that checks only the nearby
        // blocks of the released blocks
        
        // horizontal
        
        String scanColor;
        for (int i = 0; i < 3; i++) {
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX - 1, releaseY + 2  - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX - 2, releaseY + 2 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX - 1, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX - 2, releaseY + 2 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX - 1, releaseY + 2 - i);
                    dropAbove(releaseX - 2, releaseY + 2 - i);
                    gameStatistics.addScore(1);
                    return;
                
                }
            }
        
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX - 1, releaseY + 2 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX + 1, releaseY + 2 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX - 1, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX + 1, releaseY + 2 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX - 1, releaseY + 2 - i);
                    dropAbove(releaseX + 1, releaseY + 2 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }
        
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX + 1, releaseY + 2 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX + 2, releaseY + 2 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX + 1, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX + 2, releaseY + 2 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX + 1, releaseY + 2 - i);
                    dropAbove(releaseX + 2, releaseY + 2 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }
        }
        
        // vertical
        
        for (int i = 0; i < 3; i++) {
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX, releaseY + 1 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX, releaseY - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX, releaseY + 1 - i).demolishBlock();
                    getBlock(releaseX, releaseY - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX, releaseY + 1 - i);
                    dropAbove(releaseX, releaseY - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }
            
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX, releaseY + 1 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX, releaseY + 3 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX, releaseY + 1 - i).demolishBlock();
                    getBlock(releaseX, releaseY + 3 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX, releaseY + 1 - i);
                    dropAbove(releaseX, releaseY + 3 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }   
        
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX, releaseY + 3 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX, releaseY + 4 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX, releaseY + 3 - i).demolishBlock();
                    getBlock(releaseX, releaseY + 4 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX, releaseY + 3 - i);
                    dropAbove(releaseX, releaseY + 4 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }  
           
        }
        
        // diagonal
        
        for (int i = 0; i < 3; i++) {
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX - 1, releaseY + 1 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX + 1, releaseY + 3 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX - 1, releaseY + 1 - i).demolishBlock();
                    getBlock(releaseX + 1, releaseY + 3 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX - 1, releaseY + 1 - i);
                    dropAbove(releaseX + 1, releaseY + 3 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }
            
            scanColor = getBlock(releaseX, releaseY + 2 - i).getColor();
            if (getBlock(releaseX - 1, releaseY + 1 - i).getColor().equals(scanColor)) {
                if (getBlock(releaseX + 1, releaseY + 3 - i).getColor().equals(scanColor)) {
                
                    getBlock(releaseX, releaseY + 2 - i).demolishBlock();
                    getBlock(releaseX + 1, releaseY + 1 - i).demolishBlock();
                    getBlock(releaseX - 1, releaseY + 3 - i).demolishBlock();
                    dropAbove(releaseX, releaseY + 2 - i);
                    dropAbove(releaseX + 1, releaseY + 1 - i);
                    dropAbove(releaseX - 1, releaseY + 3 - i);
                    gameStatistics.addScore(1);
                    return;
                }
            }
           
        }
        
    }
    
    public void dropAbove(int dropX, int dropY) {
        // TO DO - temporary
        while (dropY > 1) {
            getBlock(dropX, dropY).setColor(getBlock(dropX, dropY - 1).getColor());
            dropY--;
        }
        
    }
    
    public void releaseBlocks() {
        setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
        playerBlock.newBlocks();
        playerBlock.setY(0);
        playerBlock.setGridY(0);
    }
    
    public boolean gameOver() {
        ArrayList respawns = eglibleRespawn();
        if (respawns.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public ArrayList<Integer> eglibleRespawn() {
        ArrayList<Integer> possibleRespawnX = new ArrayList<>();
        int i = 0;
        while (i < getAreaEdgeX()) {
            if (!hasBlock(i, 0) && !hasBlock(i, 1) && !hasBlock(i, 2)) {
                possibleRespawnX.add(i);
            }
            i++;    
        }
        return possibleRespawnX;
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
        if ((x >= getAreaEdgeX()) || (x < 0) || (y < 0) || (y >= getAreaEdgeY())) {
            Block outOfArea = new Block();
            outOfArea.setColor("Out of Bounds");
            return outOfArea;
        }
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
    
    public GameStatistics getStatistics() {
        return gameStatistics;
    }
    
}
