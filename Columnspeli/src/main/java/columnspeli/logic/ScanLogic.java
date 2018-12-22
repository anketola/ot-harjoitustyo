package columnspeli.logic;

import columnspeli.domain.Block;
import columnspeli.domain.Directions;
import columnspeli.domain.GameBlockArea;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class ScanLogic {
    
    /**
     * Metodi sisältää pelialueen systemaattista tarkastelua varten metodikokoelman.
     */
    
    private GameBlockArea gameBlockArea;
    private ArrayList<Block> demolishCollect;
    private int scanX;
    private int scanY;
    private int streakCount;
    
    /**
     * Konstruktori, joka ottaa parameterinä pelialueen.
     * @param gameBlockArea Käytössä oleva pelialue.
     */
    
    public ScanLogic(GameBlockArea gameBlockArea) {
        this.gameBlockArea = gameBlockArea;
        this.demolishCollect = new ArrayList<>(); 
    }
    
    /**
     * Kokoelma, joka kutsuu muita skannausmetodeita.
     */
    
    public void scanStreaks() {
        horizontalScan();
        verticalScan();
        diagonalScanDownRight(0, 0);
        diagonalScanDownLeft(gameBlockArea.getAreaEdgeX() - 1, 0);
    }
    
    /**
     * Metodi systemaattiselle vasemmalta oikealle tarkastelulle. Käy koko pelialueen läpi.
     */
    
    public void horizontalScan() {
        streakCount = 1;
        for (int scanY = gameBlockArea.getAreaEdgeY() - 1; scanY > 0; scanY--) {
            for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, Directions.RIGHT)) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                collectSingle(scanX - i, scanY);
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
        streakCount = 1;
        for (int scanX = 0; scanX < gameBlockArea.getAreaEdgeX(); scanX++) {
            for (int scanY = gameBlockArea.getAreaEdgeY() - 1; scanY > 0; scanY--) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, Directions.UP)) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            for (int i = 0; i < streakCount; i++) {
                                collectSingle(scanX, scanY + i);
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
    
    public void diagonalScanDownRight(int scanStartX, int scanStartY) {
        streakCount = 1;
        while (scanStartY < gameBlockArea.getAreaEdgeY()) {
            setScanPointer(scanStartX, scanStartY);
            while ((scanX < gameBlockArea.getAreaEdgeX()) && (scanY < gameBlockArea.getAreaEdgeY())) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, Directions.DOWNRIGHT)) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            collectStreak(Directions.DOWNRIGHT);  
                        }
                        streakCount = 1;
                    }
                }    
                moveScanPointer(1, 1);
            }
            scanStartY++;
        }
    }
    
    /**
     * Metodi vasempaan alaviistoon menevien suorien tunistamiseksi.
     */
    
    public void diagonalScanDownLeft(int scanStartX, int scanStartY) {
        streakCount = 1;
        while (scanStartY < gameBlockArea.getAreaEdgeY()) {
            setScanPointer(scanStartX, scanStartY);
            while ((scanX >= 0) && (scanY < gameBlockArea.getAreaEdgeY())) {
                if (gameBlockArea.hasBlock(scanX, scanY)) {
                    if (nextBlockSimiliar(scanX, scanY, Directions.DOWNLEFT)) {
                        streakCount++;
                    } else {
                        if (streakCount >= 3) {
                            collectStreak(Directions.DOWNLEFT);
                        }
                        streakCount = 1;
                    }
                }    
                moveScanPointer(-1, 1);
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
    
    public boolean nextBlockSimiliar(int compX, int compY, Directions direction) {
        if (direction.equals(Directions.RIGHT)) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX + 1, compY).getColor()) && (gameBlockArea.getBlock(compX + 1, compY).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals(Directions.UP)) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX, compY - 1).getColor()) && (gameBlockArea.getBlock(compX, compY - 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals(Directions.DOWNRIGHT)) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX + 1, compY + 1).getColor()) && (gameBlockArea.getBlock(compX + 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        } else if (direction.equals(Directions.DOWNLEFT)) {
            if ((gameBlockArea.getBlock(compX, compY).getColor() == gameBlockArea.getBlock(compX - 1, compY + 1).getColor()) && (gameBlockArea.getBlock(compX - 1, compY + 1).getColor() != Color.GRAY)) {
                return true;
            }
        }
        return false;
    }
        
    /**
    * Kerää algoritmin havitseman palikan talteen.
    * @param x neliön x sijainti
    * @param y neliön y sijainti.
    */
    
    public void collectSingle(int x, int y) {
        demolishCollect.add(gameBlockArea.getBlock(x, y));
    }

    /**
     * Metodi kerää värisuorat viistosuuntaisille algoritmeille.
     * @param direction Käytetty suunta.
     */
    
    public void collectStreak(Directions direction) {
        if (direction == Directions.DOWNRIGHT) {
            for (int i = 0; i < streakCount; i++) {
                collectSingle(scanX - i, scanY - i);
            }
        } else if (direction == Directions.DOWNLEFT) {
            for (int i = 0; i < streakCount; i++) {
                collectSingle(scanX + i, scanY - i);
            }
        }
           
    }
    
    /**
     * Siirtää scankohtaa.
     * @param x Koordinaatiston x kohta.
     * @param y Koordinaatiston y kohta.
     */
    
    public void moveScanPointer(int x, int y) {
        this.scanX = this.scanX + x;
        this.scanY = this.scanY + y;
    }
    
    /**
     * Asettaa skannauskohdan.
     * @param x Koordinaatiston x kohta.
     * @param y Koordinaatiston y kohta.
     */
    
    public void setScanPointer(int x, int y) {
        this.scanX = x;
        this.scanY = y;
    }
    
    /**
     * Metodi poistettujen palikoiden palauttamiselle.
     * @return Palauttaa algoritmien keräämät palikat.
     */
    
    public ArrayList<Block> getCollected() {
        return this.demolishCollect;
    }
    
}
