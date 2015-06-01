package thevault.junit.test;

import thevault.position.DirectionUtil;
import net.minecraftforge.common.util.ForgeDirection;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest
{
    @Test
    public void testDirections()
    {
        assertEquals(ForgeDirection.WEST, DirectionUtil.closest(0, 0, 0));
        assertEquals(ForgeDirection.UP, DirectionUtil.closest(0, 1, 0));
        assertEquals(ForgeDirection.EAST, DirectionUtil.closest(1, 0, 0));
        assertEquals(ForgeDirection.SOUTH, DirectionUtil.closest(0, 0, 1));
        assertEquals(ForgeDirection.EAST, DirectionUtil.closest(1, 0.5F, 0));
        assertEquals(ForgeDirection.WEST, DirectionUtil.closest(-1, 0.5F, 0));
        assertEquals(ForgeDirection.DOWN, DirectionUtil.closest(1, -1.5F, 0));
        assertEquals(ForgeDirection.NORTH, DirectionUtil.closest(1, 0.5F, -10));
        assertEquals(ForgeDirection.EAST, DirectionUtil.closest(1.5F, 0.5F, -1));
    }
}
