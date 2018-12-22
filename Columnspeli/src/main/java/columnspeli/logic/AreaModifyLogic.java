package columnspeli.logic;

import columnspeli.domain.GameBlockArea;
import columnspeli.domain.PlayerBlock;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Pelialueen neliöille suoritettavista muokkauksista huolehtiva luokka.
 * 
 */

public class AreaModifyLogic {
 
    private GameBlockArea gameBlockArea;
    private PlayerBlock playerBlock;
    
    /**
     * Konstruktori, joka ottaa parametreinä pelialueen ja pelaajan palikan.
     * @param gameBlockArea Kontrolerin käyttämä pelialue.
     * @param playerBlock Käytössä oleva pelaajan palikka.
     */
    
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
     * @return palauttaa ArrayListin jokaisen sarakkeen korkeimmasta kohdasta.
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
     * Metodi tiputtaa annettujen koordinaattien päällä olevat neliöt.
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
     * @param shrinkValue Harmaan alueen nousukohta.
     */
    
    public void fillIndestructible(int shrinkValue) {
        int y = gameBlockArea.getAreaEdgeY() - 1 - shrinkValue;
        int x = 0;
        while (y < gameBlockArea.getAreaEdgeY()) {
            while (x < gameBlockArea.getAreaEdgeX()) {
                gameBlockArea.getBlock(x, y).setColor(Color.GRAY);
                x++;
            }
            x = 0;
            y++;    
        }
    }
    
    
}
