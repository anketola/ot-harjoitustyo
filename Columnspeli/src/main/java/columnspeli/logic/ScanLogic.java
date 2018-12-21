package columnspeli.logic;

import columnspeli.domain.Block;
import columnspeli.domain.GameBlockArea;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class ScanLogic {
    
    /**
     * Metodi sisältää pelialueen systemaattista tarkastelua varten metodikokoelman.
     */
    
    private GameBlockArea gameBlockArea;
    private ArrayList<Block> demolishCollect;
    
    public ScanLogic(GameBlockArea gameBlockArea) {
        this.gameBlockArea = gameBlockArea;
        this.demolishCollect = new ArrayList<>(); 
    }
    
    public void scanStreaks() {
        horizontalScan();
        verticalScan();
        diagonalScanDownRight();
        diagonalScanDownLeft();
    }
    
    /**
     * Metodi systemaattiselle vasemmalta oikealle tarkastelulle. Käy koko pelialueen läpi.
     */
    
    public void horizontalScan() {
        int streakCount = 1;
        for (int scanY = gameBlockArea.getAreaEdgeY() - 1; scanY > 0; scanY--) {
            for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
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
        for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
            for (int scanY = gameBlockArea.getAreaEdgeY() - 1; scanY > 0; scanY--) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
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
        while (scanStartY < gameBlockArea.getAreaEdgeY()) {
            scanY = scanStartY;
            scanX = scanStartX;
            while ((scanX < gameBlockArea.getAreaEdgeX()) && (scanY < gameBlockArea.getAreaEdgeY())) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
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
        int scanStartX = gameBlockArea.getAreaEdgeX() - 1;
        int scanY;
        int scanX;
        while (scanStartY < gameBlockArea.getAreaEdgeY()) {
            scanY = scanStartY;
            scanX = scanStartX;
            while ((scanX >= 0) && (scanY < gameBlockArea.getAreaEdgeY())) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
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
     * Metodi tarkastaa onko parametrien mukainen Block saman värinen kuin vertailusuunta;
     * @param compX Vertailtavaksi otettavan Block-olion x-koordinaatti.
     * @param compY Vertailtavaksi otettavan Block-olion y-koordinaatti.
     * @param direction Suunta, mihin verrataan.
     * @return palauttaa true, jos parametrien perusteella seuraava palikka on samanvärinen.
     */
    
    public boolean nextBlockSimiliar(int compX, int compY, String direction) {
        if (direction.equals("right")) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX + 1, compY).getColor()) && (gameBlockArea.getBlock(compX + 1, compY).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("up")) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX, compY - 1).getColor()) && (gameBlockArea.getBlock(compX, compY - 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("downright")) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX + 1, compY + 1).getColor()) && (gameBlockArea.getBlock(compX + 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals("downleft")) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX - 1, compY + 1).getColor()) && (gameBlockArea.getBlock(compX - 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        }
        return false;
    }
        
    /**
    * Kerää algoritmien havitsemat palikat talteen.
    * @param x neliön x sijainti
    * @param y neliön y sijainti.
    */
    
    public void collect(int x, int y) {
        demolishCollect.add(gameBlockArea.getBlock(x, y));
    }
    
    public ArrayList<Block> getCollected() {
        return this.demolishCollect;
    }
    
}
