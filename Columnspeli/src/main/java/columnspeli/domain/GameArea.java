package columnspeli.domain;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Pelialueen sisällöstä huolehtiva luokka ( tässä vaiheessa myönnän, että tämä pitää pilkkoa, sisällä logiikkaa)
 * 
 */


public class GameArea {
    
    private Block[][] areaBlocks;
    private PlayerBlock playerBlock;
    private GameStatistics gameStatistics;
    private ArrayList<Block> demolishCollect = new ArrayList<>();
    boolean gameActive;
    boolean gamePaused;
    private int shrinkValue;
    
    public GameArea(int x, int y) {
        this.areaBlocks = new Block[x][y];
        generateEmptyArea();
        this.gameStatistics = new GameStatistics();
        this.gamePaused = false;
        this.playerBlock = new PlayerBlock(5, 0); // temp
        this.gameActive = false;
        this.shrinkValue = 0;
    }

    /**
     * Metodi luo pelialueen täyteen mustia neliöitä.
     */
    
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
    
    /**
     * Metodi nollaa pelialueen ja aloittaa pelin alusta.
     */
    
    public void resetState() {
        this.gameStatistics.setScore(0);
        this.shrinkValue = 0;
        generateEmptyArea();
        playerBlock.respawn(eglibleRespawn());
    }
    
    
    /**
     * Metodi asetta Block-olion toivottuun paikkaan.
     * @param x olion x-koordinaatti gridillä
     * @param y olion x-koordinaatti gridillä
     * @param block asetettava Block-olio.
     */
    
    public void setBlock(int x, int y, Block block) {
        areaBlocks[x][y] = block;
    }
    
    /**
     * Metodi välittää pelaajan ohjaamalle palikalle näppäimistösyötteen ja käsittelee sen tai palikan putoamisen merkityksen.
     * @param direction pelaajan näppäimistösyötteen mukainen suunta
     */
    
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
        if ((hasBlock(blockX - 1, topBlockY)) || (hasBlock(blockX - 1, topBlockY + 1)) || (hasBlock(blockX - 1, topBlockY + 2))) {
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
        if (blockX == getAreaEdgeX() - 1) {
            return true;
        }
        if ((hasBlock(blockX + 1, topBlockY)) || (hasBlock(blockX + 1, topBlockY + 1)) || (hasBlock(blockX + 1, topBlockY + 2))) {
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
        if (hasBlock(blockX, topBlockY + 3)) {
            return true;
        }
        return false;
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
            horizontalScan();
            verticalScan();
            diagonalScanDownRight();
            diagonalScanDownLeft();
            clearCollected();
            scanAndDrop();
        }
    }
    
    /**
     * Metodi tarkastaa onko parametrien mukainen Block saman värinen kuin vertailusuunta;
     * @param compX Vertailtavaksi otettavan Block-olion x-koordinaatti.
     * @param compY Vertailtavaksi otettavan Block-olion y-koordinaatti.
     * @param direction Suunta, mihin verrataan.
     * @return palauttaa true, jos parametrien perusteella seuraava palikka on samanvärinen.
     */
    
    public boolean nextBlockSimiliar(int compX, int compY, String direction) {
        if (direction.equals("right")) {
            if ((getBlock(compX, compY).getColor() == getBlock(compX + 1, compY).getColor()) && (getBlock(compX + 1, compY).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("up")) {
            if ((getBlock(compX, compY).getColor() == getBlock(compX, compY - 1).getColor()) && (getBlock(compX, compY - 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("downright")) {
            if ((getBlock(compX, compY).getColor() == getBlock(compX + 1, compY + 1).getColor()) && (getBlock(compX + 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("downleft")) {
            if ((getBlock(compX, compY).getColor() == getBlock(compX - 1, compY + 1).getColor()) && (getBlock(compX - 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodi systemaattiselle vasemmalta oikealle tarkastelulle. Käy koko pelialueen läpi.
     */
    
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
    
    /**
     * Metodi systemaattiselle vasemmalta oikealle tarkastelulle. Käy koko pelialueen läpi.
     */
    
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
    
    /**
     * Metodi oikeaan alaviistoon menevien suorien tunistamiseksi.
     */
    
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
    
    /**
     * Metodi vasempaan alaviistoon menevien suorien tunistamiseksi.
     */
    
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

    /**
     * Kerää algoritmien havitsemat palikat talteen.
     * @param x neliön x sijainti
     * @param y neliön y sijainti.
     */
    
    public void collect(int x, int y) {
        demolishCollect.add(getBlock(x , y));
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
    
    /**
     * Metodi etsii jokaisen ruudukon X kohdalta korkeimman palikan.
     * @return 
     */
    
    
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
        System.out.println(highestBlocks);
        return highestBlocks;
    }
    
    /**
     * Metodi luo pelialueen alle harmaata, tuhoutumatonta aluetta pelin päättymisen nopeuttamiseksi.
     */
    
    public void shrinkArea() {
        int x = 0;
        int y = getAreaEdgeY() - 1 - this.shrinkValue;
        while (x < getAreaEdgeX()) {
            getBlock(x, y).setColor(Color.GRAY);
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
                getBlock(dropX, dropY).setColor(getBlock(dropX, dropY - 1).getColor());
            } else if (dropY == 0) {
                getBlock(dropX, dropY).setColor(Color.BLACK);
            }
            dropY--;
        }
        
    }
    
    /**
     * Metodi vapauttaa pelaajan ohjattavana olleet kolme palikkaa osaksi pelilautaa.
     */
    
    public void releaseBlocks() {
        setBlock(playerBlock.getGridX(), playerBlock.getGridY(), playerBlock.getTopBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 1, playerBlock.getMiddleBlock());
        setBlock(playerBlock.getGridX(), playerBlock.getGridY() + 2, playerBlock.getBottomBlock());
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
