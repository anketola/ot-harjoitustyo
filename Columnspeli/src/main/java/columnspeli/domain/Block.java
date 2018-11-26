package columnspeli.domain;

import java.util.Random;

public class Block {
    
    private String blockColor;
    
    public Block() {
        this.blockColor = "empty";
    }
            
    public Block(String identity) {
        blockColor = identity;
    }
    
    public void randomizeBlock() {
        Random rand = new Random();
        int i = rand.nextInt(3) + 1;
        if (i == 1) {
            blockColor = "red";
        } else if (i == 2) {
            blockColor = "yellow";
        } else if (i == 3) {
            blockColor = "blue";
        }
    }
    
    public void demolishBlock() {
        blockColor = "empty";
    }
    
    public void setColor(String newColor) {
        blockColor = newColor;
    }
    
    public String getColor() {
        return blockColor;
    }
    
}
