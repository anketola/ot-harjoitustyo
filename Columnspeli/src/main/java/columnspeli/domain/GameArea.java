package columnspeli.domain;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class GameArea {
    
    private Block[][] areaBlocks;
    private PlayerBlock playerBlock;
    private GameStatistics gameStatistics;
    private ArrayList<Block> demolishCollect = new ArrayList<>();
    boolean gameActive;
    boolean gamePaused;
    
    public GameArea(int x, int y) {
        this.areaBlocks = new Block[x][y];
        generateEmptyArea();
        this.gameStatistics = new GameStatistics();
        this.gamePaused = false;
        this.playerBlock = new PlayerBlock(5, 0); // temp
        this.gameActive = false;
    }
    
    public void generateEmptyArea() {
        int y2 = 0;
        while (y2 < areaBlocks.length) {
            int x2 = 0;
            while (x2 < areaBlocks[y2].length) {
                areaBlocks[y2][x2] = new Block(Color.BLACK);
                x2++;
            }
            y2++;    
        }
    }
    
    public void resetState() {
        this.gameStatistics.setScore(0);
        generateEmptyArea();
        playerBlock.respawn(eglibleRespawn());
    }
    
    
    public void setBlock(int x, int y, Block block) {
        areaBlocks[x][y] = block;
    }
    
    public void movePlayer(String direction) {
        if (direction.equals("left") && (!isCollisionLeft())) {
            playerBlock.moveX(direction);
        }
        if (direction.equals("right") && (!isCollisionRight())) {
            playerBlock.moveX(direction);
        }
        if (direction.equals("down") && (!isCollisionDown())) {
            playerBlock.moveDown();
        } else if (direction.equals("down") && (isCollisionDown())) {
            releaseBlocks();
            seekBlockStreaks();    
            if (!gameOver()) {
                playerBlock.respawn(eglibleRespawn());
            }
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
    
    public void seekBlockStreaks() {
        for (int i = 0; i < 5; i++) {   
        // TEMPORARY LOOP - loops five times as I haven't had time to implement
        // everything fully, now it just provides more functionality
        // this is for the cases when clear and drop gives new streaks
        // going to fix later
            horizontalScan();
            verticalScan();
            diagonalScanDownRight();
            diagonalScanDownLeft();
            clearCollected();
            scanAndDrop();
        }
    }
    
    public boolean nextBlockSimiliar(int compX, int compY, String direction) {
        if (direction.equals("right")) {
            if (getBlock(compX, compY).getColor() == getBlock(compX + 1, compY).getColor()) {
                return true;
            }
        } else if (direction.equals("up")) {
            if (getBlock(compX, compY).getColor() == getBlock(compX, compY - 1).getColor()) {
                return true;
            }
        } else if (direction.equals("downright")) {
            if (getBlock(compX, compY).getColor() == getBlock(compX + 1, compY + 1).getColor()) {
                return true;
            }
        } else if (direction.equals("downleft")) {
            if (getBlock(compX, compY).getColor() == getBlock(compX - 1, compY + 1).getColor()) {
                return true;
            }
        }
        return false;
    }
    
    public void horizontalScan() {
        int streakCount = 1;
        for (int scanY = getAreaEdgeY() - 1; scanY > 0; scanY--) {
            for (int scanX = 0; scanX < getAreaEdgeX(); scanX++) {
                if (hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, "right")) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                collect(scanX - i, scanY);
                            }
                        }
                        streakCount = 1;
                    }
                }
            }
        }
    }
        
    public void verticalScan() {
        int streakCount = 1;
        for (int scanX = 0; scanX < getAreaEdgeX(); scanX++) {
            for (int scanY = getAreaEdgeY() - 1; scanY > 0; scanY--) {
                if (hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, "up")) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                collect(scanX, scanY + i);
                            }
                        }
                        streakCount = 1;
                    }   
                
                }
            }
        }
    }
    
    public void diagonalScanDownRight() {
        int streakCount = 1;
        int scanStartY = 0;
        int scanStartX = 0;
        int scanY;
        int scanX;
        while (scanStartY < getAreaEdgeY()) {
            scanY = scanStartY;
            scanX = scanStartX;
            while ((scanX < getAreaEdgeX()) && (scanY < getAreaEdgeY())) {
                if (hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, "downright")) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                System.out.println("Deleting" + (scanX - i) + " , " + (scanY - i));
                                collect(scanX - i, scanY - i);
                            }   
                        }
                        streakCount = 1;
                    }
                }    
                scanX++;
                scanY++;
            }
            scanStartY++;
        }
    }
    
    public void diagonalScanDownLeft() {
        int streakCount = 1;
        int scanStartY = 0;
        int scanStartX = getAreaEdgeX() - 1;
        int scanY;
        int scanX;
        while (scanStartY < getAreaEdgeY()) {
            scanY = scanStartY;
            scanX = scanStartX;
            while ((scanX >= 0) && (scanY < getAreaEdgeY())) {
                if (hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, "downleft")) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                System.out.println("Deleting" + (scanX + i) + " , " + (scanY - i));
                                collect(scanX + i, scanY - i);
                            }   
                        }
                        streakCount = 1;
                    }
                }    
                scanX--;
                scanY++;
            }
            scanStartY++;
        }
    }
   
    public void collect(int x, int y) {
        demolishCollect.add(getBlock(x , y));
        //System.out.println("Collected: " + x + " , " + y);
        //System.out.println(demolishCollect);
    }
    
    public int clearCollected() {
        gameStatistics.addScore(demolishCollect.size());
        int amount = demolishCollect.size();
        for (int i = 0; i < demolishCollect.size(); i++) {
            demolishCollect.get(i).demolishBlock();
        }
        demolishCollect.clear();
        return amount;
    }
    
    public void scanAndDrop() {
        ArrayList<Integer> topLine = findHighestBlock();
        for (int scanX = 0; scanX < getAreaEdgeX(); scanX++) {
            int scanY = topLine.get(scanX);
            while (scanY < getAreaEdgeY()) {
                if (!hasBlock(scanX, scanY)) {
                    dropAbove(scanX, scanY);
                    
                }
                scanY++;        
            }
        }
    }
    
    public ArrayList<Integer> findHighestBlock() {
        ArrayList<Integer> highestBlocks = new ArrayList<>();
        int scanY = 0;
        for (int scanX = 0; scanX < getAreaEdgeX(); scanX++) {
            while (!hasBlock(scanX, scanY)) {
                scanY++;
            }
            highestBlocks.add(scanY);
            scanY = 0;
        }
        return highestBlocks;
    }
    
    
    public void dropAbove(int dropX, int dropY) {
        while (dropY > 0) {
            getBlock(dropX, dropY).setColor(getBlock(dropX, dropY - 1).getColor());
            dropY--;
        }
        
    }
    
    public void releaseBlocks() {
        setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
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
            if ((!hasBlock(i, 0)) && (!hasBlock(i, 1)) && (!hasBlock(i, 2))) {
                possibleRespawnX.add(i);
            }
            i++;    
        }
        return possibleRespawnX;
    }
    
    
    public boolean hasBlock(int x, int y) {
        if (y < getAreaEdgeY()) {
            if (this.areaBlocks[x][y].getColor() == Color.BLACK) {
                return false;
            }
        }
        return true;        
    }
    
    public Block getBlock(int x, int y) {
        if ((x >= getAreaEdgeX()) || (x < 0) || (y < 0) || (y >= getAreaEdgeY())) {
            Block outOfArea = new Block();
            outOfArea.setColor(Color.BLACK);
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
    
    public void activateGame() {
        this.gameActive = true;
        gameStatistics.startTimer();
    }
    
    public void closeGame() {
        this.gameActive = false;
    }
    
    public boolean gameActive() {
        return this.gameActive;
    }
    
    public void pausePressed() {
        this.gamePaused = !gamePaused;
    }
    
    public boolean paused() {
        return this.gamePaused;
    }
    
}
