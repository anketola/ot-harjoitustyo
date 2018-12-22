package columnspeli.domain;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import columnspeli.logic.ScanLogic;
import columnspeli.logic.PlayerCollisionLogic;
import java.lang.Math;

/**
 * Pelialueen sisällöstä huolehtiva luokka ( tässä vaiheessa myönnän, että tämä pitää pilkkoa, sisällä logiikkaa)
 * 
 */

public class GameController {
    
    private GameBlockArea areaBlocks;
    private PlayerBlock playerBlock;
    private ScanLogic scanLogic;
    private PlayerCollisionLogic playerCollisionLogic;
    private GameStatistics gameStatistics;
    private ArrayList<Block> demolishCollect = new ArrayList<>();
    boolean gameActive;
    boolean gamePaused;
    private int shrinkValue;
    
    public GameController(int x, int y) {
        this.areaBlocks = new GameBlockArea(x, y);
        this.scanLogic = new ScanLogic(this.areaBlocks);
        this.gameStatistics = new GameStatistics();
        this.gamePaused = false;
        this.playerBlock = new PlayerBlock(0, 0);
        this.playerCollisionLogic = new PlayerCollisionLogic(this.playerBlock, this.areaBlocks);
        this.gameActive = false;
        this.shrinkValue = 0;
    }

    /**
     * Metodi nollaa pelialueen ja aloittaa pelin alusta.
     */
    
    public void resetState() {
        this.gameStatistics.setScore(0);
        this.shrinkValue = 0;
        this.areaBlocks.generateEmptyArea();
        playerBlock.respawn(eglibleRespawn());
    }
    
    /**
     * Metodi välittää pelaajan ohjaamalle palikalle näppäimistösyötteen ja käsittelee sen tai palikan putoamisen merkityksen.
     * @param direction pelaajan näppäimistösyötteen mukainen suunta
     */
    
    public void movePlayer(Directions direction) {
        if ((direction == Directions.LEFT) && (!playerCollisionLogic.isCollisionLeft())) {
            playerBlock.moveX(direction);
        }
        if ((direction == Directions.RIGHT) && (!playerCollisionLogic.isCollisionRight())) {
            playerBlock.moveX(direction);
        }
        if ((direction == Directions.DOWN) && (!playerCollisionLogic.isCollisionDown())) {
            playerBlock.moveDown();
        } else if ((direction == Directions.DOWN) && (playerCollisionLogic.isCollisionDown())) {
            releaseBlocks();
            seekBlockStreaks();    
            if (!gameOver()) {
                playerBlock.respawn(eglibleRespawn());
            }
        }
                
    }
    
    /**
     * Metodi sisältää pelialueen systemaattista tarkastelua varten metodikokoelman.
     */
    
    public void seekBlockStreaks() {
        for (int i = 0; i < 5; i++) {   
        // TEMPORARY LOOP - loops five times as I haven't had time to implement
        // everything fully, now it just provides more functionality
        // this is for the cases when clear and drop gives new streaks
        // going to fix later
            scanLogic.scanStreaks();
            this.demolishCollect = scanLogic.getCollected();
            clearCollected();
            scanAndDrop();
        }
    }
    
    
    /**
     * Metodi lisää pistetä kerättyjen neliöiden määrän perusteella, muuttaa palikat mustiksi ja tyhjentää demolishCollect-korin.
     * @return palauttaaa poistettujen määrän. 
     */
    
    public int clearCollected() {
        gameStatistics.addScore(demolishCollect.size());
        int amount = demolishCollect.size();
        for (int i = 0; i < demolishCollect.size(); i++) {
            demolishCollect.get(i).demolishBlock();
        }
        demolishCollect.clear();
        return amount;
    }
    
    /**
     * Metodi etsii ja tiputtaa ylempänä olevia palikoita, jos niiden alla on mustia aukkoja.
     */
    
    public void scanAndDrop() {
        ArrayList<Integer> topLine = findHighestBlock();
        for (int scanX = 0; scanX < areaBlocks.getAreaEdgeX(); scanX++) {
            int scanY = topLine.get(scanX);
            while (scanY < areaBlocks.getAreaEdgeY()) {
                if (!areaBlocks.hasBlock(scanX, scanY)) {
                    dropAbove(scanX, scanY);
                    
                }
                scanY++;        
            }
        }
    }
    
    /**
     * Metodi etsii jokaisen ruudukon X kohdalta korkeimman palikan.
     * @return 
     */
    
    
    public ArrayList<Integer> findHighestBlock() {
        ArrayList<Integer> highestBlocks = new ArrayList<>();
        int scanY = 0;
        for (int scanX = 0; scanX < areaBlocks.getAreaEdgeX(); scanX++) {
            while (!areaBlocks.hasBlock(scanX, scanY)) {
                scanY++;
            }
            highestBlocks.add(scanY);
            scanY = 0;
        }
        System.out.println(highestBlocks);
        return highestBlocks;
    }
    
    /**
     * Metodi luo pelialueen alle harmaata, tuhoutumatonta aluetta pelin päättymisen nopeuttamiseksi.
     */
    
    public void shrinkArea() {
        int x = 0;
        int y = areaBlocks.getAreaEdgeY() - 1 - this.shrinkValue;
        while (x < areaBlocks.getAreaEdgeX()) {
            areaBlocks.getBlock(x, y).setColor(Color.GRAY);
            x++;
        }
        this.shrinkValue++;
    }
        
    /**
     * Metodi tiputtaa annettujen koordinaattien päällä olevat neliöt. Ei etsi.
     * @param dropX aloituskohdan X arvo.
     * @param dropY aloituskohdan Y arvo.
     */    
    
    
    public void dropAbove(int dropX, int dropY) {
        while (dropY >= 0) {
            if (dropY > 0) {
                areaBlocks.getBlock(dropX, dropY).setColor(areaBlocks.getBlock(dropX, dropY - 1).getColor());
            } else if (dropY == 0) {
                areaBlocks.getBlock(dropX, dropY).setColor(Color.BLACK);
            }
            dropY--;
        }
        
    }
    
    /**
     * Metodi vapauttaa pelaajan ohjattavana olleet kolme palikkaa osaksi pelilautaa.
     */
    
    public void releaseBlocks() {
        areaBlocks.setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        areaBlocks.setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        areaBlocks.setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
    }
    
    /**
     * Metodi tarkkailee pelin päättymistä
     * @return palauttaa true, jos peli päättynyt.
     */
    
    public boolean gameOver() {
        ArrayList respawns = eglibleRespawn();
        if (respawns.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi skannaa pelialueen ylialuetta tunnistaakseen paikkoja, johon pelaajan palikka voi vielä syntyä.
     * @return 
     */
    
    public ArrayList<Integer> eglibleRespawn() {
        ArrayList<Integer> possibleRespawnX = new ArrayList<>();
        int i = 0;
        while (i < areaBlocks.getAreaEdgeX()) {
            if ((!areaBlocks.hasBlock(i, 0)) && (!areaBlocks.hasBlock(i, 1)) && (!areaBlocks.hasBlock(i, 2))) {
                possibleRespawnX.add(i);
            }
            i++;    
        }
        return possibleRespawnX;
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
    
    public GameBlockArea getGameBlockArea() {
        return this.areaBlocks;
    }
    
}
