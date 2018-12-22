package columnspeli.domain;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Pelialueen täyttäviä eri värisiä neliöitä edustava luokka.
 * Luokka on käytössä myös ylhäältä tiippuva PlayerObjectin osana.
 */


public class Block {
    
    private Color blockColor;
    
    /**
     * Parametritön konstruktori luo tyhjän neliön.
     */
    
    public Block() {
        this.blockColor = Color.BLACK;
    }
    
    /**
     * Konstruktori tietyn tyyppisen neliön luomiseen.
     * @param identity Neliölle annettava väri tai tyypi.
     */
    
    public Block(Color identity) {
        blockColor = identity;
    }
    
    /**
     * Metodi antaa Blockille sattumanvarainen väri.
     */
    
    public void randomizeBlock() {
        Random rand = new Random();
        int i = rand.nextInt(5) + 1;
        if (i == 1) {
            blockColor = Color.RED;
        } else if (i == 2) {
            blockColor = Color.YELLOW;
        } else if (i == 3) {
            blockColor = Color.BLUE;
        } else if (i == 4) {
            blockColor = Color.GREEN;
        } else if (i == 5) {
            blockColor = Color.PURPLE;
        }
    }
    
    /**
     * Metodi tuhoaa Blockin muuttamalla.
     * Musta väli tulkitaan ei-palikaksi.
     */
    
    public void demolishBlock() {
        blockColor = Color.BLACK;
    }
    
    public void setColor(Color newColor) {
        blockColor = newColor;
    }
    
    public Color getColor() {
        return blockColor;
    }
    
}
