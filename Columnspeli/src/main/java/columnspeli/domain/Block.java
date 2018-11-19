package columnspeli.domain;

public class Block {
    
    private String blockColor;
    
    public Block() {
        this.blockColor = "empty";
    }
            
    
    public Block (String identity) {
        blockColor = identity;
    }
    
    public String getColor() {
        return blockColor;
    }
    
}
