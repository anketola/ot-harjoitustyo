package columnspeli.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pelaajan ohjauksessa oleva pelipalikka.
 * 
 */

public class PlayerBlock {
    
    private int gridX;
    private int gridY;
    private Block topBlock;
    private Block middleBlock;
    private Block bottomBlock;
    
    public PlayerBlock(int x, int y) {
        this.gridY = y;
        this.gridX = x;
        this.topBlock = new Block();
        this.topBlock.randomizeBlock();
        this.middleBlock = new Block();
        this.middleBlock.randomizeBlock();
        this.bottomBlock = new Block();
        this.bottomBlock.randomizeBlock();
    }
    
    /**
     * Metodi on tarkoitettu reagoimaan käyttäjän "ylös" napin painallukseen
     * vaihtamalla palikoiden järjestystä asianmukaisessti.
     */
    
    
    public void shuffleBlocks() {
        Block shuffleHelper = topBlock;
        topBlock = middleBlock;
        middleBlock = bottomBlock;
        bottomBlock = shuffleHelper;
    }
    
    /**
     * Metodi asettaa pelaajan ohjaaman palikan taikaisin ylös ja arpoo uudet
     * neliöt.
     * 
     * @param possibleRespawns sisältää pelialuen X akselilla mahdolliset sytymäpaikat
     *
     */
    
    public void respawn(ArrayList<Integer> possibleRespawns) {
        Random rand = new Random();
        int i = possibleRespawns.get(rand.nextInt(possibleRespawns.size()));
        this.gridX = i;
        newBlocks();
        setGridY(0);
    }
    
    /**
     * Metodi siirtää pelaajan ohjaamaa palikkaan yhden ruudun alas
     */
    
    public void moveDown() {
        this.gridY++;
    }
    
    /**
     * Metodi siirtää pelajan ohjaamaa palikkaa sivusuunnassa
     * @param direction ilmaisee haluaako pelaaja siirtyä vasemmalle vai oikealle
     */
    
    public void moveX(Directions direction) {
        if (direction == Directions.RIGHT) {        
            gridX++;
        } else if (direction == Directions.LEFT) {
            this.gridX--; 
        }
    }
    
    public void speedPush() {
        this.gridY++;
    }
    
    public int getGridX() {
        return this.gridX;
    }
    
    public int getGridY() {
        return this.gridY;
    }
    
    public void setGridY(int newGridY) {
        this.gridY = newGridY;
    }
    
    public void setGridX(int newGridX) {
        this.gridX = newGridX;
    }
    
    public Block getTopBlock() {
        return topBlock;
    }
    
    public Block getMiddleBlock() {
        return middleBlock;
    }
    
    public Block getBottomBlock() {
        return bottomBlock;
    }
    
    /**
     * Metodi arpo uudet sattumanvaraiset Block-oliot pelaajan kuljetettavaksi
     */
    
    
    public void newBlocks() {
        topBlock = new Block();
        topBlock.randomizeBlock();
        middleBlock = new Block();
        middleBlock.randomizeBlock();
        bottomBlock = new Block();
        bottomBlock.randomizeBlock();
    }
    
}
