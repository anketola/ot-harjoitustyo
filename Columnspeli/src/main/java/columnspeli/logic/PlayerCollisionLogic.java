package columnspeli.logic;

import columnspeli.domain.PlayerBlock;
import columnspeli.domain.GameBlockArea;
import java.util.ArrayList;

public class PlayerCollisionLogic {
    
    private PlayerBlock playerBlock;
    private GameBlockArea gameBlockArea;
    
    public PlayerCollisionLogic (PlayerBlock playerBlock, GameBlockArea gameBlockArea) {
        this.playerBlock = playerBlock;
        this.gameBlockArea = gameBlockArea;
    }

    /**
     * Metodi tarkistaa, onko pelaajan kolmesta nelliöstä koostuvan palikan mahdollista liikkuva vasemmalle.
     * @return palauttaa true, jos vasemmalla puolella on este.
     */
    
    public boolean isCollisionLeft() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (blockX == 0) {
            return true;
        }
        if ((gameBlockArea.hasBlock(blockX - 1, topBlockY)) || (gameBlockArea.hasBlock(blockX - 1, topBlockY + 1)) || (gameBlockArea.hasBlock(blockX - 1, topBlockY + 2))) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tarkistaa, onko pelaajan kolmesta nelliöstä koostuvan palikan mahdollista liikkuva oikealle.
     * @return palauttaa true, jos oikealla puolella on este.
     */
    
    public boolean isCollisionRight() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (blockX == gameBlockArea.getAreaEdgeX() - 1) {
            return true;
        }
        if ((gameBlockArea.hasBlock(blockX + 1, topBlockY)) || (gameBlockArea.hasBlock(blockX + 1, topBlockY + 1)) || (gameBlockArea.hasBlock(blockX + 1, topBlockY + 2))) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tarkistaa onko pelaajan ohjaaman, kolmesta neliöstä koostuvan palikan mahdollista liikkua alas.
     * @return 
     */
    
    public boolean isCollisionDown() {
        int blockX = playerBlock.getGridX();
        int topBlockY = playerBlock.getGridY();
        if (gameBlockArea.hasBlock(blockX, topBlockY + 3)) {
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
        while (i < gameBlockArea.getAreaEdgeX()) {
            if ((!gameBlockArea.hasBlock(i, 0)) && (!gameBlockArea.hasBlock(i, 1)) && (!gameBlockArea.hasBlock(i, 2))) {
                possibleRespawnX.add(i);
            }
            i++;    
        }
        return possibleRespawnX;
    }
    
}
