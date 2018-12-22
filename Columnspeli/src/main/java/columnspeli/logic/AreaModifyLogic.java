package columnspeli.logic;

import columnspeli.domain.GameBlockArea;
import columnspeli.domain.PlayerBlock;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class AreaModifyLogic {
 
    private GameBlockArea gameBlockArea;
    private PlayerBlock playerBlock;
    
    public AreaModifyLogic(GameBlockArea gameBlockArea, PlayerBlock playerBlock) {
        this.gameBlockArea = gameBlockArea;
        this.playerBlock = playerBlock;
    }
    
    /**
     * Metodi vapauttaa pelaajan ohjattavana olleet kolme palikkaa osaksi pelilautaa.
     */
    
    public void releaseBlocks() {
        gameBlockArea.setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        gameBlockArea.setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        gameBlockArea.setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
    }
    
    
    /**
     * Metodi etsii jokaisen ruudukon X kohdalta korkeimman palikan.
     * @return 
     */
    
    public ArrayList<Integer> findHighestBlock() {
        ArrayList<Integer> highestBlocks = new ArrayList<>();
        int scanY = 0;
        for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
            while (!gameBlockArea.hasBlock(scanX, scanY)) {
                scanY++;
            }
            highestBlocks.add(scanY);
            scanY = 0;
        }
        return highestBlocks;
    }
    
    /**
     * Metodi etsii ja tiputtaa ylempänä olevia palikoita, jos niiden alla on mustia aukkoja.
     */
    
    public void scanAndDrop() {
        ArrayList<Integer> topLine = findHighestBlock();
        for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
            int scanY = topLine.get(scanX);
            while (scanY < gameBlockArea.getAreaEdgeY()) {
                if (!gameBlockArea.hasBlock(scanX, scanY)) {
                    dropAbove(scanX, scanY);
                    
                }
                scanY++;        
            }
        }
    }
    
    /**
     * Metodi tiputtaa annettujen koordinaattien päällä olevat neliöt. Ei etsi.
     * @param dropX aloituskohdan X arvo.
     * @param dropY aloituskohdan Y arvo.
     */    
    
    
    public void dropAbove(int dropX, int dropY) {
        while (dropY >= 0) {
            if (dropY > 0) {
                gameBlockArea.getBlock(dropX, dropY).setColor(gameBlockArea.getBlock(dropX, dropY - 1).getColor());
            } else if (dropY == 0) {
                gameBlockArea.getBlock(dropX, dropY).setColor(Color.BLACK);
            }
            dropY--;
        }
        
    }
    
    /**
     * Metodi luo pelialueen alle harmaata, tuhoutumatonta aluetta pelin päättymisen nopeuttamiseksi.
     */
    
    public void fillIndestructible(int shrinkValue) {
        int x = 0;
        int y = gameBlockArea.getAreaEdgeY() - 1 - shrinkValue;
        while (x < gameBlockArea.getAreaEdgeX()) {
            gameBlockArea.getBlock(x, y).setColor(Color.GRAY);
            x++;
        }
    }
    
    
    
}
