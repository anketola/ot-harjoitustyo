package columnspeli.domain;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import columnspeli.logic.ScanLogic;
import columnspeli.logic.PlayerCollisionLogic;
import columnspeli.logic.AreaModifyLogic;

/**
 * Pelialueen sisällöstä huolehtiva luokka ( tässä vaiheessa myönnän, että tämä pitää pilkkoa, sisällä logiikkaa)
 * 
 */

public class GameController {
    
    private GameBlockArea areaBlocks;
    private PlayerBlock playerBlock;
    private ScanLogic scanLogic;
    private PlayerCollisionLogic playerCollisionLogic;
    private AreaModifyLogic areaModifyLogic;
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
        this.areaModifyLogic = new AreaModifyLogic(this.areaBlocks, this.playerBlock);
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
        playerBlock.respawn(playerCollisionLogic.eglibleRespawn());
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
            areaModifyLogic.releaseBlocks();
            seekAndDestroy();    
            if (!gameOver()) {
                playerBlock.respawn(playerCollisionLogic.eglibleRespawn());
            }
        }
                
    }
    
    /**
     * Metodi sisältää pelialueen systemaattista tarkastelua varten metodikokoelman.
     */
    
    public void seekAndDestroy() {
        scanLogic.scanStreaks();
        this.demolishCollect = scanLogic.getCollected();
        clearCollected();
        areaModifyLogic.scanAndDrop();
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
     * Metodi luo pelialueen alle harmaata, tuhoutumatonta aluetta pelin päättymisen nopeuttamiseksi.
     */
    
    public void shrinkArea() {
        areaModifyLogic.fillIndestructible(shrinkValue);
        this.shrinkValue++;
    }
        
    /**
     * Metodi tarkkailee pelin päättymistä
     * @return palauttaa true, jos peli päättynyt.
     */
    
    public boolean gameOver() {
        ArrayList respawns = playerCollisionLogic.eglibleRespawn();
        if (respawns.isEmpty()) {
            return true;
        }
        return false;
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
