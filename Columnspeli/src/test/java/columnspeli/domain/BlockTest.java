package columnspeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;

public class BlockTest {
    
    @Test
    public void emptyConstructorCreatesEmptyBlock() {
        Block testBlock = new Block();
        assertEquals(Color.BLACK, testBlock.getColor());
    }
    
    @Test
    public void constructorWithIdentityHasCorrectColor() {
        Block testBlock = new Block(Color.PURPLE);
        assertEquals(Color.PURPLE, testBlock.getColor());
    }
    
    @Test
    public void demolishedBlockIsEmpty() {
        Block testBlock = new Block(Color.YELLOW);
        testBlock.demolishBlock();
        assertEquals(Color.BLACK, testBlock.getColor());
    }
    
    @Test
    public void setColorWorks() {
        Block testBlock = new Block(Color.BLUE);
        testBlock.setColor(Color.RED);
        assertEquals(Color.RED, testBlock.getColor());
    }
    

}
