package hilburnlib.junit.test;

import hilburnlib.position.RotationUtil;
import net.minecraftforge.common.util.ForgeDirection;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotationTest
{
    @Test
    public void testRotateClockWise()
    {
        assertEquals(RotationUtil.rotateClockwise(ForgeDirection.EAST), ForgeDirection.NORTH);
        assertEquals(RotationUtil.rotateClockwise(ForgeDirection.NORTH), ForgeDirection.WEST);
        assertEquals(RotationUtil.rotateClockwise(ForgeDirection.WEST), ForgeDirection.SOUTH);
        assertEquals(RotationUtil.rotateClockwise(ForgeDirection.SOUTH), ForgeDirection.EAST);

        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.UP), ForgeDirection.UP);
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.DOWN), ForgeDirection.DOWN);
    }

    @Test
    public void testRotateAntiClockWise()
    {
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.EAST), ForgeDirection.SOUTH);
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.SOUTH), ForgeDirection.WEST);
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.WEST), ForgeDirection.NORTH);
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.NORTH), ForgeDirection.EAST);

        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.UP), ForgeDirection.UP);
        assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.DOWN), ForgeDirection.DOWN);
    }
}
