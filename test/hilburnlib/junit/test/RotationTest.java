package hilburnlib.junit.test;

import hilburnlib.position.RotationUtil;
import net.minecraftforge.common.util.ForgeDirection;
import org.junit.Assert;
import org.junit.Test;

public class RotationTest
{
    @Test
    public void testRotateClockWise()
    {
        Assert.assertEquals(RotationUtil.rotateClockwise(ForgeDirection.EAST), ForgeDirection.NORTH);
        Assert.assertEquals(RotationUtil.rotateClockwise(ForgeDirection.NORTH), ForgeDirection.WEST);
        Assert.assertEquals(RotationUtil.rotateClockwise(ForgeDirection.WEST), ForgeDirection.SOUTH);
        Assert.assertEquals(RotationUtil.rotateClockwise(ForgeDirection.SOUTH), ForgeDirection.EAST);

        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.UP), ForgeDirection.UP);
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.DOWN), ForgeDirection.DOWN);
    }

    @Test
    public void testRotateAntiClockWise()
    {
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.EAST), ForgeDirection.SOUTH);
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.SOUTH), ForgeDirection.WEST);
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.WEST), ForgeDirection.NORTH);
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.NORTH), ForgeDirection.EAST);

        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.UP), ForgeDirection.UP);
        Assert.assertEquals(RotationUtil.rotateAntiClockwise(ForgeDirection.DOWN), ForgeDirection.DOWN);
    }
}
