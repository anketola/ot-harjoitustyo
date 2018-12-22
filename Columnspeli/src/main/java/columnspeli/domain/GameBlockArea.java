package columnspeli.domain;

import javafx.scene.paint.Color;

/**
 * Sovelluksen Block-oloista muodostuva pelikenttä.
 * 
 */

public class GameBlockArea {
   
    private Block[][] gameBlocks;
    
    public GameBlockArea(int x, int y) {
        this.gameBlocks = new Block [x][y];
        generateEmptyArea();
    }
    
    /**
     * Metodi luo pelialueen täyteen mustia neliöitä.
     */
    
    public void generateEmptyArea() {
        int y2 = 0;
        while (y2 < gameBlocks.length) {
            int x2 = 0;
            while (x2 < gameBlocks[y2].length) {
                gameBlocks[y2][x2] = new Block(Color.BLACK);
                x2++;
            }
            y2++;    
        }
    }
    
    /**
     * Metodi asetta Block-olion toivottuun paikkaan.
     * @param x olion x-koordinaatti gridillä
     * @param y olion x-koordinaatti gridillä
     * @param block asetettava Block-olio.
     */
    
    public void setBlock(int x, int y, Block block) {
        gameBlocks[x][y] = block;
    }
    
    /**
     * Metodi tarkastaa, sijaitseeko koordinaatissa neliötä.
     * @param x olion x-koordinaatti gridillä
     * @param y olion y-koordinaatti gridillä
     * @return Palauttaa true, jos ruudussa muu kuin tyhjä neliö.
     */
    
    public boolean hasBlock(int x, int y) {
        if (y < getAreaEdgeY()) {
            if (this.gameBlocks[x][y].getColor() == Color.BLACK) {
                return false;
            }
        }
        return true;        
    }
    
    /**
     * Metodi palauttaa koordinaattien mukaisen neliön.
     * @param x olion x-koordinaatti gridillä
     * @param y olion y-koordinaatti gridillä
     * @return Palauttaa joko koordinaatin neliön. Jos pyydetty alue pelialueen ulkopuolella, palautetaan tyhjä neliö.
     */
    
    public Block getBlock(int x, int y) {
        if ((x >= getAreaEdgeX()) || (x < 0) || (y < 0) || (y >= getAreaEdgeY())) {
            Block outOfArea = new Block();
            outOfArea.setColor(Color.BLACK);
            return outOfArea;
        }
        return this.gameBlocks[x][y];
    }
    
    public int getAreaEdgeX() {
        return this.gameBlocks.length;
    }
    
    public int getAreaEdgeY() {
        return this.gameBlocks[0].length;
    }
    
}
