package columnspeli.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlockTest {
    
    @Test
    public void demolishedBlockIsEmpty() {
    Block testBlock = new Block("yellow");
    testBlock.demolishBlock();
    assertEquals("empty", testBlock.getColor());
    }
    
    @Test
    public void setColorWorks() {
    Block testBlock = new Block("blue");
    testBlock.setColor("red");
    assertEquals("red", testBlock.getColor());
    }
    

}
