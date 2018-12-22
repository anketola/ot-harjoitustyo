package columnspeli.domain;


import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;

public class PlayerBlockTest {

    @Test
    public void shuffleBlockSwitchesTopBlockCorrectly() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.getTopBlock().setColor(Color.RED);
        playerBlock.getMiddleBlock().setColor(Color.BLUE);
        playerBlock.getBottomBlock().setColor(Color.BLUE);
        playerBlock.shuffleBlocks();
        assertEquals(playerBlock.getBottomBlock().getColor(), Color.RED);
    }
    
    @Test
    public void shuffleBlockSwitchesMiddleBlockCorrectly() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.getTopBlock().setColor(Color.BLUE);
        playerBlock.getMiddleBlock().setColor(Color.RED);
        playerBlock.getBottomBlock().setColor(Color.BLUE);
        playerBlock.shuffleBlocks();
        assertEquals(playerBlock.getTopBlock().getColor(), Color.RED);
    }
    
    @Test
    public void shuffleBlockSwitchesBottomBlockCorrectly() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.getTopBlock().setColor(Color.BLUE);
        playerBlock.getMiddleBlock().setColor(Color.BLUE);
        playerBlock.getBottomBlock().setColor(Color.RED);
        playerBlock.shuffleBlocks();
        assertEquals(playerBlock.getMiddleBlock().getColor(), Color.RED);
    }
    
    @Test
    public void getGridXReturnsSetGridXValue() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.setGridX(7);
        assertEquals(7, playerBlock.getGridX());
    }
    
    @Test
    public void getGridYReturnsSetGridYValue() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.setGridY(8);
        assertEquals(8, playerBlock.getGridY());
    }
    
    @Test
    public void moveDownChangesGridYCorrectly() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.moveDown();
        assertEquals(1, playerBlock.getGridY());
    }
    
    @Test
    public void speedPushIncreasesGridY() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.speedPush();
        assertEquals(1, playerBlock.getGridY());
    }
    
    @Test
    public void movingLeftDecreasesGridX() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.moveX(Directions.LEFT);
        assertEquals(2, playerBlock.getGridX());
    }
    
    @Test
    public void movingRightIncreasesGridX() {
        PlayerBlock playerBlock = new PlayerBlock(3, 0);
        playerBlock.moveX(Directions.RIGHT);
        assertEquals(4, playerBlock.getGridX());
    }
    
}

    
    
