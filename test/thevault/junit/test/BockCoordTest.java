package thevault.junit.test;

import thevault.position.BlockCoord;
import net.minecraftforge.common.util.ForgeDirection;
import org.junit.Test;

import static thevault.junit.minecraft.Assert.*;

public class BockCoordTest
{
    @Test
    public void blockCoordTester()
    {
        BlockCoord block = new BlockCoord(0, 0, 0);
        BlockCoord block1 = new BlockCoord(0, 0, 0);
        BlockCoord block2 = new BlockCoord(0, 1, 1);
        BlockCoord block3 = new BlockCoord(5, 0, 0);

        assertTrue(block.equals(block1));
        assertFalse(block1.equals(block2));
        assertTrue(block3.equals(block3));
        assertFalse(block2.equals(block3));

        assertTrue(block.offset(ForgeDirection.UP).offset(ForgeDirection.SOUTH).equals(block2));

        assertTrue(block1.offset(ForgeDirection.EAST, 5).equals(block3));
    }
    
    @Test
    public void hashCodeTester()
    {
        BlockCoord block = new BlockCoord(0, 0, 0);
        BlockCoord block1 = new BlockCoord(0, 0, 0);
        BlockCoord block2 = new BlockCoord(0, 1, 1);
        BlockCoord block3 = new BlockCoord(5, 0, 0);
        
        assertEquals(block.hashCode(), block1.hashCode());
        assertNotEquals(block2.hashCode(), block1.hashCode());
        assertEquals(block.hashCode(), block.hashCode());
        assertNotEquals(block.hashCode(), block3.hashCode());
    }
}
