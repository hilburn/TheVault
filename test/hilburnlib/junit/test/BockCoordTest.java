package hilburnlib.junit.test;

import hilburnlib.position.BlockCoord;
import net.minecraftforge.common.util.ForgeDirection;
import org.junit.Assert;
import org.junit.Test;

public class BockCoordTest
{
    @Test
    public void blockCoordTester()
    {
        BlockCoord block = new BlockCoord(0, 0, 0);
        BlockCoord block1 = new BlockCoord(0, 0, 0);
        BlockCoord block2 = new BlockCoord(0, 1, 1);
        BlockCoord block3 = new BlockCoord(5, 0, 0);

        Assert.assertTrue(block.equals(block1));
        Assert.assertFalse(block1.equals(block2));
        Assert.assertTrue(block3.equals(block3));
        Assert.assertFalse(block2.equals(block3));

        Assert.assertTrue(block.offset(ForgeDirection.UP).offset(ForgeDirection.SOUTH).equals(block2));

        Assert.assertTrue(block1.offset(ForgeDirection.EAST, 5).equals(block3));
    }
    
    @Test
    public void hashCodeTester()
    {
        BlockCoord block = new BlockCoord(0, 0, 0);
        BlockCoord block1 = new BlockCoord(0, 0, 0);
        BlockCoord block2 = new BlockCoord(0, 1, 1);
        BlockCoord block3 = new BlockCoord(5, 0, 0);
        
        Assert.assertEquals(block.hashCode(), block1.hashCode());
        Assert.assertNotEquals(block2.hashCode(), block1.hashCode());
        Assert.assertEquals(block.hashCode(), block.hashCode());
        Assert.assertNotEquals(block.hashCode(), block3.hashCode());
    }
}
